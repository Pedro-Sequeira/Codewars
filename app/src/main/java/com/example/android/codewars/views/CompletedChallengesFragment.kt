package com.example.android.codewars.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.android.codewars.databinding.FragmentCompletedChallengesBinding
import com.example.android.codewars.models.CompletedChallenge
import com.example.android.codewars.repository.CompletedChallengesDataSource
import com.example.android.codewars.views.adapters.CompletedChallengesAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class CompletedChallengesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
            : View? {

        val binding = FragmentCompletedChallengesBinding.inflate(inflater)

        val arguments = CompletedChallengesFragmentArgs.fromBundle(arguments!!)

        val username = arguments.username

        binding.lifecycleOwner = this

        val adapter = CompletedChallengesAdapter()
        binding.completedChallengesList.adapter = adapter

        val config = PagedList.Config.Builder()
            .setPageSize(50)
            .setPrefetchDistance(30)
            .build()

        val liveData = initializedPagedListBuilder(config, username).build()

        liveData.observe(this, Observer<PagedList<CompletedChallenge>> { pagedList ->
            adapter.submitList(pagedList)
        })

        return binding.root
    }

    private fun initializedPagedListBuilder(config: PagedList.Config, username: String):
            LivePagedListBuilder<Int, CompletedChallenge> {

        val dataSourceFactory = object : DataSource.Factory<Int, CompletedChallenge>() {
            override fun create(): DataSource<Int, CompletedChallenge> {
                return CompletedChallengesDataSource(CoroutineScope(Dispatchers.IO), username)
            }
        }
        return LivePagedListBuilder<Int, CompletedChallenge>(dataSourceFactory, config)
    }
}