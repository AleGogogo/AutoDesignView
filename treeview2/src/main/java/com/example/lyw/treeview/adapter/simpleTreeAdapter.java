package com.example.lyw.treeview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lyw.treeview.R;
import com.example.lyw.treeview.utils.Node;
import com.example.lyw.treeview.utils.TreeHelper;
import com.example.lyw.treeview.utils.TreeListAdapter;

import java.util.List;

/**
 * Created by LYW on 2016/12/1.
 */

public class SimpleTreeAdapter<T> extends TreeListAdapter<T>{


    public SimpleTreeAdapter(Context mContext, List<T> datas, ListView
            mTreeView, int defaultLevel) throws IllegalAccessException {
        super(mContext, datas, mTreeView, defaultLevel);
    }

    @Override
    protected View getConvertView(Node node, int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
             convertView = mInflater.inflate(R.layout.list_item,parent,false);
              holder = new ViewHolder();
              holder.mIcon = (ImageView) convertView.findViewById(R.id.id_imageView);
              holder.mText = (TextView) convertView.findViewById(R.id.id_textView);
              convertView.setTag(holder);
         }else {
            holder = (ViewHolder) convertView.getTag();
        }
         if (node.getIcon() == -1){
             holder.mIcon.setVisibility(View.INVISIBLE);
         }else {
             holder.mIcon.setVisibility(View.VISIBLE);
             holder.mIcon.setImageResource(node.getIcon());
             holder.mText.setText(node.getName());
         }
        return convertView;
    }

    /**
     * 动态插入节点
     * @param position
     * @param s
     */
    public void addExtraNode(int position, String s) {

        Node node = mVisibleNodes.get(position);

        int indexOf =mAllNodes.indexOf(node);
        Node extraNode = new Node(-1,node.getId(),s);
        extraNode.setParent(node);
        node.getChilden().add(node);

        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
        notifyDataSetChanged();

    }

    private class ViewHolder{
        ImageView mIcon;
        TextView  mText;
    }
}
