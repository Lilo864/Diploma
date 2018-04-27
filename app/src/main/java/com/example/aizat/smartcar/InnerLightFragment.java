package com.example.aizat.smartcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * Created by aizat on 4/20/18.
 */

public class InnerLightFragment extends Fragment {
    private static final String TAG = "InnerLight Fragment";
    private ToggleButton inLightBackStateBtn;
    private ToggleButton inLightFrontStateBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inner_light_fragment,container,false);
        inLightBackStateBtn = (ToggleButton) view.findViewById(R.id.inLightBackStateToggle);
        inLightBackStateBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    showToast("Salon back light is on.");
                }else{
                    showToast("Salon back light is off.");
                }
            }
        });
        inLightFrontStateBtn = (ToggleButton) view.findViewById(R.id.inLightFrontStateToggle);
        inLightFrontStateBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    showToast("Salon front light is on.");
                }else{
                    showToast("Salon front light is off.");
                }
            }
        });
        return view;
    }
    private void showToast(String msg){
        Toast.makeText(getContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
