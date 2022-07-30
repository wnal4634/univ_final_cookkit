package com.example.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.DeleteRequest;
import com.example.myapplication.Register.RecipewriteRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecipeManageActivity extends AppCompatActivity {

    View view;
    RecyclerView recyclerView;
    ArrayList<ManageData> list3 = new ArrayList<>();
    ManageAdapter maAdapter;
    ImageButton close;
    Button modifybtn;
    String member_id;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Data();
        setContentView(R.layout.activity_recipe_manage);

        member_id = getIntent().getStringExtra("member_id");
        TextView textview = (TextView)findViewById(R.id.memberID);
        textview.setText(member_id);

        ImageButton btn_back_mypage = (ImageButton) findViewById(R.id.back_mypage);
        btn_back_mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(
//                        RecipeManageActivity.this, Fragment_mypage.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
            }
        });

        recyclerView = findViewById(R.id.Recipemanage_recycler);
        recyclerView.setHasFixedSize(true);
        maAdapter = new ManageAdapter(list3);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(maAdapter);
//        ManageAdapter adapter3= new ManageAdapter(list3);
//        recyclerView.setAdapter(adapter3);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
    }

    @Override
    public void onStart() {
        super.onStart();
        String serverUrl = "http://admin0000.dothome.co.kr/write_recipe_my.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list3.clear();
                maAdapter.notifyDataSetChanged();

                try {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        String id = jsonObject.getString("member_id");

                        Bitmap image_bit = StringToBitmap(image);

                        ManageData manageData = new ManageData(title, category, image_bit, id);
                        if(id.equals(member_id)) {
                            maAdapter.addItem(manageData);
                        }
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
    }

    public static Bitmap StringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
//    private void Data(){
//        for(int i=0;i<3;i++) {
//            list3.add(new MainData("돈가스", "양식",R.drawable.porkcutlet));
//        }
//    }
    public void DialogClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("레시피 삭제").setMessage("작성한 레시피를 삭제하시겠습니까?");
        builder.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                Toast.makeText(getApplicationContext(), "레시피가 성공적으로 삭제되었습니다.", Toast.LENGTH_LONG).show();


//                member_id = getIntent().getStringExtra("member_id");
//                TextView textview = (TextView)findViewById(R.id.memberID);
//                textview.setText(member_id);
//                String id = (String) textview.getText();
//
//                Response.Listener<String> responseListener = new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                        try {
//                            JSONObject jsonObject = new JSONObject( response );
//                            boolean success = jsonObject.getBoolean( "success" );
//
//                            if (success) {
//
//                                Toast.makeText(getApplicationContext(), String.format("삭제"), Toast.LENGTH_SHORT).show();
////                                Intent intent = new Intent(RecipeManageActivity.this, Fragment_mypage.class);
////                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                                finish();
//
//                            } else {
//                                Toast.makeText(getApplicationContext(), "실패하였습니다.", Toast.LENGTH_SHORT).show();
//                                return;
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                };
//
//                DeleteRequest deleteRequest = new DeleteRequest( id, title, responseListener);
//                RequestQueue queue = Volley.newRequestQueue( RecipeManageActivity.this );
//                queue.add( deleteRequest );
            }
        });

        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}