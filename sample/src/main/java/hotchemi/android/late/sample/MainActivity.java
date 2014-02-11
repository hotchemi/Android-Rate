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

        // override default rate dialog label
        final DialogLabels labels = new DialogLabels();
        labels.title = R.string.title;
        labels.message = R.string.message;
        labels.positiveButton = R.string.positive_button;
        labels.neutralButton = R.string.neutral_button;
        labels.negativeButton = R.string.negative_button;

        // Monitor launch times and duration of feeding period from installation
        AppRate.setInstallDays(0)
                .setLaunchTimes(3)
//                .setDialogLabels(labels)
                .monitor(this);

        // Show a dialog if meets conditions
        AppRate.showRateDialogWhenMeetsConditions(this);
    }

}