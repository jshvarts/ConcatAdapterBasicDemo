package io.valueof.concatadapter.ui.main.model

import androidx.annotation.DrawableRes

sealed class Item(
  open val id: String,
  open val title: String,
  @DrawableRes open val imageResId: Int,
)

data class Featured(
  override val id: String,
  override val title: String,
  @DrawableRes override val imageResId: Int,
  val description: String
) : Item(
  id = id,
  title = title,
  imageResId = imageResId
)

data class Regular(
  override val id: String,
  override val title: String,
  @DrawableRes override val imageResId: Int,
) : Item(
  id = id,
  title = title,
  imageResId = imageResId
)
