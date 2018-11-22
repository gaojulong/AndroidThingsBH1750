package com.example.bkrc_109.bh1750;

import android.util.Log;

import com.google.android.things.pio.I2cDevice;

import java.io.IOException;

public class BH1750 {
    private static final String TAG = BH1750.class.getSimpleName();
    private static final int CMD_PWR_OFF=0x00;  //关机
    private static final int CMD_PWR_ON=0x01 ;  //开机
    private static final int CMD_RESET=0x07;    //重置
    private static final int CMD_CHRES=0x10;    //持续高分辨率检测
    private static final int CMD_THRES=0x20;    //一次高分辨率
    private final byte[] mBuffer = new byte[2]; // for reading sensor values

    private I2cDevice i2cBH1750;
    public BH1750(I2cDevice i2cBH1750){
        this.i2cBH1750 = i2cBH1750;
    }

    public void initSetting(){
        try {
            i2cBH1750.write(new byte[]{(byte) CMD_PWR_ON}, 1);
            i2cBH1750.write(new byte[]{(byte) CMD_RESET}, 1);
            i2cBH1750.write(new byte[]{(byte) CMD_CHRES}, 1);
            i2cBH1750.write(new byte[]{(byte) CMD_PWR_OFF}, 1);
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"BH1750 Init Failure");
        }
    }



    // Read a register block
    public float readBH1750Data() throws IOException {
        i2cBH1750.write(new byte[]{(byte) CMD_PWR_ON}, 1);
        i2cBH1750.write(new byte[]{(byte) CMD_THRES},1);
        // Read three consecutive register values
        int rawLevel;
        i2cBH1750.read(mBuffer, 2);
        rawLevel = mBuffer[0] & 0xFF; // Unsigned int
        rawLevel <<= 8;
        rawLevel |= (mBuffer[1] & 0xFF); // Unsigned int
        return  convertRawValueToLux(rawLevel);
    }

    public float convertRawValueToLux(int rawLevel){
        return rawLevel / 1.2f;
    }

    public void close() {
        if (i2cBH1750 != null) {
            try {
                i2cBH1750.close();
                i2cBH1750 = null;
            } catch (IOException e) {
               e.printStackTrace();
            }
        }
    }
}
