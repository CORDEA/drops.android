package jp.cordea.drops.infra

import com.squareup.moshi.Moshi
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
    fun provideRetrofit(apiUrlProvider: ApiUrlProvider): Retrofit =
        Retrofit.Builder()
            .baseUrl(apiUrlProvider.baseUrl)
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(LocalDateTimeJsonAdapter())
                        .build()
                )
            )
            .build()

    @Provides
    @Singleton
    fun provideDropsApi(retrofit: Retrofit): DropsApi =
        retrofit.create(DropsApi::class.java)
}
