package com.example.android.marvelcharacters;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private CharacterAdapter mExampleAdapter;
    private ArrayList<CharacterInfo> mExampleList;
    private RequestQueue mRequestQueue;
    boolean clicked = false;
    String charName;
    EditText char_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        Button char_btn = (Button) findViewById(R.id.btn);

        char_name = (EditText) findViewById(R.id.search_name);



        char_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              clicked=true;
                Log.v("MainActivity", "i am on clicklistner"+clicked );
            }
        });
parseJSON();


    }


    private void parseJSON() {
        String url;
        Long tsLong = System.currentTimeMillis() / 1000;
        final String ts = tsLong.toString();
        String privateKey = "e47b220ce3128666e3a33bf7262f1e19fbcbe763";
        String apiKey = "ab0fc06254529df51ccfb7442c2bd2c1";
        Log.v("current time", ts);
        final String urlHash = md5(ts + privateKey + apiKey);
        if (clicked) {
            charName = char_name.getText().toString();
            mExampleList.clear();
            mExampleAdapter.notifyDataSetChanged();
            charName = char_name.getText().toString();
            Log.v("MainActivity", "i am clicked==true" + charName);
            url = "https://gateway.marvel.com/v1/public/characters?name=" + charName + "&ts=" + ts + "&apikey=ab0fc06254529df51ccfb7442c2bd2c1&hash=" + urlHash;


        }
        else {

            url = "https://gateway.marvel.com/v1/public/characters?ts=" + ts + "&apikey=ab0fc06254529df51ccfb7442c2bd2c1&hash=" + urlHash;
            Log.v("MainActivity", "i am insideee elseeee" + clicked);

        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONObject charData = response.getJSONObject("data");
                            JSONArray jsonArray = charData.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject charDetails = jsonArray.getJSONObject(i);

                                String characterName = charDetails.getString("name");
                                String characterDesc = charDetails.getString("description");
                                JSONObject imageUrl = charDetails.getJSONObject("thumbnail");
                                String charImageURL = imageUrl.getString("path") + ".jpg";

                                mExampleList.add(new CharacterInfo(charImageURL, characterName, characterDesc));
                            }

                            mExampleAdapter = new CharacterAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }


}
