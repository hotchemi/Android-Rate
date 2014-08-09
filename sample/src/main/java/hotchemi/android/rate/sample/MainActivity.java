package hotchemi.android.rate.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRate.getInstance()
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowNeutralButton(true) // default true.
                .setDebug(false) // default false.
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor(this);

        // Show a dialog if meets conditions.
        AppRate.showRateDialogIfMeetsConditions(this);
    }

}