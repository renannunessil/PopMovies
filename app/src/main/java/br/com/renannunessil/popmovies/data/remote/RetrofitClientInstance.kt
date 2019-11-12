package br.com.renannunessil.popmovies.data.remote

import br.com.renannunessil.popmovies.data.Paths
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientInstance {

    companion object RetrofitInstance {

        fun create(): MoviesApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Paths.MOVIES_API)
                .client(client)
                .build()

            return retrofit.create(MoviesApi::class.java)
        }
    }
}