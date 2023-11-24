package com.fiveg.montenegreen.ui.zadaci

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.models.ZadatakModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ZadaciViewModel : ViewModel() {
    private val _zadaci = MutableLiveData<ArrayList<ZadatakModel>>()
    val zadaci: LiveData<ArrayList<ZadatakModel>> = _zadaci

    fun loadZadaci() {
        Api.service.tasks().enqueue(object :
            Callback<ArrayList<ZadatakModel>> {
            override fun onResponse(call: Call<ArrayList<ZadatakModel>>, response: Response<ArrayList<ZadatakModel>>) {
                if (response.code() == 200) {
                    _zadaci.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<ZadatakModel>>, t: Throwable) {
            }
        })
    }
}