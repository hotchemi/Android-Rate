package hotchemi.android.rate.sample;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends ActionBarActivity implements  OnClickButtonListener{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRate.setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowNeutralButton(true) // default true.
                .setDebug(false) // default false.
                .monitor(this);

        // Show a dialog if meets conditions.
        AppRate.showRateDialogIfMeetsConditions(this,999);
    }

    @Override
    public void onClickButton(int requestCode, int which) {
        // callback listener.
        Log.d(MainActivity.class.getName(), Integer.toString(requestCode));
        Log.d(MainActivity.class.getName(), Integer.toString(which));
    }

}