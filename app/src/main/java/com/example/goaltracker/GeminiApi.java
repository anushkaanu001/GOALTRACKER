package com.example.goaltracker;

import okhttp3.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class GeminiApi {

    // Paste your API key here
    private static final String API_KEY = "AQ.Ab8RN6Im3KX-5e64XekfgdoAZclKvmyRFVTYQXWZzcw_XBkszg";

    // Gemini 2.5 Flash endpoint
    private static final String URL =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key="
                    + API_KEY;

    private final OkHttpClient client = new OkHttpClient();

    public interface GeminiCallback {
        void onSuccess(String response);
        void onFailure(String error);
    }



    private String buildSystemPrompt(String mode) {

        switch (mode) {

            case "goalSuggestion":

                return "You are GoalBot.\n\n" +
                        "You help users create goals.\n" +
                        "Break every goal into small actionable tasks.\n" +
                        "Keep responses short.\n" +
                        "Use bullet points.\n" +
                        "Be encouraging.";

            case "goalAnalyser":

                return "You are GoalBot.\n\n" +
                        "Analyse the user's goal.\n" +
                        "Find weaknesses in the plan.\n" +
                        "Suggest improvements.\n" +
                        "Create a better schedule.\n" +
                        "Be concise.";

            case "successPredictor":

                return "You are GoalBot.\n\n" +
                        "Predict the likelihood of completing the user's goal.\n" +
                        "Explain why.\n" +
                        "Mention strengths and weaknesses.\n" +
                        "Suggest how the user can improve their chances.";

            case "burnoutDetector":

                return "You are GoalBot.\n\n" +
                        "You are a burnout detection assistant.\n" +
                        "Ask about:\n" +
                        "- Sleep\n" +
                        "- Mood\n" +
                        "- Stress\n" +
                        "- Workload\n\n" +
                        "Then give practical recovery advice.";

            case "accountabilityPartner":

                return "You are GoalBot.\n\n" +
                        "You are the user's accountability partner.\n" +
                        "Encourage them.\n" +
                        "Motivate them.\n" +
                        "Celebrate achievements.\n" +
                        "If they miss tasks, help them recover instead of criticizing.";

            default:

                return "You are GoalBot.\n\n" +
                        "You help users achieve their goals.\n" +
                        "Be friendly, concise and practical.";

        }

    }

    public void generateResponse(String prompt,
                                 String mode,
                                 GeminiCallback callback) {

        try {

            String systemPrompt = buildSystemPrompt(mode);

            String finalPrompt = systemPrompt + "\n\nUser:\n" + prompt;

            JSONObject text = new JSONObject();
            text.put("text", finalPrompt);

            JSONArray parts = new JSONArray();
            parts.put(text);

            JSONObject content = new JSONObject();
            content.put("parts", parts);

            JSONArray contents = new JSONArray();
            contents.put(content);

            JSONObject body = new JSONObject();
            body.put("contents", contents);

            RequestBody requestBody = RequestBody.create(
                    body.toString(),
                    MediaType.parse("application/json")
            );

            Request request = new Request.Builder()
                    .url(URL)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(new Callback() {

                @Override
                public void onFailure(Call call, IOException e) {

                    callback.onFailure(e.getMessage());

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    if(response.body()==null){

                        callback.onFailure("Empty Response");

                        return;

                    }

                    try{

                        String json=response.body().string();

                        JSONObject object=new JSONObject(json);

                        JSONArray candidates=object.getJSONArray("candidates");

                        JSONObject candidate=candidates.getJSONObject(0);

                        JSONObject content=candidate.getJSONObject("content");

                        JSONArray parts=content.getJSONArray("parts");

                        String reply=parts.getJSONObject(0).getString("text");

                        callback.onSuccess(reply);

                    }

                    catch(Exception e){

                        callback.onFailure(e.getMessage());

                    }

                }

            });

        }

        catch(Exception e){

            callback.onFailure(e.getMessage());

        }

    }

}