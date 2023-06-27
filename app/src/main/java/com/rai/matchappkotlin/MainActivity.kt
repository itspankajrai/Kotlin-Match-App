package com.rai.matchappkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.Network.MatchService
import com.rai.matchappkotlin.Network.RetrofitHelper
import com.rai.matchappkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private var _binding: ActivityMainBinding? = null

    private val binding get() = _binding!!

    lateinit var navController:NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController








    }
    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}