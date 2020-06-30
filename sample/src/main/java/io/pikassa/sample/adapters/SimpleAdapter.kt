package io.pikassa.sample.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.pikassa.sample.databinding.RecyclerviewRowBinding

/**
Created by Denis Chornyy on 30,Июнь,2020
All rights received.
 */
class SimpleAdapter(private val items: List<String>): RecyclerView.Adapter<SimpleAdapter.ViewHolder>() {

    class ViewHolder(private val binding: RecyclerviewRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
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