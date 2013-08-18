package com.hastagqq.app;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hastagqq.app.model.News;

import java.util.ArrayList;
import java.util.List;


public class NewsListFragment extends ListFragment {
    public static final String TAG_FRAGMENT = "tag_news_list_fragment";
    
    private ArrayAdapter<News> mAdapter;
    private List<News> mNewsItems;

    private class NewsArrayAdapter extends ArrayAdapter<News> {
        public NewsArrayAdapter(Context context, int resource, List<News> objects) {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ((Activity) getActivity()).getLayoutInflater();
            View view = inflater.inflate(R.layout.news_item, parent, false);
            TextView tvTitle = (TextView) view.findViewById(R.id.tv_title);
            TextView tvContent = (TextView) view.findViewById(R.id.tv_content);

            News news = mNewsItems.get(position);
            tvTitle.setText(news.getTitle());
            tvContent.setText(news.getCategory());

            return view;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_list_fragment, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mNewsItems = new ArrayList<News>();
        mAdapter = new NewsArrayAdapter(getActivity(), R.id.txt_location, mNewsItems);

        setListAdapter(mAdapter);
    }

//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//
//        getListView().setAdapter(mAdapter);
//    }

    public void onNewsAvailable(List<News> newsItems) {
        mNewsItems = newsItems;
        mAdapter = new NewsArrayAdapter(getActivity(), R.id.txt_location, newsItems);

        setListAdapter(mAdapter);
    }
}
