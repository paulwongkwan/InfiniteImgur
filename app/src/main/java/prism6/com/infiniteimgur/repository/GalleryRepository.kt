package prism6.com.infiniteimgur.repository

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.room.GalleryDB

class GalleryRepository {
    companion object {

        var galleryDB: GalleryDB? = null

        var galleryModel: LiveData<GalleryModel>? = null

        fun initializeDB(context: Context) : GalleryDB {
            return GalleryDB.getDataseClient(context)
        }

    }

}