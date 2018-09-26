package edu.cnm.deepdive.fizzbuzzgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

  private TextView valueDisplay;
  private Random rng;
  private Timer timer;
  private int value;
  private int correct;
  private int incorrect;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    valueDisplay = findViewById(R.id.value_display);
    rng = new Random();
  }

  @Override
  protected void onStart() {
    super.onStart();
    timer = new Timer();
    timer.scheduleAtFixedRate(new UpdateTask(), 1000, 3000);
  }

  @Override
  protected void onStop() {
   timer.cancel();
   timer = null;
    super.onStop();
  }

  private void refresh() {
    value = rng.nextInt(100) + 1;
    valueDisplay.setText(Integer.toString(value));
  }

  private class UpdateTask extends TimerTask {

    @Override
    public void run() {
      runOnUiThread(new Updater());

    }
  }

  private class Updater implements Runnable {

    @Override
    public void run() {
      refresh();
    }
  }
}
