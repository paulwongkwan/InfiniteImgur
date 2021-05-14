package prism6.com.infiniteimgur.component

import dagger.Component
import prism6.com.infiniteimgur.mainActivity
import prism6.com.infiniteimgur.module.NetworkModule
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface ActivityComponent {
    fun inject(main: mainActivity)
}