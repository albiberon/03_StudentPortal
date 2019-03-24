package com.example.studentportal;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {
    private List<Portal> portals;
    private RecyclerView recyclerView;
    private GestureDetector gestureDetector;
    private PortalAdapter adapter;

    //Constants used when calling the update activity
    public static final String EXTRA_REMINDER = "Reminder";
    public static final int REQUESTCODE = 1234;
    private int mModifyPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Fab button section
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mModifyPosition = -1;
                Intent intent = new Intent(MainActivity.this, AddPortalActivity.class);
                intent.putExtra(EXTRA_REMINDER, new Portal("", ""));
                startActivityForResult(intent, REQUESTCODE);
            }
        });
        //

        portals = new ArrayList<>();

        //Recyclerview created here
        recyclerView = findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PortalAdapter(this, portals);
        recyclerView.setAdapter(adapter);
        //



        //GestureDetector created here
        gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        //

        recyclerView.addOnItemTouchListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int adapterPosition = recyclerView.getChildAdapterPosition(child);
        if (child != null && gestureDetector.onTouchEvent(motionEvent)) {
            Intent intent = new Intent(MainActivity.this, WebViewer.class);
            mModifyPosition = adapterPosition;
            intent.putExtra(EXTRA_REMINDER, portals.get(adapterPosition));
            startActivityForResult(intent, REQUESTCODE);
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE) {
            if (resultCode == RESULT_OK) {
                Portal updatePortal = data.getParcelableExtra(MainActivity.EXTRA_REMINDER);

                // New timestamp: timestamp of update
                if (mModifyPosition == -1)
                    portals.add(updatePortal);
                else
                    portals.set(mModifyPosition, updatePortal);
                updateUI();
            }
        }
    }

    private void updateUI() {
        if (adapter == null) {
            adapter = new PortalAdapter(this, portals);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
        }

    }
}
