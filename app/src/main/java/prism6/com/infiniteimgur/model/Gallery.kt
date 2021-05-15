package prism6.com.infiniteimgur.model

import com.google.gson.annotations.SerializedName

class Gallery(
    @SerializedName("data") val data : List<GalleryModel>,
    @SerializedName("success") val success : Boolean,
    @SerializedName("status") val status : Int
)