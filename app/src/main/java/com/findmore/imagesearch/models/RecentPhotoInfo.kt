package com.findmore.imagesearch.models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.io.Serializable
import javax.annotation.Nonnull


/**
 * Created by Shivayogi Hiremath on 04,July,2020
 *
 */

@Entity(tableName = "Photos_table")
data class RecentPhotoInfo(
    var id:String?="",
    var owner:String?=null,
    var secret:String?=null,
    var server:String?=null,
    var farm:Int?=null,
    var title:String?=null,
    var ispublic:Int,
    var isfriend:Int,
    var isfamily:Int,
    var isSaved:Boolean=false
): Serializable{
   @NotNull
    @PrimaryKey(autoGenerate = true)
    var mId:Int?=null
}