package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.room.Database;
import androidx.room.Room;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity2 extends AppCompatActivity {
    BazaDeDateDiscuri database;
    FirebaseDatabase databaseFirebase;
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



        //baza de date:
        database = Room.databaseBuilder(getApplicationContext(), BazaDeDateDiscuri.class,"discuri_db").build();

        Intent it = getIntent();
        if(it.hasExtra("disc"))
        {
            Disc disc = it.getParcelableExtra("disc");

            EditText nume = findViewById(R.id.nume);
            EditText raza = findViewById(R.id.raza);
            Spinner vechime = findViewById(R.id.vechime);
            Switch important = findViewById(R.id.important);

            nume.setText(disc.getNume());
            raza.setText(String.valueOf(disc.getRaza()));
            if(disc.getVechime().equals("nou"))
                vechime.setActivated(true);
            if(disc.isImportant())
                important.isActivated();
        }


        databaseFirebase = FirebaseDatabase.getInstance("https://seminar04-560b9-default-rtdb.europe-west1.firebasedatabase.app/");

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

        //scriere in fisier


        //Toast.makeText(this, disc.toString(), Toast.LENGTH_LONG).show();




        //scriere in bd
        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    FileOutputStream fileOutputStream = openFileOutput("obiecteNoi.txt",MODE_PRIVATE);
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                    BufferedWriter writer = new BufferedWriter(outputStreamWriter);
                    writer.write(disc.toString());
                    writer.close();
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                database.interfataDao().insert(disc);
            }


        });

        Intent intent = new Intent();
        intent.putExtra("disc", disc);

        CheckBox disponibilOnlineCB = findViewById(R.id.disponibilOnline);
        if(disponibilOnlineCB.isChecked()){

            DatabaseReference myRef = databaseFirebase.getReference("disc");
            DatabaseReference child = myRef.child(disc.getKey());
            child.setValue(disc);

            intent.putExtra("disponibilOnline", true);
        }
        else{
            intent.putExtra("disponibilOnline", false);
        }


        setResult(RESULT_OK, intent);
        finish();
    }
}