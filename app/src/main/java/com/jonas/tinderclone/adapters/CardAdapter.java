package com.jonas.tinderclone.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jonas.tinderclone.R;
import com.jonas.tinderclone.models.CardItem;

import java.util.List;

public class CardAdapter extends ArrayAdapter {

    public CardAdapter( Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    public View getView(int position, View view, ViewGroup parent) {
        CardItem cardItem = (CardItem) getItem(position);

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView tvName = view.findViewById(R.id.tvName);
        ImageView imageCard = view.findViewById(R.id.imageCard);

        tvName.setText(cardItem.getName());
        imageCard.setImageResource(R.mipmap.ic_launcher);

        return  view;
    }
}
