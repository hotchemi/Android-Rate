package hotchemi.android.rate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author Shintaro Katafuchi
 */
class RateDialogSupportFragment extends DialogFragment {

    public static RateDialogSupportFragment newInstance(boolean showNeutralButton) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        RateDialogSupportFragment dialogFragment = new RateDialogSupportFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    public RateDialogSupportFragment() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle params = getArguments();
        boolean isShowNeutralButton = params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.createDialog(getActivity(), isShowNeutralButton);
    }

}