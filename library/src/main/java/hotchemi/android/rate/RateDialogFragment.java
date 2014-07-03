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

    public static RateDialogFragment getInstance(boolean showNeutralButton,int requestCode) {
        RateDialogFragment dialog = new RateDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON, showNeutralButton);
        bundle.putInt(KEY_REQUEST_CODE,requestCode);
        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        boolean isShowNeutralButton = arguments.getBoolean(KEY_IS_SHOW_NEUTRAL_BUTTON);
        int requestCode = arguments.getInt(KEY_REQUEST_CODE);
        OnClickButtonListener listener = null;
        if(getTargetFragment() instanceof OnClickButtonListener){
            listener = (OnClickButtonListener)getTargetFragment();
        }else if(getActivity() instanceof OnClickButtonListener){
            listener = (OnClickButtonListener)getActivity();
        }
        return DialogManager.create(getActivity(), isShowNeutralButton, listener,requestCode);
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        PreferenceHelper.clearSharedPreferences(getActivity());
    }

}