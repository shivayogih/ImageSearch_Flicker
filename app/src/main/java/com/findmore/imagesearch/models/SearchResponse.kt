package com.findmore.imagesearch.models

import androidx.room.Entity


/**
 * Created by Shivayogi Hiremath on 05,July,2020
 *
 */
@Entity
data class SearchResponse(
    var photos:PhotosList,
    var stat:String?=null
)