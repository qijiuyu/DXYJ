package com.bian.dan.dxyj.activity.setting;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.activity.base.BaseActivity;
import com.bian.dan.dxyj.adapter.BaseAdapter;
import com.bian.dan.dxyj.adapter.OnItemClickListener;
import com.bian.dan.dxyj.adapter.ViewHolder;
import com.bian.dan.dxyj.bean.NameBean;
import com.bian.dan.dxyj.dialog.AddProjectNameDialog;
import com.bian.dan.dxyj.utils.JsonUtil;
import com.bian.dan.dxyj.utils.SPUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 工程名列表
 */
public class ProjectNameActivity extends BaseActivity {

    @BindView(R.id.rv_recycleView)
    RecyclerView rvRecycleView;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_name);
        ButterKnife.bind(this);
        //显示数据
        showListData();
    }

    @OnClick({R.id.ib_back, R.id.tv_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ib_back:
                 finish();
                break;
            case R.id.tv_add:
                AddProjectNameDialog addDialog = new AddProjectNameDialog(activity, R.style.MyDialog, null);
                addDialog.show();
                break;
            default:
                break;
        }
    }


    /**
     * 显示数据
     */
    public void showListData(){
        //获取保存的所有数据
        final String PROJECT_NAME= SPUtil.getInstance(this).getString(SPUtil.PROJECT_NAME);
        if (!TextUtils.isEmpty(PROJECT_NAME)) {
            List<NameBean> dataBeanList=JsonUtil.stringToList(PROJECT_NAME, NameBean.class);
            rvRecycleView.setLayoutManager(new LinearLayoutManager(this));
            BaseAdapter baseAdapter = new BaseAdapter<NameBean>(activity, R.layout.item_name_value, dataBeanList) {
                public void convert(ViewHolder holder, NameBean nameBean) {
                    holder.setText(R.id.tv_serialNumber, String.valueOf(holder.getPosition()+1));
                    holder.setText(R.id.tv_name, String.valueOf(nameBean.getName()));
                }
            };
            rvRecycleView.setAdapter(baseAdapter);

            /**
             * 修改与删除
             */
            baseAdapter.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(ViewGroup parent, View view, Object o, int position) {
                    AddProjectNameDialog addDialog = new AddProjectNameDialog(activity, R.style.MyDialog, dataBeanList.get(position));
                    addDialog.show();
                }

                public boolean onItemLongClick(ViewGroup parent, View view, Object o, int position) {
                    AlertDialog alertDialog = new AlertDialog.Builder(activity)
                            .setTitle("是否删除？")
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dataBeanList.remove(position);
                                    baseAdapter.notifyDataSetChanged();
                                    //更新存储的数据
                                    SPUtil.getInstance(activity).addString(SPUtil.PROJECT_NAME, JsonUtil.objectToString(dataBeanList));
                                }
                            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            }).create();
                    alertDialog.show();
                    return false;
                }
            });
        }
    }
}
