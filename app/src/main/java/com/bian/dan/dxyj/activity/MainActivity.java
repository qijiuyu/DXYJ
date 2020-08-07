package com.bian.dan.dxyj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import com.bian.dan.dxyj.R;
import com.bian.dan.dxyj.bean.NameBean;
import com.bian.dan.dxyj.bean.MainBean;
import com.bian.dan.dxyj.db.dao.MainDao;
import com.bian.dan.dxyj.utils.JsonUtil;
import com.bian.dan.dxyj.utils.SPUtil;
import com.bian.dan.dxyj.utils.ToastUtil;
import com.bian.dan.dxyj.view.MainPopwindow;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements View.OnFocusChangeListener, TextWatcher {

    @BindView(R.id.et_project)
    EditText etProject;
    @BindView(R.id.et_conductor)
    EditText etConductor;
    @BindView(R.id.et_supervision)
    EditText etSupervision;
    @BindView(R.id.et_construction)
    EditText etConstruction;
    @BindView(R.id.et_block)
    EditText etBlock;
    @BindView(R.id.et_loop)
    EditText etLoop;
    @BindView(R.id.et_lineNum)
    EditText etLineNum;
    private MainPopwindow mainPopwindow;
    /**
     * 首页配置的数据集合
     */
    private List<MainBean> mainList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();

//        new AddDataUtil().addZXdata(this);
//        new AddDataUtil().addNZdata(this);
    }


    /**
     * 初始化
     */
    private void initView() {
        /**
         * 监听获得焦点
         */
        etProject.setOnFocusChangeListener(this);
        etConductor.setOnFocusChangeListener(this);
        etSupervision.setOnFocusChangeListener(this);
        etConstruction.setOnFocusChangeListener(this);
        /**
         * 监听输入框输入
         */
        etProject.addTextChangedListener(this);
        etConductor.addTextChangedListener(this);
        etSupervision.addTextChangedListener(this);
        etConstruction.addTextChangedListener(this);


        /**
         * 查询数据库是否有存储的首页配置数据
         */
        mainList = MainDao.getInstance(this).getList();
        if(mainList.size()>0){
            final MainBean mainBean=mainList.get(mainList.size()-1);
            etProject.setText(mainBean.getProjectName());
            etBlock.setText(mainBean.getBlock());
            etConductor.setText(mainBean.getConductor());
            etLoop.setText(mainBean.getLoop());
            etLineNum.setText(mainBean.getLineNum());
            etSupervision.setText(mainBean.getSupervision());
            etConstruction.setText(mainBean.getConstruction());
        }

    }

    /**
     * 监听获得焦点
     */
    String tag;
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            tag = v.getTag().toString();
            //设置下拉框显示的数据
            getShowData();
        } else {
            //关闭下拉框
            closePopwindow();
            //保存最新的值
            saveNewData(((EditText) v).getText().toString().trim());
        }
    }


    /**
     * 监听输入框内容
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            //关闭下拉框
            closePopwindow();
        } else {
            //设置下拉框显示的数据
            getShowData();
        }
    }


    @OnClick({R.id.tv_login_out, R.id.tv_go})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //退出
            case R.id.tv_login_out:
                SPUtil.getInstance(this).removeMessage(SPUtil.ACCOUNT);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
            //进入
            case R.id.tv_go:
                String project = etProject.getText().toString().trim();
                String block = etBlock.getText().toString().trim();
                String conductor = etConductor.getText().toString().trim();
                String loop=etLoop.getText().toString().trim();
                String lineNum=etLineNum.getText().toString().trim();
                String superVision = etSupervision.getText().toString().trim();
                String construction = etConstruction.getText().toString().trim();
                if (TextUtils.isEmpty(project)) {
                    ToastUtil.showLong("请输入工程名");
                    return;
                }
                if (TextUtils.isEmpty(block)) {
                    ToastUtil.showLong("请输入标段");
                    return;
                }
                if (TextUtils.isEmpty(conductor)) {
                    ToastUtil.showLong("请输入导线型号");
                    return;
                }
                if (TextUtils.isEmpty(loop)) {
                    ToastUtil.showLong("请输入回路数");
                    return;
                }
                if (TextUtils.isEmpty(lineNum)) {
                    ToastUtil.showLong("请输入导线分裂数");
                    return;
                }
                if (TextUtils.isEmpty(superVision)) {
                    ToastUtil.showLong("请输入监理单位");
                    return;
                }
                if (TextUtils.isEmpty(construction)) {
                    ToastUtil.showLong("请输入施工单位");
                    return;
                }
                MainBean mainBean=new MainBean();
                mainBean.setProjectName(project);
                mainBean.setBlock(block);
                mainBean.setConductor(conductor);
                mainBean.setLoop(loop);
                mainBean.setLineNum(lineNum);
                mainBean.setSupervision(superVision);
                mainBean.setConstruction(construction);

                /**
                 * 判断表中是否有相同数据
                 */
                final boolean isRepeat=MainDao.getInstance(this).isRepeat(mainBean);
                if(!isRepeat){
                    //存入数据库
                    MainDao.getInstance(this).add(mainBean);
                }
                setClass(ManagerActivity.class);
                break;
            default:
                break;
        }
    }


    /**
     * 设置下拉框显示的数据
     */
    private void getShowData() {
        String message;
        switch (tag) {
            case "1":
                message = SPUtil.getInstance(this).getString(SPUtil.PROJECT_NAME);
                showPopwindow(etProject, message, 2);
                break;
            case "2":
                message = SPUtil.getInstance(this).getString(SPUtil.STRAIGHT_LINE);
                showPopwindow(etConductor, message, 1);
                break;
            case "3":
                message = SPUtil.getInstance(this).getString(SPUtil.SUPERVISION);
                showPopwindow(etSupervision, message, 2);
                break;
            case "4":
                message = SPUtil.getInstance(this).getString(SPUtil.CONSTRUCTION);
                showPopwindow(etConstruction, message, 2);
                break;
            default:
                break;
        }
    }


    /**
     * 展示下拉框
     */
    private void showPopwindow(EditText editText, String message, int type) {
        if (mainPopwindow != null && mainPopwindow.isShowing()) {
            return;
        }
        if (TextUtils.isEmpty(message)) {
            return;
        }
        mainPopwindow = new MainPopwindow(this);
        mainPopwindow.showAsDropDown(editText);
        mainPopwindow.setData(editText, message, type);
        mainPopwindow.openShow();
    }


    /**
     * 关闭下拉框
     */
    private void closePopwindow() {
        if (mainPopwindow != null && mainPopwindow.isShowing()) {
            mainPopwindow.closeShow();
            mainPopwindow = null;
        }
    }


    /**
     * 保存最新的值
     */
    private void saveNewData(String data) {
        if (TextUtils.isEmpty(data)) {
            return;
        }
        String totalMsg = null;
        boolean isAdd = true;
        List<NameBean> dataBeanList = new ArrayList<>();
        NameBean nameBean = new NameBean();
        switch (tag) {
            case "1":
                totalMsg = SPUtil.getInstance(this).getString(SPUtil.PROJECT_NAME);
                if (!TextUtils.isEmpty(totalMsg)) {
                    dataBeanList.addAll(JsonUtil.stringToList(totalMsg, NameBean.class));
                    for (int i = 0, len = dataBeanList.size(); i < len; i++) {
                        if (dataBeanList.get(i).getName().equals(data)) {
                            isAdd = false;
                            break;
                        }
                    }
                }
                if (isAdd) {
                    nameBean.setName(data);
                    dataBeanList.add(nameBean);
                    SPUtil.getInstance(this).addString(SPUtil.PROJECT_NAME, JsonUtil.objectToString(dataBeanList));
                }
                break;
            case "2":
                break;
            case "3":
                totalMsg = SPUtil.getInstance(this).getString(SPUtil.SUPERVISION);
                if (!TextUtils.isEmpty(totalMsg)) {
                    dataBeanList.addAll(JsonUtil.stringToList(totalMsg, NameBean.class));
                    for (int i = 0, len = dataBeanList.size(); i < len; i++) {
                        if (dataBeanList.get(i).getName().equals(data)) {
                            isAdd = false;
                            break;
                        }
                    }
                }
                if (isAdd) {
                    nameBean.setName(data);
                    dataBeanList.add(nameBean);
                    SPUtil.getInstance(this).addString(SPUtil.SUPERVISION, JsonUtil.objectToString(dataBeanList));
                }
                break;
            case "4":
                totalMsg = SPUtil.getInstance(this).getString(SPUtil.CONSTRUCTION);
                if (!TextUtils.isEmpty(totalMsg)) {
                    dataBeanList.addAll(JsonUtil.stringToList(totalMsg, NameBean.class));
                    for (int i = 0, len = dataBeanList.size(); i < len; i++) {
                        if (dataBeanList.get(i).getName().equals(data)) {
                            isAdd = false;
                            break;
                        }
                    }
                }
                if (isAdd) {
                    nameBean.setName(data);
                    dataBeanList.add(nameBean);
                    SPUtil.getInstance(this).addString(SPUtil.CONSTRUCTION, JsonUtil.objectToString(dataBeanList));
                }
                break;
            default:
                break;
        }
    }
}
