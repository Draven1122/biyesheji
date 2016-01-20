package com.zhicall.hax.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Disease;
import com.zhicall.hax.utils.TianGouUtils;

/**
 * Created by Xingchen on 2016/1/20.
 * Email:huangjinxin@zhicall.cn
 */
public class DiseaseDetailActivity extends BaseActivity {
  @Bind(R.id.img_icon) ImageView mIconImageView;
  @Bind(R.id.tv_disease_name) TextView mDiseaseNameTextView;
  @Bind(R.id.tv_disease_desc) TextView mDiseaseDescTextView;
  @Bind(R.id.tv_disease_detail) TextView mDiseaseDetailTextView;
  @Bind(R.id.tv_disease_cause) TextView mDiseaseCauseTextView;
  @Bind(R.id.tv_disease_check) TextView mDiseaseCheckTextView;
  @Bind(R.id.tv_disease_drug) TextView mDiseaseDrugTextView;
  @Bind(R.id.tv_disease_food) TextView mDiseaseFoodTextView;
  private Disease mDisease;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_disease_detail);
    initActionbar(true, false, "DiseaseDetail");
    initData();
  }

  @Override public void initData() {
    mDisease =
        (Disease) getIntent().getExtras().getSerializable("disease");      //tips:用这种方式传递的数据不能太大lou
    Picasso.with(this)
        .load(TianGouUtils.getImageUrl(mDisease.getImg()))
        .placeholder(R.drawable.loding)
        .error(R.drawable.load_error)
        .into(mIconImageView);
    mDiseaseNameTextView.setText(mDisease.getName());
    mDiseaseDescTextView.setText(mDisease.getDescription());
    mDiseaseDetailTextView.setText(mDisease.getMessage());
    mDiseaseCauseTextView.setText(Html.fromHtml(mDisease.getCausetext()));
    mDiseaseCheckTextView.setText(Html.fromHtml(mDisease.getChecktext()));
    mDiseaseDrugTextView.setText(Html.fromHtml(mDisease.getDrugtext()));
    mDiseaseFoodTextView.setText(Html.fromHtml(mDisease.getFoodtext()));
  }
}
