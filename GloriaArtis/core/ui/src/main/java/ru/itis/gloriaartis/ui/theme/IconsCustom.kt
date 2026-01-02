package ru.itis.gloriaartis.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import ru.itis.gloriaartis.ui.R

object IconsCustom {

    @Composable
    fun isHighlightArtIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_crown)

    @Composable
    fun profileIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_profile)

    @Composable
    fun arrowBackIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_arrow_back)

    @Composable
    fun arrowForwardIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_arrow_forward)

    @Composable
    fun tabLogOutIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_logout)

    @Composable
    fun addPhotoIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_add_photo)

    @Composable
    fun crossIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_cross)

    @Composable
    fun crossCircleIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_cross_circle)

    @Composable
    fun visibilityIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_visibility)

    @Composable
    fun visibilityOffIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_visibility_off)

    @Composable
    fun noImageArtIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_palette)

    @Composable
    fun addContentIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_add)

    @Composable
    fun editIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_pencil)

    @Composable
    fun deleteIcon(): ImageVector = ImageVector.vectorResource(R.drawable.ic_trash)
}