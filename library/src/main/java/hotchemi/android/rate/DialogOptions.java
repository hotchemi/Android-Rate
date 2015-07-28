package hotchemi.android.rate;

import android.view.View;

final class DialogOptions {

    private boolean showNeutralButton = true;

    private boolean showTitle = true;

    private int titleResId = R.string.rate_dialog_title;

    private int messageResId = R.string.rate_dialog_message;

    private int textPositiveResId = R.string.rate_dialog_ok;

    private int textNeutralResId = R.string.rate_dialog_cancel;

    private int textNegativeResId = R.string.rate_dialog_no;

    private View view;

    private OnClickButtonListener listener;

    public boolean shouldShowNeutralButton() {
        return showNeutralButton;
    }

    public void setShowNeutralButton(boolean showNeutralButton) {
        this.showNeutralButton = showNeutralButton;
    }

    public boolean shouldShowTitle() {
        return showTitle;
    }

    public void setShowTitle(boolean showTitle) {
        this.showTitle = showTitle;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    public int getMessageResId() {
        return messageResId;
    }

    public void setMessageResId(int messageResId) {
        this.messageResId = messageResId;
    }

    public int getTextPositiveResId() {
        return textPositiveResId;
    }

    public void setTextPositiveResId(int textPositiveResId) {
        this.textPositiveResId = textPositiveResId;
    }

    public int getTextNeutralResId() {
        return textNeutralResId;
    }

    public void setTextNeutralResId(int textNeutralResId) {
        this.textNeutralResId = textNeutralResId;
    }

    public int getTextNegativeResId() {
        return textNegativeResId;
    }

    public void setTextNegativeResId(int textNegativeResId) {
        this.textNegativeResId = textNegativeResId;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public OnClickButtonListener getListener() {
        return listener;
    }

    public void setListener(OnClickButtonListener listener) {
        this.listener = listener;
    }

}
