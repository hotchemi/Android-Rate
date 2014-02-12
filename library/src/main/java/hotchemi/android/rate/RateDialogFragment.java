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
        boolean isShowNeutralButton =
                params.getBoolean(Constants.BUNDLE_KEY_IS_SHOW_NEUTRAL_BUTTON);
        return DialogManager.createDialog(getActivity(), isShowNeutralButton);
    }

}