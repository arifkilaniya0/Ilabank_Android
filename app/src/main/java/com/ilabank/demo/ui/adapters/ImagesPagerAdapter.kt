package com.ilabank.demo.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.ilabank.demo.databinding.ImageItemViewBinding
import com.ilabank.demo.ui.models.SubData

class ImagesPagerAdapter(
    private val activity: Activity,
    private val list: ArrayList<SubData>
) : RecyclerView.Adapter<ImagesPagerAdapter.MyViewHolder>() {

    /**
     * Override onCreateViewHolder which deals with the inflation of the card layout as an item for the RecyclerView.
     */
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        // Inflate item.xml using Layout Inflator
        val binding = ImageItemViewBinding.inflate(LayoutInflater.from(activity), parent, false)
        // return itemView
        return MyViewHolder(binding)
    }

    /**
     * Override onBindViewHolder which deals with the setting of different data and methods related to clicks on particular items of the RecyclerView.
     */
    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val image = list[position].image
        // Recycler view with the list items
        holder.view.carouselImageView.setImageDrawable(ContextCompat.getDrawable(activity, image))
    }

    /**
     * Override getItemCount which Returns the length of the RecyclerView.
     */
    override fun getItemCount(): Int {
        return list.size
    }

    /**
     * View Holder class which extends RecyclerView.ViewHolder
     */
    inner class MyViewHolder(val view: ImageItemViewBinding) : RecyclerView.ViewHolder(view.root)
}