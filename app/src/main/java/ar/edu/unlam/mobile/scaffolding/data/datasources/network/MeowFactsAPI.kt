package ar.edu.unlam.mobile.scaffolding.data.datasources.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MeowFactsAPI {
    @GET("/")
    suspend fun getCatFact(): MeowFactDTO

    @GET("/")
    suspend fun getCatFact(
        @Query("count") count: Int = 1,
    ): MeowFactDTO
}
