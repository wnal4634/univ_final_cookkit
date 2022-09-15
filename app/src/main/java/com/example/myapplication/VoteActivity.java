package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RecipewriteRequest;
import com.example.myapplication.Register.VoteRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
    View view;
    RecyclerView recyclerView;
    ArrayList<voteItem> list7 = new ArrayList<>();
    VoteAdapter vAdapter;
    String member_id;
    ItemClickListener itemClickListener;
    Button btn;
    int position;
    String m_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        member_id = shared_preferences.get_user_email(VoteActivity.this);

        ImageButton back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VoteActivity.this, Fragment_index.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });
        itemClickListener = new ItemClickListener() {
            @Override
            public void onclick(String s, int pos) {
                 position = pos;
                recyclerView.post(new Runnable() {
                    @Override
                    public void run() {
                        vAdapter.notifyDataSetChanged();

                    }
                });
            }
        };
        recyclerView = findViewById(R.id.listview);
        recyclerView.setHasFixedSize(true);
        vAdapter = new VoteAdapter(list7, itemClickListener);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(vAdapter);
        btn = findViewById(R.id.vote_btn);

        String serverUrl = "http://admin0000.dothome.co.kr/vote_display.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list7.clear();
                vAdapter.notifyDataSetChanged();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String id = jsonObject.getString("member_id");
                        int r_id = jsonObject.getInt("recipe_id");

                        voteItem voteItem = new voteItem(id, r_id, title, category);
                        vAdapter.addItem(voteItem);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        String serverUrl1 = "http://admin0000.dothome.co.kr/meal_vote_check.php";
        JsonArrayRequest jsonArrayRequest1 = new JsonArrayRequest(Request.Method.POST, serverUrl1, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {

                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject1 = response.getJSONObject(i);
                        String id1 = jsonObject1.getString("member_id");
                        m_id = id1;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VoteActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue1 = Volley.newRequestQueue(VoteActivity.this);
        requestQueue1.add(jsonArrayRequest1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voteItem voteItem = list7.get(position);
                String rid = String.valueOf(voteItem.recipe_id);
                final String id = member_id;

                if (member_id.equals(m_id)) {
                    Toast.makeText(getApplicationContext(), "이미 투표하였습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject = new JSONObject( response );
                                boolean success = jsonObject.getBoolean( "success" );

                                if (success) {

                                    Toast.makeText(getApplicationContext(), "투표가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(VoteActivity.this, Fragment_index.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    finish();

                                } else {
                                    Toast.makeText(getApplicationContext(), "실패하였습니다.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    };

                    //서버로 Volley를 이용해서 요청
                    VoteRequest voteRequest = new VoteRequest(rid, id, responseListener);
                    RequestQueue queue = Volley.newRequestQueue( VoteActivity.this );
                    queue.add( voteRequest );
                }



            }
        });
    }
}