package com.example.layouts.nearby

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.layouts.databinding.NearbyListItemBinding

class NearbyItemsAdapter(private val onClick: (View) -> Unit) :
    ListAdapter<NearbyItem, NearbyListViewHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<NearbyItem>() {
        override fun areItemsTheSame(oldItem: NearbyItem, newItem: NearbyItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NearbyItem, newItem: NearbyItem) =
            oldItem == newItem
    }

    private var data : List<NearbyItem>? = null

    fun setData(newData: List<NearbyItem>, onDone: Runnable) {
        data = newData
        submitList(newData, onDone)
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NearbyListViewHolder.from(parent)

    override fun onBindViewHolder(holder: NearbyListViewHolder, position: Int) {
        val bindItem = getItem(position)
        holder.bind(bindItem, onClick)
    }

}

class NearbyListViewHolder(private var binding: NearbyListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(bindItem: NearbyItem, onSelect: (View) -> Unit) = binding.apply {
        item = bindItem
        root.tag = bindItem
        root.setOnClickListener(onSelect)
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup) =
            NearbyListViewHolder(
                NearbyListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

}