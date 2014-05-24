package hotchemi.android.rate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import static hotchemi.android.rate.DialogManager.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON;
import static hotchemi.android.rate.PreferenceUtils.clearSharedPreferences;

class RateDialogSupportFragment extends DialogFragment {

    public RateDialogSupportFragment() {
    }

    public static RateDialogSupportFragment newInstance(boolean showNeutralButton) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        RateDialogSupportFragment dialogFragment = new RateDialogSupportFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle params = getArguments();
        boolean isShowNeutralButton = params.getBoolean(BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.create(getActivity(), isShowNeutralButton);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        clearSharedPreferences(getActivity());
    }

}