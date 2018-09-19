package com.hy.base.androidbase.androidbasesave.greendao;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hy.base.androidbase.androidbasesave.db.DateUtils;
import com.hy.datasave.R;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author
 * @date 2017/9/1
 */
public class GreenDaoQureyActivity extends Activity {
    String tag = "060_GreenDaoQureyActivity";
    List<User> mDatas;
    UserAdapter mAdapter;
    RecyclerView mbphistory;

    Button bt1;
    Button bt2;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gdquery);

        mbphistory = findViewById(R.id.mbphistory);

        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertall();
            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryall();
            }
        });
    }

    private void insertall() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        fixedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String time1 = DateUtils.getDateStr(System.currentTimeMillis());
                    for(long i= 0; i<1000;i++){
                        UserUtils.getUserUtils().insert(new User(i,"aa","20",""));
                    }
                    String time2 = DateUtils.getDateStr(System.currentTimeMillis());

                    Log.e(tag,"insertall time1 : "+ time1 + ", time2 : "+ time2);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private void queryall() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
        fixedThreadPool.execute(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    String time1 = DateUtils.getDateStr(System.currentTimeMillis());
                    mDatas = UserUtils.getUserUtils().queryAll();
                    String time2 = DateUtils.getDateStr(System.currentTimeMillis());

                    Log.e(tag,"queryall time1 : "+ time1 + ", time2 : "+ time2);

                    handler.sendEmptyMessage(0);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }

    private void overrideCallback() {
        mAdapter.setOnItemClickLitener(new UserAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(GreenDaoQureyActivity.this, position + "", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        break;
                    default:
                }
            }
        });
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    mbphistory.setLayoutManager(new LinearLayoutManager(GreenDaoQureyActivity.this, LinearLayoutManager.VERTICAL, false));
                    mAdapter = new UserAdapter(GreenDaoQureyActivity.this, mDatas);
                    mbphistory.setAdapter(mAdapter);
                    //override click callback
                    overrideCallback();
                    break;
                case 2:

                    break;
            }
        }
    };

}
