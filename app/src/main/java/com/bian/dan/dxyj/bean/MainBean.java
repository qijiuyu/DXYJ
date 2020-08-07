package com.bian.dan.dxyj.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "tb_main")
public class MainBean {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(columnName = "projectName")
    private String projectName;
    @DatabaseField(columnName = "block")
    private String block;
    @DatabaseField(columnName = "conductor")
    private String conductor;
    @DatabaseField(columnName = "loop")
    private String loop;
    @DatabaseField(columnName = "lineNum")
    private String lineNum;
    @DatabaseField(columnName = "supervision")
    private String supervision;
    @DatabaseField(columnName = "construction")
    private String construction;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getLoop() {
        return loop;
    }

    public void setLoop(String loop) {
        this.loop = loop;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getSupervision() {
        return supervision;
    }

    public void setSupervision(String supervision) {
        this.supervision = supervision;
    }

    public String getConstruction() {
        return construction;
    }

    public void setConstruction(String construction) {
        this.construction = construction;
    }

    @Override
    public int hashCode() {
        return  projectName.hashCode()+block.hashCode()+conductor.hashCode()+loop.hashCode()+lineNum.hashCode()+supervision.hashCode()+construction.hashCode();
    }

}
