package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_index extends Fragment {  //메인페이지 프래그먼트
    private View view;
    private RecyclerView recyclerView;
    private ArrayList<MainData> list = new ArrayList<>();
    private MainAdapter mAdapter;
    private Spinner indexSpinner;
    private SwipeRefreshLayout mysrl;
    AlertDialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index, container, false);

        Button vote_ck = view.findViewById(R.id.vote_ck);
        vote_ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //DB값 불러와 투표기간인지 아닌지 확인
                String serverUrl7 = "http://admin0000.dothome.co.kr/vote_recipe_count.php";
                JsonArrayRequest jsonArrayRequest7 = new JsonArrayRequest(Request.Method.POST, serverUrl7, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String vote_count = jsonObject.getString("num");
                                if (vote_count.equals("0")) {  //투표기간이 아니라면 다이얼로그 띄움
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    dialog = builder.setMessage("투표기간이 아닙니다.").setNegativeButton("확인", null).create();
                                    dialog.show();
                                } else {
                                    Intent intent = new Intent(getActivity(), VoteActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                                    startActivity(intent);
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
                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(jsonArrayRequest7);
            }
        });

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        mAdapter = new MainAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mysrl =  view.findViewById(R.id.swipe_layout);
        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {  //메인페이지 리사이클러뷰 새로고침
                indexSpinner = (Spinner) view.findViewById(R.id.reco);
                indexSpinner.setSelection(0);
                String serverUrl = "http://admin0000.dothome.co.kr/index.php";  //DB에 저장된 레시피 불러오기
                JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        list.clear();
                        mAdapter.notifyDataSetChanged();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);

                                String title = jsonObject.getString("recipe_title");
                                String category = jsonObject.getString("recipe_category");
                                String image = jsonObject.getString("image_main");
                                int click = jsonObject.getInt("click_count");
                                int r_id = jsonObject.getInt("recipe_id");

                                Bitmap image_bit = StringToBitmap(image);

                                MainData mainData = new MainData(title, category, click, image_bit, r_id);
                                mAdapter.addItem(mainData);
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
                //서버로 Volley를 이용해서 요청
                RequestQueue requestQueue3 = Volley.newRequestQueue(getActivity());
                requestQueue3.add(jsonArrayRequest3);
                mysrl.setRefreshing(false);  //새로고침 멈추기(없으면 무한로딩)
            }
        });
       return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        String[] values1 = {"최신순", "조회수순"};
        indexSpinner = view.findViewById(R.id.reco);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter(this.getActivity(), android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        indexSpinner.setPrompt("최신순");  //스피너 기본값은 최신순
        indexSpinner.setAdapter(adapterSpinner);
        adapterSpinner.addAll(values1);

        indexSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (indexSpinner.getSelectedItem().toString() == "조회수순") {  //조회수순을 클릭하면 조회수순으로 정렬
                    String serverUrl4 = "http://admin0000.dothome.co.kr/index_highview.php";  //조회수순을 불러오는 php 연동
                    JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.POST, serverUrl4, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            list.clear();
                            mAdapter.notifyDataSetChanged();
                            try {
                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    String title = jsonObject.getString("recipe_title");
                                    String category = jsonObject.getString("recipe_category");
                                    String image = jsonObject.getString("image_main");
                                    int click = jsonObject.getInt("click_count");
                                    int r_id = jsonObject.getInt("recipe_id");

                                    Bitmap image_bit = StringToBitmap(image);

                                    MainData mainData = new MainData(title, category,click, image_bit, r_id);
                                    mAdapter.addItem(mainData);
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
                    //서버로 Volley를 이용해서 요청
                    RequestQueue requestQueue4 = Volley.newRequestQueue(getActivity());
                    requestQueue4.add(jsonArrayRequest4);
                } else {
                    String serverUrl5 = "http://admin0000.dothome.co.kr/index.php";  //최신순을 클릭하면 기본값로 정렬
                    JsonArrayRequest jsonArrayRequest5 = new JsonArrayRequest(Request.Method.POST, serverUrl5, null, new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            list.clear();
                            mAdapter.notifyDataSetChanged();

                            try {

                                for (int i = 0; i < response.length(); i++) {
                                    JSONObject jsonObject = response.getJSONObject(i);

                                    String title = jsonObject.getString("recipe_title");
                                    String category = jsonObject.getString("recipe_category");
                                    String image = jsonObject.getString("image_main");
                                    int click = jsonObject.getInt("click_count");
                                    int r_id = jsonObject.getInt("recipe_id");

                                    Bitmap image_bit = StringToBitmap(image);

                                    MainData mainData = new MainData(title, category,click, image_bit, r_id);
                                    mAdapter.addItem(mainData);
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
                    //서버로 Volley를 이용해서 요청
                    RequestQueue requestQueue5 = Volley.newRequestQueue(getActivity());
                    requestQueue5.add(jsonArrayRequest5);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        String serverUrl = "http://admin0000.dothome.co.kr/index.php";  //시작할 때마다 기본값으로 정렬
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, serverUrl, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                list.clear();
                mAdapter.notifyDataSetChanged();
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("recipe_title");
                        String category = jsonObject.getString("recipe_category");
                        String image = jsonObject.getString("image_main");
                        int click = jsonObject.getInt("click_count");
                        int r_id = jsonObject.getInt("recipe_id");

                        Bitmap image_bit = StringToBitmap(image);

                        MainData mainData = new MainData(title, category,click, image_bit, r_id);
                        mAdapter.addItem(mainData);
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
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue = Volley.newRequestQueue(this.getActivity());
        requestQueue.add(jsonArrayRequest);

        String serverUrl2 = "http://admin0000.dothome.co.kr/del_overlab.php";
        //레시피 작성 시 이미지가 들어가면 DB에 두번씩 저장되는 문제가 있어 사진을 기준으로 두번 저장된 값 삭제(이에 대한 해결방안은 찾을 수 없어 이렇게 진행함)
        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.POST, serverUrl2, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        //서버로 Volley를 이용해서 요청
        RequestQueue requestQueue2 = Volley.newRequestQueue(this.getActivity());
        requestQueue2.add(jsonArrayRequest2);
    }

    public static Bitmap StringToBitmap(String encodedString) {  //String으로 저장된 이미지를 비트맵으로 변환
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