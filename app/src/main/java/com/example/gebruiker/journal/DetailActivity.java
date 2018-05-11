package com.example.gebruiker.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView title = (TextView) findViewById(R.id.TitleDetail);
        TextView date = (TextView) findViewById(R.id.DateDetail);
        TextView text = (TextView) findViewById(R.id.TextDetail);
        ImageView mood = (ImageView) findViewById(R.id.DetailMood);

        Intent intent = getIntent();
        String retrievedTitle = intent.getStringExtra("Title");
        String retrievedDate = intent.getStringExtra("Date");
        String retrievedMood = intent.getStringExtra("Mood");
        String retrievedText = intent.getStringExtra("Text");

        title.setText(retrievedTitle);
        date.setText(retrievedDate);
        text.setText(retrievedText);

        if (retrievedMood.equals("Mad")){
            mood.setImageResource(R.drawable.angry);
        }

        if (retrievedMood.equals("Happy")){
            mood.setImageResource(R.drawable.happy);
        }

        if (retrievedMood.equals("Sad")){
            mood.setImageResource(R.drawable.sad);
        }
    }

}
