package com.example.goaltracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<ChatMessage> chatList;

    private final int USER = 1;
    private final int AI = 2;

    public ChatAdapter(ArrayList<ChatMessage> chatList) {
        this.chatList = chatList;
    }

    @Override
    public int getItemViewType(int position) {

        if(chatList.get(position).isUser())
            return USER;

        return AI;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if(viewType==USER){

            View view= LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_user_message,parent,false);

            return new UserHolder(view);

        }

        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ai_message,parent,false);

        return new AIHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ChatMessage chatMessage=chatList.get(position);

        if(holder instanceof UserHolder){

            ((UserHolder) holder).message.setText(chatMessage.getMessage());

        }

        else{

            ((AIHolder) holder).message.setText(chatMessage.getMessage());

        }

    }

    @Override
    public int getItemCount() {

        return chatList.size();

    }

    class UserHolder extends RecyclerView.ViewHolder{

        TextView message;

        public UserHolder(@NonNull View itemView) {

            super(itemView);

            message=itemView.findViewById(R.id.userMessage);

        }

    }

    class AIHolder extends RecyclerView.ViewHolder{

        TextView message;

        public AIHolder(@NonNull View itemView) {

            super(itemView);

            message=itemView.findViewById(R.id.aiMessage);

        }

    }

}