package prism6.com.infiniteimgur.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import prism6.com.infiniteimgur.mApplication
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.repository.GalleryRepository
import prism6.com.infiniteimgur.uilitiy.Resource

class GalleryViewModel : ViewModel() {
    private val repository: GalleryRepository = mApplication.instance.repository
    val gallerys : LiveData<Resource<List<GalleryModel>>> = repository.getGallerys()
}