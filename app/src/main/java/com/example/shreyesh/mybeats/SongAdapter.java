package com.example.shreyesh.mybeats;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {



    private List<Song> songList;
    private Context context;

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
            imageView.setImageBitmap(bitmap);
        }


    }
}
