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
 * 压接后一钢管
 */
public class AfterSteelActivity extends BaseWebView {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.webview)
    WebView webview;

    /**
     * 蓝牙相关信息
     */
    private String macAddress = "FF:FF:40:00:01:AE";
    private String serviceUUID = "0000ffff-0000-1000-8000-00805f9b34fb";
    private String characteristicUUID = "0000ff00-0000-1000-8000-00805f9b34fb";
    private String descriptorUUID = "00001801-0000-1000-8000-00805f9b34fb";
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
