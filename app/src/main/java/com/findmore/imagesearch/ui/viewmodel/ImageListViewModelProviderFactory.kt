package com.findmore.imagesearch.ui.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.findmore.imagesearch.repository.NewsRepository


class ImageListViewModelProviderFactory(
    val app: Application,
    val imageRepository: NewsRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageListViewModel(app,imageRepository) as T
    }

}