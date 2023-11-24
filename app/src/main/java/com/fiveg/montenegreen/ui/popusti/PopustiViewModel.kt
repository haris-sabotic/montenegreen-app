package com.fiveg.montenegreen.ui.popusti

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.models.PopustModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PopustiViewModel : ViewModel() {
    private val _popusti = MutableLiveData<ArrayList<PopustModel>>()
    val popusti: LiveData<ArrayList<PopustModel>> = _popusti

    fun loadPopusti() {
        Api.service.discounts().enqueue(object :
            Callback<ArrayList<PopustModel>> {
            override fun onResponse(call: Call<ArrayList<PopustModel>>, response: Response<ArrayList<PopustModel>>) {
                if (response.code() == 200) {
                    _popusti.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<PopustModel>>, t: Throwable) {
            }
        })
    }
}