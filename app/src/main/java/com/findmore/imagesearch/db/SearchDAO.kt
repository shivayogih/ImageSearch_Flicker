package  com.findmore.imagesearch.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.findmore.imagesearch.models.PhotosList
import com.findmore.imagesearch.models.RecentPhotoInfo

/**
 * Created by Shivayogi Hiremath on 04,July,2020
 *
 */


@Dao
interface SearchDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(photosLists:List<RecentPhotoInfo>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(photo:RecentPhotoInfo)

    @Delete
    suspend fun deleteRun(photosList: RecentPhotoInfo)

    @Query("SELECT * FROM Photos_table")
    fun getAllSavedImagesByPage(): LiveData<List<RecentPhotoInfo>>

}










