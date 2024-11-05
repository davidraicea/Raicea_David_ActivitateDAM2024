package com.example.seminar04;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DiscAdapter extends BaseAdapter
{

    private List<Disc> discuri = null;
    private Context ctx;
    private int resursaLayout;

    public DiscAdapter(List<Disc> discuri, Context ctx, int resursaLayout) {
        this.discuri = discuri;
        this.ctx = ctx;
        this.resursaLayout = resursaLayout;
    }


    @Override
    public int getCount() {
        return discuri.size();
    }

    @Override
    public Object getItem(int position) {
        return discuri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,viewGroup,false);

        TextView numeTV = v.findViewById(R.id.numeAd);
        TextView razaTV = v.findViewById(R.id.razaAd);
        TextView diametruTV = v.findViewById(R.id.diametruAd);
        TextView vechimeTV = v.findViewById(R.id.vechimeAd);
        TextView importantTV = v.findViewById(R.id.importantAd);
        TextView arieTV = v.findViewById(R.id.arieAd);

        Disc disc = (Disc)getItem(i);

        numeTV.setText(disc.getNume());
        razaTV.setText(String.valueOf(disc.getRaza()));
        diametruTV.setText(String.valueOf(disc.getDiametru()));
        vechimeTV.setText(disc.getVechime());
        importantTV.setText(String.valueOf(disc.isImportant()));
        arieTV.setText(String.valueOf(disc.getArie()));

        return v;



    }

    


}
