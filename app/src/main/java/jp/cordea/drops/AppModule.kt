package jp.cordea.drops

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import jp.cordea.drops.infra.ApiUrlProvider

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {
    @Provides
    fun provideApiUrlProvider() = object : ApiUrlProvider {
        override val baseUrl: String get() = BuildConfig.API_BASE_URL
    }
}
