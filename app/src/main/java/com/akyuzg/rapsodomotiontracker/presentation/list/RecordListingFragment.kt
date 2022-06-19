package com.akyuzg.rapsodomotiontracker.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akyuzg.rapsodomotiontracker.R

class RecordListingFragment: Fragment() {

    private val viewModel: RecordListingViewModel by viewModels()

    private lateinit var v: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v = inflater.inflate(R.layout.fragment_record_listing, container, false)
        return v
    }

    private fun prepareRecyclerView(){
        val recyclerView = v.findViewById<RecyclerView>(R.id.recyclerView)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.scrollToPosition(0)
        recyclerView.layoutManager = layoutManager

        //adapter = RecordListAdapter(requireContext(), viewModel)
        //recyclerView.adapter = adapter
    }
}