package com.findmore.imagesearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.findmore.imagesearch.R
import com.findmore.imagesearch.models.RecentPhotoInfo
import com.findmore.imagesearch.ui.viewmodel.ImageListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_image_details.*
import kotlinx.android.synthetic.main.item_feed.view.*


/**
 * A simple [Fragment] subclass.
 * Use the [ImageDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ImageDetailsFragment :Fragment(R.layout.fragment_image_details){

    lateinit var viewModel: ImageListViewModel
    var photo: RecentPhotoInfo?=null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        arguments?.let {
            photo= it.getSerializable("images") as RecentPhotoInfo?
            photo?.apply {
                initDetailsPage(photo!!)
            }
        }

        imgFav.setOnClickListener {
            if (photo!=null){
                photo!!.isSaved=true
                viewModel.saveImage(photo!!)
                imgFav.setImageResource(R.drawable.ic_favorite_active)
                Snackbar.make(view, "Image saved successfully", Snackbar.LENGTH_SHORT).show()
            }
        }
    }




    private fun initDetailsPage(photo: RecentPhotoInfo) {
        try {
            tvTitle.text = photo.title
           if ( viewModel.hasInternetConnection()){
               val imageSource = ("http://farm" + photo.farm + ".staticflickr.com/"
                       + photo.server+"/" + photo.id + "_" + photo.secret + "_m.jpg")
               Glide.with(this).load(imageSource).into(imgPhoto)
           }else{
               Toast.makeText(activity, "An error occured: No Internet connection", Toast.LENGTH_LONG).show()
           }
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}