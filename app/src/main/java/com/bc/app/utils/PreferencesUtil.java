package com.bc.app.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Base64;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.bc.app.entity.User;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PreferencesUtil {

    private SharedPreferences preferences = null;
    private SharedPreferences.Editor editor = null;
    Object object;
    public static PreferencesUtil preferencesUtil;

    public static PreferencesUtil getInstance() {
        if (preferencesUtil == null) {
            synchronized (PreferencesUtil.class) {
                if (preferencesUtil == null) {
                    // 使用双重同步锁
                    preferencesUtil = new PreferencesUtil();
                }
            }
        }
        return preferencesUtil;
    }

    public void init(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context
                .getApplicationContext());
    }

    /**
     * 问题在于，这个Context哪来的我们不能确定，很大的可能性，你在某个Activity里面为了方便，直接传了个this;
     * 这样问题就来了，我们的这个类中的sInstance是一个static且强引用的，在其内部引用了一个Activity作为Context，也就是说，
     * 我们的这个Activity只要我们的项目活着，就没有办法进行内存回收。而我们的Activity的生命周期肯定没这么长，造成了内存泄漏。
     * 所以这里使用context.getApplicationContext()
     */
    private PreferencesUtil() {

    }

    /**
     * 保存数据(所有的类型都适用)
     *
     * @param key    键
     * @param object 值
     */
    public synchronized void saveParam(String key, Object object) {
        if (editor == null)
            editor = preferences.edit();
        // 得到object的类型
        String type = object.getClass().getSimpleName();
        if ("String".equals(type)) {
            // 保存String 类型
            editor.putString(key, (String) object);
        } else if ("Integer".equals(type)) {
            // 保存integer 类型
            editor.putInt(key, (Integer) object);
        } else if ("Boolean".equals(type)) {
            // 保存 boolean 类型
            editor.putBoolean(key, (Boolean) object);
        } else if ("Float".equals(type)) {
            // 保存float类型
            editor.putFloat(key, (Float) object);
        } else if ("Long".equals(type)) {
            // 保存long类型
            editor.putLong(key, (Long) object);
        } else {
            if (!(object instanceof Serializable)) {
                throw new IllegalArgumentException(object.getClass().getName() + " 必须实现Serializable接口!");
            }

            // 不是基本类型则是保存对象
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(stream);
                oos.writeObject(object);
                String productBase64 = Base64.encodeToString(
                        stream.toByteArray(), Base64.DEFAULT);
                editor.putString(key, productBase64);
                Log.d(this.getClass().getSimpleName(), "save object success");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(this.getClass().getSimpleName(), "save object error");
            }
        }
        editor.commit();
    }

    /**
     * 移除信息
     */
    public synchronized void remove(String key) {
        if (editor == null)
            editor = preferences.edit();
        editor.remove(key);
        editor.commit();
    }


    /**
     * 获取数据(所有类型都适用)
     *
     * @param key           键
     * @param defaultObject 默认值
     * @return 值
     */
    public Object getParam(String key, Object defaultObject) {
        if (defaultObject == null) {
            return getObject(key);
        }

        String type = defaultObject.getClass().getSimpleName();

        if ("String".equals(type)) {
            return preferences.getString(key, (String) defaultObject);
        } else if ("Integer".equals(type)) {
            return preferences.getInt(key, (Integer) defaultObject);
        } else if ("Boolean".equals(type)) {
            return preferences.getBoolean(key, (Boolean) defaultObject);
        } else if ("Float".equals(type)) {
            return preferences.getFloat(key, (Float) defaultObject);
        } else if ("Long".equals(type)) {
            return preferences.getLong(key, (Long) defaultObject);
        }
        return getObject(key);
    }

    /**
     * 是否第一次使用
     *
     * @return true: 是  false: 否
     */
    public boolean isFirst() {
        return (Boolean) getParam("isFirst", true);
    }

    /**
     * 设置是否第一次使用
     *
     * @param isFirst 是否第一次使用
     */
    public void setFirst(Boolean isFirst) {
        saveParam("isFirst", isFirst);
    }

    /**
     * 是否登录
     *
     * @return true: 是  false: 否
     */
    public boolean isLogin() {
        return (Boolean) getParam("isLogin", false);
    }

    /**
     * 设置是否登录
     *
     * @param isLogin 是否登录
     */
    public void setLogin(Boolean isLogin) {
        saveParam("isLogin", isLogin);
    }


    public void setUser(User user) {
        saveParam("user", JSON.toJSONString(user));
    }

    public User getUser() {
        User user;
        try {
            user = JSON.parseObject((String) getParam("user", ""), User.class);
        } catch (Exception e) {
            user = new User();
        }
        return user;
    }

    public Object getObject(String key) {
        String wordBase64 = preferences.getString(key, "");
        byte[] base64 = Base64.decode(wordBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64);
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = objectInputStream.readObject();
            Log.d(this.getClass().getSimpleName(), "Get object success");
            return object;
        } catch (Exception e) {
            Log.d(this.getClass().getSimpleName(), "Get object is error");
        }
        Log.e(this.getClass().getSimpleName(), "Get object is error");
        return null;
    }

    public <T> void setList(String key, List<T> list) {
        saveParam(key, JSON.toJSONString(list));
    }

    public <T> List<T> getList(String key, Class<T> clazz) {
        List<T> list;
        try {
            list = JSON.parseArray((String) getParam(key, ""), clazz);
        } catch (Exception e) {
            list = new ArrayList<>();
        }
        return list;
    }
}
