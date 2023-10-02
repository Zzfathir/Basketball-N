package com.fathirdev.basketball_n

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fathirdev.basketball_n.data.NewsResponse
import com.fathirdev.basketball_n.data.network.ApiClient
import retrofit2.Call
import retrofit2.Callback

class NewsViewModel : ViewModel() {
    private var _basketballNews = MutableLiveData<NewsResponse>()
    val basketballNews get() = _basketballNews as LiveData<NewsResponse>
    private var _playersNews = MutableLiveData<NewsResponse>()
    val playersNews get() = _playersNews as LiveData<NewsResponse>
    private var _nbaNews = MutableLiveData<NewsResponse>()
    val nbaNews get() = _nbaNews as LiveData<NewsResponse>

    private var _searchNews = MutableLiveData<NewsResponse>()
    val searchNews get() = _searchNews as LiveData<NewsResponse>

    fun getBasketballNews() {
        ApiClient.getApiService().getBasketballNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _basketballNews.postValue(response.body())
                } else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }
    fun getPlayersNews() {
        ApiClient.getApiService().getPlayersNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _playersNews.postValue(response.body())
                } else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }
    fun getNbaNews() {
        ApiClient.getApiService().getNbaNews().enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: retrofit2.Response<NewsResponse>) {
                if (response.isSuccessful) {
                    Log.i("ViewModel", "onResponse: ${response.body()}")
                    _nbaNews.postValue(response.body())
                } else Log.e(
                    "ViewModel",
                    "onFailedCall: Call error with Http Status Code : " + response.code()
                )
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("ViewModel", "onFailure: " + t.localizedMessage)
            }

        })
    }

    fun searchNews(query: String) {
        ApiClient.getApiService().searchNews(query).enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: retrofit2.Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    _searchNews.postValue(response.body())
                } else Log.e("NewsViewModels", "onResponse: ${response.message()}")
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e("NewsViewModel", "onFailure: ${t.localizedMessage}")
            }

        })
    }
}