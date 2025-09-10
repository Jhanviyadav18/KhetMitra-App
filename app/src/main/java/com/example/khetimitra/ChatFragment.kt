package com.example.khetimitra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageAdapter: MessageAdapter
    private val messages = mutableListOf<Message>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ✅ Set all EditText text color to black and hint gray
        (requireActivity().application as KhetiMitraApp).setEditTextColors(this)

        // Back button
        view.findViewById<ImageView>(R.id.backButton).setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HomeFragment())
                .commit()
        }

        recyclerView = view.findViewById(R.id.chatRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        messageAdapter = MessageAdapter(messages)
        recyclerView.adapter = messageAdapter

        val inputMessage = view.findViewById<EditText>(R.id.inputMessage)
        val sendButton = view.findViewById<ImageButton>(R.id.sendButton)

        sendButton.setOnClickListener {
            val text = inputMessage.text.toString()
            if (text.isNotBlank()) {
                // User message
                messages.add(Message(text, true))
                messageAdapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
                inputMessage.text.clear()

                // Simulate AI response
                val aiResponse = "AI: I received \"$text\""
                messages.add(Message(aiResponse, false))
                messageAdapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }
}
