package com.example.hw1_m3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.hw1_m3.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(v -> {
            String message = "Привет";

            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("MESSAGE", message);
            startActivity(intent);
        });

        binding.sendToGmailButton.setOnClickListener(v -> {
            String message = "Привет";
            openEmailPicker(message);
        });
    }

    private void openEmailPicker(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"example@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Тема сообщения");
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Выберите почтовое приложение"));
        }
    }
}
