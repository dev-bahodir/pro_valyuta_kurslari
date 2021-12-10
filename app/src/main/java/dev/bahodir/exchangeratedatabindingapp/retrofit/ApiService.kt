package dev.bahodir.exchangeratedatabindingapp.retrofit

import dev.bahodir.exchangeratedatabindingapp.users.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("uz/exchange-rates/json/")
    fun getUsers(): Call<List<Users>>
}