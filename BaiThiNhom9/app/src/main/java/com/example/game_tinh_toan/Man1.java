package com.example.game_tinh_toan;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class Man1 extends AppCompatActivity {
    private Button btnDongY;
    private Button btnTroVe;
    private TextView txtTinhToan;
    private TextView txtDiem;
    private EditText editKetQua;
    private TextView txtTongCauTraLoi;
    private double values = 0.0;
    private double value = 0.0;
    private String toantu;
    private String[] array = {"+", "-", "*", "/"};
    private int random1 = 0;
    private int random2 = 0;
    private int diem = 0;
    private SharedPreferences sharedPreferences;
    private int tongCauTraLoiDung = 0;
    private int tongCauDaTraLoi = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man1);

        AnhXa();

        sharedPreferences = getSharedPreferences("DiemSoGame", MODE_PRIVATE);
        txtDiem.setText(String.valueOf(sharedPreferences.getInt("diemso", 100)));

        Random();

        toantu = GetRandomArray(array);
        txtTinhToan.setText(random1 + " " + toantu + " " + random2 + " = ");

        btnDongY.setOnClickListener(view -> {
            try {
                switch (toantu) {
                    case "+":
                        values = (random1 + random2);
                        break;
                    case "-":
                        values = (random1 - random2);
                        break;
                    case "*":
                        values = (random1 * random2);
                        break;
                    case "/":
                        values = (double) random1 / random2;
                        break;
                }

                diem = Integer.parseInt(txtDiem.getText().toString());
                values = Math.round(values * 100) / 100.0;
                value = Double.parseDouble(editKetQua.getText().toString());

                tongCauDaTraLoi++;
                if (value == values) {
                    Toast.makeText(Man1.this, "Đúng!", Toast.LENGTH_SHORT).show();
                    diem += 10;
                    tongCauTraLoiDung++;
                    Toast.makeText(Man1.this, "+10 điểm", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Man1.this, "Sai! Kết quả của phép tính là: " + values, Toast.LENGTH_SHORT).show();
                    if (diem != 0) {
                        diem -= 5;
                        Toast.makeText(Man1.this, "-5 điểm", Toast.LENGTH_SHORT).show();
                    } else {
                        diem = 0;
                    }
                }

                LuuDiem();
                txtTongCauTraLoi.setText("Tổng câu trả lời: " + tongCauTraLoiDung + "/" + tongCauDaTraLoi);
                Random();
                toantu = GetRandomArray(array);
                editKetQua.setText("");
                txtTinhToan.setText(random1 + " " + toantu + " " + random2 + " = ");
                txtDiem.setText(String.valueOf(diem));
            } catch (Exception ex) {
                Toast.makeText(Man1.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });

        btnTroVe.setOnClickListener(view -> finish());
    }

    private void LuuDiem() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("diemso", diem);
        editor.putInt("tongCauTraLoiDung", tongCauTraLoiDung);
        editor.putInt("tongCauDaTraLoi", tongCauDaTraLoi);
        editor.apply();
    }

    private void AnhXa() {
        btnDongY = findViewById(R.id.buttonDongY);
        btnTroVe = findViewById(R.id.buttonTroVe);
        txtDiem = findViewById(R.id.textViewDiem);
        txtTinhToan = findViewById(R.id.textViewTinhToan);
        editKetQua = findViewById(R.id.editTextKetQua);
        txtTongCauTraLoi = findViewById(R.id.txtTongCauTraLoi);
    }

    public void Random() {
        Random generator = new Random();
        random1 = generator.nextInt(10);
        random2 = generator.nextInt(10);
        while (random2 == 0) {
            random2 = generator.nextInt(10);
        }
    }

    public static String GetRandomArray(String[] array) {
        Random random = new Random();
        int index = random.nextInt(array.length);
        return array[index];
    }
}