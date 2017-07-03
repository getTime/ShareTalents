package com.sirenzu.sharetalents.view.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sirenzu.sharetalents.R;


/**
 * zhangyao
 * 16/9/4
 * zhangyao@jiandanxinli.com
 */
public class LoadingMoreFooter extends LinearLayout {

    private Context context;
    private LinearLayout loading_view_layout;
    private LinearLayout end_layout;
    private TextView tvDaodi, tvMorePull;

    public LoadingMoreFooter(Context context) {
        super(context);
        this.context = context;
        initView(context);
    }

    /**
     * @param context
     * @param attrs
     */
    public LoadingMoreFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        setGravity(Gravity.CENTER);
        setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.footer_layout,
                null);
        loading_view_layout = (LinearLayout) view.findViewById(R.id.loading_view_layout);
        end_layout = (LinearLayout) view.findViewById(R.id.end_layout);
        tvDaodi = (TextView) end_layout.findViewById(R.id.tv_daodi);
        tvMorePull = (TextView) end_layout.findViewById(R.id.tv_more_pull);
        addView(view);
    }


    //设置底部加载中效果
    public void addFootLoadingView() {
        setVisibility(VISIBLE);
        loading_view_layout.setVisibility(VISIBLE);
        end_layout.setVisibility(GONE);
    }

    //设置底部到底了布局
    public void addFootEndView() {
        end_layout.setVisibility(VISIBLE);
        tvMorePull.setVisibility(GONE);
        tvDaodi.setVisibility(VISIBLE);
        loading_view_layout.setVisibility(GONE);
    }

    //设置 上拉加载  字样
    public void addPullupView() {
        end_layout.setVisibility(VISIBLE);
        tvMorePull.setVisibility(VISIBLE);
        tvDaodi.setVisibility(GONE);
        loading_view_layout.setVisibility(GONE);
    }


    //设置已经没有更多数据
    public void setEnd() {
        setVisibility(VISIBLE);
        addFootEndView();
    }


    public void initLoadView() {
        addPullupView();
    }


    public void setGone() {
        setVisibility(GONE);
    }

}
