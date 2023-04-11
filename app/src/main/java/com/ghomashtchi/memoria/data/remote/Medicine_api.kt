package com.ghomashtchi.memoria.data.remote

import com.ghomashtchi.memoria.data.medicineApi.MedicineDTO
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
//whole URL of the api except last endpoint.
const val BASE_URL = "http://10.0.2.2:3000/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()
interface MedicineApiService {
    //@GET = gets the endpoint we want from the BASE_URL.
    @GET("/Medicine")
    suspend fun getMedicine(): MedicineDTO
    //if queries are needed
}
//This is an object instance of the ApiService and this object is used for the app to access the API.
object MedicineApi {
    val retrofitService: MedicineApiService by lazy { retrofit.create(MedicineApiService::class.java) }
}