package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

import static hotchemi.android.rate.PreferenceUtils.getInstallDate;
import static hotchemi.android.rate.PreferenceUtils.getIsAgreeShowDialog;
import static hotchemi.android.rate.PreferenceUtils.getLaunchTimes;
import static hotchemi.android.rate.PreferenceUtils.getRemindInterval;
import static hotchemi.android.rate.PreferenceUtils.isFirstLaunch;
import static hotchemi.android.rate.PreferenceUtils.setInstallDate;

/**
 * Help you promote your app by prompting users to rate the app after using it for a few days.
 */
public class AppRate {

    private static final String TAG = AppRate.class.getName();

    private static final AppRate INSTANCE = new AppRate();

    private static long sInstallDate = new Date().getTime();

    private static int sInstallDateThreshold = 10;

    private static int sLaunchTimes = 0;

    private static int sLaunchTimesThreshold = 10;

    private static long sRemindInterval = 0;

    private static int sRemindIntervalThreshold = 1;

    private static int sEventsUntilPrompt = 0;

    private static int sEventsUntilPromptThreshold = -1;

    private static boolean sIsAgreeShowDialog = true;

    private static boolean sIsShowNeutralButton = true;

    private static boolean sIsDebug = false;

    protected AppRate() {
    }

    public static AppRate setLaunchTimes(int launchTimesThreshold) {
        sLaunchTimesThreshold = launchTimesThreshold;
        return INSTANCE;
    }

    public static AppRate setInstallDays(int installDaysThreshold) {
        sInstallDateThreshold = installDaysThreshold;
        return INSTANCE;
    }

    public static AppRate setRemindInterval(int remindInterval) {
        sRemindIntervalThreshold = remindInterval;
        return INSTANCE;
    }

    public static AppRate setShowNeutralButton(boolean isShowNeutralButton) {
        sIsShowNeutralButton = isShowNeutralButton;
        return INSTANCE;
    }

    public static AppRate setEventsUntilPrompt(int eventsUntilPrompt) {
        sEventsUntilPromptThreshold = eventsUntilPrompt;
        return INSTANCE;
    }

    public static AppRate clearAgreeShowDialog(Context context) {
        PreferenceUtils.setAgreeShowDialog(context, true);
        return INSTANCE;
    }

    /**
     * Set debug flag.<br/>
     * when debug flag is true, always show rating dialog.
     *
     * @param isDebug set debug flag
     */
    public static AppRate setDebug(boolean isDebug) {
        sIsDebug = isDebug;
        return INSTANCE;
    }

    /**
     * Monitor launch times and interval from installation.<br/>
     * Call this method when the launcher activity is launched.
     */
    public static void monitor(Context context) {
        if (isFirstLaunch(context)) {
            setInstallDate(context, sInstallDate);
        }
        PreferenceUtils.setLaunchTimes(context, getLaunchTimes(context) + 1);
        sInstallDate = getInstallDate(context);
        sLaunchTimes = getLaunchTimes(context);
        sIsAgreeShowDialog = getIsAgreeShowDialog(context);
        sRemindInterval = getRemindInterval(context);
    }

    public static void showRateDialogIfMeetsConditions(Activity activity) {
        if (sIsDebug || shouldShowRateDialog())
            showRateDialog(activity instanceof FragmentActivity ? (FragmentActivity) activity : activity);
    }

    public static void passSignificantEvent(Activity activity) {
        if (sIsDebug || isOverEventsPass())
            showRateDialog(activity instanceof FragmentActivity ? (FragmentActivity) activity : activity);
    }

    public static void showRateDialog(FragmentActivity activity) {
        RateDialogSupportFragment fragment = RateDialogSupportFragment.newInstance(sIsShowNeutralButton);
        fragment.show(activity.getSupportFragmentManager(), TAG);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showRateDialog(Activity activity) {
        RateDialogFragment fragment = RateDialogFragment.newInstance(sIsShowNeutralButton);
        fragment.show(activity.getFragmentManager(), TAG);
    }

    private static boolean isOverLaunchTimes() {
        return sLaunchTimes >= sLaunchTimesThreshold;
    }

    private static boolean isOverInstallDate() {
        return new Date().getTime() - sInstallDate >= sInstallDateThreshold * 24 * 60 * 60 * 1000;
    }

    private static boolean isOverRemindDate() {
        return new Date().getTime() - sRemindInterval >= sRemindIntervalThreshold * 24 * 60 * 60 * 1000;
    }

    private static boolean isOverEventsPass() {
        return sEventsUntilPromptThreshold != -1 && ++sEventsUntilPrompt > sEventsUntilPromptThreshold;
    }

    private static boolean shouldShowRateDialog() {
        return sIsAgreeShowDialog && isOverLaunchTimes() && isOverInstallDate() && isOverRemindDate();
    }

}