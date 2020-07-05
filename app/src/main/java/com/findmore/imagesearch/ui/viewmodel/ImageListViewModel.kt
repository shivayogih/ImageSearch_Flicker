package com.findmore.imagesearch.ui.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.findmore.imagesearch.BaseApplication
import com.findmore.imagesearch.models.PhotosList
import com.findmore.imagesearch.models.RecentPhotoInfo
import com.findmore.imagesearch.models.SearchResponse
import com.findmore.imagesearch.repository.NewsRepository
import com.findmore.imagesearch.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ImageListViewModel(app: Application, val newsRepository: NewsRepository) : AndroidViewModel(app) {



    val searchNews: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: PhotosList? = null

    fun searchImages(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        try {
            if(hasInternetConnection()) {
                val response = newsRepository.searchImages(searchQuery, searchNewsPage)
                searchNews.postValue(handleSearchImagesResponse(response))
            } else {
                searchNews.postValue(Resource.Error("No internet connection"))
            }
        } catch(t: Throwable) {
            when(t) {
                is IOException -> searchNews.postValue(Resource.Error("Network Failure"))
                else -> searchNews.postValue(Resource.Error("Conversion Error"))
            }
        }
    }

    private fun handleSearchImagesResponse(response: Response<SearchResponse>) : Resource<SearchResponse> {
        if(response.isSuccessful) {
            response.body().let { resultResponse ->
                searchNewsPage++
                return if (resultResponse!!.stat=="ok"){
                    Resource.Success(resultResponse)
                }else{
                    Resource.Error(response.message())
                }
            }
        }
        return Resource.Error(response.message())
    }


    fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<BaseApplication>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val activeNetwork = connectivityManager.activeNetwork ?: return false
            val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
            return when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.activeNetworkInfo?.run {
                return when(type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
        return false
    }



    fun saveImage(photo: RecentPhotoInfo) = viewModelScope.launch {
        newsRepository.upsert(photo)
    }

    fun getSavedImages() = newsRepository.getSavedImages()

    fun deleteArticle(image: RecentPhotoInfo) = viewModelScope.launch {
        newsRepository.deleteArticle(image)
    }



}