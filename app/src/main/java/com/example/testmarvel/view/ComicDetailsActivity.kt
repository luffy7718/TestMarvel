package com.example.testmarvel.view

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testmarvel.R
import com.example.testmarvel.databinding.ActivityComicDetailsBinding
import com.example.testmarvel.databinding.ActivityMainBinding
import com.example.testmarvel.model.Comic
import com.squareup.picasso.Picasso
import okhttp3.HttpUrl
import java.net.URL

class ComicDetailsActivity : AppCompatActivity() {

    var comic: Comic? = null

    private lateinit var binding: ActivityComicDetailsBinding

    companion object {
        const val EXTRA_COMIC = "extra_comic"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_details)

        comic = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA_COMIC, Comic::class.java) as Comic
        } else{
            intent.getParcelableExtra(EXTRA_COMIC) as Comic?
        }

        binding = ActivityComicDetailsBinding.inflate(layoutInflater)
        delegate.setContentView(binding.root)

       supportActionBar?.title = comic?.title

        binding.titleTextView.text = comic?.title

        binding.descriptionTextView.text = comic?.description

        val path = comic?.thumbnail?.path.toString()
        val extension = comic?.thumbnail?.extension.toString()
        val urlMarvelImage = URL("$path.$extension")

        Picasso.get().load(urlMarvelImage.toString())
            .placeholder(R.drawable.image_not_available)
            .error(R.drawable.image_not_available)
            .fit().centerInside()
            .into(binding.thumbnailImageView)
    }
}