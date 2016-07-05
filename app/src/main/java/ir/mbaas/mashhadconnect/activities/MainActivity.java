package ir.mbaas.mashhadconnect.activities;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ir.mbaas.mashhadconnect.R;
import ir.mbaas.mashhadconnect.adapters.NavigationAdapter;
import ir.mbaas.mashhadconnect.helpers.ActionBarRtlizer;
import ir.mbaas.mashhadconnect.helpers.RtlizeEverything;
import ir.mbaas.mashhadconnect.listeners.ClickListener;
import ir.mbaas.mashhadconnect.listeners.RecyclerTouchListener;
import ir.mbaas.mashhadconnect.models.AppMenu;
import ir.mbaas.mashhadconnect.models.AppMenus;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";

    Toolbar toolbar;
    RecyclerView mRecyclerView;
    NavigationAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    DrawerLayout Drawer;
    ActionBarRtlizer rtlizer;
    AppMenus appMenus;

    ActionBarDrawerToggle mDrawerToggle;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;

        initFAB();
        initNavigationDrawer();
    }

    private void initNavigationDrawer() {
        appMenus = getAppMenus();

        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mRecyclerView.setHasFixedSize(true);

        mAdapter = new NavigationAdapter(appMenus, "MBaaS", "info@mbaas.ir", "");

        mRecyclerView.setAdapter(mAdapter);

        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);

        Drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        mDrawerToggle = new ActionBarDrawerToggle(this,Drawer,toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        mRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                        mRecyclerView, new ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        if(position > 0 && appMenus != null && appMenus.menus != null) {
                            AppMenu appMenu = appMenus.menus.get(position - 1);

                            if(appMenu == null || appMenu.type == AppMenus.MenuType.DIVIDER)
                                return;

                            switch (appMenu.getResTitle()) {
                                case R.string.action_about_text:
                                    Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                                    startActivity(intent);
                                    break;
                            }

                            Drawer.closeDrawers();
                        }
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                })
        );

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (Drawer.isDrawerOpen(GravityCompat.END)) {
                    Drawer.closeDrawer(GravityCompat.END);
                } else {
                    Drawer.openDrawer(GravityCompat.END);
                }
            }
        });
    }

    private void initFAB() {
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.DrawerLayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        rtlizer = new ActionBarRtlizer(this, "toolbar");
        ViewGroup actionBarView = rtlizer.getActionBarView();
        ViewGroup homeView = (ViewGroup)rtlizer.getHomeView();

        rtlizer.flipActionBarUpIconIfAvailable(homeView);
        RtlizeEverything.rtlize(actionBarView);
        RtlizeEverything.rtlize(homeView);

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public AppMenus getAppMenus() {
        List<AppMenu> menus = new ArrayList<>();

        menus.add(new AppMenu(this,
                R.string.action_about_text, R.string.action_about_icon));
        menus.add(new AppMenu("", "", AppMenus.MenuType.DIVIDER));
        menus.add(new AppMenu(this,
                R.string.action_help_text, R.string.action_help_icon));

        return new AppMenus(menus);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aboutapp:
                Intent intent = new Intent(MainActivity.this,AboutActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
