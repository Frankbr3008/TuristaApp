package com.example.guatapev4.data

import com.example.guatapev4.model.Guatape
import retrofit2.http.GET

interface ApiService {

    @GET("Frankbr3008/TuristaApp/guatape")
    suspend fun getGuatape(): Guatape

}