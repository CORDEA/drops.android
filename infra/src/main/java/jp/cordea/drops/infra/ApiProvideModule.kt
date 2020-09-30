package jp.cordea.drops.infra

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
internal class ApiProvideModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideDropsApi(retrofit: Retrofit): DropsApi =
        retrofit.create(DropsApi::class.java)
}
