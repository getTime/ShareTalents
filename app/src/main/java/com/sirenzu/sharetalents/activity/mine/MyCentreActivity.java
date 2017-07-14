package com.sirenzu.sharetalents.activity.mine;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.AppBaseActivity;
import com.sirenzu.sharetalents.adapter.ServiceAdapter;
import com.sirenzu.sharetalents.fragment.NeedFragment2;
import com.sirenzu.sharetalents.fragment.home.TaHelpFragment;
import com.sirenzu.sharetalents.utils.StatusBarCompat;
import com.sirenzu.sharetalents.view.loadmore.WrapAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 个人中心  主页
 */
public class MyCentreActivity extends AppBaseActivity {
//    @BindView(R.id.rv_mine_dynamic)
//    RecyclerView rvMineDynamic;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbarLayout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private List<Fragment> fragments = new ArrayList<>();
    private MyCentreActivity.FragmentAdapter fragmentAdapter;
    private WrapAdapter<ServiceAdapter> mWrapAdapter;
    private ServiceAdapter mServiceAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_centre;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        setTransparentStateBar();
        toolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        //设置收缩展开toolbar字体颜色
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        toolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
        initAdapter();
    }

    /**
     * 设置状态栏是否 FitsSystemWindows
     *  false 不修改，true 修改
     */
    @Override
    public void initViewSuper() {
        isFitsSystemWindows=false;
        super.initViewSuper();
    }

    private void initAdapter() {

        fragments.add(new TaHelpFragment());
        fragments.add(new TaHelpFragment());
        fragments.add(new TaHelpFragment());
        fragmentAdapter=new MyCentreActivity.FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);

//        mServiceAdapter = new ServiceAdapter(this);
//        mWrapAdapter = new WrapAdapter<>(mServiceAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        rvMineDynamic.setLayoutManager(layoutManager);
//        addHeadView();
//        rvMineDynamic.setAdapter(mWrapAdapter);
    }

    private void addHeadView() {
        View headView = LayoutInflater.from(this).inflate(R.layout.header_mine_centre, null, false);
        mWrapAdapter.addHeaderView(headView);
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        private String[] titles = {"我的动态", "我的服务","我的需求"};
        public Context context;

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        //重写这个方法，将设置每个Tab的标题
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }

}
