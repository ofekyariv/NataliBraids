package com.example.natalibraids;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BraidsKindsAdapter extends ArrayAdapter<BraidsKinds> {

    Context context;
    List<BraidsKinds> objects;
    public BraidsKindsAdapter(@NonNull Context context, int resource, int textViewResourceId, List<BraidsKinds> objects ) {
        super(context, resource, textViewResourceId, objects);

        this.context=context;
        this.objects=objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view= layoutInflater.inflate(R.layout.item,parent,false);

        TextView tvTitle= (TextView)view.findViewById(R.id.tvTitle);
        ImageView ivLike= (ImageView)view.findViewById(R.id.ivLike);
        TextView tvSubTitle= (TextView)view.findViewById(R.id.tvSubTitle);
        ImageView ivPhoto= (ImageView)view.findViewById(R.id.ivPhoto);
        BraidsKinds temp= objects.get(position);

        if(!temp.getLike()){
            ivLike.setVisibility(View.INVISIBLE);
        }
        tvTitle.setText(temp.getTitle());
        tvSubTitle.setText(temp.getSubTitle());
        ivPhoto.setImageBitmap(temp.getBitmap());

        return view;
    }
}
