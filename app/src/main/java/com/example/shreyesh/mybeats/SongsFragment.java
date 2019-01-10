package com.example.shreyesh.mybeats;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;


public class SongsFragment extends Fragment {

    private List<Song> songList;
    private SongAdapter songAdapter;
    private RecyclerView songRecylerView;
    private View view;
    private byte[] art;
    private Bitmap songImage;
    private MediaMetadataRetriever metadataRetriever;
    private SeekBar seekBar;


    public SongsFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_songs, container, false);


        songRecylerView=(RecyclerView)view.findViewById(R.id.songList);
        seekBar=(SeekBar)getActivity().findViewById(R.id.seekbar);

        songList=new ArrayList<>();
        songAdapter=new SongAdapter(songList,seekBar);

        songRecylerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        songRecylerView.setAdapter(songAdapter);




        String[] proj={MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME};
        Cursor audioCursor=getContext().getApplicationContext().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,proj,null,null,MediaStore.Audio.Media.TITLE);
        if(audioCursor!=null){
            if(audioCursor.moveToFirst()){
                do{
                    String title=audioCursor.getString(3);
                    String album=audioCursor.getString(0);
                    String artist=audioCursor.getString(1);
                    String len=audioCursor.getString(2);
                    String path=audioCursor.getString(4);



                    try {
                        metadataRetriever = new MediaMetadataRetriever();
                        metadataRetriever.setDataSource(path);
                        art=metadataRetriever.getEmbeddedPicture();
                    }catch (Exception e){
                        continue;
                    }

                    if(art!=null) {
                        songImage = BitmapFactory.decodeByteArray(art, 0, art.length);
                    }else{
                        songImage=Bitmap.createBitmap(10,10,Bitmap.Config.ARGB_8888);
                    }


                    System.out.println("Title:"+title+"\tAlbum:"+album+"\tArtist:"+artist+"\tPath:"+path);
                    songList.add(new Song(title,artist,album,len,songImage,path));
                    songAdapter.notifyDataSetChanged();

                }while (audioCursor.moveToNext());
            }
        }
        return view;

    }




    // TODO: Rename method, update argument and hook method into UI event



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
