package com.example.seminar04;

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
        EditText nume = findViewById(R.id.nume);
        EditText raza = findViewById(R.id.raza);
        Switch important = findViewById(R.id.important);
        boolean importantBool = important.isActivated();
        Spinner vechime = findViewById(R.id.vechime);
        String vechimeString = vechime.getSelectedItem().toString();
        Disc disc = new Disc(nume.toString(),Float.parseFloat(String.valueOf(raza)),importantBool,vechimeString);
        Toast.makeText(this,disc.toString(), Toast.LENGTH_LONG).show();
    }
}