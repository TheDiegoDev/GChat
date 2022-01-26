package com.example.gchat;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animacionTexto();

        Intent intent = new Intent(MainActivity.this, LoguinActivity.class );
        ImageView icon_G = findViewById(R.id.imageViewGLogo);
        icon_G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
                finish();
            }
        });
    }

    private void animacionTexto() {
        final AlphaAnimation fadeIn = new AlphaAnimation(0.0f,1.0f);
        fadeIn.setDuration(1000);
        fadeIn.setStartOffset(500);
        fadeIn.setFillAfter(true);
        fadeIn.setRepeatCount(Animation.INFINITE);

        TextView textParpadeante = findViewById(R.id.textViewTouchMe);

        final AlphaAnimation fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(500);
        fadeOut.setStartOffset(500);
        fadeOut.setFillAfter(true);
        fadeOut.setRepeatCount(Animation.INFINITE);

        textParpadeante.setAnimation(fadeIn);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                textParpadeante.setAnimation(fadeOut);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                textParpadeante.setAnimation(fadeIn);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


    }
}