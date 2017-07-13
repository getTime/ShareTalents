package com.sirenzu.sharetalents.activity.mine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;
import com.sirenzu.sharetalents.view.ClearEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 编辑 昵称  职业 等信息
 */
public class EditContentActivity extends AppBaseActivity {
    public static final String NICKNAME = "nickname";
    public static final String WORK = "work";
    public static final String AREA = "area";
    public static final String GOOD = "good";
    public static final String CERTIFICATE = "certificate";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.et_content)
    ClearEditText etContent;

    @Override
    public int getLayoutId() {
        return R.layout.activity_edit_content;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        String type = getIntent().getStringExtra(EditMaterialActivity.EXTRA_TYPE);
        if (NICKNAME.equals(type)) {
            tvTitle.setText("设置昵称");
            etContent.setHint("输入昵称");
        }
        tvTitleRight.setText("保存");
    }

    @OnClick({R.id.iv_title_left, R.id.tv_title_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_title_left:
                this.finish();
                break;
            case R.id.tv_title_right:
                this.finish();
                break;
        }
    }
}
