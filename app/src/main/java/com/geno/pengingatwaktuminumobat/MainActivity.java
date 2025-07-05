package com.geno.pengingatwaktuminumobat;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    // Ganti dengan nama & NIM Anda
    private final String NAMA = "Febriansah jeri Harnando";
    private final String NIM  = "STI202303708";

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_main);

        TextView tvName = findViewById(R.id.tvName);
        TextView tvNim  = findViewById(R.id.tvNim);
        Button  bAdd    = findViewById(R.id.btnAdd);
        Button  bList   = findViewById(R.id.btnList);
        Button  bSettings = findViewById(R.id.btnSettings);

        tvName.setText("Nama: " + NAMA);
        tvNim .setText("NIM: " + NIM);

        bAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddReminderActivity.class)));
        bList.setOnClickListener(v ->
                startActivity(new Intent(this, ReminderListActivity.class)));
        bSettings.setOnClickListener(v ->
                startActivity(new Intent(this, SettingsActivity.class)));
    }
}
