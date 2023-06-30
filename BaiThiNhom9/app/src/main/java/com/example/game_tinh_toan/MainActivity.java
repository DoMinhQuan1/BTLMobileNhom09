package com.example.game_tinh_toan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    private Button btnlv1;
    private Button btnlv2;
    private Button btnlv3;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnlv1 = findViewById(R.id.buttonLevel1);
        btnlv2 = findViewById(R.id.buttonLevel2);
        btnlv3 = findViewById(R.id.buttonLevel3);
        btnReset = findViewById(R.id.buttonReset);

        btnlv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Man1.class);
                startActivity(intent);
            }
        });

        btnlv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Man2.class);
                startActivity(intent);
            }
        });

        btnlv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Man3.class);
                startActivity(intent);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("DiemSoGame", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("diemso", 0);
                editor.putInt("tongCauTraLoiDung", 0);
                editor.putInt("tongCauDaTraLoi", 0);
                editor.apply();
            }
        });
        // Các công việc khác trong onCreate() (nếu có)
    }
}
