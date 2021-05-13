package prism6.com.infiniteimgur.room

import androidx.lifecycle.LiveData
import androidx.room.*
import prism6.com.infiniteimgur.model.GalleryModel

@Dao
interface DAOAccess {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertData(galleryModel: GalleryModel)

    @Query("SELECT * FROM gallery WHERE id =:id")
    fun getGalleryDetails(id: String?) : LiveData<GalleryModel>

}
