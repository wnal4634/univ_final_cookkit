package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RecipeEditRequest;
import com.example.myapplication.Register.RecipewriteRequest;
import com.example.myapplication.Register.UpdateRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class RecipeEditActivity extends AppCompatActivity {
    ImageView main, image1, image2, image3, image4, image5, image6;
    EditText recipe_title, recipe_mat, recipe_text1, recipe_text2, recipe_text3, recipe_text4,
            recipe_text5, recipe_text6, rid;
    Button closebutton;
    TextView imgpath;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_edit);

        closebutton = findViewById(R.id.closebutton);
        closebutton.setOnClickListener(new View.OnClickListener() {
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
        image1 = findViewById(R.id.recipegallery1);
        image2 = findViewById(R.id.recipegallery2);
        image3 = findViewById(R.id.recipegallery3);
        image4 = findViewById(R.id.recipegallery4);
        image5 = findViewById(R.id.recipegallery5);
        image6 = findViewById(R.id.recipegallery6);
        imgpath = findViewById(R.id.imgpath);
        rid = findViewById(R.id.recipeID);


        String serverUrl = "http://admin0000.dothome.co.kr/recipe_edit_import.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String tt = getIntent().getStringExtra("title");
                String ii = getIntent().getStringExtra("id");

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
                        String image = jsonObject.getString("image_main");

                        Bitmap image_bit = StringToBitmap(image);

                        if (tt.equals(title1) && ii.equals(id1)) {
                            rid.setText(r_id);
                            recipe_title.setText(title1);
                            recipe_mat.setText(mat1);
                            recipe_text1.setText(t1);
                            recipe_text2.setText(t2);
                            recipe_text3.setText(t3);
                            recipe_text4.setText(t4);
                            recipe_text5.setText(t5);
                            recipe_text6.setText(t6);
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
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 2);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 3);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 4);
            }
        });
        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 5);
            }
        });
        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 6);
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
                final String image_main = imgpath.getText().toString();

                if (title.equals("") || mat.equals("") || text1.equals("") || text2.equals("") || text3.equals("") || text4.equals("") || text5.equals("") || text6.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipeEditActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if (cate.equals("--카테고리 선택--")) {
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

                                Toast.makeText(getApplicationContext(), String.format("수정 완료."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RecipeEditActivity.this, Fragment_mypage.class);
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
                RecipeEditRequest recipeEditRequest = new RecipeEditRequest( r_id, id, title, mat, cate, text1, text2, text3, text4, text5, text6, image_main, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RecipeEditActivity.this );
                queue.add( recipeEditRequest );
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getData();

                try {
//                        InputStream in = getContentResolver().openInputStream(data.getData());
//                        Bitmap img = BitmapFactory.decodeStream(in);
//                        in.close();
//                        main.setImageBitmap(img);

                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 10, stream);
                    byte[] bytes = stream.toByteArray();
                    String imagepath = Base64.encodeToString(bytes, Base64.DEFAULT);
                    imgpath.setText(imagepath);
                    main.setImageBitmap(bitmap);

                } catch (Exception e) {
                }
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image1.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image2.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }
        else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image3.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }
        else if (requestCode == 4) {
            if (resultCode == RESULT_OK) {

                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image4.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }
        else if (requestCode == 5) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image5.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }
        else if (requestCode == 6) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    image6.setImageBitmap(img);
                } catch (Exception e) {
                }
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "사진 선택 취소", Toast.LENGTH_LONG).show();
        }
    }
}