package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

import static hotchemi.android.rate.PreferenceHelper.getInstallDate;
import static hotchemi.android.rate.PreferenceHelper.getIsAgreeShowDialog;
import static hotchemi.android.rate.PreferenceHelper.getLaunchTimes;
import static hotchemi.android.rate.PreferenceHelper.getRemindInterval;
import static hotchemi.android.rate.PreferenceHelper.isFirstLaunch;
import static hotchemi.android.rate.PreferenceHelper.setAgreeShowDialog;
import static hotchemi.android.rate.PreferenceHelper.setInstallDate;
import static hotchemi.android.rate.RateDialogSupportFragment.newInstance;

public class AppRate {

    private static final String TAG = AppRate.class.getName();
    private static final AppRate SINGLETON = new AppRate();
    private static int sInstallDate = 10;
    private static int sLaunchTimes = 10;
    private static int sRemindInterval = 1;
    private static int sEventsTimes = 0;
    private static int sEventsTimesThreshold = -1;
    private static boolean sIsShowNeutralButton = true;
    private static boolean sIsDebug = false;

    protected AppRate() {
    }

    public static AppRate setLaunchTimes(int launchTimes) {
        sLaunchTimes = launchTimes;
        return SINGLETON;
    }

    public static AppRate setInstallDays(int installDays) {
        sInstallDate = installDays;
        return SINGLETON;
    }

    public static AppRate setRemindInterval(int remindInterval) {
        sRemindInterval = remindInterval;
        return SINGLETON;
    }

    public static AppRate setShowNeutralButton(boolean isShowNeutralButton) {
        sIsShowNeutralButton = isShowNeutralButton;
        return SINGLETON;
    }

    public static AppRate setEventsTimes(int eventsTimes) {
        sEventsTimesThreshold = eventsTimes;
        return SINGLETON;
    }

    public static AppRate clearAgreeShowDialog(Context context) {
        setAgreeShowDialog(context, true);
        return SINGLETON;
    }

    /**
     * Set debug flag.<br/>
     * when debug flag is true, always show rating dialog.
     *
     * @param isDebug debug flag
     */
    public static AppRate setDebug(boolean isDebug) {
        sIsDebug = isDebug;
        return SINGLETON;
    }

    /**
     * Monitor launch times and interval from installation.<br/>
     * Call this method when the launcher activity is launched.
     */
    public static void monitor(Context context) {
        if (isFirstLaunch(context)) {
            setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, getLaunchTimes(context) + 1);
    }

    public static void showRateDialogIfMeetsConditions(Activity activity) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity);
    }

    public static void showRateDialogIfMeetsConditions(FragmentActivity activity) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity);
    }

    public static void passSignificantEvent(Activity activity) {
        if (sIsDebug || isOverEventPass()) showRateDialog(activity);
    }

    public static void passSignificantEvent(FragmentActivity activity) {
        if (sIsDebug || isOverEventPass()) showRateDialog(activity);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showRateDialog(Activity activity) {
        RateDialogFragment fragment = RateDialogFragment.newInstance(sIsShowNeutralButton);
        fragment.show(activity.getFragmentManager(), TAG);
    }

    public static void showRateDialog(FragmentActivity activity) {
        RateDialogSupportFragment fragment = newInstance(sIsShowNeutralButton);
        fragment.show(activity.getSupportFragmentManager(), TAG);
    }

    private static boolean isOverLaunchTimes(Context context) {
        return getLaunchTimes(context) >= sLaunchTimes;
    }

    private static boolean isOverInstallDate(Context context) {
        return isOverDate(getInstallDate(context), sInstallDate);
    }

    private static boolean isOverRemindDate(Context context) {
        return isOverDate(getRemindInterval(context), sRemindInterval);
    }

    private static boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= threshold * 24 * 60 * 60 * 1000;
    }

    private static boolean isOverEventPass() {
        return sEventsTimesThreshold != -1 && ++sEventsTimes > sEventsTimesThreshold;
    }

    private static boolean shouldShowRateDialog(Context context) {
        return getIsAgreeShowDialog(context) &&
                isOverLaunchTimes(context) &&
                isOverInstallDate(context) &&
                isOverRemindDate(context);
    }

}