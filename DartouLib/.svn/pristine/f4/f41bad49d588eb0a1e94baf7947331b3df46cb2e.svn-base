package com.dartou.lib.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dartou.lib.R;
import com.dartou.lib.utils.DensityUtil;

/**
 * 计时器
 * 
 * @author cyn
 * 
 */
public class CTimerLayout extends RelativeLayout {

	private DrawBackgroud m_drawBackgroud;
	private TextView m_drawText;

	public CTimerLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setGravity(Gravity.CENTER);

		m_drawBackgroud = new DrawBackgroud(getContext(), null);

		img = new ImageView(getContext(), null);
		img.setBackgroundResource(R.drawable.room_clock);

		m_drawText = new TextView(getContext(), null);
		m_drawText.setText("15");
		m_drawText.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));// 加粗
		m_drawText.setGravity(Gravity.CENTER);
		m_drawText.setTextColor(Color.parseColor("#CDCD00"));

	}

	public void InitScale(int w, int h) {
		m_drawBackgroud.InitScale(w, h);
		m_drawText.setLayoutParams(new LayoutParams(w, h));
		img.setLayoutParams(new LayoutParams(w, h));
		addView(m_drawBackgroud);
		addView(img);
		addView(m_drawText);
	}

	/**
	 * sum 倒计时总时间 count 在总时间秒内画的次数 i 用于当前已经画了的次数 count = sum *20
	 */
	int sum = 20;
	public int count = sum * 20;
	public int i = 0;
	boolean flag = true;
	boolean isRunning = false;
	int everytime = 0;
	private float every_degree = 0;
	Runnable runnable = new Runnable() {
		public void run() {
			while (true) {

				try {
					Thread.sleep(everytime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (flag) {
					if (i <= count) {
						i++;
						m_drawBackgroud.postInvalidate();
					} else {
						handler.sendEmptyMessage(0);
						isRunning = false;
						break;
					}
				} else {
					isRunning = false;
					break;
				}
			}
		}
	};
	private ImageView img;
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			StopTimer();
		};
	};

	/**
	 * 计时器启动
	 * 
	 * @param n
	 *            当前时间
	 * @param nSum
	 *            总时间
	 */
	public void StartTimer(int n, int nSum) {
		if(sum==0){
			return;
		}
		sum = nSum;
		count = sum * 20;
		everytime = sum * 1000 / count;
		i = (sum - n) * (count / sum);
		every_degree = (float) 360 / count;
		flag = true;
		if (!isRunning) {
			new Thread(runnable).start();
			isRunning = true;
		}
		setVisibility(View.VISIBLE);
	}

	public void StopTimer() {
		flag = false;
		setVisibility(View.GONE);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		// TODO Auto-generated method stub
		super.onWindowFocusChanged(hasWindowFocus);

	}

	public int GetTimerTime() {
		return (sum - i / (count / sum));
	}

	class DrawBackgroud extends View {

		private int width = 0;
		private int height = 0;

		// private SweepGradient sw1 = null;
		// private SweepGradient sw2 = null;

		private RectF oval;
		private Paint paint = new Paint();

		public DrawBackgroud(Context context, AttributeSet attrs) {
			super(context, attrs);

		}

		public void InitScale(int width1, int height1) {
			width = width1;
			height = height1;
			setLayoutParams(new LayoutParams(width, height));
			int detal = 11 * width / 115;
			m_drawText.setTextSize(DensityUtil.px2sp(getContext(),
					30 * width / 115));
			oval = new RectF(detal, detal, width - detal, height - detal);
		}

		@Override
		protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
			// TODO Auto-generated method stub
			super.onMeasure(widthMeasureSpec, heightMeasureSpec);
			setMeasuredDimension(width, height);
		}

		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			paint.setAntiAlias(true);
			int nTime = (sum - i / (count / sum));
			if (nTime != 0)
				m_drawText.setText("" + nTime);

			paint.setColor(Color.rgb(255 * i / count, 255 - 255 * i / count, 0));
			canvas.drawArc(oval, i * every_degree - 90,
					(360 - i * every_degree), true, paint);

		}
	}
}
