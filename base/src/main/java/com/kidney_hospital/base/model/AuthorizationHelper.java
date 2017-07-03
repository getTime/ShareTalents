package com.kidney_hospital.base.model;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * 授权管理
 * Created by Lianxw on 2016/4/19.
 */
public class AuthorizationHelper {

    private static final String PREFERENCE_NAME = "account.xml";
    private static final String PREF_USER_ID = "PREF_USER_ID";
    private static final String PREF_USER_NAME = "PREF_USER_NAME";
    private static final String PREF_USER_AVATAR = "PREF_USER_AVATAR";
    private static final String PREF_USER_PHONE = "PREF_USER_PHONE";
    private static final String PREF_USER_SMALL_ID = "PREF_USER_SMALL_ID";
    private static final String PREF_IS_FRIST = "PREF_IS_FRIST";

    private SharedPreferences mPreferences;

    public AuthorizationHelper(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context should not be null");
        }
        mPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        return getPreference().getString(PREF_USER_ID, null) != null;
    }
    /**
     * 是否是第一次登录
     */
    private boolean isFirst;
    public void setFirstOpenState(boolean isFirst){
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putBoolean(PREF_IS_FRIST, isFirst);
        editor.apply();
    }
    public boolean isFirst(){
        return  getPreference().getBoolean(PREF_IS_FRIST, false);
    }
    /**
     * 注销登录
     */
    public void signOut() {
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putString(PREF_USER_ID, null);
        editor.apply();
    }

    /**
     * 保存登录信息
     *
     * @param info
     */
    public void saveAuthorization(UserInfo.DbBean info) {
        SharedPreferences.Editor editor = getPreference().edit();
        editor.putString(PREF_USER_ID, info.getUserId());
        editor.putString(PREF_USER_NAME, info.getUserName());
        editor.putString(PREF_USER_AVATAR, info.getHeadUrl());
        editor.putString(PREF_USER_PHONE, info.getPhone());
        editor.putInt(PREF_USER_SMALL_ID, info.getId());
        editor.apply();
    }

    /**
     * 获取登录信息
     *
     * @return
     */
    public UserInfo.DbBean getAuthorization() {
        SharedPreferences sp = getPreference();
        UserInfo.DbBean auth = new UserInfo.DbBean();
        auth.setUserId(sp.getString(PREF_USER_ID, ""));
        auth.setUserName(sp.getString(PREF_USER_NAME, ""));
        auth.setHeadUrl(sp.getString(PREF_USER_AVATAR, ""));
        auth.setPhone(sp.getString(PREF_USER_PHONE, ""));
        auth.setId(sp.getInt(PREF_USER_SMALL_ID, -1));
        return auth;
    }


    private SharedPreferences getPreference() {
        return mPreferences;
    }
}
