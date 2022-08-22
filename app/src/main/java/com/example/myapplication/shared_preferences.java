package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class shared_preferences {

    static String pref_user_email = "user_email";

    static public SharedPreferences get_shared_preferences(Context ctx) {//모든 액티비티에서 인스턴스 얻음
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void set_user_email(Context ctx, String user_email) {//이메일 저장
        SharedPreferences.Editor editor = get_shared_preferences(ctx).edit();
        editor.putString(pref_user_email, user_email);
        editor.commit();//커밋은 필수
    }

    public static String get_user_email(Context ctx) {//저장된 이메일 가져오기
        return get_shared_preferences(ctx).getString(pref_user_email, "");
    }

    public static void clear_user(Context ctx) {//로그아웃 시 데이터 삭제
        SharedPreferences.Editor editor = get_shared_preferences(ctx).edit();
        editor.clear();
        editor.commit();//커밋은 필수
    }
}