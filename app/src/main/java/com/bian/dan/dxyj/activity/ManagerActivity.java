package com.bian.dan.dxyj.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bian.dan.dxyj.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 初始化
     */
    private void initView() {
        tvTitle.setText(R.string.app_name);
    }

    @OnClick({R.id.ib_back, R.id.tv_straight_before, R.id.tv_straight_after, R.id.tv_straight_bending, R.id.tv_tension_before, R.id.tv_tension_after, R.id.tv_tension_bending,R.id.tv_total_straight, R.id.tv_total_bending, R.id.tv_total_tension})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                finish();
                break;
            //直线压接前
            case R.id.tv_straight_before:
                break;
            //直线压接后
            case R.id.tv_straight_after:
                break;
            //直线弯曲度
            case R.id.tv_straight_bending:
                break;
            //耐张压接前
            case R.id.tv_tension_before:
                break;
            //耐张压接后
            case R.id.tv_tension_after:
                break;
            //耐张弯曲度
            case R.id.tv_tension_bending:
                break;
            case R.id.tv_total_straight:
                break;
            case R.id.tv_total_bending:
                break;
            case R.id.tv_total_tension:
                break;
            default:
                break;
        }
    }

}
