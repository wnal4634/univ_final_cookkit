package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RecipeEditRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class RecipeEditActivity extends AppCompatActivity {  //레시피 수정 페이지
    ImageView main;
    EditText recipe_title, recipe_mat, recipe_text1, recipe_text2, recipe_text3, recipe_text4,
            recipe_text5, recipe_text6, rid, comment;
    Button closebutton;
    TextView imgpath;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {  //뒤로가기
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spinner spinner = findViewById(R.id.categorysellect);
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(
                this, R.array.spinnerArray, android.R.layout.simple_spinner_item);

        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinner_adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String a = spinner.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        recipe_title = findViewById(R.id.recipe_title);
        recipe_mat = findViewById(R.id.recipe_mat);
        recipe_text1 = findViewById(R.id.recipe_text1);
        recipe_text2 = findViewById(R.id.recipe_text2);
        recipe_text3 = findViewById(R.id.recipe_text3);
        recipe_text4 = findViewById(R.id.recipe_text4);
        recipe_text5 = findViewById(R.id.recipe_text5);
        recipe_text6 = findViewById(R.id.recipe_text6);
        main = findViewById(R.id.recipegallery_main);
        imgpath = findViewById(R.id.imgpath);
        rid = findViewById(R.id.recipeID);
        comment = findViewById(R.id.health);

        String serverUrl = "http://admin0000.dothome.co.kr/recipe_edit_import.php";  //저장된 레시피 불러오기
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int rr = getIntent().getIntExtra("r_id", 0);
                String rrtostr = String.valueOf(rr);
                try {
                    for(int i=0; i< response.length(); i++){
                        JSONObject jsonObject= response.getJSONObject(i);

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
                        String com = jsonObject.getString("comment");
                        String image = jsonObject.getString("image_main");

                        Bitmap image_bit = StringToBitmap(image);

                        if (rrtostr.equals(r_id)) {
                            rid.setText(r_id);
                            recipe_title.setText(title1);
                            recipe_mat.setText(mat1);
                            recipe_text1.setText(t1);
                            recipe_text2.setText(t2);
                            recipe_text3.setText(t3);
                            recipe_text4.setText(t4);
                            recipe_text5.setText(t5);
                            recipe_text6.setText(t6);
                            comment.setText(com);
                            main.setImageBitmap(image_bit);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RecipeEditActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgpath.setText("");
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 0);
            }
        });

        String member_id = getIntent().getStringExtra("id");
        TextView textview = findViewById(R.id.memberID);
        textview.setText(member_id);

        Button update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String r_id = rid.getText().toString();
                final String id = (String) textview.getText();
                final String title = recipe_title.getText().toString();
                final String mat = recipe_mat.getText().toString();
                final String cate = spinner.getSelectedItem().toString();
                final String text1 = recipe_text1.getText().toString();
                final String text2 = recipe_text2.getText().toString();
                final String text3 = recipe_text3.getText().toString();
                final String text4 = recipe_text4.getText().toString();
                final String text5 = recipe_text5.getText().toString();
                final String text6 = recipe_text6.getText().toString();
                final String comm = comment.getText().toString();
                final String image_main = (String) imgpath.getText();

                //하나라도 입력 안 했을 경우
                if (title.equals("") || mat.equals("") || text1.equals("") || text2.equals("") || text3.equals("") || text4.equals("") || text5.equals("") || text6.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipeEditActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if (cate.equals("--카테고리 선택--")) {  //카테고리 선택 안 했을 경우
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipeEditActivity.this);
                    dialog = builder.setMessage("카테고리를 선택해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );
                            if (success) {
                                Toast.makeText(getApplicationContext(), String.format("수정했습니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RecipeEditActivity.this, Fragment_mypage.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), "실패했습니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                //서버로 Volley를 이용해서 요청
                RecipeEditRequest recipeEditRequest = new RecipeEditRequest( r_id, id, title, mat, cate, text1, text2, text3, text4, text5, text6, comm, image_main, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RecipeEditActivity.this );
                queue.add( recipeEditRequest );
            }
        });
    }

    public static Bitmap StringToBitmap(String encodedString) {  //String으로 저장된 이미지 비트맵으로 변환
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {  //비트맵을 String으로 변환
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                    byte[] bytes = stream.toByteArray();
                    String imagepath = Base64.encodeToString(bytes, Base64.DEFAULT);
                    imgpath.setText(imagepath);
                    main.setImageBitmap(bitmap);
                } catch (Exception e) {
                }
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }
}