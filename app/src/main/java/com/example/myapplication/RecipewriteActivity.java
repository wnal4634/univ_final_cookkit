package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Base64;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class RecipewriteActivity extends AppCompatActivity {

    ImageView main, image1, image2, image3, image4, image5, image6;
    EditText recipe_title, recipe_mat, recipe_text1, recipe_text2, recipe_text3, recipe_text4
            ,recipe_text5, recipe_text6;
    String member_id;
    String imagepath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipewrite);

        member_id = getIntent().getStringExtra("member_id");
        TextView textview = (TextView)findViewById(R.id.memberID);
        textview.setText(member_id);


        Button close;
        close = (Button) findViewById(R.id.closebutton);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.categorysellect);

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

        main = findViewById(R.id.recipegallery_main);
        image1 = findViewById(R.id.recipegallery1);
        image2 = findViewById(R.id.recipegallery2);
        image3 = findViewById(R.id.recipegallery3);
        image4 = findViewById(R.id.recipegallery4);
        image5 = findViewById(R.id.recipegallery5);
        image6 = findViewById(R.id.recipegallery6);
        recipe_title = findViewById(R.id.recipe_title);
        recipe_mat = findViewById(R.id.recipe_mat);
        recipe_text1 = findViewById(R.id.recipe_text1);
        recipe_text2 = findViewById(R.id.recipe_text2);
        recipe_text3 = findViewById(R.id.recipe_text3);
        recipe_text4 = findViewById(R.id.recipe_text4);
        recipe_text5 = findViewById(R.id.recipe_text5);
        recipe_text6 = findViewById(R.id.recipe_text6);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        Button button_upload = findViewById(R.id.upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertToDatabase((String) textview.getText(), recipe_title.getText().toString(), recipe_mat.getText().toString(),
                        spinner.getSelectedItem().toString(), recipe_text1.getText().toString(), recipe_text2.getText().toString(), recipe_text3.getText().toString(),
                        recipe_text4.getText().toString(), recipe_text5.getText().toString(), recipe_text6.getText().toString(),
                        main.toString());
                Intent intent = new Intent(RecipewriteActivity.this, RecipeexplanationActivity.class);
                startActivity(intent);
                finish();

            }
        });
    }

    private void insertToDatabase(final String ed1, String ed2, String ed3, String ed4, String ed5, String ed6, String ed7, String ed8, String ed9, String ed10,
                                  String ed11) {
        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RecipewriteActivity.this, "Please Wait", null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //Log.d("Tag : ", s); // php에서 가져온 값을 최종 출력함
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {

                try {
                    String edt1Text = (String) params[0];
                    String edt2Text = (String) params[1];
                    String edt3Text = (String) params[2];
                    String edt4Text = (String) params[3];
                    String edt5Text = (String) params[4]; //text1
                    String edt6Text = (String) params[5];
                    String edt7Text = (String) params[6];
                    String edt8Text = (String) params[7];
                    String edt9Text = (String) params[8];
                    String edt10Text = (String) params[9];
                    String edt11Text = (String) params[10];

                    String link = "http://admin0000.dothome.co.kr/insert.php";
                    String data = URLEncoder.encode("member_id", "UTF-8") + "=" + URLEncoder.encode(edt1Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_title", "UTF-8") + "=" + URLEncoder.encode(edt2Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_material", "UTF-8") + "=" + URLEncoder.encode(edt3Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_category", "UTF-8") + "=" + URLEncoder.encode(edt4Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text1", "UTF-8") + "=" + URLEncoder.encode(edt5Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text2", "UTF-8") + "=" + URLEncoder.encode(edt6Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text3", "UTF-8") + "=" + URLEncoder.encode(edt7Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text4", "UTF-8") + "=" + URLEncoder.encode(edt8Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text5", "UTF-8") + "=" + URLEncoder.encode(edt9Text, "UTF-8");
                    data += "&" + URLEncoder.encode("recipe_text6", "UTF-8") + "=" + URLEncoder.encode(edt10Text, "UTF-8");
                    data += "&" + URLEncoder.encode("image_main", "UTF-8") + "=" + URLEncoder.encode(edt11Text, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
                    outputStreamWriter.write(data);
                    outputStreamWriter.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    Log.d("tag : ", sb.toString()); // php에서 결과값을 리턴
                    return sb.toString();

                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }
        InsertData task = new InsertData();
        task.execute(ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
//                Uri uri = data.getData();

                try {
                        InputStream in = getContentResolver().openInputStream(data.getData());
                        Bitmap img = BitmapFactory.decodeStream(in);
                        in.close();
                        main.setImageBitmap(img);
//                    BitmapDrawable drawable = (BitmapDrawable) main.getDrawable();
//                    Bitmap bitmap = drawable.getBitmap();
//                    bitmapToByteArray(bitmap);
                        imagepath = BitmapToString(img);

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


    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }


}

