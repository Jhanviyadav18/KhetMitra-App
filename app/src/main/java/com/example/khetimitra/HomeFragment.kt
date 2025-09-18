package com.example.khetimitra

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.khetimitra.SensorModels.SensorApiResponse
import com.example.khetimitra.SensorModels.SensorData
import com.example.khetimitra.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var bannerAdapter: BannerAdapter
    private var recommendationScrollView: HorizontalScrollView? = null
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private var scrollPos = 0

    // --- Sensor Live Data ---
    private var sensorData: SensorData? = null
    private val sensorHandler = Handler(Looper.getMainLooper())
    private val sensorInterval = 5000L
    private val sensorRunnable = object : Runnable {
        override fun run() {
            fetchSensorData()
            sensorHandler.postDelayed(this, sensorInterval)
        }
    }

    // --- Banner & Recommendation Runnables ---
    private val bannerRunnable = object : Runnable {
        override fun run() {
            if (::bannerAdapter.isInitialized && bannerAdapter.itemCount > 0) {
                currentPage = (currentPage + 1) % bannerAdapter.itemCount
                bannerViewPager.setCurrentItem(currentPage, true)
                handler.postDelayed(this, 3000)
            }
        }
    }

    private val recommendationScrollRunnable = object : Runnable {
        override fun run() {
            recommendationScrollView?.let { scrollView ->
                val container = scrollView.getChildAt(0)
                if (container != null) {
                    scrollPos += 5
                    scrollView.smoothScrollTo(scrollPos, 0)
                    if (scrollPos >= container.width) scrollPos = 0
                }
                handler.postDelayed(this, 50)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_home, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // --- Help Icon ---
        view.findViewById<TextView>(R.id.helpText)?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HelpFragment())
                .addToBackStack(null)
                .commit()
        }

        // --- Menu Icon ---
        view.findViewById<ImageView>(R.id.menuIcon)?.setOnClickListener { showMenu(it) }

        // --- Banner Setup ---
        bannerViewPager = view.findViewById(R.id.bannerViewPager)
        val bannerImages = listOf(
            R.drawable.banner1,
            R.drawable.banner2,
            R.drawable.banner3,
            R.drawable.banner4
        )
        bannerAdapter = BannerAdapter(bannerImages)
        bannerViewPager.adapter = bannerAdapter

        // --- Recommendation Scroll ---
        recommendationScrollView = view.findViewById(R.id.recommendationScrollView)

        // --- Floating Action Buttons ---
        view.findViewById<ImageButton>(R.id.btnCall)?.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+1234567890"))
            startActivity(intent)
        }

        view.findViewById<ImageButton>(R.id.btnShare)?.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app!")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

        view.findViewById<ImageButton>(R.id.btnChat)?.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.fragmentContainer, ChatFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        // --- Live Field Diagnosis Section ---
        view.findViewById<LinearLayout>(R.id.icon_camera_box)?.setOnClickListener { openCamera(101) }
        view.findViewById<LinearLayout>(R.id.icon_diagnose_box)?.setOnClickListener {
            Toast.makeText(requireContext(), "See Diagnose clicked", Toast.LENGTH_SHORT).show()
        }
        view.findViewById<LinearLayout>(R.id.icon_medicine_box)?.setOnClickListener {
            Toast.makeText(requireContext(), "Get Medicine clicked", Toast.LENGTH_SHORT).show()
        }

        // --- Take a picture button ---
        view.findViewById<Button>(R.id.btn_take_picture)?.setOnClickListener { openCamera(100) }

        // --- Start Live Sensor Fetch ---
        sensorHandler.post(sensorRunnable)
    }

    private fun fetchSensorData() {
        RetrofitInstance.api.getLatestSensorData().enqueue(object : Callback<SensorApiResponse> {
            override fun onResponse(
                call: Call<SensorApiResponse>,
                response: Response<SensorApiResponse>
            ) {
                val apiResponse = response.body()
                if (response.isSuccessful && apiResponse?.success == true) {
                    val newData = apiResponse.data
                    handleSensorAlerts(newData)
                    sensorData = newData
                }
            }

            override fun onFailure(call: Call<SensorApiResponse>, t: Throwable) {
                // Optional: handle error
            }
        })
    }

    private fun handleSensorAlerts(data: SensorData) {
        if (data.rain == 1 && sensorData?.rain != 1) {
            Toast.makeText(requireContext(), "🌧 बारिश शुरू!", Toast.LENGTH_SHORT).show()
        }
        if (data.voltage > 0.4 && (sensorData?.voltage ?: 0f) <= 0.4) {
            Toast.makeText(requireContext(), "🌬 तेज हवा!", Toast.LENGTH_SHORT).show()
        }
        if (data.button == 1 && sensorData?.button != 1) {
            Toast.makeText(requireContext(), "🚨 पशु खेत में!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun openCamera(requestCode: Int) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(cameraIntent, requestCode)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Camera not available", Toast.LENGTH_SHORT).show()
        }
    }

    private lateinit var popup: PopupWindow
    private fun showMenu(anchor: View) {
        val menuItems = listOf("Diagnose", "Crop Data", "Market", "Profile")

        val container = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundResource(R.drawable.popup_menu_bg)
            setPadding(16, 16, 16, 16)
        }

        menuItems.forEach { item ->
            val tv = TextView(requireContext()).apply {
                text = item
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
                textSize = 16f
                setPadding(16, 16, 16, 16)
                setOnClickListener {
                    popup.dismiss()
                    when (item) {
                        "Diagnose" -> parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, HomeFragment())
                            .commit()
                        "Crop Data" -> parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, CommunityFragment())
                            .commit()
                        "Market" -> parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, MarketFragment())
                            .commit()
                        "Profile" -> parentFragmentManager.beginTransaction()
                            .replace(R.id.fragmentContainer, ProfileFragment())
                            .commit()
                    }
                }
            }
            container.addView(tv)
        }

        popup = PopupWindow(container, 550, ViewGroup.LayoutParams.WRAP_CONTENT, true)
        popup.isOutsideTouchable = true
        popup.elevation = 10f
        popup.showAsDropDown(anchor, 0, 10)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == 100 || requestCode == 101) && resultCode == Activity.RESULT_OK) {
            val photo: Bitmap? = data?.extras?.get("data") as? Bitmap
            if (photo != null) {
                Toast.makeText(requireContext(), "Photo Captured Successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Failed to capture photo", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(bannerRunnable, 3000)
        handler.post(recommendationScrollRunnable)
        sensorHandler.post(sensorRunnable)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(bannerRunnable)
        handler.removeCallbacks(recommendationScrollRunnable)
        sensorHandler.removeCallbacks(sensorRunnable)
        recommendationScrollView = null
    }
}
