package com.fiveg.montenegreen.ui.leaderboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.models.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LeaderboardViewModel : ViewModel() {
    private val _leaderboard = MutableLiveData<ArrayList<UserModel>>()
    val leaderboard: LiveData<ArrayList<UserModel>> = _leaderboard

    fun loadLeaderboard() {
        Api.service.leaderboard().enqueue(object :
            Callback<ArrayList<UserModel>> {
            override fun onResponse(call: Call<ArrayList<UserModel>>, response: Response<ArrayList<UserModel>>) {
                if (response.code() == 200) {
                    _leaderboard.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<UserModel>>, t: Throwable) {
            }
        })
    }
}