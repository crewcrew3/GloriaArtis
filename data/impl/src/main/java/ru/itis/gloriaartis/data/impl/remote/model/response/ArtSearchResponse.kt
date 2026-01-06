package ru.itis.gloriaartis.data.impl.remote.model.response

import com.google.gson.annotations.SerializedName

internal data class ArtSearchResponse(
    @SerializedName("total")
    val total: Int?,

    @SerializedName("objectIDs")
    val objectIDs: List<Int?>?
)
