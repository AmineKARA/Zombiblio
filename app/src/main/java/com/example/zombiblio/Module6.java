package com.example.zombiblio;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class Module6 extends AppCompatActivity {

    public int _xDelta;
    public int _yDelta;
    public ConstraintLayout rootLayout;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.module6);

        rootLayout = (ConstraintLayout) findViewById(R.id.linearLayout11);



        SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

        if(prefs.getInt("module6_win",0)==1){
            delete_item();
        }


        /*GESTION DRAG AND DROP*/

        ImageView food = (ImageView) findViewById(R.id.food);
        food.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();


                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:


                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        System.out.println( layoutParams.topMargin);

                        if(layoutParams.topMargin<=1180 && layoutParams.topMargin>=502){
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                            editor.putInt("food", 1);
                            editor.apply();
                        }

                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });



        ImageView graff = (ImageView) findViewById(R.id.graff);
        graff.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        if(layoutParams.topMargin<=1180 && layoutParams.topMargin>=502){
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                            editor.putInt("graff", 1);
                            editor.apply();
                        }

                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });


        ImageView noise = (ImageView) findViewById(R.id.noise);
        noise.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        if(layoutParams.topMargin<=1180 && layoutParams.topMargin>=502){
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                            editor.putInt("noise", 1);
                            editor.apply();
                        }

                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });


        ImageView phone = (ImageView) findViewById(R.id.phone);
        phone.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        if(layoutParams.topMargin<=1180 && layoutParams.topMargin>=502){
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                            editor.putInt("phone", 1);
                            editor.apply();
                        }

                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });


        ImageView smoke = (ImageView) findViewById(R.id.smoke);
        smoke.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                int X = (int) event.getRawX();
                int Y = (int) event.getRawY();
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        ConstraintLayout.LayoutParams lParams = (ConstraintLayout.LayoutParams) view.getLayoutParams();
                        _xDelta = X - lParams.leftMargin;
                        _yDelta = Y - lParams.topMargin;
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = X - _xDelta;
                        layoutParams.topMargin = Y - _yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);

                        if(layoutParams.topMargin<=1180 && layoutParams.topMargin>=502){
                            SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                            editor.putInt("smoke", 1);
                            editor.apply();
                        }

                        break;
                }
                rootLayout.invalidate();
                return true;
            }
        });

        /*----------------------- */







        //GESTION CLIQUE RETOUR
        ModuleListener obs = new ModuleListener(this);
        Button retour = (Button) findViewById(R.id.retour);
        retour.setOnClickListener(obs);
        /*---------------------------*/


        /*GESTION VALIDATION CLIQUE*/

        Button valide = (Button)  findViewById(R.id.valider);
        valide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                SharedPreferences prefs = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE);

                if(prefs.getInt("food",0)==1 && prefs.getInt("graff",0)==1 && prefs.getInt("noise",0)==1 && prefs.getInt("phone",0)==1 && prefs.getInt("smoke",0)==1){
                    SharedPreferences.Editor editor = getApplicationContext().getSharedPreferences("Time", MODE_PRIVATE).edit();
                    editor.putInt("module6_win",1);
                    editor.apply();
                    delete_item();
                }

            }
        });

        /*-------------------------*/



        long milis = prefs.getLong("milis", 0); //0 is the default value.

        new CountDownTimer(milis, 1000) {

            TextView counter = (TextView)  findViewById(R.id.timer);

            public void onTick(long millisUntilFinished) {
                //counter.setText("Temps restant: " + millisUntilFinished / 1000);

                String text = String.format(Locale.getDefault(), "%02d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60);
                counter.setText(text);


            }

            public void onFinish() {
                Intent perdu = new Intent(Module6.this , Perdu.class);
                Module6.this.startActivity(perdu);
                //counter.setText("done!");
            }
        }.start();




    }

    public void delete_item(){

        ImageView imagetop = (ImageView) findViewById(R.id.imageView4);
        ImageView food = (ImageView) findViewById(R.id.food);
        ImageView graff = (ImageView) findViewById(R.id.graff);
        ImageView phone = (ImageView) findViewById(R.id.phone);
        ImageView noise = (ImageView) findViewById(R.id.noise);
        ImageView smoke = (ImageView) findViewById(R.id.smoke);


        Button valider = (Button) findViewById(R.id.valider);
        ImageView image = (ImageView) findViewById(R.id.imageView5);
        image.setImageResource(R.drawable.gagne);


        valider.setVisibility(View.GONE);
        imagetop.setVisibility(View.GONE);
        food.setVisibility(View.GONE);
        graff.setVisibility(View.GONE);
        phone.setVisibility(View.GONE);
        noise.setVisibility(View.GONE);
        smoke.setVisibility(View.GONE);

    }

}
