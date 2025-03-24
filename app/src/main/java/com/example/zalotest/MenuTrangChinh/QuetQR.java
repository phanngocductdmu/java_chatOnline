package com.example.zalotest.MenuTrangChinh;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;
import com.google.zxing.Result;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.zalotest.R;

public class QuetQR extends AppCompatActivity implements DecoratedBarcodeView.TorchListener {

    private DecoratedBarcodeView barcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quet_qr);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        barcodeView = findViewById(R.id.barcode_scanner);
        barcodeView.setTorchListener(this);
        barcodeView.decodeContinuous(result -> {
            // Xử lý kết quả quét ở đây
            String qrData = result.getText();
            Toast.makeText(QuetQR.this, qrData, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        barcodeView.pause();
    }

    @Override
    public void onTorchOn() {
        // Xử lý khi đèn flash được bật
    }

    @Override
    public void onTorchOff() {
        // Xử lý khi đèn flash được tắt
    }
}
