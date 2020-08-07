package com.bian.dan.dxyj.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.enumer.ManagerEnum;
import com.bian.dan.dxyj.utils.DialogUtil;
import com.bian.dan.dxyj.utils.LogUtils;
import com.bian.dan.dxyj.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManagerActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    /**
     * 选择的类型
     */
    private ManagerEnum managerEnum;
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
                selectType(0);
                break;
            //直线压接后
            case R.id.tv_straight_after:
                selectType(1);
                break;
            //直线弯曲度
            case R.id.tv_straight_bending:
                managerEnum=ManagerEnum.直线弯曲度;
                break;
            //耐张压接前
            case R.id.tv_tension_before:
                selectType(2);
                break;
            //耐张压接后
            case R.id.tv_tension_after:
                selectType(3);
                break;
            //耐张弯曲度
            case R.id.tv_tension_bending:
                managerEnum=ManagerEnum.耐张弯曲度;
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


    /**
     * 选择钢管/铝管
     */
    private void selectType(int type){
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_manager,null);
        final Dialog dialog= DialogUtil.getDialog(this,view);
        ImageView imgGg=view.findViewById(R.id.img_gg);
        ImageView imgLg=view.findViewById(R.id.img_lg);
        imgGg.setTag(0);
        imgLg.setTag(0);
        /**
         * 选择钢管
         */
        view.findViewById(R.id.lin_gg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGg.setImageResource(R.mipmap.check_yes);
                imgLg.setImageResource(R.mipmap.check_no);
                imgGg.setTag(1);
                imgLg.setTag(0);
            }
        });

        /**
         * 选择铝管
         */
        view.findViewById(R.id.lin_lg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGg.setImageResource(R.mipmap.check_no);
                imgLg.setImageResource(R.mipmap.check_yes);
                imgGg.setTag(0);
                imgLg.setTag(1);
            }
        });


        /**
         * 确定
         */
        view.findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int ggTag= (int) imgGg.getTag();
                final int lgTag= (int) imgLg.getTag();
                if(ggTag==0 && lgTag==0){
                    ToastUtil.showLong("请选择类型");
                    return;
                }
                switch (type){
                    case 0:
                         if(ggTag>0){
                             managerEnum=ManagerEnum.直线压接前钢管;
                         }else{
                             managerEnum=ManagerEnum.直线压接前铝管;
                         }
                         break;
                    case 1:
                        if(ggTag>0){
                            managerEnum=ManagerEnum.直线压接后钢管;
                        }else{
                            managerEnum=ManagerEnum.直线压接后铝管;
                        }
                        break;
                    case 2:
                        if(ggTag>0){
                            managerEnum=ManagerEnum.耐张压接前钢管;
                        }else{
                            managerEnum=ManagerEnum.耐张压接前铝管;
                        }
                        break;
                    case 3:
                        if(ggTag>0){
                            managerEnum=ManagerEnum.耐张压接后钢管;
                        }else{
                            managerEnum=ManagerEnum.耐张压接后铝管;
                        }
                        break;
                    default:
                        break;
                }
                LogUtils.e(managerEnum.value()+"++++++++++++++++");
            }
        });

    }

}
