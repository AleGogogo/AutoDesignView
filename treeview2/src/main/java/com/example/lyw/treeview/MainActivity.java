package com.example.lyw.treeview;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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
                        .setPositiveButton("Sure", new DialogInterface.OnClickListener() {


                            @Override
                            public void onClick(DialogInterface dialog, int
                                    which) {
                                if (TextUtils.isEmpty(et.getText().toString()))
                                    return;
                                mAdapter.addExtraNode(position,et.getText().toString());
                            }
                        }).setNegativeButton("Cancle",null).show();
                return true;
            }
        });
    }

    private void initData() {
        mDatas = new ArrayList<>();
        FileBean file1 = new FileBean(1, 0, "根目录1");
        FileBean file2 = new FileBean(2, 0, "根目录2");
        FileBean file3 = new FileBean(3, 0, "根目录3");
        FileBean file4 = new FileBean(4, 0, "根目录4");
        FileBean file5 = new FileBean(2, 1, "根目录1-2");
        FileBean file6 = new FileBean(3, 1, "根目录1-3");
        FileBean file7 = new FileBean(4, 2, "根目录2-4");
        FileBean file8 = new FileBean(5, 3, "根目录3-5");
        FileBean file9 = new FileBean(2, 3, "根目录3-2");
        FileBean file10 = new FileBean(2, 4, "根目录4-2");
        FileBean file11 = new FileBean(5, 4, "根目录4-5");
        mDatas.add(file1);
        mDatas.add(file2);
        mDatas.add(file3);
        mDatas.add(file4);
        mDatas.add(file5);
        mDatas.add(file6);
        mDatas.add(file7);
        mDatas.add(file8);
        mDatas.add(file9);
        mDatas.add(file10);
        mDatas.add(file11);
    }
}
