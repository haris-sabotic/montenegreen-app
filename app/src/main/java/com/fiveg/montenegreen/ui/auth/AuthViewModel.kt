package com.fiveg.montenegreen.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiveg.montenegreen.api.Api
import com.fiveg.montenegreen.api.requests.LoginRequest
import com.fiveg.montenegreen.api.requests.RegisterRequest
import com.fiveg.montenegreen.api.responses.LoginResponse
import com.fiveg.montenegreen.api.responses.RegisterResponse
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.StringReader


class AuthViewModel : ViewModel() {
    private val _token = MutableLiveData<String>()
    val token: LiveData<String> = _token

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _errors = MutableLiveData<JsonObject>()
    val errors: LiveData<JsonObject> = _errors

    fun login(email: String, password: String) {
        Api.service.login(LoginRequest(email, password)).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                when (response.code()) {
                    200 -> {
                        _token.postValue(response.body()!!.token)
                    }

                    else -> {
                        val body = JsonParser
                            .parseReader(StringReader(response.errorBody()!!.string()))
                            .asJsonObject

                        if (body.has("errors")) {
                            _errors.postValue(body["errors"].asJsonObject)
                        } else {
                            _message.postValue(body["message"].asString)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _message.postValue(t.message)
            }
        })
    }

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        Api.service.register(RegisterRequest(name, email, password, confirmPassword)).enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                when (response.code()) {
                    201 -> {
                        _token.postValue(response.body()!!.token)
                    }

                    else -> {
                        val body = JsonParser
                            .parseReader(StringReader(response.errorBody()!!.string()))
                            .asJsonObject

                        if (body.has("errors")) {
                            _errors.postValue(body["errors"].asJsonObject)
                        } else {
                            _message.postValue(body["message"].asString)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                _message.postValue(t.message)
            }
        })
    }
}