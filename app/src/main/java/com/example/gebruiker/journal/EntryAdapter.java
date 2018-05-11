package com.example.gebruiker.journal;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

public class EntryAdapter extends ResourceCursorAdapter {


    public EntryAdapter (Context context, Cursor cursor){
        super(context, R.layout.activity_entry_row, cursor);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        // getting views
        TextView TitleEntry = view.findViewById(R.id.TitleEntry);
        TextView DateEntry = view.findViewById(R.id.DateEntry);
        TextView MoodEntry = view.findViewById(R.id.MoodEntry);
        ImageView MoodImage = view.findViewById(R.id.MoodImage);

        // retrieving column index for title, mood and date
        int columnIndexTitle = cursor.getColumnIndex("title");
        int columnIndexMood = cursor.getColumnIndex("mood");
        int columnIndexDate = cursor.getColumnIndex("date");

        // getting actual values
        String title = cursor.getString(columnIndexTitle);
        String mood = cursor.getString(columnIndexMood);
        String date = cursor.getString(columnIndexDate);

        // setting imageview to mood, if not setting it to neutral
        if (mood.equals("Mad")){
            MoodImage.setImageResource(R.drawable.angry);
        }

        if (mood.equals("Happy")){
            MoodImage.setImageResource(R.drawable.happy);
        }

        if (mood.equals("Sad")){
            MoodImage.setImageResource(R.drawable.sad);
        }

        // setting date textview
        DateEntry.setText(date);

        // setting title if entered, else to "no title selected
        if (title.isEmpty()){
            TitleEntry.setText("No title");
        }
        else{
            TitleEntry.setText(title);
        }

        // setting mood textview, else "no mood selected"
        if (mood.equals("select a mood")){
            MoodEntry.setText("No mood selected");
        }
        else {
            MoodEntry.setText(mood);
        }
    }
}
