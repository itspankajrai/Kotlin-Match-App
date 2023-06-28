package com.rai.matchappkotlin.UI.TeamsScreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rai.matchappkotlin.R

class TeamScreen : Fragment() {

    companion object {
        fun newInstance() = TeamScreen()
    }

    private lateinit var viewModel: TeamScreenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_team_screen, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamScreenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}