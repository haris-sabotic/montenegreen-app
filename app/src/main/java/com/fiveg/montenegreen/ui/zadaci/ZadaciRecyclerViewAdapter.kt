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
import com.fiveg.montenegreen.util.GlobalData
import com.google.android.material.card.MaterialCardView

class ZadaciRecyclerViewAdapter(
    private val context: Context,
    private val dataSet: ArrayList<ZadatakModel>,
    private val onclick: (ZadatakModel) -> Unit
) :
    RecyclerView.Adapter<ZadaciRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val card: MaterialCardView
        val photo: ImageView
        val name: TextView
        val points: TextView
        val location: TextView
        val description: TextView

        init {
            card = view.findViewById(R.id.zadatak_card)
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
        viewHolder.card.setOnClickListener {
            onclick(dataSet[position])
        }

        viewHolder.name.text = dataSet[position].name
        viewHolder.location.text = dataSet[position].location
        viewHolder.points.text = dataSet[position].points.toString()
        viewHolder.description.text = shortenDescription(dataSet[position].description)

        Glide.with(context)
            .load(GlobalData.PHOTO_URL_PREFIX + dataSet[position].photoUrl)
            .into(viewHolder.photo);
    }

    private fun shortenDescription(description: String): String {
        return if (description.length < 100) {
            description
        } else {
            "${description.substring(0, 97)}..."
        }
    }

    override fun getItemCount() = dataSet.size

}