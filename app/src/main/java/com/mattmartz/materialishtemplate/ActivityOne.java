package com.mattmartz.materialishtemplate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by matthewmartz on 11/11/14.
 */
public class ActivityOne extends BaseActivity {
    private DrawerLayout drawer;
    private RecyclerView mRecyclerView;
    private CustomAdapter reAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences settings;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setActionBarIcon(R.drawable.ic_ab_drawer);

        settings = getSharedPreferences(PREFS_NAME, 0);
        editor = settings.edit();

        mRecyclerView = (RecyclerView) findViewById(R.id.activityone_recycler_view);
        mRecyclerView.setHasFixedSize(true); //list length is not changing

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        reAdapter = new CustomAdapter();
        mRecyclerView.setAdapter(reAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ActivityOne.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ActivityOne.this, reAdapter.getItemName(position), Toast.LENGTH_SHORT).show();
            }
        }));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //do stuff?
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_one;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
            changeIntent(ActivityOne.this, MENU_TARGET_CLASSES[position]);
        }
    }
}
