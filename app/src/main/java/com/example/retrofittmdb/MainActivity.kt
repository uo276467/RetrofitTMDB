package com.example.retrofittmdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.retrofittmdb.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch(Dispatchers.IO) {
            val listMovies = RetrofitClient.makeClient().listMovies(
                "popular",
                "6bc4475805ebbc4296bcfa515aa8df08",
                "es-ES",
                1
            )
            println(listMovies)

            lifecycleScope.launch(Dispatchers.IO) {
                val movieDetails = RetrofitClient.makeClient().getMovieDetails(
                    listMovies.results.get(0).id.toString(),
                    "6bc4475805ebbc4296bcfa515aa8df08",
                    "es-ES",
                    "videos"
                )
                println(movieDetails)
            }
        }
    }
}