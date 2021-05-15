package prism6.com.infiniteimgur.model

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

class Image (
	@ColumnInfo(name = "id") @SerializedName("id") val id : String,
//	@SerializedName("title") val title : String,
//	@SerializedName("description") val description : String,
//	@SerializedName("datetime") val datetime : Int,
	@ColumnInfo(name = "type") @SerializedName("type") val type : String?,
//	@SerializedName("animated") val animated : Boolean,
//	@SerializedName("width") val width : Int,
//	@SerializedName("height") val height : Int,
//	@SerializedName("size") val size : Int,
//	@SerializedName("views") val views : Int,
//	@SerializedName("bandwidth") val bandwidth : Int,
//	@SerializedName("vote") val vote : String,
//	@SerializedName("favorite") val favorite : Boolean,
//	@SerializedName("nsfw") val nsfw : String,
//	@SerializedName("section") val section : String,
//	@SerializedName("account_url") val account_url : String,
//	@SerializedName("account_id") val account_id : String,
//	@SerializedName("is_ad") val is_ad : Boolean,
//	@SerializedName("in_most_viral") val in_most_viral : Boolean,
//	@SerializedName("has_sound") val has_sound : Boolean,
//	@SerializedName("tags") val tags : List<String>,
//	@SerializedName("ad_type") val ad_type : Int,
//	@SerializedName("ad_url") val ad_url : String,
//	@SerializedName("edited") val edited : Int,
//	@SerializedName("in_gallery") val in_gallery : Boolean,
	@ColumnInfo(name = "link") @SerializedName("link") val link : String,
//	@SerializedName("mp4_size") val mp4_size : Int,
	@Nullable @ColumnInfo(name = "mp4") @SerializedName("mp4") val mp4 : String,
	@Nullable @ColumnInfo(name = "gifv") @SerializedName("gifv") val gifv : String,
	@Nullable @ColumnInfo(name = "hls") @SerializedName("hls") val hls : String,
//	@SerializedName("processing") val processing : Processing,
//	@SerializedName("comment_count") val comment_count : String,
//	@SerializedName("favorite_count") val favorite_count : String,
//	@SerializedName("ups") val ups : String,
//	@SerializedName("downs") val downs : String,
//	@SerializedName("points") val points : String,
//	@SerializedName("score") val score : String
){}