package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

public class AppRate {

    private static final AppRate SINGLETON = new AppRate();

    private static int sInstallDate = 10;

    private static int sLaunchTimes = 10;

    private static int sRemindInterval = 1;

    private static int sEventsTimes = -1;

    private static boolean sIsShowNeutralButton = true;

    private static boolean sIsDebug = false;

    private AppRate() {
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
        sEventsTimes = eventsTimes;
        return SINGLETON;
    }

    public static AppRate clearAgreeShowDialog(Context context) {
        PreferenceHelper.setAgreeShowDialog(context, true);
        return SINGLETON;
    }

    public static AppRate setDebug(boolean isDebug) {
        sIsDebug = isDebug;
        return SINGLETON;
    }

    /**
     * Monitor launch times and interval from installation.<br/>
     * Call this method when the activity is launched.
     */
    public static void monitor(Context context) {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
    }

    public static void showRateDialogIfMeetsConditions(Activity activity) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity);
    }

    public static void showRateDialogIfMeetsConditions(FragmentActivity activity) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity);
    }

    public static void passSignificantEvent(Activity activity) {
        if (sIsDebug || isOverEventPass(activity.getApplicationContext())) {
            showRateDialog(activity);
        } else {
            Context context = activity.getApplicationContext();
            int eventTimes = PreferenceHelper.getEventTimes(context);
            PreferenceHelper.setEventTimes(context, ++eventTimes);
        }
    }

    public static void passSignificantEvent(FragmentActivity activity) {
        if (sIsDebug || isOverEventPass(activity.getApplicationContext())) {
            showRateDialog(activity);
        } else {
            Context context = activity.getApplicationContext();
            int eventTimes = PreferenceHelper.getEventTimes(context);
            PreferenceHelper.setEventTimes(context, ++eventTimes);
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showRateDialog(Activity activity) {
        RateDialogFragment fragment = RateDialogFragment.newInstance(sIsShowNeutralButton);
        fragment.show(activity.getFragmentManager(), AppRate.class.getName());
    }

    public static void showRateDialog(FragmentActivity activity) {
        RateDialogSupportFragment fragment = RateDialogSupportFragment.newInstance(sIsShowNeutralButton);
        fragment.show(activity.getSupportFragmentManager(), AppRate.class.getName());
    }

    private static boolean isOverLaunchTimes(Context context) {
        return PreferenceHelper.getLaunchTimes(context) >= sLaunchTimes;
    }

    private static boolean isOverInstallDate(Context context) {
        return isOverDate(PreferenceHelper.getInstallDate(context), sInstallDate);
    }

    private static boolean isOverRemindDate(Context context) {
        return isOverDate(PreferenceHelper.getRemindInterval(context), sRemindInterval);
    }

    private static boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= threshold * 24 * 60 * 60 * 1000;
    }

    private static boolean isOverEventPass(Context context) {
        return sEventsTimes != -1 && PreferenceHelper.getEventTimes(context) > sEventsTimes;
    }

    private static boolean shouldShowRateDialog(Context context) {
        return PreferenceHelper.getIsAgreeShowDialog(context) &&
                isOverLaunchTimes(context) &&
                isOverInstallDate(context) &&
                isOverRemindDate(context);
    }

}