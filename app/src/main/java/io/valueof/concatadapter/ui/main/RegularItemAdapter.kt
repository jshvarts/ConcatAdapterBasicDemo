package io.valueof.concatadapter.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import io.valueof.concatadapter.databinding.ItemRegularBinding
import io.valueof.concatadapter.ui.main.model.Regular
import timber.log.Timber

class RegularItemAdapter(
    private val listener: (String) -> Unit
) : ListAdapter<Regular, RegularItemViewHolder>(RegularItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegularItemViewHolder {
        val binding = ItemRegularBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return RegularItemViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RegularItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class RegularItemViewHolder(
    private val binding: ItemRegularBinding,
    private val listener: (String) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    lateinit var item: Regular

    init {
        itemView.setOnClickListener {
            Timber.d("item with position within adapter selected $bindingAdapterPosition")
            Timber.d("item with position within all adapters selected $absoluteAdapterPosition")
            listener(item.id)
        }
    }

    fun bind(item: Regular) {
        this.item = item

        binding.image.load(item.imageResId) {
            crossfade(true)
        }
        binding.title.text = item.title
    }
}

class RegularItemDiffCallback : DiffUtil.ItemCallback<Regular>() {
    override fun areItemsTheSame(oldItem: Regular, newItem: Regular): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Regular, newItem: Regular): Boolean {
        return oldItem == newItem
    }
}
