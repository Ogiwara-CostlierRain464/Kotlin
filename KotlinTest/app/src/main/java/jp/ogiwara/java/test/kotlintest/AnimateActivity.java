package jp.ogiwara.java.test.kotlintest;

import android.support.transition.Fade;
import android.support.transition.TransitionManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class AnimateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animate);

        final TextView textView = (TextView) findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.testanime);
        textView.setAnimation(animation);

        Button button = (Button) findViewById(R.id.button234);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView1 = new TextView(AnimateActivity.this);
                textView.setText("Label");
                ViewGroup root = (ViewGroup) findViewById(R.id.mainLayout);
                Fade fade = new Fade(Fade.IN);
                TransitionManager.beginDelayedTransition(root,fade);
                root.addView(textView1);
            }
        });
    }
}
