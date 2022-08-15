package com.example.myapplication;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RecipewriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class RecipewriteActivity extends AppCompatActivity {

    ImageView main, image1, image2, image3, image4, image5, image6;
    EditText recipe_title, recipe_mat, recipe_text1, recipe_text2, recipe_text3, recipe_text4
            ,recipe_text5, recipe_text6;
    String member_id;
    //    String imagepath;
    TextView imgpath;
    String imgPath;
    private AlertDialog dialog;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipewrite);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permissionResult= checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if(permissionResult== PackageManager.PERMISSION_DENIED){
                String[] permissions= new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,10);
            }
        }

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
        imgpath = findViewById(R.id.imgpath);

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

        Button button_upload = findViewById(R.id.upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                insertToDatabase((String) textview.getText(), recipe_title.getText().toString(), recipe_mat.getText().toString(),
//                        spinner.getSelectedItem().toString(), recipe_text1.getText().toString(), recipe_text2.getText().toString(), recipe_text3.getText().toString(),
//                        recipe_text4.getText().toString(), recipe_text5.getText().toString(), recipe_text6.getText().toString(),
//                        imgpath.getText().toString());

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipewriteActivity.this);
                    dialog = builder.setMessage("모두 입력해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                if (cate.equals("--카테고리 선택--")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipewriteActivity.this);
                    dialog = builder.setMessage("카테고리를 선택해주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                progressDialog = ProgressDialog.show(RecipewriteActivity.this, "", "업로드 중입니다.\n시간이 걸리는 작업이니 잠시만 기다려주세요.", true);

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("업로드를 완료했습니다."), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(RecipewriteActivity.this, Fragment_index.class);
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
                RecipewriteRequest recipewriteRequest = new RecipewriteRequest( id, title, mat, cate, text1, text2, text3, text4, text5, text6, image_main, responseListener);
                RequestQueue queue = Volley.newRequestQueue( RecipewriteActivity.this );
                queue.add( recipewriteRequest );
            }

//                Intent intent = new Intent(RecipewriteActivity.this, RecipeexplanationActivity.class);
//                startActivity(intent);
//                finish();

        });
    }

//    private void insertToDatabase(final String ed1, String ed2, String ed3, String ed4, String ed5, String ed6, String ed7, String ed8, String ed9, String ed10,
//                                  String ed11) {
//        class InsertData extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//                loading = ProgressDialog.show(RecipewriteActivity.this, "Please Wait", null, true, true);
//            }
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//                loading.dismiss();
//                //Log.d("Tag : ", s); // php에서 가져온 값을 최종 출력함
//                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//            }
//            @Override
//            protected String doInBackground(String... params) {
//
//                try {
//                    String edt1Text = (String) params[0];
//                    String edt2Text = (String) params[1];
//                    String edt3Text = (String) params[2];
//                    String edt4Text = (String) params[3];
//                    String edt5Text = (String) params[4]; //text1
//                    String edt6Text = (String) params[5];
//                    String edt7Text = (String) params[6];
//                    String edt8Text = (String) params[7];
//                    String edt9Text = (String) params[8];
//                    String edt10Text = (String) params[9];
//                    String edt11Text = (String) params[10];
//
//                    String link = "http://admin0000.dothome.co.kr/insert.php";
//                    String data = URLEncoder.encode("member_id", "UTF-8") + "=" + URLEncoder.encode(edt1Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_title", "UTF-8") + "=" + URLEncoder.encode(edt2Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_material", "UTF-8") + "=" + URLEncoder.encode(edt3Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_category", "UTF-8") + "=" + URLEncoder.encode(edt4Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text1", "UTF-8") + "=" + URLEncoder.encode(edt5Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text2", "UTF-8") + "=" + URLEncoder.encode(edt6Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text3", "UTF-8") + "=" + URLEncoder.encode(edt7Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text4", "UTF-8") + "=" + URLEncoder.encode(edt8Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text5", "UTF-8") + "=" + URLEncoder.encode(edt9Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("recipe_text6", "UTF-8") + "=" + URLEncoder.encode(edt10Text, "UTF-8");
//                    data += "&" + URLEncoder.encode("image_main", "UTF-8") + "=" + URLEncoder.encode(edt11Text, "UTF-8");
//
//                    URL url = new URL(link);
//                    URLConnection conn = url.openConnection();
//
//                    conn.setDoOutput(true);
//                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(conn.getOutputStream());
//                    outputStreamWriter.write(data);
//                    outputStreamWriter.flush();
//
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//
//                    StringBuilder sb = new StringBuilder();
//                    String line = null;
//
//                    // Read Server Response
//                    while ((line = reader.readLine()) != null) {
//                        sb.append(line);
//                        break;
//                    }
//                    Log.d("tag : ", sb.toString()); // php에서 결과값을 리턴
//                    return sb.toString();
//
//                } catch (Exception e) {
//                    return new String("Exception: " + e.getMessage());
//                }
//            }
//        }
//        InsertData task = new InsertData();
//        task.execute(ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11);
//    }

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


    public static String BitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
    }

    private Bitmap resize(Context context,Uri uri,int resize){
        Bitmap resizeBitmap=null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        try {
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            int width = options.outWidth;
            int height = options.outHeight;
            int samplesize = 1;
            while (true) {
                if (width / 2 < resize || height / 2 < resize)
                    break;
                width /= 2;
                height /= 2;
                samplesize *= 2;
            }
            options.inSampleSize = samplesize;
            Bitmap bitmap = BitmapFactory.decodeStream(context.getContentResolver().openInputStream(uri), null, options);
            resizeBitmap=bitmap;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return resizeBitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case 10:
                if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(this, "외부 메모리 읽기/쓰기 사용 가능", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "외부 메모리 읽기/쓰기 제한", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
