package prism6.com.infiniteimgur.room

import android.content.Context
import androidx.room.Database
import androidx.room.*
import prism6.com.infiniteimgur.model.GalleryModel

@Database(entities = arrayOf(GalleryModel::class), version = 1, exportSchema = false)
abstract class GalleryDB : RoomDatabase() {

    abstract fun galleryDao() : DAOAccess

    companion object {

        @Volatile
        private var INSTANCE: GalleryDB? = null

        fun getDataseClient(context: Context) : GalleryDB {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, GalleryDB::class.java, "GALLERY_DB")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}