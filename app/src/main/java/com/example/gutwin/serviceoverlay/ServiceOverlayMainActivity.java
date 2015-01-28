package com.example.gutwin.serviceoverlay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ServiceOverlayMainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_overlay_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_overlay_main, menu);
        return true;
    }

    public void startMyService(View v) {
        // does something very interesting

        //Intent serviceIntent = new Intent(this, MyService.class);
        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }

    public void stopMyService(View v) {
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
    }

    public void onPause() {
        super.onPause();  // Always call the superclass method first
        Intent serviceIntent = new Intent(this, MyService.class);
        stopService(serviceIntent);
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
}
