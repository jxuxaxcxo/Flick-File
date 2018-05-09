package com.example.joaquin.final1;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Joaquin on 17/12/2017.
 */

public class TouchListener {

    private ImageView img;
    private ViewGroup rootLayout;
    private int xPos;
    private int yPos;
    private TextView txt;

    public TouchListener(final Activity activity,FileExplorer fl){

        final FileExplorer fileExplorer = fl;
        txt = activity.findViewById(R.id.estado);
        rootLayout = activity.findViewById(R.id.viewRoot);
        img = activity.findViewById(R.id.imageView2);

        final RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(img.getWidth(),img.getHeight());
        layoutParams.leftMargin=0;
        layoutParams.topMargin=0;
        img.setLayoutParams(layoutParams);
        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int X = (int) motionEvent.getRawX();
                int Y = (int) motionEvent.getRawY();

                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        RelativeLayout.LayoutParams lParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        xPos = X-lParams.leftMargin;
                        yPos = Y-lParams.topMargin;
                        break;

                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        RelativeLayout.LayoutParams layoutParams2  = (RelativeLayout.LayoutParams) view.getLayoutParams();
                        layoutParams2.leftMargin =X- xPos;
                        layoutParams2.topMargin =Y- yPos;

                        if(layoutParams2.topMargin<-500){
                        txt.setText("ENVIAAAAR");
                        fileExplorer.uploadImage();
                        return true;}
                        else txt.setText("NOOOOO");
                        layoutParams2.rightMargin =-250;
                        layoutParams2.bottomMargin =-250;
                        view.setLayoutParams(layoutParams2);
                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });
    }

}
