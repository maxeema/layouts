package com.example.layouts.diseases

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.layouts.databinding.DiseasesListItemBinding

class DiseaseItemsAdapter(private val onClick: (View) -> Unit) :
    ListAdapter<DiseaseItem, DiseaseListItemHolder>(
        DiffCallback
    ) {

    companion object DiffCallback : DiffUtil.ItemCallback<DiseaseItem>() {
        override fun areItemsTheSame(oldItem: DiseaseItem, newItem: DiseaseItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DiseaseItem, newItem: DiseaseItem) =
            oldItem == newItem
    }

    private var data : List<DiseaseItem>? = null
    private var selected : DiseaseItem? = null

    fun setData(newData: List<DiseaseItem>, selectedItem: DiseaseItem, onDone: Runnable) {
        data = newData
        selected = selectedItem
        submitList(newData, onDone)
    }

    fun select(selectedItem: DiseaseItem) {
        data?.let {
            if (it.contains(selectedItem)) {
                val curSelectedIdx = if (selected == null) -1 else it.indexOf(selected)
                if (curSelectedIdx != -1)
                    notifyItemChanged(curSelectedIdx)
                selected = selectedItem
                notifyItemChanged(it.indexOf(selectedItem))
            }
        }

    }
    override fun getItemId(position: Int): Long = position.toLong()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DiseaseListItemHolder.from(parent)

    override fun onBindViewHolder(holder: DiseaseListItemHolder, position: Int) {
        val bindItem = getItem(position)
        holder.bind(bindItem, onClick, selected = selected == bindItem)
    }

}

class DiseaseListItemHolder(private var binding: DiseasesListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(bindItem: DiseaseItem, onSelect: (View) -> Unit, selected: Boolean) = binding.apply {
        item = bindItem
        root.tag = bindItem
        root.setOnClickListener(onSelect)
        checked = selected
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup) =
            DiseaseListItemHolder(
                DiseasesListItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
    }

}