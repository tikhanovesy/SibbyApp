package com.tikhanovesy.aplikasimarble;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class KuisActivity extends AppCompatActivity {
    private TextView textView;
    private VideoView vView;
    Button Btn_a,Btn_b,Btn_c,Btn_d,Btn_Ok_benar,Btn_Ok_salah;
    private ImageButton btnBack, btnUlang;
    ImageView imgBenar,imgSalah;
    private int posisi;
    Dialog popupDialog;
    List<SoalLatihan> daftarSoal = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);
        textView = findViewById(R.id.TextPertanyaan);
        Btn_a = findViewById(R.id.buttonA);
        Btn_b = findViewById(R.id.buttonB);
        Btn_c = findViewById(R.id.buttonC);
        Btn_d = findViewById(R.id.buttonD);
        btnBack = findViewById(R.id.btn_kembali);
        btnUlang = findViewById(R.id.btn_ulang);
        vView = findViewById(R.id.vidplayer);
        popupDialog = new Dialog(this);
        posisi = 0;
        isiSoal();
        refreshSoal(posisi);

        Btn_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Btn_a.getText().toString()==daftarSoal.get(posisi).getJawaban_benar()){
                    makePopupBenar();
                } else {
                    makePopupSalah();
                }
            }
        });

        Btn_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Btn_b.getText().toString()==daftarSoal.get(posisi).getJawaban_benar()){
                    makePopupBenar();
                } else {
                    makePopupSalah();
                }
            }
        });

        Btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Btn_c.getText().toString()==daftarSoal.get(posisi).getJawaban_benar()){
                   makePopupBenar();
                } else {
                   makePopupSalah();
                }
            }
        });

        Btn_d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Btn_d.getText().toString()==daftarSoal.get(posisi).getJawaban_benar()){
                   makePopupBenar();
                } else {
                    makePopupSalah();
                }
            }
        });



        vView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                btnUlang.setVisibility(View.VISIBLE);
            }
        });

        btnUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vView.start();
                btnUlang.setVisibility(View.INVISIBLE);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void isiSoal(){
        daftarSoal.add(new SoalLatihan(
                "Angka berapakah itu?",
                "Angka 1",
                "Angka 5",
                "Angka 10",
                "Angka 100",
                "Angka 10",
                "/storage/emulated/0/Video/Angka/10.mp4"));

        daftarSoal.add(new SoalLatihan(
                "Makanan apa yang disebutkan oleh bu guru?",
                "Sayur",
                "Ayam",
                "Nasi",
                "Permen",
                "Sayur",
                "/storage/emulated/0/Video/Makanan/Sayur.mp4"
        ));
        daftarSoal.add(new SoalLatihan(
                "Siapa yang dimaksud oleh bu guru?",
                "Ayah",
                "Ibu",
                "Nenek",
                "Adik",
                "Adik",
                "/storage/emulated/0/Video/Keluarga/Adik.mp4"));

        daftarSoal.add(new SoalLatihan(
                "Makanan apa yang disebutkan bu guru?",
                "Permen",
                "Nasi",
                "Air",
                "Kue",
                "Permen",
                "/storage/emulated/0/Video/Makanan/Permen.mp4"));

        daftarSoal.add(new SoalLatihan(
                "Bahasa isyarat apakah ini?",
                "Sedih",
                "Senang",
                "Sakit",
                "Marah",
                "Senang",
                "/storage/emulated/0/Video/Perasaan/Senang.mp4"
        ));

    }

    public void refreshSoal(int posisi){

        textView.setText(daftarSoal.get(posisi).getSoalLatihan());
        Btn_a.setText(daftarSoal.get(posisi).getJawaban_a());
        Btn_b.setText(daftarSoal.get(posisi).getJawaban_b());
        Btn_c.setText(daftarSoal.get(posisi).getJawaban_c());
        Btn_d.setText(daftarSoal.get(posisi).getJawaban_d());
        String UrlVid = daftarSoal.get(posisi).getVidUrl();

        Uri uri = Uri.parse(UrlVid);
        vView.setVideoURI(uri);
        vView.start();

    }

    public void makePopupBenar(){
        popupDialog.setContentView(R.layout.popup_benar);
        Btn_Ok_benar = popupDialog.findViewById(R.id.OkButton);
        imgBenar = popupDialog.findViewById(R.id.ImageBener);

        Btn_Ok_benar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi<daftarSoal.size()-1){
                    posisi = posisi+1;
                    refreshSoal(posisi);
                    popupDialog.dismiss();

                }else {
                    popupDialog.dismiss();
                    finish();
                }
            }
        });

        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }

    public void makePopupSalah(){
        popupDialog.setContentView(R.layout.popup_salah);
        Btn_Ok_salah = popupDialog.findViewById(R.id.OkButtonSalah);
        imgSalah = popupDialog.findViewById(R.id.ImageSalah);

        Btn_Ok_salah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (posisi<daftarSoal.size()-1){
                    refreshSoal(posisi);
                    popupDialog.dismiss();
                }else {
                    popupDialog.dismiss();
                    refreshSoal(posisi);
                }
            }
        });
        popupDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupDialog.show();
    }

}
