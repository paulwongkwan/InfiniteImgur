package prism6.com.infiniteimgur.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

class Gallery(
    val data: List<GalleryModel>,
    val status: Int,
    val success: Boolean
)

@Entity(tableName = "gallery")
data class GalleryModel(
//    val animated: Boolean,
//    val bandwidth: Long,
//    val comment_count: Int,
//    val cover: String,
//    val cover_height: Int,
//    val cover_width: Int,
//    val datetime: Int,
//    val description: String,
//    val downs: Int,
//    val edited: Int,
//    val favorite: Boolean,
//    val favorite_count: Int,
//    val gifv: String,
//    val has_sound: Boolean,
//    val height: Int,
//    val hls: String,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "key")
    val key: Int,
    val id: String,
//    val images: List<Image>,
//    val images_count: Int,
//    val in_gallery: Boolean,
//    val in_most_viral: Boolean,
//    val include_album_ads: Boolean,
//    val is_ad: Boolean,
//    val is_album: Boolean,
//    val layout: String,
    val link: String,
//    val looping: Boolean,
//    val mp4: String,
//    val mp4_size: Int,
//    val nsfw: Boolean,
//    val points: Int,
//    val privacy: String,
//    val processing: ProcessingX,
//    val score: Int,
//    val section: String,
//    val size: Int,
//    val tags: List<Tag>,
    val title: String,
//    val topic: Any,
//    val topic_id: Int,
//    val type: String,
//    val ups: Int,
//    val views: Int,
//    val vote: Any,
//    val width: Int
)

data class Image(
    val animated: Boolean,
    val bandwidth: Long,
    val comment_count: Any,
    val datetime: Int,
    val description: String,
    val downs: Any,
    val edited: String,
    val favorite: Boolean,
    val favorite_count: Any,
    val gifv: String,
    val has_sound: Boolean,
    val height: Int,
    val hls: String,
    val id: String,
    val in_gallery: Boolean,
    val in_most_viral: Boolean,
    val is_ad: Boolean,
    val link: String,
    val mp4: String,
    val mp4_size: Int,
    val nsfw: Any,
    val points: Any,
    val processing: Processing,
    val score: Any,
    val section: Any,
    val size: Int,
    val tags: List<Any>,
    val title: Any,
    val type: String,
    val ups: Any,
    val views: Int,
    val vote: Any,
    val width: Int
)

data class ProcessingX(
    val status: String
)

data class Tag(
    val accent: String,
    val background_hash: String,
    val background_is_animated: Boolean,
    val description: String,
    val display_name: String,
    val followers: Int,
    val following: Boolean,
    val is_promoted: Boolean,
    val is_whitelisted: Boolean,
    val logo_destination_url: Any,
    val logo_hash: Any,
    val name: String,
    val thumbnail_hash: Any,
    val thumbnail_is_animated: Boolean,
    val total_items: Int
)

data class Processing(
    val status: String
)