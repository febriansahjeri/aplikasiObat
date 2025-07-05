package com.geno.pengingatwaktuminumobat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditReminderActivity extends AppCompatActivity {
    private EditText etName;
    private TimePicker tp;
    private Button btnSave;
    private DBHelper db;
    private int rid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_reminder);

        db     = new DBHelper(this);
        etName = findViewById(R.id.etName);
        tp     = findViewById(R.id.timePicker);
        btnSave= findViewById(R.id.btnSave);

        tp.setIs24HourView(true);

        // Ambil ID dan data lama
        rid = getIntent().getIntExtra("id", -1);
        Reminder r = db.getById(rid);
        if (r != null) {
            etName.setText(r.getName());
            tp.setHour(r.getHour());
            tp.setMinute(r.getMinute());
        }

        btnSave.setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            int hour  = tp.getHour();
            int min   = tp.getMinute();
            if (name.isEmpty()) {
                etName.setError("Masukkan nama obat");
                return;
            }
            // Update dan finish
            r.setName(name);
            r.setHour(hour);
            r.setMinute(min);
            db.updateReminder(r);
            Toast.makeText(this, "Reminder diperbarui", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
