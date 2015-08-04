package org.mmaug.InfoCenter.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import org.mmaug.InfoCenter.model.Location;

/**
 * Created by indexer on 8/4/15.
 */
public class LocationsAdapter extends android.widget.BaseAdapter implements SpinnerAdapter {

  /**
   * The internal data (the ArrayList with the Objects).
   */
  private List<Location> data;
  private Context mContext;

  public LocationsAdapter(Context mContext) {
    this.mContext = mContext;
  }

  /**
   * Returns the Size of the ArrayList
   */
  @Override public int getCount() {
    return data.size();
  }

  /**
   * Returns one Element of the ArrayList
   * at the specified position.
   */
  @Override public Object getItem(int position) {
    return data.get(position);
  }

  @Override public long getItemId(int i) {
    return i;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater vi =
          (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = vi.inflate(android.R.layout.simple_spinner_dropdown_item, null);
    }
    TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
    textView.setTextColor(Color.BLACK);
    textView.setText(data.get(position).township);
    LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,100);
    textView.setLayoutParams(Params1);
    return convertView;
  }

  @Override public int getViewTypeCount() {
    return 1;
  }

  @Override public boolean isEmpty() {
    return false;
  }

  @Override public View getDropDownView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
      LayoutInflater vi =
          (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = vi.inflate(android.R.layout.simple_spinner_dropdown_item, null);
    }
    TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
    textView.setTextColor(Color.BLACK);
    textView.setText(data.get(position).township);
    LinearLayout.LayoutParams Params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,50);
    textView.setLayoutParams(Params1);
    return convertView;
  }

  public void setLocations(ArrayList<Location> mLocations) {
    this.data = mLocations;
    Log.e("data", "" + data.size());
    notifyDataSetChanged();
  }
}
