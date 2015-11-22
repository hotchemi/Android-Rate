package hotchemi.android.rate.sample;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppRate.with(this)
                .setInstallDays(3) // default 10, 0 means install day.
                .setLaunchTimes(10) // default 10 times.
                .setRemindInterval(2) // default 1 day.
                .setShowLaterButton(true) // default true.
                .setDebug(true) // default false.
                .setCancelable(false) // default false.
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .setTitle(R.string.new_rate_dialog_title)
                .setTextLater(R.string.new_rate_dialog_later)
                .setTextNever(R.string.new_rate_dialog_never)
                .setTextRateNow( R.string.new_rate_dialog_ok )
                .monitor();

        /*

        Methods for setting text are overloaded, so if you need to set custom text at runtime
        (for example if you retrieve your translations from server) set String
        parameters instead of resource ids:

        .setTitle("CustomTitle")
        .setTextLater("Later")
        and so on

         */

        AppRate.showRateDialogIfMeetsConditions(this);
    }

}