package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RecipeexplanationActivity extends AppCompatActivity {

    private View view;
    private ImageButton btn_back_index;
    private Button btn_like, btn_thumbs;
    private TextView thumsup_count, favorite_count, title_main,
            id, title, cate, mat, text1, text2, text3, text4, text5, text6, see_count, rid;
    ImageView image_main, watch, image1, image2, image3, image4, image5, image6;
    boolean selected2 = false;
    MyDatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeexplanation);

        title_main = findViewById(R.id.recipename_topbar);
        id = findViewById(R.id.recipe_id);
        title = findViewById(R.id.recipename);
        cate = findViewById(R.id.cate_ex);
        mat = findViewById(R.id.mat);
        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        image_main = findViewById(R.id.image_main);
//        image1 = findViewById(R.id.Image1);
//        image2 = findViewById(R.id.Image2);
//        image3 = findViewById(R.id.Image3);
//        image4 = findViewById(R.id.Image4);
//        image5 = findViewById(R.id.Image5);
//        image6 = findViewById(R.id.Image6);
        see_count = findViewById(R.id.click);
        watch = findViewById(R.id.watch);
        rid = findViewById(R.id.recipeID);
        Button btn_like = findViewById(R.id.favorite);

        myDb = new MyDatabaseHelper(this);
        myDb.getWritableDatabase();

        String serverUrl = "http://admin0000.dothome.co.kr/explain.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String tt = getIntent().getStringExtra("title");
                int rr = getIntent().getIntExtra("r_id", 0);
                String rrtostr = String.valueOf(rr);

                try {

                    for(int i=0; i< response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String r_id = jsonObject.getString("recipe_id");
                        String id1 = jsonObject.getString("member_id");
                        String title1 = jsonObject.getString("recipe_title");
                        String mat1 = jsonObject.getString("recipe_material");
                        String cate1 = jsonObject.getString("recipe_category");
                        String t1 = jsonObject.getString("recipe_text1");
                        String t2 = jsonObject.getString("recipe_text2");
                        String t3 = jsonObject.getString("recipe_text3");
                        String t4 = jsonObject.getString("recipe_text4");
                        String t5 = jsonObject.getString("recipe_text5");
                        String t6 = jsonObject.getString("recipe_text6");
                        String image = jsonObject.getString("image_main");
//                        String image_1 = jsonObject.getString("image1");
//                        String image_2 = jsonObject.getString("image2");
//                        String image_3 = jsonObject.getString("image3");
//                        String image_4 = jsonObject.getString("image4");
//                        String image_5 = jsonObject.getString("image5");
//                        String image_6 = jsonObject.getString("image6");
                        int click = jsonObject.getInt("click_count");


                        Bitmap image_bit = StringToBitmap(image);
//                        Bitmap image_bit1 = StringToBitmap(image_1);
//                        Bitmap image_bit2 = StringToBitmap(image_2);
//                        Bitmap image_bit3 = StringToBitmap(image_3);
//                        Bitmap image_bit4 = StringToBitmap(image_4);
//                        Bitmap image_bit5 = StringToBitmap(image_5);
//                        Bitmap image_bit6 = StringToBitmap(image_6);

                        if (rrtostr.equals(r_id)) {
                            rid.setText(r_id);
                            title_main.setText(title1);
                            title.setText(title1);
                            id.setText(id1);
                            mat.setText(mat1);
                            cate.setText(cate1);
                            text1.setText(t1);
                            text2.setText(t2);
                            text3.setText(t3);
                            text4.setText(t4);
                            text5.setText(t5);
                            text6.setText(t6);
                            image_main.setImageBitmap(image_bit);
//                            image1.setImageBitmap(image_bit1);
//                            image2.setImageBitmap(image_bit2);
//                            image3.setImageBitmap(image_bit3);
//                            image4.setImageBitmap(image_bit4);
//                            image5.setImageBitmap(image_bit5);
//                            image6.setImageBitmap(image_bit6);
                            see_count.setText(click + "");

                            Cursor cursor = myDb.AllView();
                            while (cursor.moveToNext()) {
                                String sql_rid = cursor.getString(1);
                                if (sql_rid.equals(r_id)) {
                                    btn_like.setSelected(true);
                                    selected2 = true;
                                }
                            }
                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipeexplanationActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        ImageButton btn_back_index = findViewById(R.id.back_index);
        btn_back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        btn_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected2){
                    btn_like.setSelected(false);
                    selected2 = false;
                    MyDatabaseHelper myDb = new MyDatabaseHelper(RecipeexplanationActivity.this);
                    myDb.delLike((String) rid.getText());
                }else {
                    btn_like.setSelected(true);
                    selected2 = true;
                    MyDatabaseHelper myDb = new MyDatabaseHelper(RecipeexplanationActivity.this);
                    myDb.addLike((String) rid.getText());
                }
            }
        });

        Button share=findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent Sharing_intent=new Intent(Intent.ACTION_SEND);
                Sharing_intent.setType("text/plain");

                String Test_Message = "링크";

                Sharing_intent.putExtra(Intent.EXTRA_TEXT, Test_Message);

                Intent Sharing = Intent.createChooser(Sharing_intent, "공유하기");
                startActivity(Sharing);
            }

        });

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
}