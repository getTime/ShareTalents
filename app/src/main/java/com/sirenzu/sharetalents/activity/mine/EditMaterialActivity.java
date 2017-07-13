package com.sirenzu.sharetalents.activity.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.BottomSheetDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  编辑 个人 资料
 */
public class EditMaterialActivity extends AppBaseActivity {

    public static final String EXTRA_TYPE="type";

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

    @OnClick({R.id.iv_title_left,R.id.rl_head_pic,R.id.rl_nickname})
    public void onViewClicked(View view) {
        Intent intent=null;
        switch (view.getId()) {
            case R.id.iv_title_left:
                this.finish();
                break;
            case R.id.rl_head_pic:
                showChooseAvatarDialog();
                break;
            case R.id.rl_nickname:
                intent=new Intent(this,EditContentActivity.class);
                intent.putExtra(EXTRA_TYPE,EditContentActivity.NICKNAME);
                break;
        }
        if(intent!=null){
            startActivity(intent);
        }
    }

    private void showChooseAvatarDialog() {
        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_choose_avatar, null);
        TextView tvPhotoGraph = (TextView) view.findViewById(R.id.tv_photo_graph);
        TextView tvPhotoAlbum = (TextView) view.findViewById(R.id.tv_photo_album);
        TextView tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvPhotoGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                File file = new File(Environment.getExternalStorageDirectory(), "/temp/" + System.currentTimeMillis() + ".jpg");
//                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
//                Uri imageUri = Uri.fromFile(file);
//                getTakePhoto().onPickFromCaptureWithCrop(imageUri, getCropOptions());
            }
        });
        tvPhotoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getTakePhoto().onPickMultiple(1);
            }
        });
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setContentView(view);
        dialog.show();
    }

}
