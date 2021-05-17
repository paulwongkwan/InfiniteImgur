package prism6.com.infiniteimgur.repository

import android.util.Log
import dagger.Module
import prism6.com.infiniteimgur.mApplication
import prism6.com.infiniteimgur.network.APIService
import prism6.com.infiniteimgur.network.Header
import prism6.com.infiniteimgur.uilitiy.Resource
import retrofit2.Response
import javax.inject.Inject

@Module
class GalleryRemoteRepository constructor() {
    suspend fun getGallerys() = getResult { apiService.gallery(Header.header(), "hot", "viral", 0, true) }

    val apiService: APIService = mApplication.instance.apiService

    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        Log.e("remoteDataSource", message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
}