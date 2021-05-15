package prism6.com.infiniteimgur.viewmodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import prism6.com.infiniteimgur.R
import prism6.com.infiniteimgur.mApplication
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.repository.GalleryRepository
import prism6.com.infiniteimgur.uilitiy.Resource


class GalleryViewModel : ViewModel() {
    private val repository: GalleryRepository = mApplication.instance.repository
    var gallerys: LiveData<Resource<List<GalleryModel>>> = repository.getGallerys()
    var isLoading = MutableLiveData<Boolean>()

    val empty: LiveData<Boolean> = Transformations.map(gallerys) {
        it.data.isNullOrEmpty()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("mainImage")
        fun loadImage(view: ImageView, galleryModel: GalleryModel) {
            try {
                Glide.with(view.context)
                    .load(galleryModel.images!![0].link)
                    .placeholder(R.drawable.placeholder)
                    .into(view)
            }catch (e : Exception){
                Glide.with(view.context)
                    .load(galleryModel.link)
                    .placeholder(R.drawable.placeholder)
                    .into(view)
            }
        }
    }
}