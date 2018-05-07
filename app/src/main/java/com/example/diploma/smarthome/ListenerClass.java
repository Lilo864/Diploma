package com.example.diploma.smarthome;

import android.content.Context;
import android.widget.Button;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Homer Simpson on 05.05.2018.
 */

public class ListenerClass {

    private Map<Buttons,String> onMap;
    private Map<String,Activities> activityMap;
    private Map<String,Buttons> keyMap;
    public static Map<Buttons,Integer> buttonMap = new HashMap<>();
    public ListenerClass(){
        onMap = new HashMap<>();
        activityMap = new HashMap<>();
        keyMap = new HashMap<>();
        initMaps();
    }
    private void initMaps(){
        keyMap.put("кондиционер",Buttons.FAN);
        keyMap.put("дверь",Buttons.DOOR);
        keyMap.put("свет",Buttons.LIGHT);
        keyMap.put("спальняя",Buttons.BED);
        keyMap.put("гараж",Buttons.GARAGE);

        activityMap.put("включить",Activities.TURN_ON);
        activityMap.put("выключить", Activities.TURN_OFF);
        activityMap.put("открыть", Activities.OPEN);
        activityMap.put("закрыть", Activities.CLOSE);

        onMap.put(Buttons.FAN,"F");
        onMap.put(Buttons.DOOR,"D");
        onMap.put(Buttons.LIGHT,"L");
        onMap.put(Buttons.BED,"B");
        onMap.put(Buttons.GARAGE, "G");
    }
    public boolean listener(Activities activity,Buttons key) throws Exception{
        String sendCode = onMap.get(key);
        if (activity== Activities.OPEN || activity==Activities.TURN_ON) {
            BluetoothActivity.outputStream.write(sendCode.toUpperCase().getBytes());
            return true;
        }else
//        if (activity==Activities.CLOSE || activity==Activities.TURN_OFF)
        {
            BluetoothActivity.outputStream.write(sendCode.toLowerCase().getBytes());
            return false;
        }

    }

    public Map<String, Activities> getActivityMap() {
        return activityMap;
    }

    public Map<String, Buttons> getKeyMap() {
        return keyMap;
    }
}
enum Buttons{
    FAN,LIGHT,DOOR,BED,GARAGE;
}
enum Activities{
    TURN_ON, TURN_OFF, OPEN,CLOSE;
}