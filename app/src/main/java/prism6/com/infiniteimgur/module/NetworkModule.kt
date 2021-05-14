package prism6.com.infiniteimgur.module

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import prism6.com.infiniteimgur.network.APIService
import prism6.com.infiniteimgur.repository.GalleryLocalRepository
import prism6.com.infiniteimgur.repository.GalleryRemoteRepository
import prism6.com.infiniteimgur.repository.GalleryRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.imgur.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(provideOkHttpClient())
            .build()
    }

    @Singleton
    @Provides
    fun provideApi(): APIService {
        return provideRetrofit().create(APIService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .followRedirects(false)
            .addNetworkInterceptor(StethoInterceptor())
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRemoteRepository() = GalleryRemoteRepository()

    @Singleton
    @Provides
    fun provideLocalepository() = GalleryLocalRepository()

    @Singleton
    @Provides
    fun provideRepository(galleryRemoteRepository: GalleryRemoteRepository,
                          galleryLocalRepository: GalleryLocalRepository) =
        GalleryRepository(galleryRemoteRepository, galleryLocalRepository)
}