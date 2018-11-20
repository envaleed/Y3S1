package com.example.admin.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragementPersonal extends Fragment {


    public FragementPersonal() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragement_personal, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        ImageView addImage = (ImageView) getView().findViewById(R.id.imageView);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddPersonal.class);
                startActivity(intent);
            }
        });
    }
}
