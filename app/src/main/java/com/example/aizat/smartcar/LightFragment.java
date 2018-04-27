package com.example.aizat.smartcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by aizat on 4/20/18.
 */

public class LightFragment extends Fragment {
    private static final String TAG = "Conditioner Fragment";
    private ToggleButton condStateBtn;
    private SeekBar condSeekBar;
    private TextView condSeekValue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.conditioner_fragment,container,false);
        condStateBtn = (ToggleButton) view.findViewById(R.id.condStateToggle);
        condSeekBar = (SeekBar) view.findViewById(R.id.condSeekBar);
        condSeekValue = (TextView) view.findViewById(R.id.condSeekValue);
//        condSeekValue.setText(condSeekBar.getProgress());
        condStateBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    showToast("Conditioner is on.");
                }else{
                    showToast("Conditioner is off.");
                }
            }
        });
        condSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                condSeekValue.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return view;
    }

    private void showToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
