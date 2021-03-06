package com.sirenzu.sharetalents.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.mine.EditMaterialActivity;
import com.sirenzu.sharetalents.activity.mine.MyCentreActivity;
import com.sirenzu.sharetalents.adapter.ServiceAdapter;
import com.sirenzu.sharetalents.fragment.NeedFragment;
import com.sirenzu.sharetalents.fragment.NeedFragment2;
import com.sirenzu.sharetalents.fragment.ServiceFragment;

import java.io.FileDescriptor;

import butterknife.BindView;

import static com.sirenzu.sharetalents.R.*;

public class MainActivity extends AppBaseActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener, RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.drawer_layout)
    public DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    public NavigationView navigationView;
    @BindView(R.id.rb_need)
    RadioButton rbNeed;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.iv_head_pic)
    ImageView ivHeadPic;

    public FragmentManager fragmentManager;
    public static final int FRAGMENT_ONE = 1;
    public static final int FRAGMENT_TWO = 2;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutId() {
        return layout.activity_main;
    }

    private NeedFragment2 needFragment;
    private ServiceFragment serviceFragment;


    @Override
    public void initData(Bundle savedInstanceState) {
        drawerLayout.setFitsSystemWindows(true);
        initStatusBar(color.colorPrimary);
        initView();
        fragmentManager = getSupportFragmentManager();
        showFragment(FRAGMENT_ONE);
    }

    private void initView() {
        rbNeed.setChecked(true);
        //获取头布局文件
        View headerView = navigationView.getHeaderView(0);
        ImageView ivAvatar = (ImageView) headerView.findViewById(id.iv_avatar);
        ivAvatar.setOnClickListener(this);
        ivHeadPic.setOnClickListener(this);
        navigationView.setNavigationItemSelectedListener(this);
        rg.setOnCheckedChangeListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case id.iv_avatar:
                startActivity(new Intent(this, EditMaterialActivity.class));
                break;
            case R.id.iv_head_pic:
                drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = null;
        switch (id) {
            case R.id.nav_home:
                intent = new Intent(this, MyCentreActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
        return false;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case id.rb_need:
                showFragment(FRAGMENT_ONE);
                break;
            case id.rb_service:
                showFragment(FRAGMENT_TWO);
                break;
        }
    }


    public void showFragment(int index) {
        Log.e("tag", "showFragment");
        FragmentTransaction ft = fragmentManager.beginTransaction();
        hideFragment(ft);
        //注意这里设置位置
        position = index;
        switch (index) {
            case FRAGMENT_ONE:
                /**
                 * 如果Fragment为空，就新建一个实例
                 * 如果不为空，就将它从栈中显示出来
                 */
                if (needFragment == null) {
                    needFragment = new NeedFragment2();
                    ft.add(R.id.fl_fragment, needFragment);

                } else {
                    ft.show(needFragment);
                }
                break;
            case FRAGMENT_TWO:
                if (serviceFragment == null) {
                    serviceFragment = new ServiceFragment();
                    ft.add(R.id.fl_fragment, serviceFragment);
                } else {
                    ft.show(serviceFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    public void hideFragment(FragmentTransaction ft) {
        Log.e("tag", "hideFragment");
        //如果不为空，就先隐藏起来
        if (needFragment != null) {
            ft.hide(needFragment);
        }
        if (serviceFragment != null) {
            ft.hide(serviceFragment);
        }
    }


    public static final String POSITION = "position";

    /**
     * 解决屏幕旋转时：重复添加fragment。
     *
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //屏幕旋转时记录位置
        outState.putInt(POSITION, position);
        Log.e("tag", "onSaveInstanceState");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.e("tag", "onRestoreInstanceState");
        //屏幕恢复时取出位置
        showFragment(savedInstanceState.getInt(POSITION));
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("tag", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("tag", "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("tag", "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("tag", "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("tag", "onDestroy");
    }
}
