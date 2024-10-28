package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void creareDisc(View view)
    {
        String nume = ((EditText)findViewById(R.id.nume)).getText().toString();
        String razaText = ((EditText) findViewById(R.id.raza)).getText().toString();
        boolean importantBool = ((Switch)findViewById(R.id.important)).isChecked();
        Spinner vechime = findViewById(R.id.vechime);
        String vechimeString = vechime.getSelectedItem().toString();

        float raza = 0;
        try {
            raza = Float.parseFloat(razaText);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Introduceți o valoare validă pentru rază.", Toast.LENGTH_SHORT).show();
            return;
        }

        Disc disc = new Disc(nume, raza, importantBool, vechimeString);

        //Toast.makeText(this, disc.toString(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent();
        intent.putExtra("disc", disc);
        setResult(RESULT_OK, intent);
        finish();
    }
}