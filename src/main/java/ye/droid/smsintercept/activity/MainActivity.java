package ye.droid.smsintercept.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import ye.droid.smsintercept.R;
import ye.droid.smsintercept.mbinder.SmsBinder;
import ye.droid.smsintercept.service.SmsService;

public class MainActivity extends AppCompatActivity {
    private final String TAG = MainActivity.class.getSimpleName();

    private ServiceConnection connection;
    private SmsBinder smsBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }

    private void initUI() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                smsBinder = (SmsBinder) service;
                Toast.makeText(getApplicationContext(), "服务已经连接!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(getApplicationContext(), "服务已经断开!", Toast.LENGTH_SHORT).show();
            }
        };
        Intent intent = new Intent(this, SmsService.class);
        bindService(intent, connection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }

    public void openIntercept(View view) {
        smsBinder.callOpenIntercept();
        Toast.makeText(this, "开启", Toast.LENGTH_SHORT).show();
    }

    public void closeIntercept(View view) {
        smsBinder.callCloseIntercept();
        Toast.makeText(this, "关闭", Toast.LENGTH_SHORT).show();
    }

    public void getInterceptStatus(View view) {
        smsBinder.callGetInterceptStatus();
        Toast.makeText(this, "获取SMS状态！", Toast.LENGTH_SHORT).show();
    }
}
