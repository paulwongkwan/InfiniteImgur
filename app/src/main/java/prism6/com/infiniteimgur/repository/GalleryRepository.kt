package prism6.com.infiniteimgur.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import prism6.com.infiniteimgur.uilitiy.Resource
import javax.inject.Inject

class GalleryRepository @Inject constructor(
    private val galleryRemoteRepository: GalleryRemoteRepository,
    private val galleryLocalRepository: GalleryLocalRepository
) {
    fun getGallerys() = get(
        db = { galleryLocalRepository.getGallerys() },
        call = { galleryRemoteRepository.getGallerys() },
        result = { galleryLocalRepository.insertAll(it.data) }
    )
}

fun <T, A> get(
    db: () -> LiveData<T>,
    call: suspend () -> Resource<A>,
    result: suspend (A) -> Unit
): LiveData<Resource<T>> =
    liveData(Dispatchers.IO) {
        emit(Resource.loading())
        val source = db.invoke().map { Resource.success(it) }
        emitSource(source)

        val responseStatus = call.invoke()
        if (responseStatus.status == Resource.Status.SUCCESS) {
            result(responseStatus.data!!)

        } else if (responseStatus.status == Resource.Status.ERROR) {
            emit(Resource.error(responseStatus.message!!))
            emitSource(source)
        }
    }