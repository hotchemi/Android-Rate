package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

/**
 * Help you promote your app by prompting users to rate the app after using it for a few days.
 *
 * @author Shintaro Katafuchi
 */
public class AppRate {

    private static final String TAG = AppRate.class.getName();

    private static final AppRate INSTANCE = new AppRate();

    private static int sLaunchTimesThreshold = 10;

    private static int sInstallDaysThreshold = 10;

    private static long sInstallDateTime = new Date().getTime();

    private static int sLaunchTimes = 0;

    private static boolean sIsAgreeShoWDialog = true;

    private static boolean sIsShoWNeutralButton = true;

    private AppRate() {
    }

    public static AppRate setLaunchTimes(final int launchTimesThreshold) {
        sLaunchTimesThreshold = launchTimesThreshold;
        return INSTANCE;
    }

    public static AppRate setInstallDays(final int installDaysThreshold) {
        sInstallDaysThreshold = installDaysThreshold;
        return INSTANCE;
    }

    public static AppRate setShowNeutralButton(final boolean isShowNeutralButton) {
        sIsShoWNeutralButton = isShowNeutralButton;
        return INSTANCE;
    }

    public static AppRate clearAgreeShowDialog(final Context context) {
        PreferenceUtils.setAgreeShowDialog(context, true);
        return INSTANCE;
    }

    /**
     * Monitor launch times and interval from installation.<br/>
     * Call this method when the launcher activity's onCreate() is launched.
     */
    public static void monitor(final Context context) {
        final SharedPreferences pref = PreferenceUtils.getPreferences(context);
        final Editor editor = pref.edit();
        if (isFirstLaunch(pref)) {
            editor.putLong(Constants.PREF_KEY_INSTALL_DATE, sInstallDateTime);
        }
        int launchTimes = pref.getInt(Constants.PREF_KEY_LAUNCH_TIMES, 0);
        editor.putInt(Constants.PREF_KEY_LAUNCH_TIMES, launchTimes + 1);
        editor.commit();

        sInstallDateTime = new Date(pref.getLong(Constants.PREF_KEY_INSTALL_DATE, 0)).getTime();
        sLaunchTimes = pref.getInt(Constants.PREF_KEY_LAUNCH_TIMES, 0);
        sIsAgreeShoWDialog = pref.getBoolean(Constants.PREF_KEY_IS_AGREE_SHOW_DIALOG, true);
    }

    /**
     * Show rate dialog when meets conditions.
     *
     * @param activity fragment activity
     */
    public static void showRateDialogIfMeetsConditions(final FragmentActivity activity) {
        if (shouldShowRateDialog()) {
            final RateDialogSupportFragment fragment = new RateDialogSupportFragment();
            final Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, sIsShoWNeutralButton);
            fragment.setArguments(bundle);
            fragment.show(activity.getSupportFragmentManager(), TAG);
        }
    }

    /**
     * Show rate dialog when meets conditions.
     *
     * @param activity fragment activity
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showRateDialogIfMeetsConditions(final Activity activity) {
        if (shouldShowRateDialog()) {
            final RateDialogFragment fragment = new RateDialogFragment();
            final Bundle bundle = new Bundle();
            bundle.putBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, sIsShoWNeutralButton);
            fragment.setArguments(bundle);
            fragment.show(activity.getFragmentManager(), TAG);
        }
    }

    private static boolean isFirstLaunch(final SharedPreferences pref) {
        return pref.getLong(Constants.PREF_KEY_INSTALL_DATE, 0) == 0L;
    }

    private static boolean isOverLaunchTimes() {
        return sLaunchTimes >= sLaunchTimesThreshold;
    }

    private static boolean isOverInstallDate() {
        return new Date().getTime() - sInstallDateTime >= sInstallDaysThreshold * 24 * 60 * 60 * 1000; //msec
    }

    private static boolean shouldShowRateDialog() {
        return sIsAgreeShoWDialog && isOverLaunchTimes() && isOverInstallDate();
    }

}