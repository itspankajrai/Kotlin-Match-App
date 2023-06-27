package com.rai.matchappkotlin.UI

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
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.UI.Views.MatchDetailRecycler
import com.rai.matchappkotlin.databinding.ActivityMainBinding
import com.rai.matchappkotlin.databinding.FragmentMatchScreenBinding
import kotlin.math.log

class MatchScreen : Fragment() {
    companion object {
        fun newInstance() = MatchScreen()
    }
    private lateinit var viewModel: MatchScreenViewModel
    private var _binding: FragmentMatchScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var getMatchApi: MatchService
    private lateinit var adapter: MatchDetailRecycler
    var dataList=ArrayList<MatchDetail>()
    private lateinit var matchRecyclerView:RecyclerView
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













        viewModel.useCoroutineMatchOne(getMatchApi)

        viewModel.listOfMatch.observe(viewLifecycleOwner){
            Log.d("TAG", "onCreateView: $it")
            dataList.addAll(it)
            adapter.notifyDataSetChanged()

        }











        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}