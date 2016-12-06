package com.example.lyw.treeview.utils;



import com.example.lyw.treeview.R;
import com.example.lyw.treeview.utils.annotation.TreeNodeId;
import com.example.lyw.treeview.utils.annotation.TreeNodeLabel;
import com.example.lyw.treeview.utils.annotation.TreeNodepId;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LYW on 2016/11/30.
 */

public class TreeHelper {
    /**
     * 将用户输入的数据转化为树形节点
     * @param data
     * @param <T>
     * @return
     */
    public static <T> List<T> convertData2TreeNode(List<T> data) throws
            IllegalAccessException {
         List<Node> nodes = new ArrayList<>();
         Node node = null;
        for (T t :
                data) {
            int id =-1;
            int pId = -1;
            String label = null;
            node = new Node();
            Class tClass = t.getClass();
            //拿到类的所有属性
            Field[] declaredFields = tClass.getDeclaredFields();
            for (Field field:
                 declaredFields) {
                if (field.getAnnotation(TreeNodeId.class)!=null){
                    field.setAccessible(true);
                    id =field.getInt(t);
                }
                if (field.getAnnotation(TreeNodepId.class)!=null){
                    field.setAccessible(true);
                    pId =field.getInt(t);
                }
                if (field.getAnnotation(TreeNodeLabel.class)!=null){
                    field.setAccessible(true);
                    label = (String) field.get(t);
                }
            }
            node = new Node(id,pId,label);
            nodes.add(node);
        }

        /**
         * 设置node间的节点关系
         */
        for (int i = 0; i < nodes.size() ; i++) {
            Node n = nodes.get(i);
            for (int j = i+1; j < nodes.size(); j++) {
                   Node m = nodes.get(j);
                //n 是 m 的 父节点
                if (m.getpId() == n.getId()){
                    m.setParent(n);
                    n.getChilden().add(m);
                    // m 是 n 的父节点
                }else if ( m.getId() == n.getpId()){
                    n.setParent(m);
                    m.getChilden().add(n);
                }
            }
        }

        /**
         * 为各个节点设置图标
         */
        for (Node n :
                nodes) {
           setNodeIcon(n);
        }
        return (List<T>) nodes;
    }

    public static <T> List<Node> getSortedNodes(List<T> data,int defaultExpandLevel) throws
            IllegalAccessException {
        List<Node> result = new ArrayList<>();
        List<Node> nodes = (List<Node>) convertData2TreeNode(data);
        List<Node> rootNodes = getRootNodes(nodes);
        for (Node root :
                rootNodes) {
            addNode(result,root,defaultExpandLevel,1);
        }
        return result ;
    }

    private static void addNode(List<Node> result, Node root, int
            defaultExpandLevel, int currentLevel) {
            result.add(root);
            if (defaultExpandLevel >currentLevel){

                 root.setExpand(true);

            }
            if (root.isLeaf())
                return;

            for (int i = 0; i < root.getChilden().size() ; i++)
            {
                addNode(result,root.getChilden().get(i),
                        defaultExpandLevel,currentLevel+1);
            }
    }
        /**
        * 过滤出可见的节点
        */
    public static <T> List<Node> filterVisibleNodes(List<Node> nodes){
        List<Node> result = new ArrayList<Node>();
        for (Node n : nodes) {
            if (n.isRoot() || n.isParentExpand()){
                setNodeIcon(n);
                result.add(n);
            }
        }
        return result ;
    }

    /**
     * 从所有节点中过滤出根节点
     * @return
     */
    private static List<Node> getRootNodes(List<Node> nodes) {
        List<Node> roots = new ArrayList<>();
        for (Node n:nodes) {
            if (n.isRoot()){
                roots.add(n);
            }
        }
            return roots;
    }

    /**
     * 为Node设置图标
     * @param n
     */
    private static void setNodeIcon(Node n) {
        if (n.getChilden().size()> 0 && n.isExpand()){
               n.setIcon(R.drawable.tree_ex);
        }else if ( n.getChilden().size() > 0 && !n.isExpand()){
               n.setIcon(R.drawable.tree_ec);
        }else {
              n.setIcon(-1);
        }
    }

}
