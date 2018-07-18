package com.youtu.myapplication.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.tmgg.xbaselibrary.ui.BaseActivity;
import com.youtu.myapplication.R;
import com.youtu.myapplication.widget.AdImageView;

import java.util.ArrayList;
import java.util.List;

public class TestScrollImageViewActivity extends BaseActivity {

    private ListView mLv;
    private List<String> mockDatas;

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_test_scroll_image_view;
    }

    @Override
    protected void processLogic() {
        mLv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                int fPos = absListView.getFirstVisiblePosition();
                int lPos = absListView.getLastVisiblePosition();
                for (int k = fPos; k <= lPos; k++) {
                    View view = getViewByPosition(k,mLv);
                    AdImageView adImageView = view.findViewById(R.id.id_iv_ad);
                    if (adImageView.getVisibility() == View.VISIBLE) {
                        adImageView.setDy(absListView.getHeight() - view.getTop());
                    }
                }
            }
        });
    }
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    @Override
    protected void initView() {
        mockDatas = new ArrayList<>();
        for (int i = 0; i < 80; i++) {
            mockDatas.add(i + "");
        }
        mLv = findViewById(R.id.lv_scroll);
        mLv.setAdapter(new MyAdapter());
    }


    @Override
    public void showErrMsg(String errMsg) {

    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mockDatas.size();
        }

        @Override
        public Object getItem(int i) {
            return mockDatas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            view = LayoutInflater.from(mContext).inflate(R.layout.item_scrollimage_lv, viewGroup, false);
            TextView tvTitle = view.findViewById(R.id.id_tv_title);
            TextView tvDesc = view.findViewById(R.id.id_tv_desc);
            ImageView imageView = view.findViewById(R.id.id_iv_ad);
            if (position > 0 && position % 6 == 0) {
                tvTitle.setVisibility(View.GONE);
                tvDesc.setVisibility(View.GONE);
                imageView.setVisibility(View.VISIBLE);
            }else{
                tvTitle.setVisibility(View.VISIBLE);
                tvDesc.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
            }
            return view;
        }


    }
}
