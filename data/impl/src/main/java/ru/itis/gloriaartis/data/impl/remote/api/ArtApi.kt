package ru.itis.gloriaartis.data.impl.remote.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itis.gloriaartis.data.impl.remote.model.response.ArtResponse
import ru.itis.gloriaartis.data.impl.remote.model.response.ArtSearchResponse

internal interface ArtApi {

    //там в апишке не у всех произведений есть картинки(и таких довольно много),
    //а для своего приложения я хочу только то, что с картинками, ибо без них антивайб.
    //параметр q там обязательный, без него апи падает
    @GET("search")
    suspend fun getArtListSearch(
        @Query("q") q: String = "*",
        @Query("hasImages") hasImages: Boolean = true,
    ): ArtSearchResponse?

    @GET("objects/{objectID}")
    suspend fun getArtDetails(@Path("objectID") artId: Int): ArtResponse?
}