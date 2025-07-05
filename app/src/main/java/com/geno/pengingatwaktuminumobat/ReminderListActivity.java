package com.geno.pengingatwaktuminumobat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class ReminderListActivity extends AppCompatActivity {
    private ListView lv;
    private DBHelper db;
    private List<Reminder> data;

    @Override
    protected void onCreate(Bundle s) {
        super.onCreate(s);
        setContentView(R.layout.activity_reminder_list);

        db = new DBHelper(this);
        lv = findViewById(R.id.listView);

        loadData();
        lv.setOnItemClickListener((parent, view, pos, id) -> {
            Reminder r = data.get(pos);
            Intent i = new Intent(this, ReminderDetailActivity.class);
            i.putExtra("id", r.getId());
            startActivity(i);
        });
    }

    private void loadData() {
        data = db.getAll();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                data.stream()
                        .map(r -> r.getName() + " — " +
                                String.format("%02d:%02d", r.getHour(), r.getMinute()))
                        .toList()
        );
        lv.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
