package com.forev.obsidian;

/**
 * Created by Roshan Singh on 28-02-2018.
 */



import com.chad.library.adapter.base.BaseItemDraggableAdapter;

import com.chad.library.adapter.base.BaseViewHolder;



import java.util.List;





public class ItemDragAdapter extends BaseItemDraggableAdapter<String, BaseViewHolder> {

    public ItemDragAdapter(List data) {

        super(R.layout.item_draggable_view, data);

    }


int price=0;
    void priceMapper(String s)
    {
        switch (s)
        {
            case "Wheat":
                price=40;
            break;
            case "Rice":
                price=52;
            break;
            case "Bajara":
                price=50;
            break;
            case "Chana Dal":
                price=60;
            break;
            case "Toor Dal":
                price=90;
            break;
            case "Moon Dal":
                price=80;
            break;
            case "Matki Dal":
                price=85;
            break;
            case "Hair Oil":
                price=10;
            break;
            case "Cream":
                price=20;
            break;
            case "Face wash":
                price=40;
            break;
            case "Powder":
                price=40;
            break;
            case "Rava":
                price=63;
            break;
            case "Poha":
                price=50;
            break;
            case "Tea":
                price=25;
            break;
            case "Coffee":
                price=15;
            break;

        }

    }
    @Override
    protected void convert(BaseViewHolder helper, String item) {

        switch (helper.getLayoutPosition()) {

            case 0:
                priceMapper(item);
                helper.setText(R.id.price,String.valueOf(price));

                break;

            case 1:

                priceMapper(item);
                helper.setText(R.id.price,String.valueOf(price));

                break;

            case 2:

                priceMapper(item);
                helper.setText(R.id.price,String.valueOf(price));
                break;

        }


        priceMapper(item);
        helper.setText(R.id.price,String.valueOf(price));
        helper.setText(R.id.tv,item);

    }

}