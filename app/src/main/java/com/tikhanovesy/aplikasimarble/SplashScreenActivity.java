package com.tikhanovesy.aplikasimarble;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {
    private ProgressBar mProgresbar;
    private LinearLayout mLayoutMulai;
    private LinearLayout mLayoutLoad;
    private ImageButton mButton;
    private String Identitas = "Adaa";

    public void loadProgres() {
        int SplashInterval = 1500;//Lama Splash Screen
        for (int Progres = 0; Progres < 100; Progres++) {
            try {
                Thread.sleep(SplashInterval / 100);
                mProgresbar.setProgress(Progres);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void tampilButton() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLayoutMulai.setVisibility(View.VISIBLE);
                mLayoutLoad.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        mButton = findViewById(R.id.btn_mulai);
        mProgresbar = findViewById(R.id.progBar);
        mLayoutMulai = findViewById(R.id.mulai);
        mLayoutLoad = findViewById(R.id.loads);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                loadProgres();
                tampilButton();
            }
        }).start();
    }
}
