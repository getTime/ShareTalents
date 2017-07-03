package com.sirenzu.sharetalents.activity.account;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kidney_hospital.base.util.RegxUtils;
import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;
import com.sirenzu.sharetalents.constant.HttpIdentifier;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterActivity extends AppBaseActivity {
    public static final String EXTRA_IS_REGISTER = "extra_is_register";
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_re_phone)
    EditText etRePhone;
    @BindView(R.id.et_re_code)
    EditText etReCode;
    @BindView(R.id.et_re_password)
    EditText etRePassword;
    @BindView(R.id.et_re_password_next)
    EditText etRePasswordNext;
    @BindView(R.id.btn_re_queren)
    Button btnReQueren;
    @BindView(R.id.btn_verify)
    Button btnVerify;
    @BindView(R.id.rl_protocol)
    RelativeLayout rlProtocol;
    private boolean isRegister;

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_register;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        isRegister = getIntent().getBooleanExtra(EXTRA_IS_REGISTER, false);
        if (isRegister) {
            rlProtocol.setVisibility(View.GONE);
        } else {
            rlProtocol.setVisibility(View.VISIBLE);
        }
        etRePhone.addTextChangedListener(new MyTextWatcher(etRePhone));
    }

    //======================================网络请求=========================================

    @Override
    public void onResponse(int identifier, String strReuslt) {
        switch (identifier) {
            case ERROR:
                showToast("网络异常");
                break;
            case HttpIdentifier.SEND_EMARL:
                mTimer.start();
                btnVerify.setEnabled(false);
                break;
        }
    }

    //======================================点击事件=========================================
    @OnClick({R.id.iv_title_left, R.id.xieyi, R.id.btn_verify})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                this.finish();
                break;
            case R.id.xieyi:
                break;
            case R.id.btn_verify:
                break;
        }
    }

    //======================================内部类=========================================
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
            if (mEt == etRePhone) {
                if ((RegxUtils.isEmailNO(strContent)
                        || RegxUtils.isMobileNo(strContent))) {
                    btnVerify.setEnabled(true);
                } else {
                    btnVerify.setEnabled(false);
                }
            }
        }
    }

    private CountDownTimer mTimer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btnVerify.setText(String.format(Locale.CHINA, "%d秒后重试", millisUntilFinished / 1000));
        }

        @Override
        public void onFinish() {
            btnVerify.setEnabled(true);
            btnVerify.setText("获取验证码");
        }
    };
}
