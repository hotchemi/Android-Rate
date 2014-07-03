package hotchemi.android.rate;

import android.app.Activity;
import android.content.Context;

import java.util.Date;

public class AppRate {

    private static final AppRate SINGLETON = new AppRate();

    private static int sInstallDate = 10;

    private static int sLaunchTimes = 10;

    private static int sRemindInterval = 1;

    private static int sEventsTimes = -1;

    private static boolean sIsShowNeutralButton = true;

    private static boolean sIsDebug = false;

    private static OnClickButtonListener sListener;

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

    public static AppRate setOnClickButtonListener(OnClickButtonListener listener) {
        sListener = listener;
        return SINGLETON;
    }

    public static void monitor(Context context) {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
    }

    public static void showRateDialogIfMeetsConditions(Activity activity) {
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

    public static void showRateDialog(Activity activity) {
        DialogManager.create(activity, sIsShowNeutralButton, sListener).show();
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