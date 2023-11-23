package com.fiveg.montenegreen.ui.zadaci

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.models.ZadatakModel


class ZadaciRecyclerViewAdapter(
    private val context: Context,
    private val dataSet: ArrayList<ZadatakModel>
) :
    RecyclerView.Adapter<ZadaciRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView
        val name: TextView
        val points: TextView
        val location: TextView
        val description: TextView

        init {
            photo = view.findViewById(R.id.zadatak_image)
            name = view.findViewById(R.id.zadatak_text_name)
            points = view.findViewById(R.id.zadatak_text_points)
            location = view.findViewById(R.id.zadatak_text_location)
            description = view.findViewById(R.id.zadatak_text_description)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_zadatak, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name
        viewHolder.location.text = dataSet[position].location
        viewHolder.points.text = dataSet[position].points.toString()
        viewHolder.description.text = dataSet[position].description

        Glide.with(context)
            .load(dataSet[position].photoUrl)
            .into(viewHolder.photo);
    }

    override fun getItemCount() = dataSet.size

}