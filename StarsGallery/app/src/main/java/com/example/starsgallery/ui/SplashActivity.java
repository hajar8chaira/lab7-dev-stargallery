package com.example.starsgallery.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.starsgallery.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView icone = findViewById(R.id.logo_star);
        icone.animate().rotation(360f).scaleX(0.4f).scaleY(0.4f).setDuration(2500).withEndAction(() -> {
            startActivity(new Intent(SplashActivity.this, ListActivity.class));
            finish();
        });
    }
}
