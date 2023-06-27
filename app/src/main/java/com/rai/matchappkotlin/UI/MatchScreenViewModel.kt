package com.rai.matchappkotlin.UI

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.Network.MatchService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class MatchScreenViewModel : ViewModel() {

    // TODO: Implement the ViewModel
    val listOfMatch=MutableLiveData<ArrayList<MatchDetail>>()
    val currentMatchs=ArrayList<MatchDetail>()
    fun useCoroutineMatchOne(getMatchApi:MatchService){

        // launching a new coroutine
        GlobalScope.launch {
            val result = getMatchApi.getMatch()
            result.enqueue(object :retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val jsonString = responseBody?.string()
                        //  val jsonObject = JSONObject(jsonString) not using object for now

                        val gson = Gson()
                        val matchDetail = gson.fromJson(jsonString, MatchDetail::class.java)
                        currentMatchs.add(matchDetail)
                        listOfMatch.postValue(currentMatchs)
                       // Log.d("TAG", "onResponse: "+matchDetail.toString())

                    } else {
                        // API error
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

    }
    fun useCoroutineMatchTwo(getMatchApi:MatchService){

        // launching a new coroutine
        GlobalScope.launch {
            val result = getMatchApi.getMatchTwo()
            result.enqueue(object :retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val jsonString = responseBody?.string()
                        //  val jsonObject = JSONObject(jsonString) not using object for now

                        val gson = Gson()
                        val matchDetail = gson.fromJson(jsonString, MatchDetail::class.java)

                        Log.d("TAG", "onResponse: "+matchDetail.toString())

                    } else {
                        // API error
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })

        }

    }
}