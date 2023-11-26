package com.fiveg.montenegreen.ui.blog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.models.BlogpostModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BlogViewModel : ViewModel() {
    private val _blog = MutableLiveData<ArrayList<BlogpostModel>>()
    val blog: LiveData<ArrayList<BlogpostModel>> = _blog

    fun loadBlog() {
        Api.service.blog().enqueue(object :
            Callback<ArrayList<BlogpostModel>> {
            override fun onResponse(call: Call<ArrayList<BlogpostModel>>, response: Response<ArrayList<BlogpostModel>>) {
                if (response.code() == 200) {
                    _blog.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<ArrayList<BlogpostModel>>, t: Throwable) {
            }
        })
    }
}