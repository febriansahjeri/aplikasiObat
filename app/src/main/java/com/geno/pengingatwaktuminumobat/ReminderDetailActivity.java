package com.geno.pengingatwaktuminumobat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ReminderDetailActivity extends AppCompatActivity {
    private TextView tvInfo;
    private Button btnDelete, btnEdit;
    private DBHelper db;
    private int rid;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_reminder_detail);

        db    = new DBHelper(this);
        tvInfo= findViewById(R.id.tvInfo);
        btnEdit  = findViewById(R.id.btnEdit);
        btnDelete = findViewById(R.id.btnDelete);

        rid = getIntent().getIntExtra("id", -1);
        Reminder r = db.getById(rid);
        if (r != null) {
            tvInfo.setText(
                    "Obat: " + r.getName() + "\nWaktu: " +
                            String.format("%02d:%02d", r.getHour(), r.getMinute())
            );
        }

        btnEdit.setOnClickListener(v -> {
            Intent i = new Intent(this, EditReminderActivity.class);
            i.putExtra("id", rid);
            startActivity(i);
            finish();
        });


        btnDelete.setOnClickListener(v -> {
            db.deleteById(rid);
            Toast.makeText(this, "Dihapus", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
