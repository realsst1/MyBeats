package com.example.shreyesh.mybeats;

import android.graphics.Bitmap;

public class Song {
    private String songName,songArtist,songAlbum,songLength;
    private String songImage;
    private Bitmap songBitMapImage;

    public Song(String songName, String songArtist, String songAlbum, String songLength, Bitmap songBitMapImage) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songLength = songLength;
        this.songBitMapImage = songBitMapImage;
    }

    public Song(String songName, String songArtist, String songAlbum, Bitmap songBitMapImage) {

        this.songName = songName;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songBitMapImage = songBitMapImage;
    }

    public Bitmap getSongBitMapImage() {

        return songBitMapImage;
    }

    public void setSongBitMapImage(Bitmap songBitMapImage) {
        this.songBitMapImage = songBitMapImage;
    }

    public String getSongImage() {
        return songImage;
    }

    public Song(String songName, String songArtist, String songAlbum, String songLength, String songImage) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songLength = songLength;
        this.songImage = songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    public Song(String songName, String songArtist, String songAlbum, String songLength) {
        this.songName = songName;
        this.songArtist = songArtist;
        this.songAlbum = songAlbum;
        this.songLength = songLength;
    }

    public Song() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public void setSongAlbum(String songAlbum) {
        this.songAlbum = songAlbum;
    }

    public String getSongLength() {
        return songLength;
    }

    public void setSongLength(String songLength) {
        this.songLength = songLength;
    }
}
