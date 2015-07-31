package org.mmaug.InfoCenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import mmaug.org.yaybay.R;
import org.mmaug.InfoCenter.adapter.NewsAdapter.NewsHolder;
import org.mmaug.InfoCenter.base.BaseAdapter;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.widgets.ZgTextView;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsAdapter extends BaseAdapter<NewsHolder> {

  private List<News> mNews;

  public NewsAdapter() {
    mNews = new ArrayList<>();
  }

  public void setNews(ArrayList<News> News) {
    mNews.clear();
    mNews.addAll(News);
    this.notifyItemRangeInserted(0, mNews.size() - 1);
  }

  @Override public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view = inflater.inflate(R.layout.item_news_view, parent, false);

    return new NewsHolder(view, this);
  }

  @Override public void onBindViewHolder(NewsHolder holder, int position) {
    final News News = mNews.get(position);

    holder.setNewsName(News.getTitle());
  }

  @Override public int getItemCount() {
    return mNews == null ? 0 : mNews.size();
  }

  public static class NewsHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.tv_news_title) ZgTextView mNewsTitle;

    public NewsHolder(View itemView, NewsAdapter adapter) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);

      mAdapter = adapter;
    }

    public void setNewsName(String s) {
      mNewsTitle.setText(s);
    }
  }
}
