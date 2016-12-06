package com.example.lyw.treeview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.example.lyw.treeview.adapter.SimpleTreeAdapter;
import com.example.lyw.treeview.bean.FileBean;
import com.example.lyw.treeview.utils.Node;
import com.example.lyw.treeview.utils.TreeListAdapter;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView mTree;
    private SimpleTreeAdapter<FileBean> mAdapter;
    private List<FileBean> mDatas;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTree = (ListView) findViewById(R.id.id_listview);
        initData();
        try {
            mAdapter = new SimpleTreeAdapter<>(this, mDatas, mTree, 1);
            mTree.setAdapter(mAdapter);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        initEvent();

    }

    private void initEvent() {
        mAdapter.setOnTreeNodeClickListener(new TreeListAdapter
                .OnTreeNodeClickListener() {

            @Override
            public void onClick(Node node, int position) {
                Toast.makeText(MainActivity.this, node.getName(), Toast
                        .LENGTH_LONG)
                        .show();

            }
        });

        mTree.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {
                final EditText et = new EditText(MainActivity.this);
                new AlertDialog.Builder(MainActivity.this).setTitle("Add Node")
                        .setView(et)
                .setPositiveButton("Sure", new OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which)
                    {

                        if (TextUtils.isEmpty(et.getText().toString()))
                            return;
                        mAdapter.addExtraNode(position, et.getText()
                                .toString());
                    }
                }).setNegativeButton("Cancel", null).show();

                return true;
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        FileBean bean = new FileBean(1, 0, "根目录1");
        mDatas.add(bean);
        bean = new FileBean(2, 0, "根目录2");
        mDatas.add(bean);
        bean = new FileBean(3, 0, "根目录3");
        mDatas.add(bean);
        bean = new FileBean(4, 1, "根目录1-1");
        mDatas.add(bean);
        bean = new FileBean(5, 1, "根目录1-2");
        mDatas.add(bean);
        bean = new FileBean(6, 5, "根目录1-2-1");
        mDatas.add(bean);
        bean = new FileBean(7, 3, "根目录3-1");
        mDatas.add(bean);
        bean = new FileBean(8, 3, "根目录3-2");
        mDatas.add(bean);
        Log.d(TAG, "initData: mDatas size is"+mDatas.size());
    }
}
