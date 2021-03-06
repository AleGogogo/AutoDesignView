package com.example.lyw.treeview.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by LYW on 2016/12/1.
 */

public abstract class TreeListAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<Node> mAllNodes;
    protected List<Node> mVisibleNodes;
    protected ListView mTreeView;
    protected LayoutInflater mInflater;
    private static final String TAG = "TreeListAdapter";


    /**
     * 设置Node的点击回调
     *
     * @author zhy
     */
    public interface OnTreeNodeClickListener {
        void onClick(Node node, int position);
    }

    private OnTreeNodeClickListener mListener;

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener mListener) {
        this.mListener = mListener;
    }

    public TreeListAdapter(Context mContext, List<T>
            datas, ListView mTreeView, int defaultLevel) throws
            IllegalAccessException {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        mAllNodes = TreeHelper.getSortedNodes(datas, defaultLevel);
        mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);

        this.mTreeView = mTreeView;

        mTreeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {


                    openOrCollapse(position);
                if (mListener != null) {
                    mListener.onClick(mVisibleNodes.get(position), position);
                }
                Log.d(TAG, "this position is " + position);
                Log.d(TAG, "点击的位置：mVisibleNodes is" + mVisibleNodes.get
                        (position).getName());


            }

        });
    }

    /**
     * 改变节点的展开状态
     *
     * @param position
     */
    private void openOrCollapse(int position) {

            Node n = mVisibleNodes.get(position);
            Log.d(TAG, "openOrCollapse: mVisibleNodes.get(i)" + n.getName());
            if (n != null) {
                if (n.isLeaf()) {
                    Toast.makeText(mContext, "不好意思这个是叶子节点", Toast
                            .LENGTH_LONG).show();
                    return;
                }

                n.setExpand(!n.isExpand());
                mVisibleNodes = TreeHelper.filterVisibleNodes(mAllNodes);
                for (Node node :
                        mVisibleNodes) {
                    Log.d(TAG, "node is" + node.getName());
                }
                notifyDataSetChanged();
            }

    }

    @Override
    public int getCount() {
        return mVisibleNodes.size();
    }

    @Override
    public Object getItem(int position) {
        return mVisibleNodes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Node node = mVisibleNodes.get(position);

        convertView = getConvertView(node, position, convertView, parent);
        convertView.setPadding(node.getLevel() * 30, 3, 3, 3);

        return convertView;
    }

    protected abstract View getConvertView(Node node, int position, View
            convertView, ViewGroup parent);

}
