package com.cubidevs.dccomics.list

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cubidevs.dccomics.R
import com.cubidevs.dccomics.model.SitioItem
import com.squareup.picasso.Picasso

class GuatapeAdapter(
    private val sitioList: ArrayList<SitioItem>,
    private val onItemClicked: (SitioItem) -> Unit
) : RecyclerView.Adapter<GuatapeAdapter.SitioViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SitioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_sitio_item, parent, false)
        return SitioViewHolder(view)
    }

    override fun onBindViewHolder(holder: SitioViewHolder, position: Int) {
        val sitio = sitioList[position]
        holder.itemView.setOnClickListener { onItemClicked(sitioList[position]) }
        holder.bind(sitio)
    }

    override fun getItemCount(): Int = sitioList.size

    class SitioViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private var nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
        private var starsTextView: TextView = itemView.findViewById(R.id.textView)
        //private var descripcionTextView: TextView = itemView.findViewById(R.id.info)
        private var pictureImageView: ImageView = itemView.findViewById(R.id.picture_image_view)

        fun bind(sitio: SitioItem){
            Log.d("nombre",sitio.placename)
            nameTextView.text = sitio.placename
            starsTextView.text = sitio.stars
            //descripcionTextView.text = sitio.placedescripcion
            Picasso.get().load(sitio.imageUrl).into(pictureImageView)
        }
    }
}