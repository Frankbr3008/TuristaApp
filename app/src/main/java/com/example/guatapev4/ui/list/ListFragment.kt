package com.example.guatapev4.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guatapev4.databinding.FragmentListBinding
import com.example.guatapev4.ui.main.MainActivity
import com.example.guatapev4.model.GuatapeItem


class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var listViewModel: ListViewModel
    private lateinit var GuatapeAdapter: GuatapeAdapter
    private var listGuatape: ArrayList<GuatapeItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater,container, false)
        listViewModel = ViewModelProvider(this)[ListViewModel::class.java]
        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()

        listViewModel.getguatapeFromServer()

        listViewModel.onguatapeLoaded.observe(viewLifecycleOwner, { result ->
            onGuatapeLoadedSubscribe(result)
        })

       GuatapeAdapter = GuatapeAdapter(listGuatape, onItemClicked = { onGuatapeClicked(it) } )


        listBinding.GuatapeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = GuatapeAdapter
            setHasFixedSize(false)
        }
    }

    private fun onGuatapeLoadedSubscribe(result: ArrayList<GuatapeItem>?) {
        result?.let { listGuatape ->
            GuatapeAdapter.appendItems(listGuatape)

        }
    }

    private fun onGuatapeClicked(guatape: GuatapeItem){
        findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(guatape = guatape))
    }

}