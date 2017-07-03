package com.sirenzu.sharetalents.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.kidney_hospital.base.activity.BaseActivity;
import com.kidney_hospital.base.config.DefaultConfig;
import com.sirenzu.sharetalents.R;
import com.sirenzu.sharetalents.utils.NetWorkUtils;
import com.sirenzu.sharetalents.utils.StatusBarCompat;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.Locale;

import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class AppBaseActivity extends BaseActivity {
    private static final String NETWORK_ERROR = "network_error";
    private static final String REQUEST_RESULT = "request_result";
    private static final String GET_REQUEST_BODY = "get_request_body";
    private static final String POST_REQUEST_BODY = "post_request_body";
    /**
     * 系统容器布局
     */
    private FrameLayout baseContent;
    private View childView;
    private RelativeLayout rlError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_layout_content);
        baseContent = (FrameLayout) findViewById(R.id.content);
        initView();
        showView();
        ButterKnife.bind(this);
        initData(savedInstanceState);
        loadData();
    }

    public void initStatusBar(int color) {
        View statusBar = new View(this);
        FrameLayout.LayoutParams frameLayoutParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                StatusBarCompat.getStatusBarHeight(this));
        statusBar.setBackgroundResource(color);
        statusBar.setLayoutParams(frameLayoutParams);
        baseContent.addView(statusBar);
    }

    /**
     * 初始化View  并且paddingTop和状态栏一样的高度。
     */
    private void initView() {
        childView = getLayoutInflater().inflate(getLayoutId(), null, false);
        baseContent.addView(childView);
        rlError = (RelativeLayout) getLayoutInflater().inflate(R.layout.base_layout_error, null, false);
        baseContent.addView(rlError);
//        childView.setPadding(0,StatusBarCompat.getStatusBarHeight(this),0,0);
        childView.setFitsSystemWindows(true);
        rlError.setFitsSystemWindows(true);
//        rlError.setPadding(0,StatusBarCompat.getStatusBarHeight(this),0,0);
    }

    public void showView() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            rlError.setVisibility(View.GONE);
            childView.setVisibility(View.VISIBLE);
        } else {
            rlError.setVisibility(View.VISIBLE);
            childView.setVisibility(View.GONE);
        }
    }

    public void loadData() {

    }

    /**
     * 刷新
     *
     * @param
     */
    public void onRefresh() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            showView();
            loadData();
        } else {
            showToast("请检查网络设置");
        }
    }

    /**
     * 布局文件ID
     */
    public abstract int getLayoutId();

    /**
     * 初始化数据
     */
    public abstract void initData(Bundle savedInstanceState);

    /**
     * 网络请求
     *
     * @param bodyCall 请求的体
     * @param what     请求what
     */
    public void doHttp(Call<ResponseBody> bodyCall, int what) {
        bodyCall.enqueue(new MyCallback(what));
    }

    private String getResponseResult(Response<ResponseBody> response) {
        try {
            String responseBody = response.body().string().trim();
            if (String.valueOf(response.code()).startsWith("2") && responseBody.length() != 0 && responseBody.startsWith("{")) {
                return responseBody;
            } else {
                return wrap(response.raw());
            }
        } catch (Exception e) {
            e.printStackTrace();
            showToast(e.getMessage());
        }
        return DefaultConfig.HTTP_FALLBACK_RESPONSE;
    }

    private String wrap(okhttp3.Response response) {
        return String.format(Locale.CHINA, "{\"code\": -2,\"msg\":\"%s\",\"status\":%d}", response.message(), response.code());
    }


    /**
     * 获取okhttp 的请求体
     */
    private String getRequestFrom(Request request) {
        RequestBody requestBody = request.body();
        okio.Buffer buffer = new okio.Buffer();
        try {
            requestBody.writeTo(buffer);
            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = requestBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }
            String paramsStr = buffer.readString(charset);
            return URLDecoder.decode(paramsStr, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }

    public class MyCallback implements Callback<ResponseBody> {
        private int mWhat;

        MyCallback(int what) {
            mWhat = what;
        }

        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            Request request = call.request();
            RequestBody body = request.body();
            if (body == null) {
                Log.w(GET_REQUEST_BODY, GET_REQUEST_BODY + "--->" + call.request().url().toString());
            } else {
                Log.w(POST_REQUEST_BODY, POST_REQUEST_BODY + "--->" + call.request().url().toString() +
                        '\n' + getRequestFrom(request));
            }

            String strResult = getResponseResult(response);
            Log.i(REQUEST_RESULT, REQUEST_RESULT + "--->" + strResult);
            AppBaseActivity.this.onResponse(mWhat, strResult);
            hideProgress();
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Log.e(NETWORK_ERROR, NETWORK_ERROR + "--->" + t.getMessage());
            AppBaseActivity.this.onResponse(ERROR, t.getMessage());
            hideProgress();
        }
    }
}
