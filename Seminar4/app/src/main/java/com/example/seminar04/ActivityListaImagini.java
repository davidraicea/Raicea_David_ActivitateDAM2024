package com.example.seminar04;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ActivityListaImagini extends AppCompatActivity {
    private List<ImaginiDomeniu> imaginiDomeniuList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lista_imagini);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        List<String> linkuriImagini = new ArrayList<>();
        linkuriImagini.add("https://www.pro-line.ro/wp-content/uploads/2020/10/gd-rotors-main.17.jpg");
        linkuriImagini.add("https://s13emagst.akamaized.net/products/58558/58557572/images/res_a81d8f93fe6cc3303832d1b9a81e0277.jpg");
        linkuriImagini.add("https://pro-data.ro/image/cache/catalog/discuri-blank/traxdata/cd-r80-traxdata-silver-500x500.jpg");
        linkuriImagini.add("https://dt7v1i9vyp3mf.cloudfront.net/styles/news_large/s3/imagelibrary/1/1998-07-cdnextgen-1-0lTN9nwNtHZyDzJ2yXmUdc94BT8UApcc.jpg");
        linkuriImagini.add("https://www.materiale.ro/userfiles/dd42513e-307e-4ecd-b04e-52685e9ce931/products/1848868_big.jpg");

        List<Bitmap> imagini = new ArrayList<>();

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());





        executor.execute(new Runnable() {
            @Override
            public void run() {
                for(String link:linkuriImagini){
                    HttpURLConnection con = null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(inputStream));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imaginiDomeniuList = new ArrayList<>();
                        imaginiDomeniuList.add(new ImaginiDomeniu("Disc frana",imagini.get(0),"https://en.wikipedia.org/wiki/Disc_brake"));
                        imaginiDomeniuList.add(new ImaginiDomeniu("Vinil",imagini.get(1),"https://ro.wikipedia.org/wiki/Disc_de_gramofon"));
                        imaginiDomeniuList.add(new ImaginiDomeniu("CD",imagini.get(2),"https://ro.wikipedia.org/wiki/Disc_compact"));
                        imaginiDomeniuList.add(new ImaginiDomeniu("CD 2",imagini.get(3),"https://ro.wikipedia.org/wiki/Disc_compact"));
                        imaginiDomeniuList.add(new ImaginiDomeniu("Disc abraziv",imagini.get(4),"https://ro.wikipedia.org/wiki/Abraziv"));

                        ListView lv = findViewById(R.id.listViewImagini);
                        ImagineAdapter adapter = new ImagineAdapter(imaginiDomeniuList, getApplicationContext(), R.layout.raw_item_imagini);
                        lv.setAdapter(adapter);

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Intent it = new Intent(getApplicationContext(), WebViewActivity.class);
                                it.putExtra("link", imaginiDomeniuList.get(position).getLink());
                                startActivity(it);
                            }
                        });
                    }
                });
            }

        });









    }
}