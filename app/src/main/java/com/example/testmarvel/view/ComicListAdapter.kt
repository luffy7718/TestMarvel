package com.example.testmarvel.view

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.testmarvel.R
import com.example.testmarvel.model.Comic
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl
import java.net.URL

class ComicListAdapter(var comics: List<Comic>, var context:Context) : RecyclerView.Adapter<ComicListAdapter.ComicViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comic, parent, false)
        return ComicViewHolder(view)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        val comic = comics[position]
        holder.bind(comic)
    }

    override fun getItemCount(): Int {
        return comics.size
    }

    inner class ComicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        private val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val comic = comics[position]
                    showComicDetails(comic,context)
                }
            }
        }

        fun bind(comic: Comic) {
            titleTextView.text = comic.title
            val path = comic.thumbnail?.path.toString()
            val extension = comic.thumbnail?.extension.toString()
            val urlMarvelImage = URL("$path.$extension")

            Picasso.get().load(urlMarvelImage.toString())
                .placeholder(R.drawable.image_not_available)
                .error(R.drawable.image_not_available)
                .fit().centerInside()
                .into(thumbnailImageView)
        }

        private fun showComicDetails(comic: Comic,context: Context) {
            val intent = Intent(context, ComicDetailsActivity::class.java)
            intent.putExtra(ComicDetailsActivity.EXTRA_COMIC, comic)
            context.startActivity(intent)
        }
    }
}