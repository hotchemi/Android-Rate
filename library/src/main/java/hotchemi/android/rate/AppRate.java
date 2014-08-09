package hotchemi.android.rate;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.Date;

public class AppRate {

    private static final AppRate SINGLETON = new AppRate();

    private int installDate = 10;

    private int launchTimes = 10;

    private int remindInterval = 1;

    private int eventsTimes = -1;

    private boolean isShowNeutralButton = true;

    private boolean isDebug = false;

    private View view;

    private OnClickButtonListener listener;

    private AppRate() {
    }

    public static AppRate getInstance() {
        return SINGLETON;
    }

    public AppRate setLaunchTimes(int launchTimes) {
        this.launchTimes = launchTimes;
        return this;
    }

    public AppRate setInstallDays(int installDate) {
        this.installDate = installDate;
        return this;
    }

    public AppRate setRemindInterval(int remindInterval) {
        this.remindInterval = remindInterval;
        return this;
    }

    public AppRate setShowNeutralButton(boolean isShowNeutralButton) {
        this.isShowNeutralButton = isShowNeutralButton;
        return this;
    }

    public AppRate setEventsTimes(int eventsTimes) {
        this.eventsTimes = eventsTimes;
        return this;
    }

    public AppRate clearAgreeShowDialog(Context context) {
        PreferenceHelper.setAgreeShowDialog(context, true);
        return this;
    }

    public AppRate setDebug(boolean isDebug) {
        this.isDebug = isDebug;
        return this;
    }

    public AppRate setView(View view) {
        this.view = view;
        return this;
    }

    public AppRate setOnClickButtonListener(OnClickButtonListener listener) {
        this.listener = listener;
        return this;
    }

    public void monitor(Context context) {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
    }

    public static boolean showRateDialogIfMeetsConditions(Activity activity) {
        if (SINGLETON.isDebug || SINGLETON.shouldShowRateDialog(activity)) {
            SINGLETON.showRateDialog(activity);
            return true;
        }
        return false;
    }

    public static boolean passSignificantEvent(Activity activity) {
        if (SINGLETON.isDebug || SINGLETON.isOverEventPass(activity.getApplicationContext())) {
            SINGLETON.showRateDialog(activity);
            return true;
        } else {
            Context context = activity.getApplicationContext();
            int eventTimes = PreferenceHelper.getEventTimes(context);
            PreferenceHelper.setEventTimes(context, ++eventTimes);
            return false;
        }
    }

    public void showRateDialog(Activity activity) {
        DialogManager.create(activity, isShowNeutralButton, listener, view).show();
    }

    public boolean isOverEventPass(Context context) {
        return eventsTimes != -1 && PreferenceHelper.getEventTimes(context) > eventsTimes;
    }

    public boolean shouldShowRateDialog(Context context) {
        return PreferenceHelper.getIsAgreeShowDialog(context) &&
                isOverLaunchTimes(context) &&
                isOverInstallDate(context) &&
                isOverRemindDate(context);
    }

    private boolean isOverLaunchTimes(Context context) {
        return PreferenceHelper.getLaunchTimes(context) >= launchTimes;
    }

    private boolean isOverInstallDate(Context context) {
        return isOverDate(PreferenceHelper.getInstallDate(context), installDate);
    }

    private boolean isOverRemindDate(Context context) {
        return isOverDate(PreferenceHelper.getRemindInterval(context), remindInterval);
    }

    private boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= threshold * 24 * 60 * 60 * 1000;
    }

}