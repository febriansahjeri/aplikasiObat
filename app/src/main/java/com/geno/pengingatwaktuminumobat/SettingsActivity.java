package com.geno.pengingatwaktuminumobat;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    private Switch switchDark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        switchDark = findViewById(R.id.switchDarkMode);

        // Ambil SharedPreferences default
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isDark = prefs.getBoolean("dark_mode", false);

        // Set switch sesuai preference
        switchDark.setChecked(isDark);
        applyMode(isDark);

        // Listener untuk toggle
        switchDark.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Simpan pilihan ke SharedPreferences
            prefs.edit()
                    .putBoolean("dark_mode", isChecked)
                    .apply();

            // Terapkan theme
            applyMode(isChecked);
        });
    }

    private void applyMode(boolean dark) {
        // Ubah mode aplikasi
        AppCompatDelegate.setDefaultNightMode(
                dark ?
                        AppCompatDelegate.MODE_NIGHT_YES :
                        AppCompatDelegate.MODE_NIGHT_NO
        );
        // agar efek langsung terlihat
        recreate();
    }
}
