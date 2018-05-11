package com.example.gebruiker.journal;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.security.KeyStore;

public class MainActivity extends AppCompatActivity {
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get listview
        ListView listview = findViewById(R.id.listview);

        // setting clicklisteners
        listview.setOnItemClickListener(new ListViewOnItemClickListener());
        listview.setOnItemLongClickListener(new OnItemLongClickListener());

        // update listview with deleted or added entries
        updateData();

    }

    private void updateData (){

        // getting cursor with all rows from database
        EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();

        // setting adapter
        EntryAdapter adapter = new EntryAdapter(this, cursor);
        ListView listview = findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // after adding or deleting, update data
        updateData();
    }


    public void buttonMainClicked (View view){
        // starting new activity
        Intent intent = new Intent(MainActivity.this, InputActivity.class);
        startActivity(intent);
    }

    private class ListViewOnItemClickListener implements AdapterView.OnItemClickListener{

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // getting cursor
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // retrieving data from cursor
            int columnIndexTitle = cursor.getColumnIndex("title");
            int columnIndexMood = cursor.getColumnIndex("mood");
            int columnIndexText = cursor.getColumnIndex("text");
            int columnIndexDate = cursor.getColumnIndex("date");

            // retrieving actual values from cursor
            String title = cursor.getString(columnIndexTitle);
            String mood = cursor.getString(columnIndexMood);
            String text = cursor.getString(columnIndexText);
            String date = cursor.getString(columnIndexDate);

            // passing intent to new activity
            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("Mood", mood);
            intent.putExtra("Text", text);
            intent.putExtra("Title", title);
            intent.putExtra("Date", date);

            // start new activity
            startActivity(intent);
        }
    }

    public class OnItemLongClickListener implements  ListView.OnItemLongClickListener {


        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

            // getting cursor
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);

            // getting id from cursor
            int columnIndexID = cursor.getColumnIndex("_id");
            int rowID = cursor.getInt(columnIndexID);

            // deleting entry that was clicked on
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());
            db.delete(rowID);

            // updating listview
            updateData();
            return true;
        }
    }
}
