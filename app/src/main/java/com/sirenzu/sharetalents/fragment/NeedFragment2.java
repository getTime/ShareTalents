package com.sirenzu.sharetalents.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.MainActivity;
import com.sirenzu.sharetalents.adapter.MyDecoration;
import com.sirenzu.sharetalents.adapter.ServiceAdapter;
import com.sirenzu.sharetalents.fragment.home.HelpTaFragment;
import com.sirenzu.sharetalents.fragment.home.TaHelpFragment;
import com.sirenzu.sharetalents.model.CycleInfo;
import com.sirenzu.sharetalents.view.CycleViewPager;
import com.sirenzu.sharetalents.view.loadmore.WrapAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 需求
 */
public class NeedFragment2 extends AppBaseFragment implements View.OnClickListener {
    @BindView(R.id.cycle_view)
    CycleViewPager mCycleViewPager;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    private List<CycleInfo> mList = new ArrayList<>();
    private MainActivity mainActivity;
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentAdapter fragmentAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_need2;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();

        initView();
        initData();
    }

    private void initView() {
        fragments.add(new TaHelpFragment());
        fragments.add(new TaHelpFragment());
        fragmentAdapter=new FragmentAdapter(getChildFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        mList.add(new CycleInfo("标题1", "http://img2.3lian.com/2014/c7/25/d/40.jpg"));
        mList.add(new CycleInfo("标题2", "http://img2.3lian.com/2014/c7/25/d/41.jpg"));
        mList.add(new CycleInfo("标题3", "http://imgsrc.baidu.com/forum/pic/item/b64543a98226cffc8872e00cb9014a90f603ea30.jpg"));
        mList.add(new CycleInfo("标题4", "http://imgsrc.baidu.com/forum/pic/item/261bee0a19d8bc3e6db92913828ba61eaad345d4.jpg"));
        initCycleView();
    }

    /**
     * 初始化View
     */
    private void initCycleView() {
        mCycleViewPager.setIndicators(R.mipmap.ic_ad_selected, R.mipmap.ic_ad_normal);
        //设置轮播间隔时间，默认为4000
        mCycleViewPager.setDelay(4000);
        mCycleViewPager.setData(mList, mAdCycleViewListener);
    }

    /**
     * 轮播图点击监听
     */
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(CycleInfo info, int position, View imageView) {

            if (mCycleViewPager.isCycle()) {
                position = position - 1;
            }
            Toast.makeText(getActivity(), info.getTitle() + "选择了--" + position, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head_pic:
                mainActivity.drawerLayout.openDrawer(Gravity.LEFT);
                break;
        }
    }

    class FragmentAdapter extends FragmentPagerAdapter {
        private String[] titles = {"Ta帮我", "我帮Ta"};
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
