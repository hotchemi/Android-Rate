package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import static hotchemi.android.rate.DialogManager.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON;
import static hotchemi.android.rate.PreferenceUtils.clearSharedPreferences;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class RateDialogFragment extends DialogFragment {

    public RateDialogFragment() {
    }

    public static RateDialogFragment newInstance(boolean showNeutralButton) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        RateDialogFragment dialogFragment = new RateDialogFragment();
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