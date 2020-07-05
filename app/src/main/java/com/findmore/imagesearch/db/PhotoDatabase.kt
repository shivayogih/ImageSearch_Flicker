package  com.findmore.imagesearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

import com.findmore.imagesearch.models.PhotosList
import com.findmore.imagesearch.models.RecentPhotoInfo


/**
 * Created by Shivayogi Hiremath on 04,July,2020
 *
 */

@Database(
    entities = [RecentPhotoInfo::class],
    version = 1,
    exportSchema = false
)
abstract class PhotoDatabase : RoomDatabase() {
    abstract fun getSearchDao(): SearchDAO
}