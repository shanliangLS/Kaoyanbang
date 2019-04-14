package hehut.scse.kaoyanbang.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import hehut.scse.kaoyanbang.config.Config;

public class ShareUtil {
    public static void sendEmail(Context context, String title) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(
                "mailto:" + Config.email));
        context.startActivity(Intent.createChooser(intent, title));
    }
}
