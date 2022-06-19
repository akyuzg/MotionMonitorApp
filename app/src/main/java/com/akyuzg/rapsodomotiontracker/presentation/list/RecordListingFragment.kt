package com.akyuzg.rapsodomotiontracker.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.coroutineScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.akyuzg.rapsodomotiontracker.R
import com.akyuzg.rapsodomotiontracker.databinding.RecordListingFragmentBinding
import kotlinx.coroutines.launch

class RecordListingFragment: Fragment() {

    private val viewModel: RecordListingViewModel by hiltNavGraphViewModels(R.id.navigation)

    private var _binding: RecordListingFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = RecordListingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prepareRecyclerView()

        binding.newRecordFab.setOnClickListener {
            viewModel.createRecord()

            val action = RecordListingFragmentDirections
                .actionRecordListingFragmentToReplayFragment()
            view.findNavController().navigate(action)
        }


        val adapter = RecordListAdapter {
            val action = RecordListingFragmentDirections
                .actionRecordListingFragmentToReplayFragment()
            view.findNavController().navigate(action)
        }
        recyclerView.adapter = adapter


        lifecycle.coroutineScope.launch {
            viewModel.allRecords().collect() {
                adapter.submitList(it)
            }
        }
    }

    private fun prepareRecyclerView(){
        recyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        layoutManager.scrollToPosition(0)
        recyclerView.layoutManager = layoutManager
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}