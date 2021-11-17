package com.ilabank.demo.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilabank.demo.databinding.ItemLabelViewBinding

class ListItemAdapter(
    private val activity: Activity,
    private var list: ArrayList<String>
) : RecyclerView.Adapter<ListItemAdapter.MyViewHolder>() {

    /**
     * Override onCreateViewHolder which deals with the inflation of the card layout as an item for the RecyclerView.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {

        // Inflate item.xml using Layout Inflator
        val binding = ItemLabelViewBinding.inflate(LayoutInflater.from(activity), parent, false)
        // return itemView
        return MyViewHolder(binding)
    }

    /**
     * Override onBindViewHolder which deals with the setting of different
     * data and methods related to clicks on particular items of the RecyclerView.
     */
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        // Recycler view with the list items
        holder.view.tvLabel.text = list[position]
    }

    /**
     * Override getItemCount which Returns the length of the RecyclerView.
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * Function to update the view with latest list of items.
     *
     * @param arrayList :- Latest List of Items
     */
    fun updatedListItems(arrayList: ArrayList<String>) {
        list = arrayList
        notifyDataSetChanged()
    }

    /**
     * View Holder class which extends RecyclerView.ViewHolder
     */
    inner class MyViewHolder(val view: ItemLabelViewBinding) : RecyclerView.ViewHolder(view.root)
}