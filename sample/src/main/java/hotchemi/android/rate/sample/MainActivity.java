package hotchemi.android.rate.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import hotchemi.android.rate.AppRate;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Monitor launch times and duration of feeding period from installation
        AppRate.setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setShowNeutralButton(true) // default true
                .monitor(this);
        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }

}