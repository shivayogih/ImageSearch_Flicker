package com.findmore.imagesearch.db

import androidx.room.TypeConverter
import com.findmore.imagesearch.models.RecentPhotoInfo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 * Created by Shivayogi Hiremath on 04,July,2020
 *
 */
class RoomConverters {


    //list of cutome object in your database
    @TypeConverter
    fun saveAddressList(listOfString: List<RecentPhotoInfo?>?): String? {
        return Gson().toJson(listOfString)
    }

    @TypeConverter
    fun getAddressList(listOfString: String?): List<RecentPhotoInfo?>? {
        return Gson().fromJson(
            listOfString,
            object : TypeToken<List<String?>?>() {}.type
        )
    }
}