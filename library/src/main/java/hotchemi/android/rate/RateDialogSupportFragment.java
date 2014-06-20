package hotchemi.android.rate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

public final class RateDialogSupportFragment extends DialogFragment implements AppRateDialog {

    public RateDialogSupportFragment() {
    }

    public static RateDialogSupportFragment newInstance(boolean showNeutralButton, OnClickButtonListener listener) {
        RateDialogSupportFragment dialog = new RateDialogSupportFragment();
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