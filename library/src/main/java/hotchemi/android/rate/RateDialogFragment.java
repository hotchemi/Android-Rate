package hotchemi.android.rate;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;

/**
 * @author Shintaro Katafuchi
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
class RateDialogFragment extends DialogFragment {

    public RateDialogFragment() {
    }

    public static RateDialogFragment newInstance(boolean showNeutralButton) {
        final Bundle bundle = new Bundle();
        bundle.putBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        RateDialogFragment dialogFragment = new RateDialogFragment();
        dialogFragment.setArguments(bundle);
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle params = getArguments();
        boolean isShowNeutralButton = params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.create(getActivity(), isShowNeutralButton);
    }

}