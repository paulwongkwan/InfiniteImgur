package prism6.com.infiniteimgur.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import prism6.com.infiniteimgur.databinding.ImageItemLayoutBinding
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.viewmodel.GalleryViewModel

class GalleryViewHolder(private val binding: ImageItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: GalleryViewModel, item: GalleryModel) {
        binding.viewModel = viewModel
        binding.galleryModel = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): GalleryViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ImageItemLayoutBinding.inflate(layoutInflater, parent, false)
            return GalleryViewHolder(binding)
        }
    }
}