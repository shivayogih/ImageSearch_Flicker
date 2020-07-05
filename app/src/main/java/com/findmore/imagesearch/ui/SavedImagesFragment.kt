package com.findmore.imagesearch.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.findmore.imagesearch.R
import com.findmore.imagesearch.adapters.ImageAdapter
import com.findmore.imagesearch.adapters.SavedImagesAdapter
import com.findmore.imagesearch.ui.viewmodel.ImageListViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.saved_images_fragment.*

class SavedImagesFragment : Fragment(R.layout.saved_images_fragment) {


    lateinit var viewModel: ImageListViewModel
    lateinit var imageAdapter: SavedImagesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        imageAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("image", it)
            }
       /*     findNavController().navigate(
               // R.id.action_savedNewsFragment_to_articleFragment,
                bundle
            )*/
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val image = imageAdapter.listImages[position]
                viewModel.deleteArticle(image)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveImage(image)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }

        viewModel.getSavedImages().observe(viewLifecycleOwner, Observer { articles ->
            imageAdapter.submitList(articles,true)
        })
    }

    private fun setupRecyclerView() {
        imageAdapter = SavedImagesAdapter()
        rvSavedNews.apply {
            adapter = imageAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }


}