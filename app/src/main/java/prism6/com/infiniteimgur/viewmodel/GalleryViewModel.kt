package prism6.com.infiniteimgur.viewmodel

import android.content.ContextWrapper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
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
import prism6.com.infiniteimgur.view.ImageDialog
import java.util.concurrent.CopyOnWriteArrayList


class GalleryViewModel : ViewModel() {
    private val repository: GalleryRepository = mApplication.instance.repository
    var gallerys: LiveData<Resource<List<GalleryModel>>> = repository.getGallerys()
    var galleryPage: LiveData<Resource<List<GalleryModel>>>
    var isLoading = MutableLiveData<Boolean>()
    var page = 0
    var galleryList = MutableLiveData<CopyOnWriteArrayList<GalleryModel>>(CopyOnWriteArrayList())

    val empty: LiveData<Boolean> = Transformations.map(gallerys) {
        it.data.isNullOrEmpty()
    }

    init {
        repository.clearLocalCache()
        galleryPage = repository.getGallerys(page)
    }

    fun update(){
        galleryPage = repository.getGallerys(page)
    }

    fun click(view: View, url:String) {
        val context: ContextWrapper = view.context as ContextWrapper
        val activity: AppCompatActivity = context.baseContext as AppCompatActivity
        ImageDialog.newInstance(url).show(activity.supportFragmentManager, "Viewer")
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