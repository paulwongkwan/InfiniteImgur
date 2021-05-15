package prism6.com.infiniteimgur.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import prism6.com.infiniteimgur.model.GalleryModel
import prism6.com.infiniteimgur.viewholder.GalleryViewHolder
import prism6.com.infiniteimgur.viewmodel.GalleryViewModel

class MainGalleryAdapter(private val viewModel: GalleryViewModel) :
    RecyclerView.Adapter<GalleryViewHolder>() {

    var list: List<GalleryModel>? = viewModel.gallerys.value?.data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val item = list!![position]
        holder.bind(viewModel, item)
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }
}