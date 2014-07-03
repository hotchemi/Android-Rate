package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;

import java.util.Date;

public class AppRate {

    public static final String EXTRA_WHICH = "extra_which";


    private static final AppRate SINGLETON = new AppRate();

    private static int sInstallDate = 10;

    private static int sLaunchTimes = 10;

    private static int sRemindInterval = 1;

    private static int sEventsTimes = -1;

    private static boolean sIsShowNeutralButton = true;

    private static boolean sIsDebug = false;

    private static Fragment sTargetFragment;

    private static android.support.v4.app.Fragment sTargetSupportFragment;

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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static AppRate setTargetFragment(Fragment targetFragment) {
        sTargetFragment = targetFragment;
        return SINGLETON;
    }

    public static AppRate setTargetFragment(android.support.v4.app.Fragment targetFragment) {
        sTargetSupportFragment = targetFragment;
        return SINGLETON;
    }

    public static void monitor(Context context) {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
    }

    public static void showRateDialogIfMeetsConditions(Activity activity,int requestCode) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity,requestCode);
    }

    public static void showRateDialogIfMeetsConditions(FragmentActivity activity,int requestCode) {
        if (sIsDebug || shouldShowRateDialog(activity)) showRateDialog(activity,requestCode);
    }

    public static void passSignificantEvent(Activity activity,int requestCode) {
        if (sIsDebug || isOverEventPass(activity.getApplicationContext())) {
            showRateDialog(activity,requestCode);
        } else {
            setEventsTimes(activity.getApplicationContext());
        }
    }

    public static void passSignificantEvent(FragmentActivity activity,int requestCode) {
        if (sIsDebug || isOverEventPass(activity.getApplicationContext())) {
            showRateDialog(activity,requestCode);
        } else {
            setEventsTimes(activity.getApplicationContext());
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static void showRateDialog(Activity activity,int requestCode) {
        RateDialogFragment fragment = RateDialogFragment.getInstance(sIsShowNeutralButton,requestCode);
        if(sTargetFragment!=null){
            fragment.setTargetFragment(sTargetFragment,requestCode);
        }
        fragment.show(activity.getFragmentManager(), AppRate.class.getName());
    }

    public static void showRateDialog(FragmentActivity activity,int requestCode) {
        RateDialogSupportFragment fragment = RateDialogSupportFragment.newInstance(sIsShowNeutralButton,requestCode);
        if(sTargetSupportFragment!=null){
            fragment.setTargetFragment(sTargetSupportFragment,requestCode);
        }
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

    private static void setEventsTimes(Context context) {
        int eventTimes = PreferenceHelper.getEventTimes(context);
        PreferenceHelper.setEventTimes(context, ++eventTimes);
    }

}