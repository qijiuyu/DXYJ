package com.bian.dan.dxyj.activity.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.activity.BaseActivity;
import com.bian.dan.dxyj.activity.LoginActivity;
import com.bian.dan.dxyj.utils.SPUtil;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 设置页面
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_zx, R.id.tv_nz, R.id.tv_gc, R.id.tv_jl, R.id.tv_sg,R.id.tv_login_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //直线线夹数据
            case R.id.tv_zx:
                setClass(StraightLineActivity.class);
                break;
            //耐张线夹数据
            case R.id.tv_nz:
                setClass(TensionActivity.class);
                break;
            //工程名数据
            case R.id.tv_gc:
                setClass(ProjectNameActivity.class);
                break;
            //监理单位数据
            case R.id.tv_jl:
                setClass(SupervisionActivity.class);
                break;
            //施工单位数据
            case R.id.tv_sg:
                setClass(ConstructionActivity.class);
                break;
            case R.id.tv_login_out:
                SPUtil.getInstance(this).removeMessage(SPUtil.ACCOUNT);
                Intent intent=new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                 break;
            default:
                break;
        }
    }
}
