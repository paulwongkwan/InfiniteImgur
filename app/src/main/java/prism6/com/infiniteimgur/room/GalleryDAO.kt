package prism6.com.infiniteimgur.room

import androidx.lifecycle.LiveData
import androidx.room.*
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.model.Image

@Dao
interface GalleryDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(galleryModel: GalleryModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAll(galleryModels: List<GalleryModel>)

    @Query("SELECT * FROM gallery WHERE id =:id")
    fun getGalleryDetails(id: String?) : LiveData<GalleryModel>

    @Query("SELECT * FROM gallery")
    fun getGallerys() : LiveData<List<GalleryModel>>
}
