package com.findmore.imagesearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.findmore.imagesearch.R
import com.findmore.imagesearch.db.SearchDAO
import com.findmore.imagesearch.repository.NewsRepository
import com.findmore.imagesearch.ui.viewmodel.ImageListViewModel
import com.findmore.imagesearch.ui.viewmodel.ImageListViewModelProviderFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var searchDAO: SearchDAO
    lateinit var viewModel: ImageListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val newsRepository = NewsRepository(searchDAO)
        val viewModelProviderFactory = ImageListViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(ImageListViewModel::class.java)

    }
}