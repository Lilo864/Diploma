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


public class LightFragment extends Fragment {
    private static final String TAG = "Light Fragment";
    private ToggleButton livToogle;
    private ToggleButton bedToogle;
    private ToggleButton garageToogle;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.light_fragment,container,false);

        livToogle = (ToggleButton) view.findViewById(R.id.livToggle);
//        L - ON l - OFF
        livToogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        Toast.makeText(getActivity(),"Button is ON!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("L".getBytes());

                    } else {
                        Toast.makeText(getActivity(),"Button is OFF!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("l".getBytes());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        bedToogle = (ToggleButton) view.findViewById(R.id.bedToggle);
//        B - ON b - OFF
        bedToogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        Toast.makeText(getActivity(),"Button is ON!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("B".getBytes());

                    } else {
                        Toast.makeText(getActivity(),"Button is OFF!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("b".getBytes());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });

        garageToogle = (ToggleButton) view.findViewById(R.id.garageToggle);
//        G - ON g - OFF
        garageToogle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                try {
                    if (b) {
                        Toast.makeText(getActivity(),"Button is ON!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("G".getBytes());

                    } else {
                        Toast.makeText(getActivity(),"Button is OFF!",Toast.LENGTH_SHORT).show();
                        BluetoothActivity.outputStream.write("g".getBytes());
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });



        return view;
    }

}
