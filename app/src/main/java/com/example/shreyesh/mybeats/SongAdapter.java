package com.example.shreyesh.mybeats;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
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
    public static MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler seekbarHandler;
    private Runnable runnable;
    private TextView songName,songArtist;

    public SongAdapter(List<Song> songList) {
        this.songList = songList;
    }

    public SongAdapter(List<Song> songList, SeekBar seekBar) {
        this.songList = songList;
        this.seekBar = seekBar;
        seekbarHandler=new Handler();
    }

    public SongAdapter(List<Song> songList, SeekBar seekBar, TextView songName, TextView songArtist) {
        this.songList = songList;
        this.seekBar = seekBar;
        this.seekbarHandler = new Handler();
        this.songName = songName;
        this.songArtist = songArtist;
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

        final String title=songList.get(position).getSongName();
        String album=songList.get(position).getSongAlbum();
        String len=songList.get(position).getSongLength();
        final String artist=songList.get(position).getSongArtist();
        Bitmap art=songList.get(position).getSongBitMapImage();
        final String path=songList.get(position).getSongPath();
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                        seekbarHandler.removeCallbacks(runnable);
                        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                        seekBar.setMax(mediaPlayer.getDuration() / 1000);
                        mediaPlayer.start();
                        songName.setText(title);
                        songArtist.setText(artist);
                    } else {
                        mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                        seekBar.setMax(mediaPlayer.getDuration() / 1000);
                        mediaPlayer.start();
                        songName.setText(title);
                        songArtist.setText(artist);

                    }
                } else {

                    mediaPlayer = MediaPlayer.create(context, Uri.parse(path));
                    seekBar.setMax(mediaPlayer.getDuration() / 1000);
                    mediaPlayer.start();
                    songName.setText(title);
                    songArtist.setText(artist);
                }
                if (mediaPlayer != null) {
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            int current = mediaPlayer.getCurrentPosition() / 1000;
                            seekBar.setProgress(0);
                            seekBar.setProgress(current);
                        }
                    };
                    seekbarHandler.postDelayed(runnable, 1000);
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

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b && mediaPlayer!=null) {
                    mediaPlayer.seekTo(i * 1000);
                    seekBar.setProgress(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
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
