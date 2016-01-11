package com.zhicall.hax.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
private Context mContext;
  private List<T> mList;
  private LayoutInflater mLayoutInflater=null;
  private int mResID;

  public CommonAdapter(Context context, List<T> list,int resID) {
mContext = context;
mList = list;
mLayoutInflater = LayoutInflater.from(context);
mResID = resID;
}

@Override public int getCount() {
return mList.size();
}

@Override public T getItem(int position) {
return mList.get(position);
}

@Override public long getItemId(int position) {
return position;
}

@Override public View getView(int position, View convertView, ViewGroup parent) {
    CommonViewHolder viewHolder=CommonViewHolder.getViewHolder(position, convertView, parent,
mResID, mContext);
T t=mList.get(position);
initView(t,position,viewHolder);
    return viewHolder.getConvertView();
}

public abstract void initView(T t,int position,CommonViewHolder viewHolder);
}