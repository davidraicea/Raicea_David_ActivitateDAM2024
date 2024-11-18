package com.example.seminar04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImagineAdapter extends BaseAdapter
{
    private List<ImaginiDomeniu> imagini = null;
    private Context ctx;
    private int resursaLayout;

    public ImagineAdapter(List<ImaginiDomeniu> imagini, Context ctx, int resursaLayout) {
        this.imagini = imagini;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }


    @Override
    public int getCount() {
        return imagini.size();
    }

    @Override
    public Object getItem(int position) {
        return imagini.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,viewGroup,false);

        ImageView imageView = v.findViewById(R.id.imagineIV);
        TextView textView = v.findViewById(R.id.numeImagine);

        ImaginiDomeniu imaginiDomeniu = (ImaginiDomeniu)getItem(i);

        imageView.setImageBitmap(imaginiDomeniu.getImagine());
        textView.setText(imaginiDomeniu.getTextAfisat());

        return v;
    }
}
