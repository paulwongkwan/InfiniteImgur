package prism6.com.infiniteimgur.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.github.piasy.biv.indicator.progresspie.ProgressPieIndicator
import com.github.piasy.biv.view.GlideImageViewFactory
import prism6.com.infiniteimgur.databinding.BigImageViewerBinding


class ImageDialog : DialogFragment() {
    companion object{
        fun newInstance(url : String) = ImageDialog().apply {
            arguments = Bundle(1).apply {
                putString("url", url)
            }
        }
    }

    lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);

        url = arguments?.getString("url").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: BigImageViewerBinding = BigImageViewerBinding.inflate(
            inflater, container, false
        )
        val view: View = binding.getRoot()
        binding.mBigImage.setImageViewFactory(GlideImageViewFactory())
        binding.mBigImage.setProgressIndicator(ProgressPieIndicator())
        binding.mBigImage.showImage(Uri.parse(url))
        binding.closeBtn.setOnClickListener { dismiss() }
        return view
    }
}
