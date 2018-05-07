package com.example.diploma.smarthome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.IOException;


public class FanFragment extends Fragment {
    private static final String TAG = "Fan Fragment";
    private ToggleButton fanToggle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fan_fragment,container,false);

        fanToggle = (ToggleButton) view.findViewById(R.id.fanToggle);

//        F - ON f - OFF
        fanToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        Toast.makeText(getActivity(),"Button is ON!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("F".getBytes());

                    } else {
                        Toast.makeText(getActivity(),"Button is OFF!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("f".getBytes());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        return view;
    }

}
