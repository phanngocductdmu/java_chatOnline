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

public class DieuKhoan2 extends AppCompatActivity {

    private ImageButton imgBack;
    private TextView txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dieu_khoan2);
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
        txt1.setText("1. NGUYÊN TẮC CHUNG");
        txt2.setText("+ Zalo không cho phép việc đăng tải hoặc chia sẻ các thông tin sai lệch, hư cấu, những thông tin chưa có kiểm chứng, nhạy cảm hoặc gây hoang mang dự luận, ảnh hưởng tiêu cực tới con người và xã hội, bao gồm nhưng không giới hạn, về các lĩnh vực như thời sự, đời sống, xã hội, y tế, kinh tế và chính trị.");
        txt3.setText("+ Người dùng phải đảm bảo rằng, Nhóm luôn là nơi an toàn cho các kết nối có ý nghĩa diễn ra. Zalo không chấp nhận những Nhóm được tạo ra với mục đích quấy rối hoặc gây hấn. Bằng công nghệ máy học và Báo xấu từ phía người dùng, Zalo có thể xử lý nội dung vi phạm pháp luật mà bạn đăng tải trên cộng đồng. Trong một số trường hợp, chúng tôi có thể thông báo tới cơ quan có thẩm quyền.");
        txt4.setText("+ Với việc vi phạm Chính sách cộng đồng này, Zalo có toàn quyền khóa tạm thời, vĩnh viễn tài khoản của bạn hoặc thực hiện các biện pháp xử lý phù hợp khác.");
        txt5.setText("+ Zalo có toàn quyền sửa đổi, bổ sung bất kỳ hoặc toàn bộ nội dung của Chính sách cộng đồng này tại bất cứ thời điểm nào mà không cần báo trước hoặc cần có sự đồng ý của người dùng. Chính sách cộng đồng Zalo này là một phần không tách rời Thỏa Thuận Dịch Vụ Zalo.");
    }

    private void AnhXa() {
        imgBack = findViewById(R.id.imgBack);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);
    }
}