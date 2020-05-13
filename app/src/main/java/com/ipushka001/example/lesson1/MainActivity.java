package com.ipushka001.example.lesson1;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.ipushka001.example.lesson1.ContactsService.MyBinder;

public class MainActivity extends AppCompatActivity implements ServiceProvider {
    final String LOG_TAG = "myLogs";
    ContactsService mService;
    boolean mBound = false;
    boolean createdFirstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = new Intent(MainActivity.this, ContactsService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
        createdFirstTime = savedInstanceState == null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBound) {
            Log.d(LOG_TAG, "Сервис уничтожен");
            unbindService(mConnection);
            mBound = false;
        }
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyBinder binder = (MyBinder) service;
            mService = binder.getService();
            mBound = true;
            Log.d(LOG_TAG, "Сервис подключен onServiceConnected()");
            if (createdFirstTime){
                addContactListFragment();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(LOG_TAG, "Сервис отключен ");
            mBound = false;
        }
    };

    private void addContactListFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ContactListFragment fragment = new ContactListFragment();
        ft.add(R.id.container, fragment);
        ft.commit();
    }

    @Override
    public ContactsService getService() {
        Log.d(LOG_TAG, "Данные контактов получены ");
        return mService;
    }
}
