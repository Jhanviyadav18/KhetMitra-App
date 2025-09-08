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
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var bannerViewPager: ViewPager2
    private lateinit var bannerAdapter: BannerAdapter
    private val handler = Handler(Looper.getMainLooper())
    private var currentPage = 0
    private var scrollPos = 0

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
            val recommendationScrollView = view?.findViewById<HorizontalScrollView>(R.id.recommendationScrollView)
            recommendationScrollView?.let {
                scrollPos += 5
                it.smoothScrollTo(scrollPos, 0)

                if (scrollPos >= it.getChildAt(0).width) {
                    scrollPos = 0
                }

                handler.postDelayed(this, 50)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Banner setup
        bannerViewPager = view.findViewById(R.id.bannerViewPager)
        val bannerImages = listOf(R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4)
        bannerAdapter = BannerAdapter(bannerImages)
        bannerViewPager.adapter = bannerAdapter

        // Bottom-right action buttons
        view.findViewById<ImageButton>(R.id.btnCall).setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+1234567890"))
            startActivity(intent)
        }

        view.findViewById<ImageButton>(R.id.btnShare).setOnClickListener {
            val whatsappIntent = Intent(Intent.ACTION_SEND)
            whatsappIntent.type = "text/plain"
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, "Check out this awesome app!")
            whatsappIntent.setPackage("com.whatsapp")

            try {
                startActivity(whatsappIntent)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "WhatsApp not installed", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<ImageButton>(R.id.btnChat).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ChatFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<ImageView>(R.id.searchIcon).setOnClickListener {
            Toast.makeText(requireContext(), "Search clicked", Toast.LENGTH_SHORT).show()
        }

        view.findViewById<ImageView>(R.id.menuIcon).setOnClickListener {
            Toast.makeText(requireContext(), "Menu clicked", Toast.LENGTH_SHORT).show()
        }

        // Take Picture Button
        view.findViewById<Button>(R.id.btn_take_picture).setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, 100)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
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
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(bannerRunnable)
        handler.removeCallbacks(recommendationScrollRunnable)
    }
}
