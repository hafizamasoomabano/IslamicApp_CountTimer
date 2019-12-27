package com.example.roubaisha.counter;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class timer extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 6000000;

    private TextView mTextViewCountDown;
    private CountDownTimer mCountDownTimer;

    private String currentTime= "";


    private String nextPrayerTime = "08:00:12 pm";



    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prayer_time);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        currentTime =getRemainingTime();
        startTimer(currentTime,nextPrayerTime);

    }

    private void startTimer(final String currentTime, final String nextPrayerTime) {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText(currentTime,nextPrayerTime);
            }

            @Override
            public void onFinish() {
            }
        }.start();
    }
    private String getRemainingTime() {
        String delegate = "hh:mm:ss aaa";
        return (String) DateFormat.format(delegate, Calendar.getInstance().getTime());
    }


    private void updateCountDownText(String currentTime, String nextPrayerTime) {

        try
        {

            SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss aaa");
            java.util.Date date1 = format.parse(nextPrayerTime);
            Date date2 = format.parse(currentTime);
            long mills = date1.getTime() - date2.getTime();
            System.out.println("Data1"+ ""+date1.getTime());
            System.out.println("Data2"+ ""+date2.getTime());
            int hours = (int) (mills/(1000 * 60 * 60));
            int mins = (int) (mTimeLeftInMillis/(1000*60)) % 60;
            int seconds = (int) (mTimeLeftInMillis / 1000) % 60 ;
            String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d:%02d",hours, mins, seconds);

            mTextViewCountDown.setText(timeLeftFormatted);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}