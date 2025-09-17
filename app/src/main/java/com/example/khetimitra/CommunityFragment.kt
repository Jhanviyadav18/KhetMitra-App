package com.example.khetimitra

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment() {

    private lateinit var cropAdapter: CropAdapter

    private val cropInfoMap = mapOf(
        "Rice" to """
        🌾 Rice Cultivation Guide:

        Rice is a staple food crop widely cultivated in waterlogged fields.
        - Time Period: June to October
        - Growth Duration: 120-150 days
        - Temperature: 25°C - 35°C
        - Moisture: High (Waterlogged fields)
        - NPK: N-120kg, P-60kg, K-40kg per hectare
        - pH: 5.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Prepare well-leveled fields with good bunding. Soak seeds for 24 hours before sowing. 
        Use the System of Rice Intensification (SRI) or traditional methods. Apply fertilizers 
        during early tillering and flowering stages. Maintain continuous standing water until harvest. 
        Apply recommended pest and disease control practices. Harvest when grains turn golden yellow.
    """.trimIndent(),

        "Wheat" to """
        🌾 Wheat Cultivation Guide:

        Wheat is a cool-season cereal crop grown mainly in temperate zones.
        - Time Period: November to April
        - Growth Duration: 110-130 days
        - Temperature: 15°C - 25°C
        - Moisture: Moderate
        - NPK: N-120kg, P-60kg, K-40kg per hectare
        - pH: 6.0 - 7.5
        - Conductivity: Moderate

        How to Cultivate:
        Prepare the field by plowing and leveling. Use certified seeds with good germination. 
        Sow seeds at 5–7 cm depth during November. Apply fertilizers in split doses: phosphorus 
        and potassium at sowing, nitrogen in three splits. Maintain row spacing of 20 cm. 
        Irrigate during tillering and grain filling. Apply fungicides to control rust diseases. 
        Harvest when grains are mature and dry.
    """.trimIndent(),

        "Maize" to """
        🌾 Maize Cultivation Guide:

        Maize is a warm-season crop important for food and fodder.
        - Time Period: March to July
        - Growth Duration: 90-120 days
        - Temperature: 21°C - 30°C
        - Moisture: Moderate
        - NPK: N-160kg, P-60kg, K-40kg per hectare
        - pH: 5.5 - 7.0
        - Conductivity: Moderate

        How to Cultivate:
        Select well-drained fertile soil. Plow and level the field. Use high-yield hybrid seeds. 
        Sow seeds in rows spaced 60-75 cm apart. Apply basal phosphorus and potassium at sowing, 
        followed by nitrogen split doses. Irrigate especially during flowering and grain filling stages. 
        Manage weeds manually or with herbicides. Harvest when cobs mature and grains harden.
    """.trimIndent(),

        "Pulses" to """
        🌾 Pulses Cultivation Guide:

        Pulses are a major source of plant-based protein and fix atmospheric nitrogen.
        - Time Period: June to September
        - Growth Duration: 90-120 days
        - Temperature: 20°C - 30°C
        - Moisture: Moderate
        - NPK: N-20kg, P-40kg, K-20kg per hectare
        - pH: 6.0 - 7.5
        - Conductivity: Low

        How to Cultivate:
        Select well-drained loamy soil. Prepare fields by plowing and leveling. Sow seeds at 2–3 cm depth 
        with proper spacing (30 cm row spacing). Apply phosphorus and potassium at sowing; nitrogen 
        is often not required due to nitrogen-fixing properties. Maintain moderate irrigation. Control 
        pests like pod borers and diseases like wilt using organic or chemical measures. Harvest when 
        pods are dry and seeds hard.
    """.trimIndent(),

        "Mustard" to """
        🌾 Mustard Cultivation Guide:

        Mustard is an oilseed crop grown in temperate regions.
        - Time Period: October to February
        - Growth Duration: 85-110 days
        - Temperature: 10°C - 25°C
        - Moisture: Moderate
        - NPK: N-60kg, P-40kg, K-20kg per hectare
        - pH: 6.0 - 7.5
        - Conductivity: Low

        How to Cultivate:
        Prepare well-drained, fertile soil by plowing. Sow seeds in October at a depth of 2-3 cm, 
        spacing rows 30 cm apart. Apply phosphorus and potassium at sowing. Nitrogen is supplemented 
        during vegetative growth. Irrigate as required. Control aphids and white rust disease. Harvest 
        when seed pods turn brown and seeds rattle inside.
    """.trimIndent(),

        "Sugarcane" to """
        🌾 Sugarcane Cultivation Guide:

        Sugarcane is a perennial crop mainly cultivated in tropical regions.
        - Time Period: February to March
        - Growth Duration: 10-12 months
        - Temperature: 20°C - 32°C
        - Moisture: High
        - NPK: N-200kg, P-60kg, K-60kg per hectare
        - pH: 6.0 - 8.0
        - Conductivity: Moderate

        How to Cultivate:
        Use well-drained fertile loamy soil. Plant sugarcane using setts (stem cuttings) spaced 75 cm 
        apart. Apply basal fertilizers at planting and top dress NPK at 2–3 month intervals. Provide 
        regular irrigation, especially during early growth. Implement proper weed management and 
        pest control. Harvest when stalks turn hard and sugary.
    """.trimIndent(),

        "Potatoes" to """
        🌾 Potatoes Cultivation Guide:

        Potatoes are a tuber crop grown in temperate climates.
        - Time Period: September to December
        - Growth Duration: 90-120 days
        - Temperature: 15°C - 20°C
        - Moisture: Moderate
        - NPK: N-150kg, P-80kg, K-100kg per hectare
        - pH: 5.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Use light, well-drained loamy soil. Cut seed tubers into pieces with at least one eye. Plant 
        at 10–15 cm depth, with 60 cm row spacing. Apply basal phosphorus and potassium, nitrogen 
        in splits. Ensure regular irrigation, especially during tuber formation. Apply fungicides 
        to control late blight disease. Harvest when plants yellow and dry.
    """.trimIndent(),

        "Millets" to """
        🌾 Millets Cultivation Guide:

        Millets are hardy cereal crops suited to arid and semi-arid regions.
        - Time Period: June to August
        - Growth Duration: 60-90 days
        - Temperature: 25°C - 35°C
        - Moisture: Low to Moderate
        - NPK: N-30kg, P-30kg, K-20kg per hectare
        - pH: 6.0 - 7.0
        - Conductivity: Low

        How to Cultivate:
        Plow soil to break up clods. Sow seeds directly in well-prepared seedbeds. Maintain row 
        spacing of 30 cm. Apply balanced NPK fertilizer at sowing. Millets tolerate poor soil 
        but require proper weeding. Control pests manually or chemically. Harvest when grains are 
        dry and moisture content drops below 12%.
    """.trimIndent(),

        "Cotton" to """
        🌾 Cotton Cultivation Guide:

        Cotton is a major fiber crop grown in tropical and subtropical regions.
        - Time Period: May to September
        - Growth Duration: 150-180 days
        - Temperature: 21°C - 30°C
        - Moisture: Moderate
        - NPK: N-120kg, P-60kg, K-60kg per hectare
        - pH: 6.0 - 8.0
        - Conductivity: Moderate

        How to Cultivate:
        Prepare well-drained soil, plowed and leveled. Use high-yield hybrid seeds. Sow at 2-3 cm 
        depth, with row spacing of 75 cm. Apply phosphorus and potassium during sowing, nitrogen in 
        splits. Regular irrigation especially during flowering. Use pest control measures to avoid 
        bollworms. Harvest when bolls crack and fibers dry.
    """.trimIndent(),

        "Jute" to """
        🌾 Jute Cultivation Guide:

        Jute is a major fiber crop grown in humid tropical areas.
        - Time Period: June to August
        - Growth Duration: 120-150 days
        - Temperature: 24°C - 38°C
        - Moisture: High
        - NPK: N-80kg, P-40kg, K-40kg per hectare
        - pH: 5.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Use fertile alluvial soil. Soak seeds for 24 hours. Sow in well-prepared seedbeds 
        spaced 30 cm apart. Apply NPK fertilizers in split doses. Maintain regular irrigation. 
        Control weeds by manual removal or herbicides. Harvest when stems turn yellowish 
        and fibers are mature.
    """.trimIndent(),

        "Tea" to """
        🌾 Tea Cultivation Guide:

        Tea is a perennial crop mainly grown in hilly regions.
        - Time Period: Year-round
        - Growth Duration: Perennial
        - Temperature: 18°C - 30°C
        - Moisture: High (1500-2500 mm rainfall)
        - NPK: N-200kg, P-75kg, K-125kg per hectare
        - pH: 4.5 - 5.5
        - Conductivity: Low

        How to Cultivate:
        Select well-drained acidic soil. Prepare nursery beds, raise seedlings, and transplant 
        after 6-12 months. Space plants 1.2m apart. Apply NPK fertilizers regularly based on soil test. 
        Ensure frequent pruning to maintain plant health and productivity. Use organic methods to 
        control pests. Hand pluck top two leaves regularly.
    """.trimIndent(),

        "Soybean" to """
        🌾 Soybean Cultivation Guide:

        Soybean is a legume crop important for protein and oil.
        - Time Period: June to September
        - Growth Duration: 90-120 days
        - Temperature: 20°C - 30°C
        - Moisture: Moderate
        - NPK: N-20kg, P-40kg, K-20kg per hectare
        - pH: 6.0 - 7.5
        - Conductivity: Low

        How to Cultivate:
        Use well-prepared loamy soil. Sow seeds at 2–4 cm depth with 30 cm row spacing. 
        Apply phosphorus and potassium fertilizers at sowing; nitrogen is often minimal 
        because soybean fixes nitrogen. Provide moderate irrigation. Control weeds and pests 
        like aphids. Harvest when pods are dry and grains are hard.
    """.trimIndent(),

        "Turmeric" to """
        🌾 Turmeric Cultivation Guide:

        Turmeric is a spice crop with medicinal value.
        - Time Period: June to August
        - Growth Duration: 7-10 months
        - Temperature: 20°C - 30°C
        - Moisture: High
        - NPK: N-80kg, P-60kg, K-80kg per hectare
        - pH: 5.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Prepare well-drained loamy soil with compost. Plant rhizomes at 5–7 cm depth in rows 
        spaced 30 cm apart. Apply balanced NPK fertilizers and organic manure. Provide regular 
        irrigation during early growth. Mulch fields to retain moisture. Control weeds and pests. 
        Harvest after 7–10 months when leaves turn yellow.
    """.trimIndent(),

        "Ginger" to """
        🌾 Ginger Cultivation Guide:

        Ginger is a rhizome crop requiring warm and humid conditions.
        - Time Period: March to June
        - Growth Duration: 8-10 months
        - Temperature: 25°C - 30°C
        - Moisture: High
        - NPK: N-100kg, P-60kg, K-100kg per hectare
        - pH: 5.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Use well-drained sandy loam soil rich in organic matter. Plant rhizome pieces with 
        2–3 buds at 5–7 cm depth in ridges 30 cm apart. Apply organic manure and balanced 
        fertilizers. Provide regular irrigation especially during early growth. Mulch fields to 
        maintain humidity. Control nematodes and fungal diseases. Harvest after 8–10 months 
        when leaves yellow.
    """.trimIndent(),

        "Cardamom" to """
        🌾 Cardamom Cultivation Guide:

        Cardamom is a high-value spice crop grown in shaded areas.
        - Time Period: May to September
        - Growth Duration: Perennial
        - Temperature: 10°C - 35°C
        - Moisture: High
        - NPK: N-200kg, P-150kg, K-200kg per hectare
        - pH: 4.5 - 6.5
        - Conductivity: Low

        How to Cultivate:
        Grow in rich, well-drained soil under partial shade. Plant seedlings 1–1.5 m apart. 
        Apply organic manure and balanced NPK fertilizers regularly. Provide frequent irrigation 
        to maintain soil moisture. Mulch with plant debris. Regularly remove weeds and control 
        pests like thrips and aphids. Harvest ripe capsules by hand every 3–4 weeks.
    """.trimIndent()
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cropdata, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvCrops)
        cropAdapter = CropAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = cropAdapter

        RetrofitClient.instance.getAllCrops().enqueue(object : Callback<List<Crop>> {
            override fun onResponse(call: Call<List<Crop>>, response: Response<List<Crop>>) {
                if (response.isSuccessful) {
                    response.body()?.let { cropList ->
                        cropAdapter.updateCrops(cropList)
                    }
                }
            }

            override fun onFailure(call: Call<List<Crop>>, t: Throwable) {
                Log.e("API_ERROR", t.message.toString())
            }
        })

        val cropList = listOf(
            "Rice", "Wheat", "Maize", "Pulses", "Mustard", "Sugarcane", "Potatoes",
            "Millets", "Cotton", "Jute", "Tea", "Soybean", "Turmeric", "Ginger", "Cardamom"
        )

        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, cropList)
        val cropSelector = view.findViewById<AutoCompleteTextView>(R.id.etSelectCrop)
        val cropDataTv = view.findViewById<TextView>(R.id.tvCropData)

        cropSelector.setAdapter(adapter)
        cropSelector.setDropDownBackgroundResource(android.R.color.white)  // ✅ Add this line
        cropSelector.dropDownHeight = ViewGroup.LayoutParams.WRAP_CONTENT

        cropSelector.setOnClickListener {
            cropSelector.showDropDown()
        }

        cropSelector.setOnItemClickListener { _, _, position, _ ->
            val selectedCrop = adapter.getItem(position)
            cropDataTv.text = cropInfoMap[selectedCrop] ?: "No data available for $selectedCrop"
        }
    }
}