package com.example.khetimitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khetimitra.adapter.MarketAdapter
import com.example.khetimitra.network.CommodityRecord
import com.example.khetimitra.utils.IndianDistricts
import kotlin.random.Random

class MarketFragment : Fragment() {

    private lateinit var etState: AutoCompleteTextView
    private lateinit var etDistrict: AutoCompleteTextView
    private lateinit var etCommodity: AutoCompleteTextView
    private lateinit var btnShowPrices: Button
    private lateinit var rvMarketResults: RecyclerView
    private lateinit var marketAdapter: MarketAdapter
    private var recordList: MutableList<CommodityRecord> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        etState = view.findViewById(R.id.etSelectState)
        etDistrict = view.findViewById(R.id.etSelectDistrict)
        etCommodity = view.findViewById(R.id.etSelectCommodity)
        btnShowPrices = view.findViewById(R.id.btnShowPrices)
        rvMarketResults = view.findViewById(R.id.rvMarketResults)

        rvMarketResults.layoutManager = LinearLayoutManager(requireContext())
        marketAdapter = MarketAdapter(recordList)
        rvMarketResults.adapter = marketAdapter

        loadDropdownValues()

        // Update district dropdown when state is selected
        etState.setOnItemClickListener { _, _, position, _ ->
            val selectedState = etState.adapter.getItem(position).toString()
            updateDistricts(selectedState)
        }

        btnShowPrices.setOnClickListener {
            val state = etState.text.toString().trim()
            val district = etDistrict.text.toString().trim()
            val commodity = etCommodity.text.toString().trim()

            if (state.isEmpty() || district.isEmpty() || commodity.isEmpty()) {
                Toast.makeText(requireContext(), "Please select state, district, and commodity", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            showPrice(state, district, commodity)
        }
    }

    private fun loadDropdownValues() {
        val states = IndianDistricts.stateDistrictMap.keys.toList()

        val commodities = listOf(
            "Rice", "Wheat", "Maize", "Pulses", "Mustard",
            "Cotton", "Sugarcane", "Tea", "Coffee", "Groundnut",
            "Millets", "Barley", "Tobacco", "Potato", "Tomato"
        )

        etState.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, states))
        etState.dropDownHeight = 600
        etState.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) etState.showDropDown()
        }

        etCommodity.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, commodities))
        etCommodity.dropDownHeight = 600
        etCommodity.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) etCommodity.showDropDown()
        }
    }

    private fun updateDistricts(state: String) {
        val districts = IndianDistricts.stateDistrictMap[state] ?: listOf()
        etDistrict.setAdapter(ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districts))
        etDistrict.dropDownHeight = 600
        etDistrict.setText("") // Clear previous selection
        etDistrict.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) etDistrict.showDropDown()
        }
    }

    private fun showPrice(state: String, district: String, commodity: String) {
        val minPrice = Random.nextInt(1500, 2500)
        val maxPrice = Random.nextInt(2501, 5000)
        val modalPrice = Random.nextInt(minPrice, maxPrice)

        recordList.clear()
        recordList.add(
            CommodityRecord(
                state = state,
                district = district,
                market = "Local Mandi",          // Dummy market
                commodity = commodity,
                variety = "Standard",
                arrival_date = "Today",
                min_price = "₹$minPrice",
                max_price = "₹$maxPrice",
                modal_price = "₹$modalPrice"
            )
        )
        marketAdapter.updateData(recordList)
    }
}
