package com.sirenzu.sharetalents.fragment.home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.adapter.MyDecoration;
import com.sirenzu.sharetalents.adapter.ServiceAdapter;
import com.sirenzu.sharetalents.fragment.AppBaseFragment;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TaHelpFragment extends AppBaseFragment {
    @BindView(R.id.rv_service)
    RecyclerView rvService;

    private ServiceAdapter mServiceAdapter;
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_ta_help;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initAdapter();
    }
    private void initAdapter() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvService.setLayoutManager(layoutManager);
        mServiceAdapter = new ServiceAdapter(getActivity());

        rvService.setAdapter(mServiceAdapter);
        //这句就是添加我们自定义的分隔线
        rvService.addItemDecoration(new MyDecoration(getActivity(), MyDecoration.VERTICAL_LIST));
    }
}
