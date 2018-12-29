package com.example.shreyesh.mybeats;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shreyesh.mybeats.AlbumsFragment;
import com.example.shreyesh.mybeats.ArtistsFragment;
import com.example.shreyesh.mybeats.SongsFragment;

public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:AlbumsFragment albumsFragment=new AlbumsFragment();
                    return albumsFragment;
            case 1:ArtistsFragment artistsFragment=new ArtistsFragment();
                    return artistsFragment;
            case 2:SongsFragment songsFragment=new SongsFragment();
                    return songsFragment;
                    default:return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:return "Artists";
            case 1:return "Albums";
            case 2:return "Songs";
            default:return null;
        }
    }
}
