package com.example.khetimitra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.khetimitra.adapter.MarketAdapter
import com.example.khetimitra.model.MandiResponse
import com.example.khetimitra.network.ApiClient
import com.example.khetimitra.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MarketFragment : Fragment() {

    private lateinit var etState: AutoCompleteTextView
    private lateinit var etDistrict: AutoCompleteTextView
    private lateinit var etCommodity: AutoCompleteTextView
    private lateinit var btnShowPrices: Button
    private lateinit var rvMarketResults: RecyclerView
    private lateinit var marketAdapter: MarketAdapter
    private var recordList: MutableList<MandiResponse.Record> = ArrayList()
    private var allRecords: List<MandiResponse.Record> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_market, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set all EditText / AutoCompleteTextView text color safely
        (requireActivity().application as KhetiMitraApp).setEditTextColors(view)

        // Bind views
        etState = view.findViewById(R.id.etSelectState)
        etDistrict = view.findViewById(R.id.etSelectDistrict)
        etCommodity = view.findViewById(R.id.etSelectCommodity)
        btnShowPrices = view.findViewById(R.id.btnShowPrices)
        rvMarketResults = view.findViewById(R.id.rvMarketResults)

        // Setup RecyclerView
        rvMarketResults.layoutManager = LinearLayoutManager(requireContext())
        marketAdapter = MarketAdapter(recordList)
        rvMarketResults.adapter = marketAdapter

        // Load API data
        loadApiData()

        // Button click -> filter records
        btnShowPrices.setOnClickListener {
            val state = etState.text.toString().trim()
            val district = etDistrict.text.toString().trim()
            val commodity = etCommodity.text.toString().trim()
            filterAndShowPrices(state, district, commodity)
        }

        // 🔹 Help icon click listener
        val helpText = view.findViewById<TextView>(R.id.helpText)
        helpText.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .addToBackStack(null)
                .commit()
        }
    }

    private fun loadApiData() {
        val apiService = ApiClient.getClient().create(ApiService::class.java)

        val call = apiService.getMandiPrices(
            apiKey = "579b464db66ec23bdd000001f7627522131a49696da5c73f758ed0ed",
            format = "json",
            limit = 100
        )

        call.enqueue(object : Callback<MandiResponse> {
            override fun onResponse(call: Call<MandiResponse>, response: Response<MandiResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    allRecords = response.body()!!.records

                    // Populate dropdowns dynamically
                    val states = allRecords.mapNotNull { it.state }.distinct().sorted()
                    val districts = allRecords.mapNotNull { it.district }.distinct().sorted()
                    val commodities = allRecords.mapNotNull { it.commodity }.distinct().sorted()

                    etState.setAdapter(
                        ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, states)
                    )
                    etDistrict.setAdapter(
                        ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, districts)
                    )
                    etCommodity.setAdapter(
                        ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, commodities)
                    )
                } else {
                    Toast.makeText(requireContext(), "API returned empty response", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<MandiResponse>, t: Throwable) {
                Log.e("API_ERROR", "Failed: ${t.message}")
                Toast.makeText(requireContext(), "API Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun filterAndShowPrices(state: String, district: String, commodity: String) {
        val filteredList = allRecords.filter { record ->
            (state.isEmpty() || record.state?.equals(state, ignoreCase = true) == true) &&
                    (district.isEmpty() || record.district?.equals(district, ignoreCase = true) == true) &&
                    (commodity.isEmpty() || record.commodity?.equals(commodity, ignoreCase = true) == true)
        }.toMutableList()

        if (filteredList.isEmpty()) {
            Toast.makeText(requireContext(), "No records found", Toast.LENGTH_SHORT).show()
        }

        marketAdapter.updateData(filteredList)
    }
}
