package prism6.com.infiniteimgur

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import prism6.com.infiniteimgur.adapter.MainGalleryAdapter
import prism6.com.infiniteimgur.databinding.ActivityFullscreenBinding
import prism6.com.infiniteimgur.uilitiy.Resource
import prism6.com.infiniteimgur.viewmodel.GalleryViewModel

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */


class mainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding
    private lateinit var fullscreenContent: RecyclerView
    private lateinit var fullscreenContentControls: LinearLayout
    private lateinit var emptyText: TextView
    private lateinit var loadingBox: FrameLayout
    private val hideHandler = Handler()

    val galleryViewModel: GalleryViewModel by viewModels()

    private lateinit var adapter: MainGalleryAdapter

    @SuppressLint("InlinedApi")
    private val hidePart2Runnable = Runnable {
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        fullscreenContent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LOW_PROFILE or
                    View.SYSTEM_UI_FLAG_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
    }
    private val showPart2Runnable = Runnable {
        // Delayed display of UI elements
        supportActionBar?.show()
        fullscreenContentControls.visibility = View.VISIBLE
    }
    private var isFullscreen: Boolean = false

    private val hideRunnable = Runnable { hide() }

    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private val delayHideTouchListener = View.OnTouchListener { view, motionEvent ->
        when (motionEvent.action) {
            MotionEvent.ACTION_DOWN -> if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS)
            }
            MotionEvent.ACTION_UP -> view.performClick()
            else -> {
            }
        }
        fetchGallery()
        false
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        isFullscreen = true

        // Set up the user interaction to manually show or hide the system UI.
        fullscreenContent = binding.fullscreenContent

        fullscreenContentControls = binding.fullscreenContentControls

        loadingBox = binding.loadingBox

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        binding.dummyButton.setOnTouchListener(delayHideTouchListener)

        galleryViewModel.isLoading.observe(this,
            { isLoading ->
                if (isLoading != null) {
                    if (isLoading) {
                        // hide your progress bar
                        loadingBox.visibility = View.GONE
                    }else{
                        loadingBox.visibility = View.VISIBLE
                    }
                }else{
                    loadingBox.visibility = View.VISIBLE
                }
            })

        galleryViewModel.galleryList.observe(this, Observer {
            if (!::adapter.isInitialized) {
                adapter = MainGalleryAdapter(galleryViewModel)
                fullscreenContent.adapter = adapter
                fullscreenContent.setHasFixedSize(true)
                fullscreenContent.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                        super.onScrollStateChanged(recyclerView, newState)

                        if (!recyclerView.canScrollVertically(1) && galleryViewModel.isLoading.value == true) {
                            galleryViewModel.page.value = galleryViewModel.page.value!! + 1
                        }
                    }
                })
            }
        })

        fetchGallery()
    }

    fun fetchGallery() {
        galleryViewModel.isLoading.value = false
        galleryViewModel.galleryPage.observe(this, Observer {
            if (it.status == Resource.Status.SUCCESS) {
                val dataSize = it.data!!.size
                if (dataSize > 0) {
                    val size = galleryViewModel.galleryList.value?.size
                    galleryViewModel.galleryList.value?.addAll(it.data!!)
                    adapter.notifyItemRangeInserted(size!!, dataSize)
                    galleryViewModel.isLoading.value = true
//                    galleryViewModel.page.value = galleryViewModel.page.value!! + 1
                }
            }else if (it.status == Resource.Status.LOADING){
                galleryViewModel.isLoading.value = false
            }else if (it.status == Resource.Status.ERROR){
                galleryViewModel.isLoading.value = true
            }
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100)
    }

    private fun toggle() {
        if (isFullscreen) {
            hide()
        } else {
            show()
        }
    }

    private fun hide() {
        // Hide UI first
        supportActionBar?.hide()
        fullscreenContentControls.visibility = View.GONE
        isFullscreen = false

        // Schedule a runnable to remove the status and navigation bar after a delay
        hideHandler.removeCallbacks(showPart2Runnable)
        hideHandler.postDelayed(hidePart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    private fun show() {
        // Show the system bar
        fullscreenContent.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        isFullscreen = true

        // Schedule a runnable to display UI elements after a delay
        hideHandler.removeCallbacks(hidePart2Runnable)
        hideHandler.postDelayed(showPart2Runnable, UI_ANIMATION_DELAY.toLong())
    }

    /**
     * Schedules a call to hide() in [delayMillis], canceling any
     * previously scheduled calls.
     */
    private fun delayedHide(delayMillis: Int) {
        hideHandler.removeCallbacks(hideRunnable)
        hideHandler.postDelayed(hideRunnable, delayMillis.toLong())
    }

    companion object {
        /**
         * Whether or not the system UI should be auto-hidden after
         * [AUTO_HIDE_DELAY_MILLIS] milliseconds.
         */
        private const val AUTO_HIDE = true

        /**
         * If [AUTO_HIDE] is set, the number of milliseconds to wait after
         * user interaction before hiding the system UI.
         */
        private const val AUTO_HIDE_DELAY_MILLIS = 3000

        /**
         * Some older devices needs a small delay between UI widget updates
         * and a change of the status and navigation bar.
         */
        private const val UI_ANIMATION_DELAY = 300
    }
}