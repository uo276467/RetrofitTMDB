package com.example.retrofittmdb.remote

import com.example.retrofittmdb.remote.model.ResultMovieDetails
import com.example.retrofittmdb.remote.model.ResultMovieList
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbApi {

    @GET("movie/{type}")
    suspend fun listMovies(
        @Path("type") type: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): ResultMovieList

    @GET("movie/{id}")
    suspend fun getMovieDetails(
        @Path("id") type: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("append_to_response") appendToResponse: String
    ): ResultMovieDetails
}

object RetrofitClient {

    fun makeClient(): TmdbApi {
        // Se crea el interceptor
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        var httpClient : OkHttpClient.Builder = OkHttpClient.Builder()
        // Se establece el interceptor en el cliente HTTP
        httpClient.addInterceptor(logging)

        val client = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
            .create(TmdbApi::class.java)
        return client
    }
}