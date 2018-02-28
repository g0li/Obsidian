package com.forev.obsidian;


import android.content.Intent;
import android.graphics.Canvas;

import android.graphics.Color;

import android.graphics.Paint;

import android.os.Bundle;

import android.support.v4.content.ContextCompat;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.helper.ItemTouchHelper;

import android.util.Log;

import android.view.View;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;

import com.chad.library.adapter.base.BaseViewHolder;

import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;

import com.chad.library.adapter.base.listener.OnItemDragListener;

import com.chad.library.adapter.base.listener.OnItemSwipeListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import java.util.List;



/**

 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper

 */

public class CartActivity extends AppCompatActivity {

    private static final String TAG = CartActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;

    private List<String> mData;

    private ItemDragAdapter mAdapter;

    private ItemTouchHelper mItemTouchHelper;

    private ItemDragAndSwipeCallback mItemDragAndSwipeCallback;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cart);

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_list);

        setTitle("Cart");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mData = generateData();

        OnItemDragListener listener = new OnItemDragListener() {

            @Override

            public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos) {

                Log.d(TAG, "drag start");

                BaseViewHolder holder = ((BaseViewHolder) viewHolder);

//                holder.setTextColor(R.id.tv, Color.WHITE);

            }



            @Override

            public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {

                Log.d(TAG, "move from: " + source.getAdapterPosition() + " to: " + target.getAdapterPosition());

            }



            @Override

            public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {

                Log.d(TAG, "drag end");

                BaseViewHolder holder = ((BaseViewHolder) viewHolder);

//                holder.setTextColor(R.id.tv, Color.BLACK);

            }

        };

        final Paint paint = new Paint();

        paint.setAntiAlias(true);

        paint.setTextSize(20);

        paint.setColor(Color.BLACK);

        OnItemSwipeListener onItemSwipeListener = new OnItemSwipeListener() {

            @Override

            public void onItemSwipeStart(RecyclerView.ViewHolder viewHolder, int pos) {

                Log.d(TAG, "view swiped start: " + pos);

                BaseViewHolder holder = ((BaseViewHolder) viewHolder);

//                holder.setTextColor(R.id.tv, Color.WHITE);

            }



            @Override

            public void clearView(RecyclerView.ViewHolder viewHolder, int pos) {

                Log.d(TAG, "View reset: " + pos);

                BaseViewHolder holder = ((BaseViewHolder) viewHolder);
//                holder.setTextColor(R.id.tv, Color.BLACK);

            }



            @Override

            public void onItemSwiped(RecyclerView.ViewHolder viewHolder, int pos) {
                Toast.makeText(CartActivity.this, "Item Removed", Toast.LENGTH_SHORT).show();

                Log.d(TAG, "View Swiped: " + pos);

            }



            @Override

            public void onItemSwipeMoving(Canvas canvas, RecyclerView.ViewHolder viewHolder, float dX, float dY, boolean isCurrentlyActive) {

                canvas.drawColor(ContextCompat.getColor(CartActivity.this, R.color.colorPrimaryDark));

//                canvas.drawText("Just some text", 0, 40, paint);

            }

        };



        mAdapter = new ItemDragAdapter(mData);

        mItemDragAndSwipeCallback = new ItemDragAndSwipeCallback(mAdapter);

        mItemTouchHelper = new ItemTouchHelper(mItemDragAndSwipeCallback);

        mItemTouchHelper.attachToRecyclerView(mRecyclerView);



        //mItemDragAndSwipeCallback.setDragMoveFlags(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);

        mItemDragAndSwipeCallback.setSwipeMoveFlags(ItemTouchHelper.START | ItemTouchHelper.END);

        mAdapter.enableSwipeItem();

        mAdapter.setOnItemSwipeListener(onItemSwipeListener);

        mAdapter.enableDragItem(mItemTouchHelper);

        mAdapter.setOnItemDragListener(listener);

//        mRecyclerView.addItemDecoration(new GridItemDecoration(this ,R.drawable.list_divider));



        mRecyclerView.setAdapter(mAdapter);

//        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {

//            @Override

//            public void onSimpleItemClick(final BaseQuickAdapter adapter, final View view, final int position) {

//                ToastUtils.showShortToast("点击了" + position);

//            }

//        });

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {

            @Override

            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

                Toast.makeText(CartActivity.this, position, Toast.LENGTH_SHORT).show();

            }

        });
        findViewById(R.id.checkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //For pushing data to firebase
                String id = database.push().getKey();
                for(int i=0;i<mData.size();i++)
                {
                    database.child(FirebaseAuth.getInstance().getCurrentUser().getDisplayName()).child(id).child("item "+i).setValue(mData.get(i));
                }
                startActivity(new Intent(CartActivity.this,MainActivity.class));
            }
        });
    }



    private List<String> generateData() {

        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<getIntent().getExtras().getStringArrayList("basket").size();i++)
        {
            data.add(getIntent().getExtras().getStringArrayList("basket").get(i));
        }

        return data;

    }





}