package prism6.com.infiniteimgur.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import prism6.com.infiniteimgur.mApplication
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.room.GalleryDB
import javax.inject.Inject

class GalleryLocalRepository constructor() {

    var galleryDB: GalleryDB? = null

    val context : Context = mApplication.instance

    fun initializeDB(context: Context): GalleryDB {
        return GalleryDB.getDataseClient(context)
    }

    fun insert(galleryModel: GalleryModel) {

        galleryDB = initializeDB(context)

        CoroutineScope(IO).launch {
            galleryDB!!.galleryDao().Insert(galleryModel)
        }
    }

    fun insertAll(galleryModels: List<GalleryModel>) {

        galleryDB = initializeDB(context)

        var newGalleryList = galleryModels
            .filter { !it.images.isNullOrEmpty() }
            .filter { it.images!![0].type!!.contains("image/") }

        CoroutineScope(IO).launch {
            galleryDB!!.galleryDao().InsertAll(newGalleryList)
        }
    }

    fun getGalleryDetails(id: String): LiveData<GalleryModel> {

        galleryDB = initializeDB(context)

        return galleryDB!!.galleryDao().getGalleryDetails(id)
    }

    fun getGallerys(): LiveData<List<GalleryModel>> {

        galleryDB = initializeDB(context)

        return galleryDB!!.galleryDao().getGallerys()
    }
}