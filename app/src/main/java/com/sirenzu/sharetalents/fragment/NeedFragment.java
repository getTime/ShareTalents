package com.sirenzu.sharetalents.fragment;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.activity.MainActivity;
import com.sirenzu.sharetalents.adapter.MyDecoration;
import com.sirenzu.sharetalents.adapter.ServiceAdapter;
import com.sirenzu.sharetalents.model.CycleInfo;
import com.sirenzu.sharetalents.view.CycleViewPager;
import com.sirenzu.sharetalents.view.loadmore.WrapAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 需求
 */
public class NeedFragment extends AppBaseFragment implements View.OnClickListener{
    @BindView(R.id.rv_service)
    RecyclerView rvService;

    RadioButton rb1;
    RadioButton rb2;
    RadioButton rbAll;
    CycleViewPager mCycleViewPager;
//    ImageView ivHeadPic;
    private List<CycleInfo> mList = new ArrayList<>();
    private ServiceAdapter mServiceAdapter;
    // 数据适配器包装类
    private WrapAdapter<ServiceAdapter> mWrapAdapter;
    private LinearLayout llHeader;
    private MainActivity mainActivity;


    @Override
    public int getLayoutId() {
        return R.layout.fragment_need;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mainActivity = (MainActivity) getActivity();
        initView();
        initData();
        initAdapter();
    }
    private void initView() {
        llHeader = (LinearLayout) LayoutInflater.from(getActivity()).inflate(R.layout.header_home, null, false);
        mCycleViewPager = (CycleViewPager) llHeader.findViewById(R.id.cycle_view);
        rb1 = (RadioButton) llHeader.findViewById(R.id.rb1);
        rb2 = (RadioButton) llHeader.findViewById(R.id.rb2);
        rbAll = (RadioButton) llHeader.findViewById(R.id.rb_all);
//        ivHeadPic = (ImageView) llHeader.findViewById(R.id.iv_head_pic);
//        ivHeadPic.setOnClickListener(this);
        rb1.setChecked(true);
        rbAll.setChecked(true);
    }

    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvService.setLayoutManager(layoutManager);
        mServiceAdapter = new ServiceAdapter(getActivity());

        mWrapAdapter = new WrapAdapter<>(mServiceAdapter);
        mWrapAdapter.unRegisterAdapterDataObserver();

        rvService.setAdapter(mWrapAdapter);
        //这句就是添加我们自定义的分隔线
        rvService.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
        mWrapAdapter.addHeaderView(llHeader);
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

}
