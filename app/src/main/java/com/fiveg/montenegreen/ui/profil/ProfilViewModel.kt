package com.fiveg.montenegreen.ui.profil

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.models.UserModel
import com.fiveg.montenegreen.models.ZadatakModel
import com.fiveg.montenegreen.util.GlobalData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfilViewModel : ViewModel() {
    private val _zadaci = MutableLiveData<ArrayList<ZadatakModel>>()
    val zadaci: LiveData<ArrayList<ZadatakModel>> = _zadaci

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

    fun loadUserCompletedZadaci(bearerToken: String) {
        Api.service.userTasks("Bearer $bearerToken").enqueue(object : Callback<ArrayList<ZadatakModel>> {
            override fun onResponse(call: Call<ArrayList<ZadatakModel>>, response: Response<ArrayList<ZadatakModel>>) {
                if (response.code() == 200) {
                    _zadaci.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<ZadatakModel>>, t: Throwable) {
            }
        })
    }

    fun loadUserData(bearerToken: String) {
        Api.service.user("Bearer $bearerToken").enqueue(object : Callback<UserModel> {
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == 200) {
                    _user.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
            }
        })
    }
}