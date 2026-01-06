package ru.itis.gloriaartis.data.impl.repository

import retrofit2.HttpException
import ru.itis.gloriaartis.data.impl.remote.api.ArtApi
import ru.itis.gloriaartis.data.impl.remote.mapper.ArtDetailsResponseToModelMapper
import ru.itis.gloriaartis.data.impl.remote.mapper.ArtPreviewResponseToModelMapper
import ru.itis.gloriaartis.data.impl.remote.mapper.ArtSearchResponseToModelMapper
import ru.itis.gloriaartis.domain.model.ArtDetailsModel
import ru.itis.gloriaartis.domain.model.ArtPreviewModel
import ru.itis.gloriaartis.domain.repository.ArtRepository
import ru.itis.gloriaartis.utils.properties.ExceptionCode
import ru.itis.gloriaartis.utils.runCatchingNonCancellable
import java.net.HttpURLConnection
import javax.inject.Inject

internal class ArtRepositoryImpl @Inject constructor(
    private val artApi: ArtApi,
    private val artPreviewResponseToModelMapper: ArtPreviewResponseToModelMapper,
    private val artSearchResponseToModelMapper: ArtSearchResponseToModelMapper,
    private val artDetailsResponseToModelMapper: ArtDetailsResponseToModelMapper,
) : ArtRepository {

    private var cachedIds: List<Int> = emptyList()

    override suspend fun getArtList(
        page: Int,
        pageSize: Int
    ): List<ArtPreviewModel> {
        return runCatchingNonCancellable {
            if (page == 0 && cachedIds.isEmpty()) {
                cachedIds = artApi.getArtListSearch().let(artSearchResponseToModelMapper::map).objectIDs
            }

            val ids = cachedIds
                .drop(page * pageSize)
                .take(pageSize)

            //чтобы не ронять весь лист из-за одного неудачного элемента
            ids.mapNotNull { id ->
                runCatching {
                    artApi.getArtDetails(id)
                        .let(artPreviewResponseToModelMapper::map)
                }.getOrNull()
            }
        }.onFailure { exception ->
            resolveHttpError(exception)
        }.getOrThrow()
    }

    override suspend fun getArtDetails(artId: Int): ArtDetailsModel {
        return runCatchingNonCancellable {
            artApi.getArtDetails(artId = artId).let(artDetailsResponseToModelMapper::map)
        }.onFailure {exception ->
            resolveHttpError(exception)
        }.getOrThrow()
    }

    private fun resolveHttpError(exception: Throwable) {
        when (exception) {
            is HttpException -> {
                when (exception.code()) {
                    HttpURLConnection.HTTP_BAD_REQUEST -> throw IllegalArgumentException(
                        ExceptionCode.BAD_REQUEST
                    )
                    HttpURLConnection.HTTP_UNAUTHORIZED -> throw IllegalArgumentException(
                        ExceptionCode.UNAUTHORIZED
                    )
                    HttpURLConnection.HTTP_PAYMENT_REQUIRED -> throw IllegalArgumentException(
                        ExceptionCode.PAYMENT_REQUIRED
                    )
                    HttpURLConnection.HTTP_FORBIDDEN -> throw IllegalArgumentException(
                        ExceptionCode.FORBIDDEN
                    )
                    HttpURLConnection.HTTP_NOT_FOUND -> throw IllegalArgumentException(
                        ExceptionCode.NOT_FOUND
                    )
                    HttpURLConnection.HTTP_CLIENT_TIMEOUT -> throw IllegalArgumentException(
                        ExceptionCode.CLIENT_TIMEOUT
                    )
                    HttpURLConnection.HTTP_GONE -> throw IllegalArgumentException(
                        ExceptionCode.GONE
                    )
                    in 500..599 ->
                        throw IllegalArgumentException(ExceptionCode.SERVER_ERROR)
                }
            }
        }
    }
}