package cn.wqgallery.mylru;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

import cn.wqgallery.disklrucachelibrary.disk.DiskLruCacheHelper;


public class MainActivity extends AppCompatActivity {
    //内存缓存
    private LruCache<String, User> lruCache;

    //磁盘缓存
    private DiskLruCacheHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();


    }


    private void init() {
        //初始化 内存缓存LRU
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        int memoryClass = activityManager.getMemoryClass();
        lruCache = new LruCache<String, User>(memoryClass / 8 * 1024 * 1024);

        //初始化磁盘缓存 lru
        try {
            helper = new DiskLruCacheHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 内存缓存
     *
     * @param view
     */
    public void add(View view) {
        lruCache.put("1", new User("测试"));
    }

    public void get(View view) {
        User user = lruCache.get("1");
        String name = user.getName();

        Toast.makeText(this, "" + name, Toast.LENGTH_SHORT).show();

    }


    /**
     * 存的方法
     * helper.putObject(String key,T t)
     * put(String key, Bitmap bitmap)
     * put(String key, byte[] value)
     * put(String key, String value)
     * put(String key, JSONObject jsonObject)
     * put(String key, JSONArray jsonArray)
     * put(String key, Serializable value)
     * put(String key, Drawable value)
     * editor(String key).newOutputStream(0);//原有的方式
     *
     * @param view
     */

    public void addDisk(View view) {
        //存string
        helper.put("testString", "中国");

        //存对象转换json字符串
        User user = new User("李四");
        helper.putObject("user", user);


    }


    /**
     * 取
     * helper.getAsObjects(String key)
     * String getAsString(String key);
     * JSONObject getAsJson(String key)
     * JSONArray getAsJSONArray(String key)
     * <T> T getAsSerializable(String key)
     * Bitmap getAsBitmap(String key)
     * byte[] getAsBytes(String key)
     * Drawable getAsDrawable(String key)
     * InputStream getDisk(String key);//原有的用法
     *
     * @param view
     */
    public void getDisk(View view) {
        String testString = helper.getAsString("testString");
        Log.e("zdh", "---------->>" + testString);

        //注意bean对象需要有个空参数的构造方法
        User user = (User) helper.getAsObjects("user");
        Toast.makeText(this, "" + user.getName(), Toast.LENGTH_SHORT).show();

    }


}