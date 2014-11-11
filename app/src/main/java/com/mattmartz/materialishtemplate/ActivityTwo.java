package com.mattmartz.materialishtemplate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.WindowCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by matthewmartz on 11/11/14.
 */
public class ActivityTwo extends BaseActivity {
    private DrawerLayout drawer;
    private RecyclerView mRecyclerView;
    private CalendarView mCalendarView;
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

        mCalendarView = (CalendarView) findViewById(R.id.activitytwo_calendar_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.activitytwo_list_recycler);
        mRecyclerView.setHasFixedSize(true); //exercise list length is not changing

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        reAdapter = new CustomAdapter();
        mRecyclerView.setAdapter(reAdapter);

        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(ActivityTwo.this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(ActivityTwo.this, reAdapter.getItemName(position), Toast.LENGTH_SHORT).show();
            }
        }));

        FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .withDrawable(getResources().getDrawable(R.drawable.ic_action_add))
                .withButtonColor(getResources().getColor(R.color.accent))
                .withGravity(Gravity.BOTTOM | Gravity.RIGHT)
                .withMargins(0, 0, 16, 16)
                .create();

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("fabButton", "clicked");
                changeIntent(ActivityTwo.this, "com.mattmartz.materialishtemplate.ActivityOne");
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.setDrawerShadow(R.drawable.drawer_shadow, Gravity.START);
        drawerList.setOnItemClickListener(new DrawerItemClickListener());
        supportRequestWindowFeature(WindowCompat.FEATURE_ACTION_BAR);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Menu items default to never show in the action bar. On most devices this means
        // they will show in the standard options menu panel when the menu button is pressed.
        // On xlarge-screen devices a "More" button will appear in the far right of the
        // Action Bar that will display remaining items in a cascading menu.
        menu.add("Calendar View");
        menu.add("List View");

        //MenuItem actionItem = menu.add("Calendar View");

        // Items that show as actions should favor the "if room" setting, which will
        // prevent too many buttons from crowding the bar. Extra items will show in the
        // overflow area.
        //MenuItemCompat.setShowAsAction(actionItem, MenuItemCompat.SHOW_AS_ACTION_IF_ROOM);

        // Items that show as actions are strongly encouraged to use an icon.
        // These icons are shown without a text description, and therefore should
        // be sufficiently descriptive on their own.
        //actionItem.setIcon(android.R.drawable.ic_menu_share);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "Selected Item: " + item.getTitle(), Toast.LENGTH_SHORT).show();
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            switch (item.getTitle().toString()) {
                case "Calendar View":
                    mRecyclerView.setVisibility(View.GONE);
                    mCalendarView.setVisibility(View.VISIBLE);
                    break;
                case "List View":
                    mRecyclerView.setVisibility(View.VISIBLE);
                    mCalendarView.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_two;
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
            changeIntent(ActivityTwo.this, MENU_TARGET_CLASSES[position]);
        }
    }

}
