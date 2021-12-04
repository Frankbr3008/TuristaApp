package com.cubidevs.dccomics.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.cubidevs.dccomics.databinding.FragmentListBinding
import com.cubidevs.dccomics.main.MainActivity
import com.cubidevs.dccomics.model.Guatape
import com.cubidevs.dccomics.model.SitioItem
import com.google.gson.Gson

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private lateinit var sitiosAdapter: GuatapeAdapter
    private lateinit var listSitios: ArrayList<SitioItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listBinding = FragmentListBinding.inflate(inflater, container, false)

        return listBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideIcon()
        listSitios = loadMockSitiosFromJson()
        sitiosAdapter = GuatapeAdapter(listSitios, onItemClicked = { onSitioClicked(it) } )

        listBinding.superheroesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = sitiosAdapter
            setHasFixedSize(false)
        }

    }

    private fun onSitioClicked(superheroe: SitioItem) {
       findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailFragment(superheroe = superheroe))
    }

    private fun loadMockSitiosFromJson(): ArrayList<SitioItem> {
        val sitioString: String = context?.assets?.open("guatape.json")?.bufferedReader().use { it!!.readText() } //TODO reparar !!
        val gson = Gson()
        val sitioList = gson.fromJson(sitioString, Guatape::class.java)
        return sitioList
    }
}