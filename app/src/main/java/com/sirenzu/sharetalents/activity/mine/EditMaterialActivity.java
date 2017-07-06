package com.sirenzu.sharetalents.activity.mine;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditMaterialActivity extends AppBaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_title_right)
    TextView tvTitleRight;
    @BindView(R.id.iv_head_pic)
    ImageView ivHeadPic;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mine_edit_material;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        tvTitle.setText("编辑个人资料");
    }

    @OnClick(R.id.iv_title_left)
    public void onViewClicked() {
        this.finish();
    }
}
