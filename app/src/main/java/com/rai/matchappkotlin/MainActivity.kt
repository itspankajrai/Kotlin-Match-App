package com.rai.matchappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.Network.MatchService
import com.rai.matchappkotlin.Network.RetrofitHelper
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var getMatchApi:MatchService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         getMatchApi = RetrofitHelper.getInstance().create(MatchService::class.java)


        useCoroutine();
    }
    fun useCoroutine(){
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