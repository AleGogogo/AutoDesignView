package com.example.lyw.treeview.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYW on 2016/11/30.
 */

public class Node {

    private int id;
    /**
     * 根节点
     */
    private int pId = 0;
    private int icon;
    private String name;

    /**
     * 树的层级
     */
    private int level;
    /**
     * 是否展开
     */
    private boolean isExpand =false;

    private Node parent;

    private List<Node> childen = new ArrayList<>();

    public Node(){}

    public Node(int id, int pId, String name) {
        this.id = id;
        this.pId = pId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 得到当前节点的层级
     */
    public int getLevel() {
        return parent == null? 0 : parent.getLevel() + 1;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isExpand() {
        return isExpand;
    }

    /**
     * 当父节点关闭时，也要关闭子节点
     * @param expand
     */
    public void setExpand(boolean expand) {
        isExpand = expand;
        if (!expand){
            for (Node n :
                    childen) {
                n.setExpand(false);
            }
        }
    }

    /**
     * 判断当前父节点的收缩状态
     * @return
     */
    public boolean isParentExpand(){
        if (parent == null)
            return false;
        return parent.isExpand() ;
    }
    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public List<Node> getChilden() {
        return childen;
    }

    public void setChilden(List<Node> childen) {
        this.childen = childen;
    }

    /**
    *  是否是叶子节点
     */
    public boolean isLeaf(){
        return childen.size() == 0;
    }

    /**
     * 是否是根节点
     */
    public boolean isRoot(){

        return parent == null;
    }


    @Override
    public String toString() {

        return "Node [id=" + id + ", pId=" + pId + ", name=" + name
                + ", level=" + level + ", isExpand=" + isExpand + ", icon="
                + icon + "]";

    }
}
