package com.sirenzu.sharetalents.activity.account;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.kidney_hospital.base.util.RegxUtils;
import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;
import com.sirenzu.sharetalents.activity.MainActivity;
import com.sirenzu.sharetalents.activity.account.RegisterActivity;
import com.sirenzu.sharetalents.view.ClearEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/5/2.
 */

public class LoginActivity extends AppBaseActivity {
    @BindView(R.id.et_acount)
    ClearEditText etAcount;//账号
    @BindView(R.id.et_pwd)
    EditText etPwd;//密码
    @BindView(R.id.iv_eyes)
    ImageView ivEyes;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.rl_ctrl)
    RelativeLayout rlCtrl;

    private boolean blnFlagLook;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_login;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initStatusBar(R.color.bg_content);
        setListener();
    }

    private void setListener() {
        addLayoutLinstener(rlCtrl, btnCommit);
        etAcount.addTextChangedListener(new MyTextWatcher(etAcount));
        etPwd.addTextChangedListener(new MyTextWatcher(etPwd));
    }

    /**
     * 显示打开或者关闭密码的状态
     */
    private void showPwdState() {
        if (!blnFlagLook) {
            etPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            ivEyes.setImageResource(R.mipmap.ic_account_login_open_eye);
            blnFlagLook = true;
        } else {
            etPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            ivEyes.setImageResource(R.mipmap.ic_account_login_close_eye);
            blnFlagLook = false;
        }
        etPwd.setSelection(etPwd.getText().length());
    }

    @OnClick({R.id.btn_commit, R.id.btn_forgetpassword, R.id.btn_register, R.id.iv_eyes, R.id.btn_refresh})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_refresh:
                onRefresh();
                break;
            case R.id.btn_commit:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.btn_forgetpassword:
                intent = new Intent(this, RegisterActivity.class);
                intent.putExtra(RegisterActivity.EXTRA_IS_REGISTER, true);
                break;
            case R.id.btn_register:
                intent = new Intent(this, RegisterActivity.class);
                break;
            case R.id.iv_eyes:
                showPwdState();
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }

    /**
     * 不让软键盘遮住登录按钮
     *
     * @param main
     * @param scroll
     */
    public void addLayoutLinstener(final View main, final View scroll) {
        main.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Rect rect = new Rect();
                main.getWindowVisibleDisplayFrame(rect);
                //不可视高度
                int mainInvisibleHeight = main.getRootView().getHeight() - rect.bottom;
                //说明键盘弹起
                if (mainInvisibleHeight > 200) {
                    int[] location = new int[2];
                    scroll.getLocationInWindow(location);
                    int scrollViewHeight = scroll.getHeight();
                    int location1 = location[1];
                    int scrollHeight = (location1 + scrollViewHeight + 10) - rect.bottom;
                    if (scrollHeight > 0) {
                        main.scrollTo(0, scrollHeight);
                    }
                } else {
                    main.scrollTo(0, 0);
                }
            }
        });
    }

    //======================================重载的逻辑方法区=========================================
    class MyTextWatcher implements TextWatcher {
        private EditText mEt;

        public MyTextWatcher(EditText et) {
            this.mEt = et;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            String strContent = s.toString();
            if (mEt == etPwd) {
                if (!"".equals(strContent)) {
                    ivEyes.setVisibility(View.VISIBLE);
                } else {
                    ivEyes.setVisibility(View.GONE);
                }
            }
            isBtnEnabled();
        }
    }

    /**
     * 判断登录按钮是否可以点击
     */
    private void isBtnEnabled() {
        String account = etAcount.getText().toString();
        String pwd = etPwd.getText().toString();
        if ((RegxUtils.isEmailNO(account)
                || RegxUtils.isMobileNo(account)
                && !TextUtils.isEmpty(pwd))) {
            btnCommit.setEnabled(true);
        } else {
            btnCommit.setEnabled(false);
        }
    }
}
