package io.pikassa.sample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.pikassa.sample.databinding.RecyclerviewRowBinding
import io.pikassa.sample.entities.OrderHistoryData

/**
Created by Denis Chornyy on 30,Июнь,2020
All rights received.
 */
class SimpleAdapter : RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    private val items = arrayListOf<OrderHistoryData>()

    fun onAddNewItem(item: OrderHistoryData) {
        items.add(0, item)
        notifyItemInserted(0)
    }

    class ViewHolder(private val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: OrderHistoryData) {
            binding.item = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerviewRowBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])
}