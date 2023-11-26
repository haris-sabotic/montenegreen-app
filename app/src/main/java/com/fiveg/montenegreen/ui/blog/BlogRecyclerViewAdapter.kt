package com.fiveg.montenegreen.ui.blog

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.models.BlogpostModel
import com.fiveg.montenegreen.util.GlobalData

class BlogRecyclerViewAdapter(
    private val context: Context,
    private val dataSet: ArrayList<BlogpostModel>
) :
    RecyclerView.Adapter<BlogRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView
        val name: TextView
        val description: TextView

        init {
            photo = view.findViewById(R.id.blogpost_image)
            name = view.findViewById(R.id.blogpost_text_name)
            description = view.findViewById(R.id.blogpost_text_description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_blogpost, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name
        viewHolder.description.text = dataSet[position].description

        Glide.with(context)
            .load(GlobalData.PHOTO_URL_PREFIX + dataSet[position].photoUrl)
            .into(viewHolder.photo);
    }

    override fun getItemCount() = dataSet.size
}