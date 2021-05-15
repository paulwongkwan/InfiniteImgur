package prism6.com.infiniteimgur.model

import androidx.room.*
import com.google.gson.annotations.SerializedName

@Entity(tableName = "gallery")
@TypeConverters(ImagesTypeConverter::class)
class GalleryModel(

    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "key") val key: Int,
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
//	@SerializedName("description") val description : String,
//	@SerializedName("datetime") val datetime : Int,
//    @SerializedName("type") val type: String,
//	@SerializedName("animated") val animated : Boolean,
//	@SerializedName("width") val width : Int,
//	@SerializedName("height") val height : Int,
//	@SerializedName("size") val size : Int,
//	@SerializedName("views") val views : Int,
//	@SerializedName("bandwidth") val bandwidth : Int,
//	@SerializedName("vote") val vote : String,
//	@SerializedName("favorite") val favorite : Boolean,
//	@SerializedName("nsfw") val nsfw : Boolean,
//	@SerializedName("section") val section : String,
//	@SerializedName("account_url") val account_url : String,
//	@SerializedName("account_id") val account_id : Int,
//	@SerializedName("is_ad") val is_ad : Boolean,
//	@SerializedName("in_most_viral") val in_most_viral : Boolean,
//	@SerializedName("has_sound") val has_sound : Boolean,
//	@SerializedName("ad_type") val ad_type : Int,
//	@SerializedName("ad_url") val ad_url : String,
//	@SerializedName("edited") val edited : Int,
//	@SerializedName("in_gallery") val in_gallery : Boolean,
//	@SerializedName("topic") val topic : String,
//	@SerializedName("topic_id") val topic_id : Int,
    @SerializedName("images") val images : List<Image>?,
    @SerializedName("link") val link: String,
//    @SerializedName("mp4") val mp4: String,
//    @SerializedName("gifv") val gifv: String,
//    @SerializedName("hls") val hls: String,
//	@SerializedName("mp4_size") val mp4_size : Int,
//	@SerializedName("looping") val looping : Boolean,
//	@SerializedName("comment_count") val comment_count : Int,
//	@SerializedName("favorite_count") val favorite_count : Int,
//	@SerializedName("ups") val ups : Int,
//	@SerializedName("downs") val downs : Int,
//	@SerializedName("points") val points : Int,
//	@SerializedName("score") val score : Int,
//	@SerializedName("is_album") val is_album : Boolean,

){
//    @SerializedName("images") @Ignore val images : List<Image>? = null
}