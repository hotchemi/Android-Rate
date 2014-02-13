package hotchemi.android.rate.sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

import hotchemi.android.rate.AppRate;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = new Button(this);
        button.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Monitor launch times and duration of feeding period from installation
        AppRate.setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setIsShowNeutralButton(true) // default true
                .monitor(this);

    }

    @Override
    public void onClick(View v) {
        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);
    }

}