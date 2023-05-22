package com.example.testmarvel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmarvel.R
import com.example.testmarvel.databinding.ActivityMainBinding
import com.example.testmarvel.service.MarvelApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var comicListAdapter: ComicListAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        delegate.setContentView(binding.root)

        binding.comicRecyclerView.layoutManager = LinearLayoutManager(this)
        comicListAdapter = ComicListAdapter(emptyList(),this@MainActivity)
        binding.comicRecyclerView.adapter = comicListAdapter
        fetchComics()
    }

    private fun fetchComics() {
        // Exécutez cette fonction pour obtenir les bandes dessinées depuis l'API Marvel et mettez à jour l'adaptateur
        CoroutineScope(Dispatchers.IO).launch {
            val comics = MarvelApiClient.getComics()
            withContext(Dispatchers.Main) {
                if (comics != null) {
                    comicListAdapter.comics = comics
                    comicListAdapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Failed to fetch comics", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}