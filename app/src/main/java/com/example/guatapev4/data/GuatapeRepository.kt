package com.example.guatapev4.data

class GuatapeRepository {

    suspend fun getGuatape() = ApiFactory.retrofit.getGuatape()

}