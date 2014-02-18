package hotchemi.android.rate;

import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * @author Shintaro Katafuchi
 */
public class UriUtilsTest extends AndroidTestCase {

    private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";

    public void testGetGooglePlayUri() {
        {
            Uri uri = UriUtils.getGooglePlayUri("");
            assertEquals(uri.toString(), GOOGLE_PLAY);
        }
        {
            final String packageName = "hotchemi.android.rate";
            Uri uri = UriUtils.getGooglePlayUri(packageName);
            assertEquals(uri.toString(), GOOGLE_PLAY + packageName);
        }
    }
}