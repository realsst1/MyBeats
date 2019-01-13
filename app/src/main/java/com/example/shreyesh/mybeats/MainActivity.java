package com.example.shreyesh.mybeats;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private SeekBar seekBar;
     Handler handler;
    private ImageView songPlayingImage,playPause;
    public static TextView songPlayingName,songPlayingArtist;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        handler=new Handler();

        seekBar=(SeekBar)findViewById(R.id.seekbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    1);

        }else {

            viewPager = (ViewPager) findViewById(R.id.tabsPager);
            tabLayout = (TabLayout) findViewById(R.id.mainActivityTabs);
            sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
            viewPager.setAdapter(sectionsPagerAdapter);
            viewPager.setOffscreenPageLimit(3);
            tabLayout.setupWithViewPager(viewPager);
        }


        songPlayingArtist=(TextView)findViewById(R.id.songPlayingArtist);
        songPlayingName=(TextView)findViewById(R.id.songPlayingName);
        songPlayingImage=(ImageView)findViewById(R.id.songPlayingImage);
        playPause=(ImageView)findViewById(R.id.playPause);



        MainActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(SongAdapter.mediaPlayer!=null) {
                    seekBar.setProgress(SongAdapter.mediaPlayer.getCurrentPosition() / 1000);

                }
                handler.postDelayed(this,1000);
            }
        });

        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SongAdapter.mediaPlayer.isPlaying()){
                    SongAdapter.mediaPlayer.pause();
                    playPause.setImageResource(R.drawable.pause);
                }else{
                    SongAdapter.mediaPlayer.start();
                    playPause.setImageResource(R.drawable.play);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    viewPager = (ViewPager) findViewById(R.id.tabsPager);
                    tabLayout = (TabLayout) findViewById(R.id.mainActivityTabs);
                    sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                    viewPager.setAdapter(sectionsPagerAdapter);
                    tabLayout.setupWithViewPager(viewPager);


                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }
}
