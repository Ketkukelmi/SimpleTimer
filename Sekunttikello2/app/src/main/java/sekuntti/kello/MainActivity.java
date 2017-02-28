package sekuntti.kello;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button button;
    TextView textview;
    EditText edittext;
    CountDownTimer countdowntimer;
    Button resetButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button1);
        textview = (TextView)findViewById(R.id.textView1);
        edittext = (EditText)findViewById(R.id.editText);
        resetButton = (Button)findViewById(R.id.resetButton);



        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CountDownTimerClass ct = new CountDownTimerClass(0 , 1000);
                countdowntimer.cancel();
                ct.stopEverything();
                button.setEnabled(true);

            }
        });


        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edittext.getText().toString().trim().length() == 0) {

                    return;

                } else {
                String text = edittext.getText().toString();

                final int seconds = Integer.valueOf(text);

                countdowntimer = new CountDownTimerClass(seconds * 1000, 1000);

                countdowntimer.start();
            } }
        });
    }


    public class CountDownTimerClass extends CountDownTimer {
        int progress;

        final MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.alarm);

        public CountDownTimerClass(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);

        }

        @Override
        public void onTick(long millisUntilFinished) {

            progress = (int) (millisUntilFinished/1000);
            button.setEnabled(false);
            textview.setText(Integer.toString(progress));

        }

        @Override
        public void onFinish() {

            textview.setText("Finished ");
            button.setEnabled(true);
            mp.start();
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mp.stop();
                }
            }, 2000);
            }

            final public void stopEverything(){
                progress = 0;
                mp.stop();
                textview.setText("");
            }
        }
    }

