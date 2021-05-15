package prism6.com.infiniteimgur.model

import android.util.Log
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ImagesTypeConverter {
    @TypeConverter
    fun ListToJson(Images: List<Image?>?): String? {
        if (Images == null) return null
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        val json: String = Gson().toJson(Images, type)
        Log.i("JSON", "toJson: $json")
        return if (Images.isEmpty()) null else json
    }

    @TypeConverter
    fun JsonToList(json: String?): List<Image>? {
        val gson = Gson()
        val type: Type = object : TypeToken<List<Image?>?>() {}.type
        return gson.fromJson<List<Image>>(json, type)
    }
}