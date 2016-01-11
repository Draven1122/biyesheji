package com.zhicall.hax.common;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CommonViewHolder {
private int mPosition;
  private Context mContext;
  private SparseArray<View> views = null;
  private int ResId;
  private View mConvertView;

CommonViewHolder(Context context, int resId, ViewGroup parent, int position) {
this.mContext = context;
mConvertView = LayoutInflater.from(context).inflate(resId, parent, false);
mConvertView.setTag(this);
mPosition = position;
views = new SparseArray<View>();
}

public static CommonViewHolder getViewHolder(int position, View convertView, ViewGroup parent,
      int ResID, Context mContext) {
    CommonViewHolder commonViewHolder = null;
    if (convertView == null) {
return new CommonViewHolder(mContext, ResID, parent, position);
} else {
      commonViewHolder = (CommonViewHolder) convertView.getTag();
commonViewHolder.mPosition = position;
      return commonViewHolder;
}
  }

/**
   * 通过控件Id从Views中查找View如不存在则新建，并添加到Views
   */
public <T extends View> T getView(int viewId) {
    View view = views.get(viewId);
    if (view == null) {
      view = mConvertView.findViewById(viewId);
views.put(viewId, view);
}
return (T) view;
}

public View getConvertView() {
return mConvertView;
}
}