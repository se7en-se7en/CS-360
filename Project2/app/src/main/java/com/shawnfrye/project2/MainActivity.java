package com.shawnfrye.project2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements CustomAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private int numColumns = 1; // Adjust the number of columns as needed
    private List<DataItem> dataList;
    private CustomAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the database helper
        databaseHelper = new DatabaseHelper(this);

        // Setup RecyclerView to display database data
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, numColumns));

        // Load data from the database
        dataList = loadDataFromDatabase();
        adapter = new CustomAdapter(dataList);
        adapter.setClickListener(this);

        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onItemClick(View view, int position) {
        // Handle delete button click here
        deleteDataFromDatabase(dataList.get(position).getId());
        dataList.remove(position);
        adapter.notifyItemRemoved(position);
    }

    // Save data to the database
    private void saveData() {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        for (DataItem item : dataList) {
            ContentValues values = new ContentValues();
            values.put("name", item.getText());

            db.update("inventory", values, "id=?", new String[]{String.valueOf(item.getId())});
        }

        db.close();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void addItem(View view) {
        // Add a new item to the dataList and update the adapter
        addDataToDatabase("New Item");
    }
    // Load data from the database
    private List<DataItem> loadDataFromDatabase() {
        List<DataItem> dataList = new ArrayList<>();

        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.query("inventory", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            dataList.add(new DataItem(id, name));
        }

        cursor.close();
        db.close();

        return dataList;
    }

    // Add data to the database
    private void addDataToDatabase(String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", name);
        long insertedRowId = db.insert("inventory", null, values);

        db.close();

        if (insertedRowId != -1) {
            // If successful, update the RecyclerView
            dataList.clear();
            dataList.addAll(loadDataFromDatabase());
            adapter.notifyDataSetChanged();
        }
    }

    // Delete data from the database
    private void deleteDataFromDatabase(int id) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        int deletedRows = db.delete("inventory", "id=?", new String[]{String.valueOf(id)});

        db.close();

        if (deletedRows > 0) {
            // If successful, update the RecyclerView
            dataList.clear();
            dataList.addAll(loadDataFromDatabase());
            adapter.notifyDataSetChanged();
        }
    }

    public void onSmsClick(View view) {
        Intent intent = new Intent(MainActivity.this, Perm.class);
        startActivity(intent);
    }

}
