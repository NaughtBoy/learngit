package com.schoolinfo.layout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.LostData;
import com.schoolinfo.data.OldData;
import com.schoolinfo.data.OldData;
import com.schoolinfo.view.OldCard;
import com.schoolinfo.view.OldCard;
import com.schoolinfo.view.ShowPictureView;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.LinearLayout.LayoutParams;

/**
 * 二手市场布局
 * 
 * @author huangwubin 2014-9-29
 */
public class OldLayout extends BaseLayout {
	private ArrayList<OldCard> OldCards = new ArrayList<OldCard>();
	private ArrayList<OldData> OldDatas = new ArrayList<OldData>();
	private LinearLayout goodsLayout;// 物品列表布局
	private PullToRefreshScrollView goodScrollView;// 滚动条

	private static BmobQuery<OldData> goodsQuery=new BmobQuery<OldData>();//查询条件
	final public static int QUERYALL=0;//查询所有数据
	final public static int QUERYMY=1;//查询当前登陆用户的数据
	
	public OldLayout(Context context) {
		super.mLayout = new RelativeLayout(context);
		mLayout.setTag(RunLayout.OLDLAYOUT);// 标签

		goodScrollView = new PullToRefreshScrollView(context);
		goodsLayout = new LinearLayout(context);
		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub

		//顶部菜单
		TopLayout topLayout=new TopLayout(MainActivity.getInstance());
		topLayout.setLayoutText(RunLayout.OLDLAYOUT);
		super.mLayout.addView(topLayout, UIControl.getLayoutParams(UIControl.topData, 1));
		
		goodsLayout.setOrientation(LinearLayout.VERTICAL);
		goodsLayout.setGravity(Gravity.CENTER_HORIZONTAL);
		// goodsLayout.setBackgroundColor(Color.RED);

		addGoods(10);

		// 上拉加载
		goodScrollView.setMode(Mode.BOTH);
		goodScrollView
				.setOnRefreshListener(new OnRefreshListener<ScrollView>() {

					@Override
					public void onRefresh(
							PullToRefreshBase<ScrollView> refreshView) {
						// TODO Auto-generated method stub
						if (goodScrollView.isHeaderShown()) {// 是否下拉
							// 下拉刷新
							goodsLayout.removeAllViews();
							OldCards.clear();
							addGoods(10);
							goodScrollView.onRefreshComplete();// 恢复
						} else if (goodScrollView.isFooterShown()) {// 判断是否上拉
							// 上拉加载
							addGoods(10);
							goodScrollView.onRefreshComplete();
						} else {

						}

					}
				});

		goodScrollView.addView(goodsLayout);
		this.mLayout.addView(goodScrollView,
				UIControl.getLayoutParams(UIControl.bodyData, 1));
	}

	/**
	 * 设置下标为index的OldCard的信息
	 * 
	 * @param index
	 * @param data
	 *            OldData对象
	 */
	public void setOldCard(int index, OldData data) {
		OldCards.get(index).setData(data);
	}

	/**
	 * 设置所有OldCard的信息
	 * 
	 * @param datas
	 */
	public void setOldCards(OldData[] datas) {
		for (int i = 0; i < datas.length; i++) {
			setOldCard(i, datas[i]);
		}
	}

	private void addGoods(int n) {
		// 查询忽略前list.size（）条的数据
		goodsQuery.setSkip(OldCards.size());
		goodsQuery.setLimit(n);
		goodsQuery.findObjects(MainActivity.getInstance(),
				new FindListener<OldData>() {

					@Override
					public void onSuccess(List<OldData> arg0) {
						// TODO Auto-generated method stub
						if (arg0.size() > 0) {
							for (int i = 0; i < arg0.size(); i++) {
								final OldCard OldCard = new OldCard(
										MainActivity.getInstance());
								LinearLayout.LayoutParams cardParams = (LayoutParams) OldCard
										.getLayoutParams();
								cardParams.topMargin = UIControl.p.y / 96;
								OldCard.setLayoutParams(cardParams);

								OldCard.setData(arg0.get(i));
								if (i % 2 == 0) {
									OldCard.isLeft(false);
								}
								// 图片的点击事件
								OldCard.mImageView
										.setOnClickListener(new OnClickListener() {

											@Override
											public void onClick(View v) {
												// TODO Auto-generated method
												// stub
												OldCard.mImageView
														.setDrawingCacheEnabled(true);
												// OldCard.mData.mThumbnail=Bitmap.createBitmap(OldCard.mImageView.getDrawingCache());

												ShowPictureView showPictureView = new ShowPictureView(
														MainActivity
																.getInstance(),
														OldCard.mData.mPicture,
														Bitmap.createBitmap(OldCard.mImageView
																.getDrawingCache()));
												MainLayout
														.getInstance()
														.addView(
																showPictureView);
												OldCard.mImageView
														.setDrawingCacheEnabled(true);
											}
										});
								goodsLayout.addView(OldCard);
								OldCards.add(OldCard);
							}
						}

					}

					@Override
					public void onError(int arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i("TAG", "error");
					}
				});

	}
	
	/**
	 * @param queryType 查询方式的编号
	 */
	public static void setGoodsQuery(int queryType) {
		switch (queryType) {
		case 0://查看所有
			goodsQuery=new BmobQuery<OldData>();
			break;
		case 1://查看当前登陆用户
			goodsQuery.addWhereEqualTo("mUser", MainLayout.getInstance().mCurrentUser);
			break;
		default:
			break;
		}
		goodsQuery.order("-createdAt");
	}

}
