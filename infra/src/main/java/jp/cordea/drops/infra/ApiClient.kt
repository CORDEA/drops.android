package jp.cordea.drops.infra

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class ApiClient @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun loginApi(): LoginApi = retrofit.create(LoginApi::class.java)
}
