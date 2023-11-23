package com.fiveg.montenegreen.ui.popusti

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiveg.montenegreen.R
import com.fiveg.montenegreen.models.PopustModel
import com.fiveg.montenegreen.util.GlobalData
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.roundToInt

class PopustiRecyclerViewAdapter(
    private val context: Context,
    private val dataSet: ArrayList<PopustModel>
) :
    RecyclerView.Adapter<PopustiRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photo: ImageView
        val name: TextView
        val location: TextView
        val description: TextView
        val fractionLeft: TextView
        val fractionRight: TextView
        val fractionLayout: LinearLayout
        val progress: LinearProgressIndicator

        init {
            photo = view.findViewById(R.id.popust_image)
            name = view.findViewById(R.id.popust_text_name)
            location = view.findViewById(R.id.popust_text_location)
            description = view.findViewById(R.id.popust_text_description)
            fractionLeft = view.findViewById(R.id.popust_text_fraction_left)
            fractionRight = view.findViewById(R.id.popust_text_fraction_right)
            fractionLayout = fractionLeft.parent as LinearLayout
            progress = view.findViewById(R.id.popust_progress)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_popust, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name
        viewHolder.location.text = dataSet[position].location
        viewHolder.description.text = dataSet[position].description

        viewHolder.fractionLeft.text = GlobalData.CURRENT_USER_POINTS.toString()
        viewHolder.fractionRight.text = dataSet[position].points.toString()

        val percentage = 100F * (GlobalData.CURRENT_USER_POINTS.toFloat() / dataSet[position].points.toFloat())
        val progress = minOf(100F, percentage)
        viewHolder.progress.progress = progress.roundToInt()

        viewHolder.fractionLayout.setOnClickListener {
            Toast.makeText(context, "${progress}%", Toast.LENGTH_SHORT).show()
        }

        Glide.with(context)
            .load(dataSet[position].photoUrl)
            .into(viewHolder.photo);
    }

    override fun getItemCount() = dataSet.size

}
