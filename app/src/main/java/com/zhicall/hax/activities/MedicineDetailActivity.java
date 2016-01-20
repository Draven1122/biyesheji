package com.zhicall.hax.activities;

import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import com.squareup.picasso.Picasso;
import com.zhicall.hax.BaseActivity;
import com.zhicall.hax.R;
import com.zhicall.hax.bean.Medicine;
import com.zhicall.hax.net.Data;
import com.zhicall.hax.net.IMedicalService;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Xingchen on 2016/1/19.
 * Email:huangjinxin@zhicall.cn
 */
public class MedicineDetailActivity extends BaseActivity {

  @Bind(R.id.img_icon) ImageView mIconImageView;
  @Bind(R.id.tv_medicine_name) TextView mMedicineNameTextView;
  @Bind(R.id.tv_medicine_desc) TextView mMedicineDescTextView;
  @Bind(R.id.tv_medicine_price) TextView mMedicinePriceTextView;
  @Bind(R.id.tv_medicine_detail) TextView mMedicineDetailTextView;
  private Medicine mMedicine;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_medicine_detail);
    initActionbar(true, false, "MedicalDetail");
    initData();
  }

  @Override public void initData() {
    mMedicine = (Medicine) getIntent().getExtras().getSerializable("medicine");
    String url;
    if (!mMedicine.getImg().contains("http")) {
      url = getResources().getString(R.string.tiangou_img_url_prefix) + mMedicine.getImg();
    }
    url = mMedicine.getImg();
    Picasso.with(this).load(url).into(mIconImageView);
    mMedicineNameTextView.setText(mMedicine.getName());
    mMedicineDescTextView.setText(mMedicine.getDescription());
    mMedicinePriceTextView.setText(mMedicine.getPrice() + "");
    Data.tianGouService(IMedicalService.class)
        .medicineDetail(mMedicine.getId())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .map(medicine -> medicine.getMessage())
        .doOnSubscribe(() -> this.showProgressdialog("正在获取药品详情..."))
        .finallyDo(this::dissmissProgressDialog)
        .subscribe(msg -> mMedicineDetailTextView.setText(Html.fromHtml(msg)));
  }
}
