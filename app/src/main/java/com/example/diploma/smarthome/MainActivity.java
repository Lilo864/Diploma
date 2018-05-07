package com.example.diploma.smarthome;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity  implements TextToSpeech.OnInitListener {
    public static final String TAG = "MainActivity";
    private SectionsPageAdapter sectionsPageAdapter;

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private static final int REQUEST_RECOGNITION = 1;

    private FloatingActionButton startRecognizer;
    private TextToSpeech tts;

    private ListenerClass listenerClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Log.d(TAG, "onCreate: Starting");

        sectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        startRecognizer = (FloatingActionButton) findViewById(R.id.fab);
//        startRecognizer.setEnabled(false);
        tts = new TextToSpeech(this, this);

        listenerClass = new ListenerClass();

        ListenerClass.buttonMap.put(Buttons.GARAGE,R.id.garageToggle);
    }

    private void setupViewPager(ViewPager viewPager){
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new LightFragment(),"Lights");
        adapter.addFragment(new FanFragment(),"Fans");
        adapter.addFragment(new DoorFragment(),"Doors");
        adapter.addFragment(new TempFragment(),"Temp");
        viewPager.setAdapter(adapter);
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
        if (id == R.id.bt_settings) {
            Intent intent = new Intent(this, BluetoothActivity.class);
            //startActivityForResult(intent,REQUEST_CODE_BLUETOOTH);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void onInit(int arg0) {
//        startRecognizer.setEnabled(true);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if ((requestCode == REQUEST_RECOGNITION) & (resultCode == RESULT_OK)) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, result);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            analyzeSpeech(adapter);
        }

    }

    public void analyzeSpeech(ArrayAdapter<?> adapter){
        int count = adapter.getCount();
        try {
            Activities activity = null;
            Buttons key = null;
            String keyString = null, activityString = null;
            boolean activityFound = false, keyFound = false;
            for (int i = 0; i < count; i++) {
                String sr[] = adapter.getItem(i).toString().split(" ");
                for(int k = 0;k < sr.length;k++){
                    String word = sr[k];
                    if (listenerClass.getKeyMap().containsKey(word)) {
                        key = listenerClass.getKeyMap().get(word);
                        keyString = word;
                        keyFound = true;
                    }
                    if (listenerClass.getActivityMap().containsKey(word)) {
                        activity = listenerClass.getActivityMap().get(word);
                        activityString = word;
                        activityFound = true;
                    }
                }
            }
            if(!keyFound || !activityFound) {
                msg("Не распознано");
                return;
            }
            msg("Выполняется: " + activityString + " " + keyString);
            boolean state = listenerClass.listener(activity, key);

            ToggleButton tb = (ToggleButton) findViewById(ListenerClass.buttonMap.get(key));
            tb.setChecked(state);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    public void speechButton(View v){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to Recognize");
        startActivityForResult(intent, REQUEST_RECOGNITION);
    }
    private void msg(String s) {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }
}
