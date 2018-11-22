# Android Things BH1750   
[![](https://jitpack.io/v/gaojulong/AndroidThingsBH1750.svg)](https://jitpack.io/#gaojulong/AndroidThingsBH1750)

用于 android things 的BH1750 驱动程序  
该驱动器支持ROHM BH1750环境光传感器。  

注意：这些驱动程序不支持生产。作为Developer Preview版本的一部分，它们作为常见外围设备的Android Things用户空间驱动程序的示例实现提供。无法保证正确性，完整性或稳健性。

### 如何使用驱动程序  
---
**在 Modeule ：app 的Gradle里添加依赖**

~~~shell
dependencies {
...
	implementation 'com.github.gaojulong:AndroidThingsBH1750:1.0.5'
}
~~~

**在Project Gradle里添加下面连接**

~~~shell
allprojects {
    repositories {
      ...
        maven { url 'https://www.jitpack.io' }
    }
}
~~~

### 样品用法

---

~~~java
public class MainActivity extends Activity {
    // I2C 设备名称
    private static final String I2C_DEVICE_NAME = "I2C1";
    // I2C 地址
    private static final int I2C_ADDRESS = 0x23;
    private static final String TAG = MainActivity.class.getSimpleName();
    private BH1750 mBH1750 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBH1750 = new BH1750(I2C_DEVICE_NAME,I2C_ADDRESS);//实例化I2C
        mBH1750.initSetting();//初始化设置
        int ill = (int) mBH1750.readBH1750Data();//读取光照强度
        Log.d(TAG,"光照度"+ill);

    }

}
~~~

![](https://raw.githubusercontent.com/alvarowolfx/bh1750-androidthings/master/assets/schematic.png)