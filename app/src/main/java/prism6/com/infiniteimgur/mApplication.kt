package prism6.com.infiniteimgur

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.piasy.biv.BigImageViewer
import com.github.piasy.biv.loader.glide.GlideImageLoader
import prism6.com.infiniteimgur.component.DaggerAppComponent
import prism6.com.infiniteimgur.network.APIService
import prism6.com.infiniteimgur.repository.GalleryRepository
import javax.inject.Inject

class mApplication: Application() {
    companion object {
        lateinit var instance : mApplication
    }

    @Inject
    lateinit var apiService: APIService
    @Inject
    lateinit var repository: GalleryRepository

    override fun onCreate() {
        super.onCreate()

        instance = this

        DaggerAppComponent.create().inject(this)

        Stetho.initializeWithDefaults(this);

        BigImageViewer.initialize(GlideImageLoader.with(this));
    }
}