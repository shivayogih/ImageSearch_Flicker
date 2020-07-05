package com.findmore.imagesearch.repository

import com.findmore.imagesearch.api.RetrofitInstance

import com.findmore.imagesearch.db.SearchDAO
import com.findmore.imagesearch.models.RecentPhotoInfo
import com.findmore.imagesearch.util.Constants

class NewsRepository(private val searchDAO: SearchDAO) {

    suspend fun getBreakingNews( pageNumber: Int) = RetrofitInstance.api.getBreakingNews(
        Constants.METHOD_GET_SEARCH,
        Constants.NO_JSONCALLBACK,
        Constants.FORMAT,
        Constants.API_KEY,
        Constants.QUERY_PAGE_SIZE.toString(),
        pageNumber)

    suspend fun searchImages(searchQuery: String, pageNumber: Int) = RetrofitInstance.api.searchForNews(
        Constants.METHOD_GET_SEARCH,
        Constants.NO_JSONCALLBACK,
        Constants.FORMAT,
        Constants.API_KEY,
        Constants.QUERY_PAGE_SIZE.toString(),
        searchQuery,
        pageNumber)

    //=======Accessing the DB==========================
    suspend fun upsert(photoInfos:RecentPhotoInfo) = searchDAO.insertImage(photoInfos)
    suspend fun upsertImages(photosList:List<RecentPhotoInfo>) = searchDAO.insertImages(photosList)
    fun getSavedImages() = searchDAO.getAllSavedImagesByPage()
    suspend fun deleteArticle(photoInfo:RecentPhotoInfo ) = searchDAO.deleteRun(photoInfo)

    //==================================================
}