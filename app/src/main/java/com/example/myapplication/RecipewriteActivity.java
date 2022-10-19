package com.example.myapplication;

import static android.net.sip.SipErrorCode.TIME_OUT;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.RecipewriteRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;

public class RecipewriteActivity extends AppCompatActivity {

    ImageView main;
    EditText recipe_title, recipe_mat, recipe_text1, recipe_text2, recipe_text3, recipe_text4
            ,recipe_text5, recipe_text6;
    String member_id;
    TextView imgpath;
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

        member_id = shared_preferences.get_user_email(RecipewriteActivity.this);
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

        Button button_upload = findViewById(R.id.upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                final String image_main = (String) imgpath.getText();

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

                if (image_main.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RecipewriteActivity.this);
                    dialog = builder.setMessage("사진을 넣어주세요.").setNegativeButton("확인", null).create();
                    dialog.show();
                    return;
                }

                progressDialog = ProgressDialog.show(RecipewriteActivity.this, "", "업로드 중입니다.\n시간이 걸리는 작업이니 잠시만 기다려주세요.", true);

                Handler mHandler = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        if (msg.what == TIME_OUT)
                        { // 타임아웃이 발생하면
                            progressDialog.dismiss();// ProgressDialog를 종료
                            Intent intent = new Intent(RecipewriteActivity.this, Fragment_index.class);
                            finish();
                        }
                    }
                };
                mHandler.sendEmptyMessageDelayed(TIME_OUT, 25000);
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
                RecipewriteRequest recipewriteRequest = new RecipewriteRequest( id, title, mat, cate, text1, text2, text3, text4, text5, text6,
                        image_main,  responseListener);
                RequestQueue queue = Volley.newRequestQueue( RecipewriteActivity.this );
                queue.add( recipewriteRequest );
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    imgpath.setText(BitmapToString(bitmap));
                    main.setImageBitmap(bitmap);
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] bytes = baos.toByteArray();
        String temp = Base64.encodeToString(bytes, Base64.DEFAULT);
        return temp;
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