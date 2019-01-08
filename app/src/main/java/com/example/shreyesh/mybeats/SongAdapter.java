package com.example.shreyesh.mybeats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {



    private List<Song> songList;
    private Context context;
    private static MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    @NonNull
    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.song_single_layout,parent,false);
        context=parent.getContext();
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull SongAdapter.ViewHolder holder, int position) {

        String title=songList.get(position).getSongName();
        String album=songList.get(position).getSongAlbum();
        String len=songList.get(position).getSongLength();
        String artist=songList.get(position).getSongArtist();
        Bitmap art=songList.get(position).getSongBitMapImage();
        final String path=songList.get(position).getSongPath();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                        mediaPlayer.start();
                    } else {
                        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                        mediaPlayer.start();
                    }
                }else{

                        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                        mediaPlayer.start();

                }
            }
        });

        if(mediaPlayer!=null) {
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.release();
                }
            });
        }
        holder.setSongName(title);
        holder.setSongArtist(artist);
        holder.setSongArt(art);

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        View view;
        public ViewHolder(View itemView) {
            super(itemView);
            view=itemView;
        }

        private void setSongName(String name){
            TextView textView=(TextView)view.findViewById(R.id.songNameSingle);
            textView.setText(name);
        }

        private void setSongArtist(String name){
            TextView textView=(TextView)view.findViewById(R.id.songArtistSingle);
            textView.setText(name);
        }
        private void setSongImage(String image){

        }
        private void setSongArt(Bitmap bitmap){
            ImageView imageView=(ImageView)view.findViewById(R.id.songImageSingle);
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,30,byteArrayOutputStream);
            imageView.setImageBitmap(bitmap);
        }


    }
}
