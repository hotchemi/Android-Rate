package hotchemi.android.rate;

import android.net.Uri;
import android.test.AndroidTestCase;

/**
 * Unit test for {@link hotchemi.android.rate.UriUtil}
 */
public class UriUtilsTest extends AndroidTestCase {

    private static final String GOOGLE_PLAY = "https://play.google.com/store/apps/details?id=";

    public void testGetGooglePlayUri() {
        {
            Uri uri = UriUtil.getGooglePlayUri("");
            assertEquals(uri.toString(), GOOGLE_PLAY);
        }
        {
            final String packageName = "hotchemi.android.rate";
            Uri uri = UriUtil.getGooglePlayUri(packageName);
            assertEquals(uri.toString(), GOOGLE_PLAY + packageName);
        }
    }
}