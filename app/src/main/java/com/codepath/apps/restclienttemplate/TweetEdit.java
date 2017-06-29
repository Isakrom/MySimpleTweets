package com.codepath.apps.restclienttemplate;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.codepath.apps.restclienttemplate.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import cz.msebera.android.httpclient.Header;

public class TweetEdit extends AppCompatActivity {
    private TwitterClient client;
    String tweetText;
    EditText tweetHolder;
    public static String TWITTER_KEY = "Tweet";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        client = TwitterApp.getRestClient();
    };
    public void submitTweet(View view){
        tweetHolder = (EditText) findViewById(R.id.etTweet);
        tweetText = tweetHolder.getText().toString();
        client.sendTweet(tweetText, new JsonHttpResponseHandler() {

            Tweet composed = null;

            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {

                try {
                    composed = Tweet.fromJSON(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtra(TWITTER_KEY, composed);
                setResult(RESULT_OK, intent);
                finish();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }


        });
        {

        };
    }
}

