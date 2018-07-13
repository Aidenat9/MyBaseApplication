package com.youtu.myapplication;


import android.content.Intent;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.tmgg.xbaselibrary.ui.BaseActivity;
import com.youtu.myapplication.animdemo.AnimDemoActivity;
import com.youtu.myapplication.iview.MainView;
import com.youtu.myapplication.presenter.MainPresenter;
import com.youtu.myapplication.servicedemo.ServiceDemoActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends BaseActivity implements MainView {

    private MainPresenter mPresenter;
    private TextView mTv;
    private Button btnPickView;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void processLogic() {
        testWeb2App();
        //测试网络
        String url = "http://v.juhe.cn/weixin/query?pno=&ps=&dtype=&key=82b8d857f497a57b2bca093d7700a5f6";
        mPresenter.getNetData(url);
        //服务
        findViewById(R.id.btn_localservice).setOnClickListener(v ->
                {
                    startActivity(new Intent(getBaseContext(), ServiceDemoActivity.class));
                }
        );
        //动画
        findViewById(R.id.btn_anim).setOnClickListener(v -> {
            startActivity(new Intent(getBaseContext(), AnimDemoActivity.class));
        });
        // TODO: 2018/7/11 测试 time
        btnPickView = findViewById(R.id.btn_pickTime);

        btnPickView.setOnClickListener(v -> testPickTime());
    }

    private void testPickTime() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        //下月最后一天
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
        String preMonth = TimeUtil.getPreMonth();
        String maxMonthDate = TimeUtil.getMaxMonthDate(preMonth);

        /**
         * //正确设置方式 原因：注意事项有说明
         // TODO: 2018/7/11  注意：当我们进行设置时间的启始位置时，需要特别注意月份的设定
         原因：Calendar组件内部的月份，是从0开始的，即0-11代表1-12月份
         错误使用案例： startDate.set(2013,1,1);  endDate.set(2020,12,1);
         正确使用案例： startDate.set(2013,0,1);  endDate.set(2020,11,1);
         */
        selectedDate.set(2018, 06, 20);//此处初始是7月份
        try {
            endDate.setTime(dft.parse(maxMonthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        TimePickerView pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");
                btnPickView.setText(dft.format(date));
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("Cancel")//取消按钮文字
                .setSubmitText("Sure")//确认按钮文字
                .setTitleSize(20)//标题文字大小
                .setTitleText("Title")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(selectedDate, endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();

    }

    private void testWeb2App() {
        WebView webView = findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/demo.html");
    }

    @Override
    protected void initView() {
        mTv = (TextView) findViewById(R.id.tv);
    }

    @Override
    public void showData(String resp) {
        mTv.setText(resp);
    }

    @Override
    public void showErrMsg(String errMsg) {

    }

}
