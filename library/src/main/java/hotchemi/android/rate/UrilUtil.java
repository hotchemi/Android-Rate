package hotchemi.android.rate;

import android.net.Uri;

final class UriUtil {

    private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";

    private UriUtil() {
    }

    static Uri getGooglePlayUri(String packageName) {
        return Uri.parse(GOOGLE_PLAY + packageName);
    }

}