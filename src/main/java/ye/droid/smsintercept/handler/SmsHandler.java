package ye.droid.smsintercept.handler;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import ye.droid.smsintercept.entity.SmsBean;

/**
 * 处理短信
 * Created by ye on 2017/5/23.
 */

public class SmsHandler extends Handler {
    private final String TAG = SmsHandler.class.getSimpleName();
    private Context context;

    public SmsHandler(Context context) {
        this.context = context;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        SmsBean smsBean = (SmsBean) msg.obj;
        if (smsBean.getAction() == 1) {
            Log.i(TAG, "已读...");
        } else if (smsBean.getAction() == 2) {
            Log.i(TAG, "删除...");
        }else {
        }
    }
}
