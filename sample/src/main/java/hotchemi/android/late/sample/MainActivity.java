package hotchemi.android.late.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.DialogLabels;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Monitor launch times and duration of feeding period from installation
        AppRate.setInstallDays(0) // default 10
                .setLaunchTimes(3) // default 10
                .setIsShowNeutralButton(true) // default true
                .monitor(this);

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }

}