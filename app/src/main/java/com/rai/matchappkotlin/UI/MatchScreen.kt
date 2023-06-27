package com.rai.matchappkotlin.UI

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rai.matchappkotlin.Network.MatchService
import com.rai.matchappkotlin.Network.RetrofitHelper
import com.rai.matchappkotlin.R
import com.rai.matchappkotlin.databinding.ActivityMainBinding
import com.rai.matchappkotlin.databinding.FragmentMatchScreenBinding

class MatchScreen : Fragment() {
    companion object {
        fun newInstance() = MatchScreen()
    }
    private lateinit var viewModel: MatchScreenViewModel
    private var _binding: FragmentMatchScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var getMatchApi: MatchService
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatchScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        getMatchApi = RetrofitHelper.getInstance().create(MatchService::class.java)
        viewModel = ViewModelProvider(this)[MatchScreenViewModel::class.java]
        viewModel.useCoroutineForData(getMatchApi)






        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}