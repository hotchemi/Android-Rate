package hotchemi.android.rate;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import java.util.Date;

public class AppRate {

    private static final AppRate SINGLETON = new AppRate();

    private int mInstallDate = 10;

    private int mLaunchTimes = 10;

    private int mRemindInterval = 1;

    private int mEventsTimes = -1;

    private boolean mIsShowNeutralButton = true;

    private boolean mIsDebug = false;

    private View mView;

    private OnClickButtonListener mListener;

    private AppRate() {
    }

    public static AppRate build() {
        return SINGLETON;
    }

    public AppRate setLaunchTimes(int launchTimes) {
        mLaunchTimes = launchTimes;
        return this;
    }

    public AppRate setInstallDays(int installDays) {
        mInstallDate = installDays;
        return this;
    }

    public AppRate setRemindInterval(int remindInterval) {
        mRemindInterval = remindInterval;
        return this;
    }

    public AppRate setShowNeutralButton(boolean isShowNeutralButton) {
        mIsShowNeutralButton = isShowNeutralButton;
        return this;
    }

    public AppRate setEventsTimes(int eventsTimes) {
        mEventsTimes = eventsTimes;
        return this;
    }

    public AppRate clearAgreeShowDialog(Context context) {
        PreferenceHelper.setAgreeShowDialog(context, true);
        return this;
    }

    public AppRate setDebug(boolean isDebug) {
        mIsDebug = isDebug;
        return this;
    }

    public AppRate setView(View view) {
        mView = view;
        return this;
    }

    public AppRate setOnClickButtonListener(OnClickButtonListener listener) {
        mListener = listener;
        return this;
    }

    public AppRate monitor(Context context) {
        if (PreferenceHelper.isFirstLaunch(context)) {
            PreferenceHelper.setInstallDate(context);
        }
        PreferenceHelper.setLaunchTimes(context, PreferenceHelper.getLaunchTimes(context) + 1);
        return this;
    }

    public boolean showRateDialogIfMeetsConditions(Activity activity) {
        if (mIsDebug || shouldShowRateDialog(activity)) {
            showRateDialog(activity);
            return true;
        }
        return false;
    }

    public boolean passSignificantEvent(Activity activity) {
        if (mIsDebug || isOverEventPass(activity.getApplicationContext())) {
            showRateDialog(activity);
            return true;
        } else {
            Context context = activity.getApplicationContext();
            int eventTimes = PreferenceHelper.getEventTimes(context);
            PreferenceHelper.setEventTimes(context, ++eventTimes);
            return false;
        }
    }

    public void showRateDialog(Activity activity) {
        DialogManager.create(activity, mIsShowNeutralButton, mListener, mView).show();
    }

    public boolean isOverEventPass(Context context) {
        return mEventsTimes != -1 && PreferenceHelper.getEventTimes(context) > mEventsTimes;
    }

    public boolean shouldShowRateDialog(Context context) {
        return PreferenceHelper.getIsAgreeShowDialog(context) &&
                isOverLaunchTimes(context) &&
                isOverInstallDate(context) &&
                isOverRemindDate(context);
    }

    private boolean isOverLaunchTimes(Context context) {
        return PreferenceHelper.getLaunchTimes(context) >= mLaunchTimes;
    }

    private boolean isOverInstallDate(Context context) {
        return isOverDate(PreferenceHelper.getInstallDate(context), mInstallDate);
    }

    private boolean isOverRemindDate(Context context) {
        return isOverDate(PreferenceHelper.getRemindInterval(context), mRemindInterval);
    }

    private boolean isOverDate(long targetDate, int threshold) {
        return new Date().getTime() - targetDate >= threshold * 24 * 60 * 60 * 1000;
    }

}