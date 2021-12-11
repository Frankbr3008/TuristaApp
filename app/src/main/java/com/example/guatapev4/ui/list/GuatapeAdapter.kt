package com.example.guatapev4.ui.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.guatapev4.R
import com.example.guatapev4.model.GuatapeItem
import com.squareup.picasso.Picasso

class GuatapeAdapter(
    private val guatapeList: ArrayList<GuatapeItem>,
    private val onItemClicked: (GuatapeItem) -> Unit
) : RecyclerView.Adapter<GuatapeAdapter.GuatapeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuatapeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_guatape_item, parent, false)
        return GuatapeViewHolder(view)
    }

    override fun onBindViewHolder(holder: GuatapeViewHolder, position: Int) {
        val guatape = guatapeList[position]
        holder.itemView.setOnClickListener { onItemClicked(guatapeList[position]) }
        holder.bind(guatape)
    }

    override fun getItemCount(): Int = guatapeList.size

    fun appendItems(newItems: ArrayList<GuatapeItem>) {
        guatapeList.clear()
        guatapeList.addAll(newItems)
        notifyDataSetChanged()
    }

    class GuatapeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private var short_dTextView: TextView = itemView.findViewById(R.id.short_d_text_view)
        private var pictureImageView: ImageView = itemView.findViewById(R.id.picture_image_view)

        fun bind(guatape: GuatapeItem){
            Log.d("nombre",guatape.name)
            nameTextView.text = guatape.name
            short_dTextView.text = guatape.short_d
            Picasso.get().load(guatape.imageUrl).into(pictureImageView)
        }
    }
}