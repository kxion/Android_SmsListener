package ye.droid.smsintercept.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ye on 2017/5/23.
 */

public class SmsReceiver extends BroadcastReceiver {
    private final String TAG = SmsReceiver.class.getSimpleName();
    public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
    public static final String SMS_DELIVER_ACTION = "android.provider.Telephony.SMS_DELIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "接收短信执行了.....", Toast.LENGTH_LONG).show();
        Log.i(TAG, "接受短信！");
    }



}
