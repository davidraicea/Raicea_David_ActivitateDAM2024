package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Disc> discList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent it = new Intent(getApplicationContext(),MainActivity2.class);
            startActivityForResult(it,123);
            }
        });
        Button btnLista = findViewById(R.id.buttonLista);
        btnLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), ActivityLista.class);
                it.putParcelableArrayListExtra("discList",discList);
                startActivity(it);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 123 && resultCode == RESULT_OK && data != null) {
            Disc disc = data.getParcelableExtra("disc");
            if (disc != null) {
                discList.add(disc);
                Toast.makeText(this, "Obiectul a fost adăugat: " + disc.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }
    //    public void deschideActivitate(View view){
//        Intent it = new Intent(this, MainActivity2.class);
//        startActivity(it);
//    }

    public void deschideImagini(View view)
    {
        Intent it = new Intent(getApplicationContext(), ActivityListaImagini.class);
        startActivity(it);
    }

    public void deschideVreme(View view)
    {
        Intent it = new Intent(this, ActivityAccuWeather.class);
        startActivity(it);
    }



}