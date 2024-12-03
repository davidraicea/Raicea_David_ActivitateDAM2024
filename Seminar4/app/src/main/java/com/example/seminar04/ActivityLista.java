package com.example.seminar04;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityLista extends AppCompatActivity {
    BazaDeDateDiscuri database;
    private List<Disc> discList;
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

        //discList = getIntent().getParcelableArrayListExtra("discList");

        database = Room.databaseBuilder(getApplicationContext(), BazaDeDateDiscuri.class,"discuri_db").build();

        discList = new ArrayList<>();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                discList = database.interfataDao().getDiscuri();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        adapter = new DiscAdapter(discList,getApplicationContext(),R.layout.raw_item);
                        listView.setAdapter(adapter);
                    }
                });
            }
        });



        if(discList != null)
        {
//            ArrayAdapter<Disc> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,discList);
//            listView.setAdapter(adapter);



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
                    Executor executor = Executors.newSingleThreadExecutor();
                    Handler handler = new Handler(Looper.myLooper());
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            database.interfataDao().delete(discList.get(position));
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    discList.remove(position);
                                    adapter.notifyDataSetChanged();
                                }
                            });
                        }

                    });
                    return false;
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(RESULT_OK == resultCode && requestCode == 200)
        {
                discList.set(idModificat,data.getParcelableExtra("disc"));
                adapter.notifyDataSetChanged();
        }
    }
}