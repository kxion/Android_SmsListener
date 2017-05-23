package ye.droid.smsintercept.service;

import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import ye.droid.smsintercept.handler.SmsHandler;
import ye.droid.smsintercept.mbinder.SmsBinder;
import ye.droid.smsintercept.observer.SmsObserver;
import ye.droid.smsintercept.receiver.SmsReceiver;

/**
 * 开启服务监听短信数据库
 * Created by ye on 2017/5/23.
 */
public class SmsService extends Service {
    private final String TAG = SmsReceiver.class.getSimpleName();

    private SmsObserver smsObserver;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new smsAgent();
    }

    private void openInter() {
        ContentResolver resolver = getContentResolver();
        smsObserver = new SmsObserver(resolver, new SmsHandler(this));
        resolver.registerContentObserver(Uri.parse("content://sms"), true, smsObserver);
        Toast.makeText(getApplicationContext(), "短信监听服务已经启动！", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "短信监听服务已经启动");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.getContentResolver().unregisterContentObserver(smsObserver);
        Toast.makeText(getApplicationContext(), "短信监听服务已经关闭！", Toast.LENGTH_SHORT).show();
        Process.killProcess(Process.myPid());
    }

    private void closeInter() {
    }

    private void getInterStatus() {
        Log.i(TAG, "getInterStatus...");
    }


    public class smsAgent extends Binder implements SmsBinder {

        @Override
        public void callOpenIntercept() {
            openInter();
        }

        @Override
        public void callCloseIntercept() {
            closeInter();
        }

        @Override
        public void callGetInterceptStatus() {
            getInterStatus();
        }
    }
}
