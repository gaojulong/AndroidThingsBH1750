package com.example.bkrc_109.mythingsbh1750;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import com.gaojulong.androidthings.bh1750.BH1750;


public class MainActivity extends Activity {

    //    private I2cDevice i2cDevice;
    private static final String TAG = "MainActivity";
    private static final int INTERVAL_BETWEEN_READ_MS = 1000;
    // I2C Device Name
    private static final String I2C_DEVICE_NAME = "I2C1";
    // I2C Slave Address
    private static final int I2C_ADDRESS = 0x23;

    private BH1750 mBH1750;

    private Handler handler =new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBH1750 = new BH1750(I2C_DEVICE_NAME,I2C_ADDRESS);
        mBH1750.initSetting();
        handler.post(mReadRunnable);
    }
    private Runnable mReadRunnable = new Runnable() {
        @Override
        public void run() {

            float ill = mBH1750.readBH1750Data();
            Log.d(TAG,"光照强度:"+(int)ill);
            handler.postDelayed(this,INTERVAL_BETWEEN_READ_MS);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBH1750.close();
    }
}
