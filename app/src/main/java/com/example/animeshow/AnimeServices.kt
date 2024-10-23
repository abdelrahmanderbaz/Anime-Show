package com.example.animeshow


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class AnimeResponse(
    val data: List<AnimeData>
)

data class AnimeData(
    val id: String,
    val attributes: Attributes
)

data class Attributes(
    val canonicalTitle: String,
    val averageRating: String,
    val episodeCount: Int,
    val posterImage: PosterImage,
    val coverImage:CoverImage,
    val status:String,
    val ageRatingGuide:String,
    val startDate:String,
    val endDate:String,
    val description:String
)

data class PosterImage(
    val original: String
)
data class CoverImage(
    val original:String
)

interface AnimeService {
    @GET("anime?sort=popularityRank")
    suspend fun getAnimeList(): AnimeResponse
}

object RetrofitInstance {
    val api: AnimeService by lazy {
        Retrofit.Builder()
            .baseUrl("https://kitsu.io/api/edge/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnimeService::class.java)
    }
}
