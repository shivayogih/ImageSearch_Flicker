package com.findmore.imagesearch.util

class Constants {
    companion object {

        const val RUNNING_DATABASE_NAME = "image_db"


        //==========API Info and Credentials===============
        const val API_KEY = "062a6c0c49e4de1d78497d13a7dbb360"
        const val BASE_URL = "https://api.flickr.com/services/"
        const val METHOD_GET_SEARCH = "flickr.photos.search"
        const val METHOD_GET_RECENT = "flickr.photos.getRecent"
        const val METHOD_GET_PERSON_INFO = "flickr.people.getInfo"
        const val METHOD_GET_IMAGE_SIZES = "flickr.photos.getSizes"
        const val FORMAT="json"
        const val NO_JSONCALLBACK=1


        const val SEARCH_NEWS_TIME_DELAY = 800L
        const val QUERY_PAGE_SIZE = 5

    }
}