package ye.droid.smsintercept.observer;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import ye.droid.smsintercept.handler.SmsHandler;

/**
 * Created by ye on 2017/5/23.
 */

public class SmsObserver extends ContentObserver {
    private final String TAG = SmsObserver.class.getSimpleName();
    private ContentResolver resolver;
    private SmsHandler handler;

    public SmsObserver(ContentResolver resolver, SmsHandler handler) {
        super(handler);
        this.resolver = resolver;
        this.handler = handler;
    }

    @Override
    public void onChange(boolean selfChange) {
        super.onChange(selfChange);
        Log.i(TAG,"短信有改变了！");
    }
}
