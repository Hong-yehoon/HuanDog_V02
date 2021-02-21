package com.example.huandog_v02;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class Splash extends Activity {

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 2000);

    }

    private class splashhandler implements Runnable{

        @Override
        public void run() {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            Splash.this.finish();       //로딩페이지 Activity stack 에서 제거
        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈 떄 뒤로가기 버튼 못누르게에ㅔㅔㅔ

    }
}
