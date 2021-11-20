package com.mintic.GautapeV3

import GautapeV3.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList


class GuatapeAdapter(
    private val mGuatapes: ArrayList<Guatape>,
    private val context: Context,
    private val onClick: (Guatape) -> Unit
) : RecyclerView.Adapter<GuatapeAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.guatape_list_item, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holderContact: ContactViewHolder, position: Int) {
        val contact = mGuatapes[position]
        holderContact.bind(guatape = contact)
    }

    override fun getItemCount(): Int {
        return mGuatapes.size
    }

    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var placenameLabel: TextView = itemView.findViewById(R.id.textview_placename)
        private var placedescriptionLabel: TextView = itemView.findViewById(R.id.textview_placedescription)
        private var starsLabel: TextView = itemView.findViewById(R.id.textview_stars)
        private var imageView: ImageView = itemView.findViewById(R.id.imageview_thumb)
        private var currentGuatape: Guatape? = null

        init {
            itemView.setOnClickListener {
                currentGuatape?.let {
                    onClick(it)
                }
            }
        }

        /* Bind Contact name and image. */
        fun bind(guatape: Guatape) {
            currentGuatape = guatape

            val fullName = "${guatape.placename}"
            placenameLabel.text = fullName
            placedescriptionLabel.text = guatape.placedescription
            starsLabel.text = guatape.stars

            Glide.with(context)
                .load(guatape.imageUrl)
                .into(imageView)
        }
    }
}
