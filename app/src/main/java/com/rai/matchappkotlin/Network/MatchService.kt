package com.rai.matchappkotlin.Network

import com.rai.matchappkotlin.DataModels.MatchDetail
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface MatchService {
    @GET("/nzin01312019187360.json")
     fun getMatch() : Call<ResponseBody>

    @GET("/sapk01222019186652.json")
    fun getMatchTwo() : Call<ResponseBody>
}