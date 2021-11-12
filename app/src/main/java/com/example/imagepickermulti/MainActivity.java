package com.example.imagepickermulti;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ViewPager viewPager;/*
    private Button previousButton,nextButton;*/
    private FloatingActionButton uploadButton;

    private Uri[] imageUris;

    private static final int PICK_IMAGE_CODE=0;


    /*ViewPager viewPager=findViewById(R.id.viewpager);
    Adapter adapter=new Adapter(this,urlArray);
    viewPager.setAdapter(adapter);*/

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=findViewById(R.id.imageViewPager);
        /*previousButton=findViewById(R.id.previousButton);
        nextButton=findViewById(R.id.nextButton);*/
        uploadButton=findViewById(R.id.uploadButton);


/*
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickImagesIntent();
            }
        });



    }

    private void pickImagesIntent(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Selected Image(s)"),PICK_IMAGE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_CODE){
            if(resultCode== Activity.RESULT_OK){
                if(data.getClipData()!=null){
                    int count=data.getClipData().getItemCount();
                    imageUris=new Uri[count];
                    for(int i=0;i<count;i++){
                            Uri imageUri=data.getClipData().getItemAt(i).getUri();
                            imageUris[i]=imageUri;

                    }
                    //viewpager
                }
                else{
                    imageUris=new Uri[1];
                    Uri imageUri=data.getData();
                    imageUris[0]=imageUri;
                }
            }
        }
        Adapter adapter=new Adapter(this,imageUris);
        viewPager.setAdapter(adapter);
    }
}