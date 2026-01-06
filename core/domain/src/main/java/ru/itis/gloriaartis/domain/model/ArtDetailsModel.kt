package ru.itis.gloriaartis.domain.model

data class ArtDetailsModel(
    val id: Int,
    val artTitle: String,
    val artistName: String,
    val imageUrl: String,
    val isHighlight: Boolean,  //When "true" indicates a popular and important artwork in the collection
    val additionalImages: List<String>,
    val accessionYear: String, //Year the artwork was acquired.
    val department: String, //Indicates The Met's curatorial department responsible for the artwork
    val objectName: String, //Describes the physical type of the object
    val culture: String, //Information about the culture, or people from which an object was created
    val period: String, // Time or time period when an object was created
    val dynasty: String, //Dynasty (a succession of rulers of the same line or family) under which an object was created
    val portfolio: String, //A set of works created as a group or published as a series.
    val artistBio: String, //Nationality and life dates of an artist, also includes birth and death city when known.
    val artistWikidataURL: String,
    val artWikidataURL: String,
    val medium: String, //Refers to the materials that were used to create the artwork
)