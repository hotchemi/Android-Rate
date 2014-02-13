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