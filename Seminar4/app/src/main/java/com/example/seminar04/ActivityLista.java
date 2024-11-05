package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {

    private ArrayList<Disc> discList;
    private int idModificat = 0;
    private DiscAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ListView listView = findViewById(R.id.listView);

        discList = getIntent().getParcelableArrayListExtra("discList");
        if(discList != null)
        {
//            ArrayAdapter<Disc> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,discList);
//            listView.setAdapter(adapter);

            adapter = new DiscAdapter(discList,getApplicationContext(),R.layout.raw_item);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intentModifica = new Intent(getApplicationContext(),MainActivity2.class);
                    intentModifica.putExtra("disc",discList.get(position));
                    idModificat = position;
                    startActivityForResult(intentModifica,200);

                    Toast.makeText(ActivityLista.this, discList.get(position).toString(), Toast.LENGTH_SHORT).show();

                }
            });
            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    discList.remove(position);
                    adapter.notifyDataSetChanged();
                    return false;
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode)
        {
            if(requestCode == 200)
            {
                discList.set(idModificat,data.getParcelableExtra("disc"));
                adapter.notifyDataSetChanged();
            }
        }
    }
}