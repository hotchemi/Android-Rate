package hotchemi.android.rate;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

/**
 * @author Shintaro Katafuchi
 */
class RateDialogSupportFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle params = getArguments();
<<<<<<< HEAD
        boolean isShowNeutralButton =
                params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
=======
<<<<<<< HEAD
<<<<<<< HEAD
        boolean isShowNeutralButton = params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
=======
        boolean isShowNeutralButton =
                params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
>>>>>>> 3876ba2... v0.0.2
=======
        boolean isShowNeutralButton = params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
>>>>>>> 28d7a9d... v0.0.3 release.
>>>>>>> 7adac95... v0.0.3 release.
        return DialogManager.createDialog(getActivity(), isShowNeutralButton);
    }

}