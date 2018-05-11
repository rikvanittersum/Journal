package com.example.gebruiker.journal;

import java.io.Serializable;

public class JournalEntry implements Serializable {

    public int id;
    public String mood, timestamp;
    public String title, content;

    public JournalEntry(String title, String content, String mood) {
        this.title = title;
        this.content = content;
        this.mood = mood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getMood() {
        return mood;
    }

    public int getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
