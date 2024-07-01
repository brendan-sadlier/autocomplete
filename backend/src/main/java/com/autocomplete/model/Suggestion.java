package com.autocomplete.model;

public class Suggestion {

    private String value;
    private String type;
    private String artist;
    private int numberOfSongs;
    private String lengthOfSong;
    private String description;

    private Suggestion(Builder builder) {
        this.value = builder.value;
        this.type = builder.type;
        this.artist = builder.artist;
        this.numberOfSongs = builder.numberOfSongs;
        this.lengthOfSong = builder.lengthOfSong;
        this.description = builder.description;
    }

    public static class Builder {
        private String value;
        private String type;
        private String artist;
        private int numberOfSongs;
        private String lengthOfSong;
        private String description;

        public Builder(String value, String type, String artist) {
            this.value = value;
            this.type = type;
            this.artist = artist;
        }

        public Builder numberOfSongs(int numberOfSongs) {
            this.numberOfSongs = numberOfSongs;
            return this;
        }

        public Builder lengthOfSong(String lengthOfSong) {
            this.lengthOfSong = lengthOfSong;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Suggestion build() {
            return new Suggestion(this);
        }
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    public void setNumberOfSongs(int numberOfSongs) {
        this.numberOfSongs = numberOfSongs;
    }

    public String getLengthOfSong() {
        return lengthOfSong;
    }

    public void setLengthOfSong(String lengthOfSong) {
        this.lengthOfSong = lengthOfSong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
