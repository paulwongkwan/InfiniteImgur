package prism6.com.infiniteimgur.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import prism6.com.infiniteimgur.model.GalleryModel

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

    @Query("SELECT * FROM gallery WHERE page = :page")
    fun getGallerys(page: Int) : LiveData<List<GalleryModel>>

    @Query("DELETE FROM gallery")
    suspend fun clearAll()
}
