package hotchemi.android.rate;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

final class RateDialogSupportFragment extends DialogFragment implements BaseDialogFragment {

    public RateDialogSupportFragment() {
    }

    public static RateDialogSupportFragment newInstance(boolean showNeutralButton) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        RateDialogSupportFragment dialog = new RateDialogSupportFragment();
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle params = getArguments();
        boolean isShowNeutralButton = params.getBoolean(BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.create(getActivity(), isShowNeutralButton);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        PreferenceHelper.clearSharedPreferences(getActivity());
    }

}