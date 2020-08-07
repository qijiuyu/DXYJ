package com.bian.dan.dxyj.activity.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.activity.base.BaseWebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 弯曲度
 */
public class BendingActivity extends BaseWebView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;

    /**
     * 蓝牙相关信息
     */
    private String macAddress = "9C:A5:25:12:C1:CC";
    private String serviceUUID = "0003cdd0-0000-1000-8000-00805f9b0131";
    private String characteristicUUID = "0003cdd1-0000-1000-8000-00805f9b0131";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView(){
        tvTitle.setText("测量数据");
    }

    @OnClick(R.id.ib_back)
    public void onViewClicked() {
        finish();
    }
}
