package com.example.zalotest;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DieuKhoan1 extends AppCompatActivity {

    private ImageButton imgBack;
    private TextView txt1, txt2, txt3, txt4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dieu_khoan1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AnhXa();
        setTextt();

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setTextt() {
        txt1.setText("1.1. Thỏa Thuận Sử Dụng Dịch Vụ Zalo (“Thỏa Thuận”) này bao gồm các điều khoản, điều kiện và các quy định về sử dụng các dịch vụ thuộc hệ sinh thái Zalo (“Nền tảng”), được sở hữu và/hoặc vận hành và/hoặc quản lý bởi Công ty Cổ phần VNG");
        txt2.setText("1.2. Để sử dụng bất kỳ Dịch vụ thuộc hệ sinh thái Zalo, Người dùng cần đồng ý với toàn bộ các điều khoản và điều kiện của Thỏa Thuận này; trường hợp Người dùng không đồng ý với bất kỳ điều khoản nào của Thỏa Thuận này thì Người dùng cần ngay lập tức chấm dứt việc sử dụng các Dịch vụ.");
        txt3.setText("1.3. Người dùng đồng ý đã đọc, hiểu và cam kết tuân thủ toàn bộ nguyên tắc được quy định trong Thỏa thuận này và quy định và/hoặc quy chế liên kết, tích hợp (nếu có, sau đây gọi chung là <b>“Quy chế, chính sách”)</b>. Trong trường hợp xảy ra mâu thuẫn giữa các điều khoản của Thỏa thuận này với Quy chế liên kết thì các điều khoản của Thỏa thuận này có hiệu lực chi phối.");
        txt4.setText("1.4. VNG có quyền được sửa đổi, bổ sung bất kỳ và toàn bộ nội dung của Thỏa Thuận này tại bất kỳ thời điểm nào mà không cần báo trước hay cần có được sự đồng ý trước của Người dùng.");
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
    }
}