package hehut.scse.kaoyanbang.TabFragment.Practice.Util;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


import java.lang.reflect.Field;

import hehut.scse.kaoyanbang.R;

public class FixWindow {
	private Window window;
	private View view;
	private Context context;

	public FixWindow(Window window, Context context) {
		this.window = window;
		this.context = context;
		view = window.getDecorView();
	}

	public void beauti() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			ViewGroup mContentView = (ViewGroup) view.findViewById(Window.ID_ANDROID_CONTENT);

			// ͸��״̬��
			window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			int statusBarHeight = getStatusBarHeight();

			View mChildView = mContentView.getChildAt(0);
			if (mChildView != null) {
				FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mChildView
						.getLayoutParams();
				// ����Ѿ�Ϊ ChildView ���ù��� marginTop, �ٴε���ʱֱ������
				if (lp != null && lp.topMargin < statusBarHeight
						&& lp.height != statusBarHeight) {
					// ��Ԥ��ϵͳ�ռ�
					ViewCompat.setFitsSystemWindows(mChildView, false);
					lp.topMargin += statusBarHeight;
					mChildView.setLayoutParams(lp);
				}
			}

			View statusBarView = mContentView.getChildAt(0);
			if (statusBarView != null
					&& statusBarView.getLayoutParams() != null
					&& statusBarView.getLayoutParams().height == statusBarHeight) {
				// �����ظ�����ʱ������ View
//				statusBarView.setBackgroundResource(R.drawable.title_bg_sky);
				statusBarView.setBackgroundColor(context.getResources().getColor(R.color.title_bg));
				return;
			}
			statusBarView = new View(context);
			ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, statusBarHeight);
//			statusBarView.setBackgroundResource(R.drawable.title_bg_sky);
			statusBarView.setBackgroundColor(context.getResources().getColor(R.color.title_bg));
			// �� ContentView ����Ӽ� View
			mContentView.addView(statusBarView, 0, lp);
		}
	}
	public void noStatus(){
		
	}

	public int getStatusBarHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			return context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
			return 75;
		}
	}

}
