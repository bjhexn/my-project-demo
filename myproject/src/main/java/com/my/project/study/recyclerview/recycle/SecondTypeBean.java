package com.my.project.study.recyclerview.recycle;

/**
 * Created by hexiaoning on 2017/2/17.
 */

public class SecondTypeBean {

    private String firstTypeName;         //一级分类的名字
    private String firstTypeCode;         //一级分类的code
    private boolean isIfTitle = false;    //是否是一级标题

    private String secondTypeName;        //二级分类的名字
    private String SecondTypeCode;        //二级分类的code
    private boolean isSelected = false;   //二级列表是否有被选中的

    private int itemType;                 //列表item的类型，是否是显示内容或显示标题

    private boolean editEnable = false;   //是否可编辑

    public static final String TEXT_ZDY = "自定义";

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getSecondTypeName() {
        return secondTypeName;
    }

    public void setSecondTypeName(String secondTypeName) {
        this.secondTypeName = secondTypeName;
    }

    public String getSecondTypeCode() {
        return SecondTypeCode;
    }

    public void setSecondTypeCode(String secondTypeCode) {
        SecondTypeCode = secondTypeCode;
    }

    public String getFirstTypeName() {
        return firstTypeName;
    }

    public void setFirstTypeName(String firstTypeName) {
        this.firstTypeName = firstTypeName;
    }

    public String getFirstTypeCode() {
        return firstTypeCode;
    }

    public void setFirstTypeCode(String firstTypeCode) {
        this.firstTypeCode = firstTypeCode;
    }

    public boolean isIfTitle() {
        return isIfTitle;
    }

    public void setIfTitle(boolean ifTitle) {
        isIfTitle = ifTitle;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public boolean isEditEnable() {
        return editEnable;
    }

    public void setEditEnable(boolean editEnable) {
        this.editEnable = editEnable;
    }
}
