package com.example.goaltracker;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AIChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText message;
    ImageButton send;

    GeminiApi geminiApi;

    ArrayList<ChatMessage> chatList;
    ChatAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aichat);

        // Initialize Views
        recyclerView = findViewById(R.id.chatRecycler);
        message = findViewById(R.id.message);
        send = findViewById(R.id.send);

        // Initialize ArrayList
        chatList = new ArrayList<>();
        geminiApi = new GeminiApi();

        // Default Welcome Message
        chatList.add(new ChatMessage(
                "Hello 👋\nI'm GoalBot.\nHow can I help you today?",
                false));

        // Adapter
        adapter = new ChatAdapter(chatList);

        // RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Send Button
        String mode = getIntent().getStringExtra("mode");

        if(mode == null){
            mode = "";
        }

        if ("goalSuggestion".equals(mode)){

            chatList.add(new ChatMessage(
                    "👋 Hi! I'm your Goal Suggestion AI.\nTell me your goal and I'll break it into tasks.",
                    false));

        }
        else if(mode.equals("goalAnalyser")){

            chatList.add(new ChatMessage(
                    "📊 Welcome to Goal Analyser.\nTell me your goal and your available time.",
                    false));

        }
        else if(mode.equals("successPredictor")){

            chatList.add(new ChatMessage(
                    "🔮 I'll predict whether you're likely to complete your goal.",
                    false));

        }

        send.setOnClickListener(v -> {

            String text = message.getText().toString().trim();

            if(text.isEmpty())
                return;

            // Add user message
            chatList.add(new ChatMessage(text, true));
            adapter.notifyItemInserted(chatList.size() - 1);
            recyclerView.smoothScrollToPosition(chatList.size() - 1);

            message.setText("");

            // Ask Gemini
            geminiApi.generateResponse(text, mode, new GeminiApi.GeminiCallback() {

                @Override
                public void onSuccess(String response) {

                    runOnUiThread(() -> {

                        chatList.add(new ChatMessage(response, false));

                        adapter.notifyItemInserted(chatList.size() - 1);

                        recyclerView.smoothScrollToPosition(chatList.size() - 1);

                    });

                }

                @Override
                public void onFailure(String error) {

                    runOnUiThread(() -> {

                        chatList.add(new ChatMessage("⚠ " + error, false));

                        adapter.notifyItemInserted(chatList.size() - 1);

                        recyclerView.smoothScrollToPosition(chatList.size() - 1);

                    });

                }

            });

        });
    }

}