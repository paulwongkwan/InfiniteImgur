package prism6.com.infiniteimgur

import android.app.Application
import com.facebook.stetho.Stetho

class mApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this);
    }
}