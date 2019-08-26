package com.jonas.tinderclone.activities;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.jonas.tinderclone.R;
import com.jonas.tinderclone.adapters.CardAdapter;
import com.jonas.tinderclone.models.CardItem;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ArrayAdapter arrayAdapter;
    private List<CardItem> cardItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProperties();
    }

    @Override
    public void initProperties() {
        cardItemList = new ArrayList();

        CardItem cardItem1 = new CardItem("001", "lucas");
        CardItem cardItem2 = new CardItem("002", "Matias");
        CardItem cardItem3 = new CardItem("003", "Hernan");
        CardItem cardItem4 = new CardItem("004", "Alexis");
        CardItem cardItem5 = new CardItem("005", "Tomas");
        CardItem cardItem6 = new CardItem("006", "Gabriel");

        cardItemList.add(cardItem1);
        cardItemList.add(cardItem2);
        cardItemList.add(cardItem3);
        cardItemList.add(cardItem4);
        cardItemList.add(cardItem5);
        cardItemList.add(cardItem6);


        arrayAdapter = new CardAdapter(this, R.layout.item, cardItemList );

        SwipeFlingAdapterView flingContainer = findViewById(R.id.frame);
        flingContainer.setAdapter(arrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                cardItemList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                // Toast.makeText(MyActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
            }

            @Override
            public void onScroll(float v) {

            }
        });

        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
            }
        });
    }

    @Override
    public void configureActionBar() { }
}

