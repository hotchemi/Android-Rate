package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public final class RateDialogFragment extends DialogFragment implements AppRateDialog {

    public RateDialogFragment() {
    }

    public static RateDialogFragment getInstance(boolean showNeutralButton, OnClickButtonListener listener) {
        RateDialogFragment dialog = new RateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        bundle.putSerializable(KEY_ON_CLICK_BUTTON_LISTENER, listener);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        boolean isShowNeutralButton = arguments.getBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON);
        OnClickButtonListener listener = (OnClickButtonListener) arguments.getSerializable(KEY_ON_CLICK_BUTTON_LISTENER);
        return DialogManager.create(getActivity(), isShowNeutralButton, listener);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        PreferenceHelper.clearSharedPreferences(getActivity());
    }

}