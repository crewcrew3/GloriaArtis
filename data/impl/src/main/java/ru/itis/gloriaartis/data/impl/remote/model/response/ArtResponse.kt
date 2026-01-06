package ru.itis.gloriaartis.data.impl.remote.model.response

import com.google.gson.annotations.SerializedName

internal class ArtResponse(

    @SerializedName("objectID")
    val id: Int?,

    @SerializedName("title")
    val artTitle: String?,

    @SerializedName("primaryImage")
    val imageUrl: String?,

    @SerializedName("isHighlight")
    val isHighlight: Boolean?,

    @SerializedName("additionalImages")
    val additionalImages: List<String?>?,

    @SerializedName("accessionYear")
    val accessionYear: String?,

    @SerializedName("department")
    val department: String?,

    @SerializedName("objectName")
    val objectName: String?,

    @SerializedName("culture")
    val culture: String?,

    @SerializedName("period")
    val period: String?,

    @SerializedName("dynasty")
    val dynasty: String?,

    @SerializedName("portfolio")
    val portfolio: String?,

    @SerializedName("artistDisplayName")
    val artistName: String?,

    @SerializedName("artistDisplayBio")
    val artistBio: String?,

    @SerializedName("artistWikidata_URL")
    val artistWikidataURL: String?,

    @SerializedName("objectWikidata_URL")
    val artWikidataURL: String?,

    @SerializedName("medium")
    val medium: String?,
)