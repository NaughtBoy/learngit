package com.schoolinfo.layout;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;
import com.schoolinfo.MainActivity;
import com.schoolinfo.control.UIControl;
import com.schoolinfo.data.LostData;
import com.schoolinfo.view.LostCard;
import com.schoolinfo.view.ShowPictureView;

import android.R.integer;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * 失物招领界面,每一页十个物品,在创建前需选择查询条件
 * 
 * @author huangwubin 2014-9-4
 */
public class LostLayout extends BaseLayout {

	private static BmobQuery<LostData> goodsQuery;
	final public static int QUERYALL=0;//查询所有数据
	final public static int QUERYMY=1;//查询当前登陆用户的数据
	
	private ArrayList<LostCard> LostCards = new ArrayList<LostCard>();
	private LinearLayout goodsLayout;// 物品列表布局
	private PullToRefreshScrollView goodScrollView;// 滚动条

	public LostLayout(Context context) {	
		super.mLayout = new RelativeLayout(context);
		mLayout.setTag(RunLayout.LOSTLAYOUT);// 标签

		setView();
	}

	private void setView() {
		// TODO Auto-generated method stub

		goodScrollView = new PullToRefreshScrollView(MainActivity.getInstance());
		goodsLayout = new LinearLayout(MainActivity.getInstance());
		//顶部菜单
		TopLayout topLayout=new TopLayout(MainActivity.getInstance());
		topLayout.setLayoutText(RunLayout.LOSTLAYOUT);
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
							LostCards.clear();
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
	 * 设置下标为index的LostCard的信息
	 * 
	 * @param index
	 * @param data
	 *            LostData对象
	 */
	public void setLostCard(int index, LostData data) {
		LostCards.get(index).setData(data);
	}

	/**
	 * 设置所有LostCard的信息
	 * 
	 * @param datas
	 */
	public void setLostCards(LostData[] datas) {
		for (int i = 0; i < datas.length; i++) {
			setLostCard(i, datas[i]);
		}
	}

	private void addGoods(int n) {
		// 查询忽略前list.size（）条的数据
		goodsQuery.setSkip(LostCards.size());
		goodsQuery.setLimit(n);
		goodsQuery.findObjects(MainActivity.getInstance(),
				new FindListener<LostData>() {

					@Override
					public void onSuccess(List<LostData> arg0) {
						// TODO Auto-generated method stub
						if (arg0.size() > 0) {
							for (int i = 0; i < arg0.size(); i++) {
								final LostCard lostCard = new LostCard(
										MainActivity.getInstance());
								LinearLayout.LayoutParams cardParams = (LayoutParams) lostCard
										.getLayoutParams();
								cardParams.topMargin = UIControl.p.y / 96;
								lostCard.setLayoutParams(cardParams);

								lostCard.setData(arg0.get(i));
								if (i % 2 == 0) {
									lostCard.isLeft(false);
								}
								// 图片的点击事件
								lostCard.mImageView
										.setOnClickListener(new OnClickListener() {

											@Override
											public void onClick(View v) {
												// TODO Auto-generated method
												// stub
												lostCard.mImageView
														.setDrawingCacheEnabled(true);
												// lostCard.mData.mThumbnail=Bitmap.createBitmap(lostCard.mImageView.getDrawingCache());

												ShowPictureView showPictureView = new ShowPictureView(
														MainActivity
																.getInstance(),
														lostCard.mData.mPicture,
														Bitmap.createBitmap(lostCard.mImageView
																.getDrawingCache()));
												MainLayout
														.getInstance()
														.addView(
																showPictureView);
												lostCard.mImageView
														.setDrawingCacheEnabled(false);
											}
										});
								goodsLayout.addView(lostCard);
								LostCards.add(lostCard);
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

	public static BmobQuery<LostData> getGoodsQuery() {
		return goodsQuery;
	}

	/**
	 * @param queryType 查询方式的编号
	 */
	public static void setGoodsQuery(int queryType) {
		if (goodsQuery==null) {
			goodsQuery=new BmobQuery<LostData>();
		}
		switch (queryType) {
		case 0://查看所有
			goodsQuery=new BmobQuery<LostData>();
			break;
		case 1://查看当前登陆用户
			goodsQuery.addWhereEqualTo("mUser", MainLayout.getInstance().mCurrentUser);
			break;
		default:
			break;
		}
	}

}
