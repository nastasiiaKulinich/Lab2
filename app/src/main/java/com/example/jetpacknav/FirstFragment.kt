package com.example.jetpacknav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jetpacknav.databinding.FragmentFirstBinding

class FirstFragment : Fragment(), CountryAdapter.Listener {

    private var _binding : FragmentFirstBinding? = null
    private val binding get() = _binding!!

    var countries = ArrayList<Country>()
    lateinit var adapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var ukraine = Country("Ukraine", "Kyiv",getString(R.string.UkraineInfo) , getString(R.string.kyiv))
        var germany = Country("Germany", "Berlin",getString(R.string.GermanyInfo), getString(R.string.berlin))
        var poland = Country ("Poland", "Warsaw", getString(R.string.PolandInfo),getString(R.string.warsaw))

        countries.add(ukraine)
        countries.add(germany)
        countries.add(poland)
        adapter = CountryAdapter(this ,countries, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.rV.layoutManager = layoutManager
        binding.rV.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onClick(itemView: Int) {
        val bundle = bundleOf(SecondFragment.numberInArray to itemView,
            SecondFragment.country to countries.get(itemView).name,
            SecondFragment.capital to countries.get(itemView).capital,
            SecondFragment.countryInfo to countries.get(itemView).informationAboutCountry,
            SecondFragment.url to countries.get(itemView).photo)
        findNavController().navigate(R.id.action_firstFragment_to_secondFragment, bundle)
    }


}

