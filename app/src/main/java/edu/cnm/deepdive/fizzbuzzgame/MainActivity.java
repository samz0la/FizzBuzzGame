package edu.cnm.deepdive.fizzbuzzgame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ToggleButton;
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
  private ToggleButton fizzToggle;
  private ToggleButton buzzToggle;
  private TextView correctTally;
  private TextView incorrectTally;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    valueDisplay = findViewById(R.id.value_display);
    fizzToggle = findViewById(R.id.fizz_toggle);
    buzzToggle = findViewById(R.id.buzz_toggle);
    correctTally = findViewById(R.id.correct_tally);
    incorrectTally = findViewById(R.id.incorrect_tally);
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
    if (value != 0) {
      if (
          ((value % 3 == 0) == fizzToggle.isChecked())
      && ((value % 5 == 0) == buzzToggle.isChecked())
      ) {
        correct++;
      } else {
        incorrect++;
      }
    }
    correctTally.setText(getString(R.string.correct_tally_format, correct));
    incorrectTally.setText(getString(R.string.incorrect_tally_format, incorrect));
    value = rng.nextInt(100) + 1;
    valueDisplay.setText(Integer.toString(value));
    fizzToggle.setChecked(false);
    buzzToggle.setChecked(false);
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
