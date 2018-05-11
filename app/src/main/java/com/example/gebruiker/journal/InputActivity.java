package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class InputActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
    }

    public void addEntry (View view) {

        // getting views
        EditText editTitle = (EditText) findViewById(R.id.editTitle);
        EditText editText = (EditText) findViewById(R.id.editText);
        ImageView mad = (ImageView) findViewById(R.id.mad);
        ImageView sad = (ImageView) findViewById(R.id.sad);
        ImageView happy= (ImageView) findViewById(R.id.happy);
        TextView moodText = (TextView) findViewById(R.id.MoodText);

        // getting added text and selected mood from views
        String Title = editTitle.getText().toString();
        String Text = editText.getText().toString();
        String mood = moodText.getText().toString();

        // creating new entry
        JournalEntry entry = new JournalEntry(Title, Text, mood);

        // insert entry in table
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        db.insert(entry);

        // returning to mainactivity
        Intent intent = new Intent(InputActivity.this, MainActivity.class);
        startActivity(intent);
    }

    // setting moodview to which mood is clicked on
    public void OnMadClicked (View view){
        TextView mood = (TextView) findViewById(R.id.MoodText);
        mood.setText("Mad");
    }

    public void OnSadClicked (View view){
        TextView mood = (TextView) findViewById(R.id.MoodText);
        mood.setText("Sad");
    }

    public void OnHappyClicked (View view){
        TextView mood = (TextView) findViewById(R.id.MoodText);
        mood.setText("Happy");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // saving selected mood
        TextView moodText = (TextView) findViewById(R.id.MoodText);
        String mood = moodText.getText().toString();
        outState.putString("mood", mood);

    }


    @Override
    public void onRestoreInstanceState (Bundle inState){
        super.onRestoreInstanceState(inState);

        // setting selected mood
        TextView moodText = (TextView) findViewById(R.id.MoodText);
        String mood = inState.getString("mood");
        moodText.setText(mood);

    }
}
