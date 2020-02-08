package DELVE;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainMenu extends AppCompatActivity {

    GraphicsControl graphicsControl = new GraphicsControl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Buttons
        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainMenu.this, MainActivity.class);
                MainMenu.this.startActivity(myIntent);
            }
        });

        //For graphics
        FrameLayout frameLayout = findViewById(R.id.layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) frameLayout.getBackground();
        graphicsControl.hideSystemUI(getWindow().getDecorView());
        graphicsControl.startAnimation(animationDrawable, 2);
    }
}
