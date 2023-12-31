package com.rai.matchappkotlin.UI.MatchScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rai.matchappkotlin.DataModels.MatchDetail
import com.rai.matchappkotlin.Network.MatchService
import com.rai.matchappkotlin.Network.RetrofitHelper
import com.rai.matchappkotlin.UI.MatchScreen.Views.MatchDetailRecycler
import com.rai.matchappkotlin.databinding.FragmentMatchScreenBinding

class MatchScreen : Fragment() {
    companion object {
        fun newInstance() = MatchScreen()
    }

    private var _binding: FragmentMatchScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: MatchScreenViewModel

    private lateinit var getMatchApi: MatchService

    var dataList=ArrayList<MatchDetail>()
    private lateinit var matchRecyclerView:RecyclerView
    private lateinit var adapter: MatchDetailRecycler
    var current_match=false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        getMatchApi = RetrofitHelper.getInstance().create(MatchService::class.java)
        viewModel = ViewModelProvider(this)[MatchScreenViewModel::class.java]
        adapter = MatchDetailRecycler(dataList)
        matchRecyclerView=binding.matchRecyclerView
        matchRecyclerView.adapter = adapter
        matchRecyclerView.layoutManager = LinearLayoutManager(context)

        binding.btnNextMatch.setOnClickListener {
            doLoadMatch()
        }
        viewModel.listOfMatch.observe(viewLifecycleOwner){
            if(dataList.size>0){
                dataList.clear()
            }

            dataList.addAll(it)
            adapter.notifyDataSetChanged()

        }


        if(dataList.size==0 || dataList.isEmpty()){
            doLoadMatch()
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
    fun  doLoadMatch(){
        if(current_match){
            context?.let { viewModel.useCoroutineMatchTwo(getMatchApi, it) }
            current_match=false
        }
        else{
            current_match=true
            context?.let { viewModel.useCoroutineMatchOne(getMatchApi, it) }
        }

    }
}