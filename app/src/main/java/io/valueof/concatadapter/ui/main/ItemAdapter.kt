package io.valueof.concatadapter.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.valueof.concatadapter.R
import io.valueof.concatadapter.databinding.ItemFeaturedBinding
import io.valueof.concatadapter.databinding.ItemRegularBinding
import io.valueof.concatadapter.ui.main.model.Featured
import io.valueof.concatadapter.ui.main.model.Item
import io.valueof.concatadapter.ui.main.model.Regular

class ItemAdapter(
    private val listener: (String) -> Unit
) : ListAdapter<Item, ItemViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return when (viewType) {
            R.layout.item_featured -> {
                val binding = ItemFeaturedBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                FeaturedViewHolder(binding, listener)
            }
            else -> {
                val binding = ItemRegularBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                RegularViewHolder(binding, listener)
            }
        }
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return getItemType(getItem(position))
    }

    @LayoutRes
    private fun getItemType(item: Item): Int {
        return when (item) {
            is Featured -> R.layout.item_featured
            is Regular -> R.layout.item_regular
        }
    }
}

abstract class ItemViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: Item)
}

class FeaturedViewHolder(
    private val binding: ItemFeaturedBinding,
    private val listener: (String) -> Unit
) : ItemViewHolder(binding.root) {

    lateinit var item: Featured

    init {
        itemView.setOnClickListener {
            listener(item.id)
        }
    }

    override fun bind(item: Item) {
        this.item = item as Featured

        binding.image.load(item.imageResId) {
            crossfade(true)
        }
        binding.title.text = item.title
        binding.description.text = item.description
    }
}

class RegularViewHolder(
    private val binding: ItemRegularBinding,
    private val listener: (String) -> Unit
) : ItemViewHolder(binding.root) {

    lateinit var item: Regular

    init {
        itemView.setOnClickListener {
            listener(item.id)
        }
    }

    override fun bind(item: Item) {
        this.item = item as Regular

        binding.image.load(item.imageResId) {
            crossfade(true)
        }
        binding.title.text = item.title
    }
}

class ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}

