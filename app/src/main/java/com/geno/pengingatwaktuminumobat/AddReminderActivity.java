package com.geno.pengingatwaktuminumobat;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddReminderActivity extends AppCompatActivity {
    private EditText etName;
    private TimePicker tp;
    private Button btnSave;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_reminder);

        db     = new DBHelper(this);
        etName = findViewById(R.id.etName);
        tp     = findViewById(R.id.timePicker);
        btnSave= findViewById(R.id.btnSave);

        tp.setIs24HourView(true);

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            int hour  = tp.getHour();
            int min   = tp.getMinute();
            if (name.isEmpty()) {
                etName.setError("Masukkan nama obat");
                return;
            }
            Reminder r = new Reminder();
            r.setName(name);
            r.setHour(hour);
            r.setMinute(min);
            db.addReminder(r);
            Toast.makeText(this, "Reminder disimpan", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
