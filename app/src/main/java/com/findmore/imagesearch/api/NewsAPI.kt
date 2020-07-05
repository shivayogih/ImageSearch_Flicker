package com.findmore.imagesearch.api


import com.findmore.imagesearch.models.PhotosList
import com.findmore.imagesearch.models.SearchResponse
import com.findmore.imagesearch.util.Constants.Companion.API_KEY
import com.findmore.imagesearch.util.Constants.Companion.FORMAT
import com.findmore.imagesearch.util.Constants.Companion.METHOD_GET_RECENT
import com.findmore.imagesearch.util.Constants.Companion.METHOD_GET_SEARCH
import com.findmore.imagesearch.util.Constants.Companion.NO_JSONCALLBACK
import com.findmore.imagesearch.util.Constants.Companion.QUERY_PAGE_SIZE

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {


    @GET("rest/")
    suspend fun getBreakingNews(
        @Query("method") method: String=METHOD_GET_RECENT,
        @Query("nojsoncallback") noJsonCallback: Int=NO_JSONCALLBACK,
        @Query("format") format: String=FORMAT,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("per_page") per_page: String=QUERY_PAGE_SIZE.toString(),
        @Query("page") pageNumber: Int =1
    ): Response<PhotosList>

    @GET("rest/")
    suspend fun searchForNews(
        @Query("method") method: String=METHOD_GET_SEARCH,
        @Query("nojsoncallback") noJsonCallback: Int=NO_JSONCALLBACK,
        @Query("format") format: String=FORMAT,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("per_page") per_page: String=QUERY_PAGE_SIZE.toString(),
        @Query("text") searchQuery: String,
        @Query("page") pageNumber: Int =1

    ): Response<SearchResponse>
}