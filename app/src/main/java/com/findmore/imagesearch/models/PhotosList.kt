package  com.findmore.imagesearch.models

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Shivayogi Hiremath on 04,July,2020
 *
 */

@Entity
data class PhotosList(
    var page:Int=0,
    var pages:Int=0,
    var perpage:Int=0,
    var total:String="",
    var photo:MutableList<RecentPhotoInfo>?=null

)