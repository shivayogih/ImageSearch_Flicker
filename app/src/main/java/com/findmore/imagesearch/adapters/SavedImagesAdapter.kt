package com.findmore.imagesearch.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.findmore.imagesearch.R
import com.findmore.imagesearch.models.RecentPhotoInfo
import kotlinx.android.synthetic.main.item_feed.view.*
import java.util.*


/**
 * Created by Shivayogi Hiremath on 05,July,2020
 *
 */


class SavedImagesAdapter : RecyclerView.Adapter<SavedImagesAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

      val listImages= ArrayList<RecentPhotoInfo>()

/*    private val differCallback = object : DiffUtil.ItemCallback<RecentPhotoInfo>() {
        override fun areItemsTheSame(oldItem: RecentPhotoInfo, newItem: RecentPhotoInfo): Boolean {
            return oldItem.mId == newItem.mId
        }

        override fun areContentsTheSame(
            oldItem: RecentPhotoInfo,
            newItem: RecentPhotoInfo
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_feed,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listImages.size
    }

    private var onItemClickListener: ((RecentPhotoInfo) -> Unit)? = null

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val imageInfo = listImages[position]
        holder.itemView.apply {
            try {
                val imageSource = ("http://farm" + imageInfo.farm + ".staticflickr.com/"
                        + imageInfo.server+"/" + imageInfo.id + "_" + imageInfo.secret + "_m.jpg")
                Glide.with(this).load(imageSource).into(picture)
            }catch (e:Exception){
                e.printStackTrace()
            }

            imgTitle.text = imageInfo.title
            if (imageInfo.isSaved){
                imgFav.visibility=View.VISIBLE
            }else{
                imgFav.visibility=View.GONE
            }
            setOnClickListener {
                onItemClickListener?.let { it(imageInfo) }
            }
        }
    }

    fun setOnItemClickListener(listener: (RecentPhotoInfo) -> Unit) {
        onItemClickListener = listener
    }

    fun submitList(listImages1: List<RecentPhotoInfo>?,isSaved:Boolean) {
            listImages.addAll(listImages1!!)
    }

    fun clearList() {
        listImages.clear()
        notifyDataSetChanged()
    }
}


