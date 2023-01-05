package com.example.sportresults.ui.results

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sportresults.databinding.ResultItemViewBinding

class RecyclerViewAdapter(val clickListener: ResultsListener): ListAdapter<String, RecyclerViewAdapter.ViewHolder>(ResultsDiffCallback()) {

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the RecyclerView.ViewHolder.itemView
     * to reflect the item at the given position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item!!, clickListener)
    }

    /**
     * Called when RecyclerView needs a new RecyclerView.ViewHolder
     * of the given type to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //parent in reality is always the RecyclerView
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(val binding: ResultItemViewBinding): RecyclerView.ViewHolder(binding.root){
        val res = binding.root.context.resources

        fun bind(item: String, clickListener: ResultsListener) {
            binding.result = item
            binding.clickListener = clickListener
            //excutes the binding dapaters that are connected to views
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ResultItemViewBinding.inflate(layoutInflater, parent, false)

                return ViewHolder(binding)
            }
        }
    }
}

class ResultsDiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}

class ResultsListener(val clickListener: (hl: String) -> Unit) {
    fun onClick(hl: String) = clickListener(hl)
}


