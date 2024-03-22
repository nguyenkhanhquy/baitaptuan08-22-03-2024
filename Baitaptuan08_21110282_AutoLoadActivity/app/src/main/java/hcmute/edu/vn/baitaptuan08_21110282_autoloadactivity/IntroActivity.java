package hcmute.edu.vn.baitaptuan08_21110282_autoloadactivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class IntroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_intro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        new Thread(() -> {
            int n = 0;
            try {
                do {
                    if (n >= 2000) {
                        IntroActivity.this.finish();
                        Intent intent = new Intent(IntroActivity.this.getApplicationContext(), (Class) LoginActivity.class);
                        IntroActivity.this.startActivity(intent);
                        return;
                    }
                    Thread.sleep(100);
                    n += 100;
                } while (true);
            } catch (InterruptedException interruptedException) {
                IntroActivity.this.finish();
                Intent intent = new Intent(IntroActivity.this.getApplicationContext(), (Class) LoginActivity.class);
                IntroActivity.this.startActivity(intent);
                return;
            }
        }).start();
    }
}