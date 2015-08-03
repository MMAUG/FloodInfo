package org.mmaug.InfoCenter.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;
import org.mmaug.InfoCenter.R;
import org.mmaug.InfoCenter.base.BaseAdapter;
import org.mmaug.InfoCenter.model.News;
import org.mmaug.InfoCenter.widgets.NkTextView;

/**
 * @author SH (swanhtet@nexlabs.co)
 */
public class NewsAdapter extends BaseAdapter<BaseAdapter.BaseViewHolder> {
  public static final int CONTENT_VIEW_TYPE = 100;
  public static final int FOOTER_VIEW_TYPE = 101;
  private List<News> mNews;
  private boolean hideFooter;

  public NewsAdapter() {
    mNews = new ArrayList<>();
    hideFooter = false;
  }

  public void setNews(ArrayList<News> News) {
    mNews = News;
    this.notifyDataSetChanged();
  }

  public void hideFooter() {
    hideFooter = true;
    notifyDataSetChanged();
  }

  @Override public BaseAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View view;
    switch (viewType) {
      case CONTENT_VIEW_TYPE:
        view = inflater.inflate(R.layout.item_news_view, parent, false);
        return new NewsHolder(view, this);
      case FOOTER_VIEW_TYPE:
        view = inflater.inflate(R.layout.progress_bar, parent, false);
        return new ProgressHolder(view);
    }

    return null;
  }

  @Override public void onBindViewHolder(BaseAdapter.BaseViewHolder holder, int position) {
    //Log.d("adapter position", position + "");
    //Log.d("view type", holder.getClass() + "");
    if (holder instanceof NewsHolder) {
      if (mNews.size() == 0) {
        return;
      }
      News News = mNews.get(position);
      ((NewsHolder) holder).setNewsName(News.getTitle());
    } else if (holder instanceof ProgressHolder) {
      if (!hideFooter) {
        ((ProgressHolder) holder).progressContainer.setVisibility(View.VISIBLE);
      } else {
        ((ProgressHolder) holder).progressContainer.setVisibility(View.GONE);
      }
    }
  }

  @Override public int getItemViewType(int position) {
    if (position != 0 && position == getItemCount() - 1) {
      return FOOTER_VIEW_TYPE;
    } else {
      return CONTENT_VIEW_TYPE;
    }
  }

  @Override public int getItemCount() {
    return mNews == null ? 0 : mNews.size() + 1;
  }

  public static class NewsHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.tv_news_title) NkTextView mNewsTitle;

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

  public static class ProgressHolder extends BaseAdapter.BaseViewHolder {
    @Bind(R.id.progress_container) FrameLayout progressContainer;

    public ProgressHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
