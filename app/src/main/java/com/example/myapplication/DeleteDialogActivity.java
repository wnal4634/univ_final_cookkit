package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.Register.DeleteRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteDialogActivity extends Activity {
    TextView member_id, title1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_dialog);

        Button ok_btn = findViewById(R.id.ok_btn);
        Button no_btn = findViewById(R.id.no_btn);

        member_id = findViewById(R.id.memberID);
        title1 = findViewById(R.id.title);

        no_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = getIntent().getStringExtra("title");
                String id = getIntent().getStringExtra("id");

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject( response );
                            boolean success = jsonObject.getBoolean( "success" );

                            if (success) {

                                Toast.makeText(getApplicationContext(), String.format("레시피를 삭제했습니다. 해당 페이지를 새로고침 해주세요"), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(DeleteDialogActivity.this, Fragment_mypage.class);
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

                DeleteRequest deleteRequest = new DeleteRequest( id, title, responseListener);
                RequestQueue queue = Volley.newRequestQueue( DeleteDialogActivity.this );
                queue.add( deleteRequest );
            }
        });
    }
}