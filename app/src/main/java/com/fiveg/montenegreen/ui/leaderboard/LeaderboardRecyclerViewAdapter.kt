package com.fiveg.montenegreen.ui.leaderboard

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
import com.fiveg.montenegreen.models.UserModel
import com.fiveg.montenegreen.util.GlobalData
import com.google.android.material.progressindicator.LinearProgressIndicator
import kotlin.math.roundToInt

class LeaderboardRecyclerViewAdapter(
    private val dataSet: ArrayList<UserModel>,
) :
    RecyclerView.Adapter<LeaderboardRecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView
        val email: TextView
        val points: TextView

        init {
            name = view.findViewById(R.id.leaderboard_user_text_name)
            email = view.findViewById(R.id.leaderboard_user_text_email)
            points = view.findViewById(R.id.leaderboard_user_text_points)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_leaderboard_user, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = dataSet[position].name
        viewHolder.email.text = dataSet[position].email
        viewHolder.points.text = dataSet[position].points.toString()
    }

    override fun getItemCount() = dataSet.size
}
