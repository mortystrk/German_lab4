package bstu.by.lab4;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {


    ImageView anton, maks;
    SeekBar seekBar, seekBarDelay;
    int duration;
    double delay;
    Button start, start_async, start_sync;
    TextView tv_duration, tv_delay;

    ObjectAnimator animator, animator1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(this);
        seekBarDelay = findViewById(R.id.seekBarDelay);
        seekBarDelay.setOnSeekBarChangeListener(listenerDelay);
        anton = findViewById(R.id.image_one);
        maks = findViewById(R.id.image_two);
        duration = 1000;
        delay = 1000d;
        tv_duration = findViewById(R.id.textviewDuration);
        tv_delay = findViewById(R.id.textviewDelay);
        tv_duration.setText("Duration: " + duration);
        tv_delay.setText("Delay: " + delay);

        animator = ObjectAnimator.ofFloat(anton, "scaleX", 0.5f);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        animator.setDuration(1000);

        animator1 = ObjectAnimator.ofFloat(maks, "scaleX", 0.5f);
        animator1.setRepeatMode(ValueAnimator.REVERSE);
        animator1.setRepeatCount(1);
        animator1.setDuration(1000);

        start = findViewById(R.id.start_anim);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
            }
        });

        start_async = findViewById(R.id.start_async);
        start_async.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.start();
                //new ReminderAnim(2000);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        animator1.start();
                    }
                }, (long) delay);
            }
        });

        start_sync = findViewById(R.id.start_synk);
        start_sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animator.setDuration(duration);
                animator1.setDuration(duration);

                animator1.start();
                animator.start();
            }
        });
    }


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        duration = seekBar.getProgress();
        tv_duration.setText("Duration: " + duration);
        animator.setDuration(duration);
        animator1.setDuration(duration);
    }

    SeekBar.OnSeekBarChangeListener listenerDelay = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            delay = seekBar.getProgress();
            tv_delay.setText("Delay: " + delay);
        }
    };
}




