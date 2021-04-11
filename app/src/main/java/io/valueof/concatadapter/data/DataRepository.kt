package io.valueof.concatadapter.data

import io.valueof.concatadapter.R
import io.valueof.concatadapter.ui.main.model.Featured
import io.valueof.concatadapter.ui.main.model.Item
import io.valueof.concatadapter.ui.main.model.Regular
import java.util.*

object DataRepository {
    fun getItems(): List<Item> =
        listOf(
            Featured(
                id = UUID.randomUUID().toString(),
                title = "Item 1 Featured",
                description = "Super exciting new design",
                imageResId = R.drawable.image1
            ),
            Featured(
                id = UUID.randomUUID().toString(),
                title = "Item 2 Featured",
                description = "Featured design is here",
                imageResId = R.drawable.image2
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 3",
                imageResId = R.drawable.image3
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 4",
                imageResId = R.drawable.image4
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 5",
                imageResId = R.drawable.image5
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 6",
                imageResId = R.drawable.image6
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 7",
                imageResId = R.drawable.image7
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 8",
                imageResId = R.drawable.image8
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 9",
                imageResId = R.drawable.image9
            ),
            Regular(
                id = UUID.randomUUID().toString(),
                title = "Item 10",
                imageResId = R.drawable.image10
            )
        )
}