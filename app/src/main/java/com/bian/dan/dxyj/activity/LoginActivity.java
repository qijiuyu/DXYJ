package com.bian.dan.dxyj.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.activity.setting.SettingActivity;
import com.bian.dan.dxyj.utils.SPUtil;
import com.bian.dan.dxyj.utils.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    /**
     * 存储账号和密码
     */
    private Map<String ,String> accountMap=new HashMap<>();
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final String account=SPUtil.getInstance(this).getString(SPUtil.ACCOUNT);
        if(!TextUtils.isEmpty(account)){
            if(account.equals("admin")){
                setClass(SettingActivity.class);
            }else{
                setClass(MainActivity.class);
            }
            finish();
        }
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }


    /**
     * 初始化
     */
    private void initView(){
        accountMap.put("admin","123456");
        accountMap.put("dxyj","123456");
    }

    @OnClick(R.id.tv_login)
    public void onViewClicked() {
        final String account=etAccount.getText().toString().trim();
        final String pwd=etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            ToastUtil.showLong("用户名不能为空");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            ToastUtil.showLong("密码不能为空");
            return;
        }
        if(accountMap.get(account)!=null && accountMap.get(account).equals(pwd)){
            //登录成功
            loginSuccess(account);
        }else{
            ToastUtil.showLong("用户名或密码错误");
        }
    }


    /**
     * 登录成功
     */
    private void loginSuccess(String account){
        SPUtil.getInstance(this).addString(SPUtil.ACCOUNT,account);
        if(account.equals("admin")){
            setClass(SettingActivity.class);
        }else{
            setClass(MainActivity.class);
        }
        finish();
    }
}
