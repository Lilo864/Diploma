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


public class DoorFragment extends Fragment {
    private static final String TAG = "Door Fragment";
    private ToggleButton doorToggle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.door_fragment,container,false);

        doorToggle = (ToggleButton) view.findViewById(R.id.doorToggle);
//        D - ON d - OFF
        doorToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        Toast.makeText(getActivity(),"Button is ON!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("D".getBytes());

                    } else {
                        Toast.makeText(getActivity(),"Button is OFF!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("d".getBytes());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        return view;
    }

}
