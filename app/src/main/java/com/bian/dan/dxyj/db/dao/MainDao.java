package com.bian.dan.dxyj.db.dao;

import android.content.Context;

import com.bian.dan.dxyj.bean.MainBean;
import com.bian.dan.dxyj.db.DatabaseHelper;
import java.sql.SQLException;
import java.util.List;

public class MainDao {

    private static MainDao mainDao;
    private static Context mContext;

    public static synchronized MainDao getInstance(Context context){
        mContext=context;
        if (mainDao == null) {
            synchronized (DatabaseHelper.class) {
                if (mainDao == null)
                    mainDao = new MainDao();
            }
        }
        return mainDao;
    }


    /**
     * 添加数据
     * @param mainBean
     */
    public void add(MainBean mainBean){
        try {
            DatabaseHelper.getHelper(mContext).getDao(MainBean.class).create(mainBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询所有列表数据
     * @return
     */
    public List<MainBean> getList(){
        try {
            return DatabaseHelper.getHelper(mContext).getDao(MainBean.class).queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 判断是否有重复数据
     * @param mainBean
     */
    public boolean isRepeat(MainBean mainBean){
       boolean repeat=false;
       final List<MainBean> list=getList();
       for (int i=0;i<list.size();i++){
            if(list.get(i).hashCode()==mainBean.hashCode()){
                repeat=true;
                break;
            }
       }
       return repeat;
    }

}
