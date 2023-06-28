package com.rai.matchappkotlin.UI.MatchScreen

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.Network.MatchService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class MatchScreenViewModel : ViewModel() {

    val listOfMatch=MutableLiveData<ArrayList<MatchDetail>>()





    fun useCoroutineMatchOne(getMatchApi:MatchService,mContext:Context){

        // launching a new coroutine
        viewModelScope.launch {
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
                        val currentMatchs=ArrayList<MatchDetail>()
                        currentMatchs.add(matchDetail)
                        listOfMatch.postValue(currentMatchs)
                       // Log.d("TAG", "onResponse: "+matchDetail.toString())

                    } else {
                        // API error
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(mContext, "Something went wrong...", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }

            })

        }

    }
    fun useCoroutineMatchTwo(getMatchApi:MatchService,mContext:Context){

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
                        val currentMatchs=ArrayList<MatchDetail>()
                        currentMatchs.add(matchDetail)
                        listOfMatch.postValue(currentMatchs)


                    } else {
                        // API error
                    }
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(mContext, "Something went wrong...", Toast.LENGTH_SHORT).show()
                   t.printStackTrace()
                }

            })

        }

    }



  /*  fun loadDataFromAssets(mContext:Context){
        lateinit var jsonString: String
        try {
            jsonString = mContext.assets.open("data/dummyData.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
           ioException.printStackTrace()
        }
        val gson = Gson()
        val matchDetail = gson.fromJson(jsonString, MatchDetail::class.java)
        val currentMatchs=ArrayList<MatchDetail>()
        currentMatchs.add(matchDetail)
        listOfMatch.postValue(currentMatchs)
    }*/
}