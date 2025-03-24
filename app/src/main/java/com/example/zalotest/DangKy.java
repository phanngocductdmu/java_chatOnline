package com.example.zalotest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import android.util.Base64;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.Map;
import android.graphics.Color;

public class DangKy extends AppCompatActivity {
    private EditText edtNhapMatKhau, edtNhapLaiMatKhau, edtNhapten;
    private RadioButton rdNam, rdNu;
    private Button btnXacNhan;
    private FirebaseDatabase mdata = FirebaseDatabase.getInstance();
    private DatabaseReference accuontRef = mdata.getReference("Account");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        AnhXa();
        QuayLai();
        XacNhan();
    }

    private void showToast(String mess) {
        Toast.makeText(DangKy.this, mess, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidInput() {
        String MatKhau = edtNhapMatKhau.getText().toString().trim();
        String NhapLai = edtNhapLaiMatKhau.getText().toString().trim();
        String TenNguoiDung = edtNhapten.getText().toString().trim();

        if (MatKhau.isEmpty()) {
            showToast(getString(R.string.enter_password));
            return false;
        } else if (!MatKhau.equals(NhapLai)) {
            showToast(getString(R.string.password_mismatch));
            return false;
        } else if (TenNguoiDung.isEmpty()) {
            showToast(getString(R.string.enter_username));
            return false;
        }
        return true;
    }

    private void XacNhan() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidInput()) {
                    InsertTaiKhoan();
                    startActivity(new Intent(DangKy.this, TrangChinhZalo.class));
                }
            }
        });
    }

    private void InsertTaiKhoan() {
        DatabaseReference newAccountRef = accuontRef.push();
        String accountId = newAccountRef.getKey();

        Map<String, Object> accuontData = new HashMap<>();

        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        String soDT = sharedPreferences.getString("soDT", "");

        String MatKhau = edtNhapMatKhau.getText().toString().trim();
        String TenNguoiDung = edtNhapten.getText().toString().trim();

        boolean Nam = rdNam.isChecked();
        String GioiTinh = Nam ? rdNam.getText().toString().trim() : rdNu.getText().toString().trim();

        String qrCodeText = soDT +"+"+ TenNguoiDung ;
        String qrCodeBase64 = generateQRCodeBase64(qrCodeText, 300, 300);

        accuontData.put("SoDienThoai", soDT);
        accuontData.put("MatKhau", MatKhau);
        accuontData.put("TenNguoiDung", TenNguoiDung);
        accuontData.put("GioiTinh", GioiTinh);
        accuontData.put("QR", qrCodeBase64);

        newAccountRef.setValue(accuontData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("Firebase", "Account created with ID: " + accountId);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Firebase", "Error creating account", e);
            }
        });
    }


    public static String generateQRCodeBase64(String text, int width, int height) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            byte[] qrCodeBytes = outputStream.toByteArray();
            String base64EncodedQRCode = Base64.encodeToString(qrCodeBytes, Base64.DEFAULT);

            return base64EncodedQRCode;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void QuayLai() {
        findViewById(R.id.imgQuayLai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void AnhXa() {
        edtNhapMatKhau = findViewById(R.id.edtNhapMatKhau);
        edtNhapLaiMatKhau = findViewById(R.id.edtNhapLaiMatKhau);
        edtNhapten = findViewById(R.id.edtTenNguoiDung);
        rdNam = findViewById(R.id.rbNam);
        rdNu = findViewById(R.id.rbNu);
        btnXacNhan = findViewById(R.id.btnDangKy);
    }
}
