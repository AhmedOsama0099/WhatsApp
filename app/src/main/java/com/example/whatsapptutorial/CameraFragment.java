package com.example.whatsapptutorial;


import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class CameraFragment extends Fragment {

    private ImageView capture;
    private FrameLayout came_view;
    private Camera camera;
    private ShowCamera showCamera;
    public CameraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=LayoutInflater.from(getContext()).inflate(R.layout.fragment_camera,container,false);
        //capture=view.findViewById(R.id.camera_fragment_capture);
        came_view=view.findViewById(R.id.cam_view);
        camera=Camera.open();
//        capture.bringToFront();
        showCamera=new ShowCamera(getContext(),camera);
        came_view.addView(showCamera);

        /*capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"asdasd",Toast.LENGTH_SHORT).show();
            }
        });*/

        return view;
    }

}
