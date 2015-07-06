package com.myandroid.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		OnCheckedChangeListener, OnPageChangeListener {
	private ViewPager viewpager;
	private RadioGroup rg;
	private HorizontalScrollView hsv;
	private TextView tvline;
	private int[] flags;
	private int fromX;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		viewpager = (ViewPager) findViewById(R.id.viewpager);
		rg = (RadioGroup) findViewById(R.id.rg);
		hsv = (HorizontalScrollView) findViewById(R.id.hsv);
		tvline = (TextView) findViewById(R.id.tvline);
		flags = new int[] { R.drawable.china, R.drawable.korea,
				R.drawable.nkorea, R.drawable.japan, R.drawable.usa,
				R.drawable.india, R.drawable.tail };
		MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
		viewpager.setAdapter(adapter);
		rg.setOnCheckedChangeListener(this);
		viewpager.setOnPageChangeListener(this);
	}

	private class MyPagerAdapter extends FragmentPagerAdapter {

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			MyFragment fragment = new MyFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("flags", flags[arg0]);
			bundle.putInt("position", arg0);
			fragment.setArguments(bundle);

			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return rg.getChildCount();
		}

	}

	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.rb_china:
			viewpager.setCurrentItem(0);
			break;
		case R.id.rb_korea:
			viewpager.setCurrentItem(1);
			break;
		case R.id.rb_nkorea:
			viewpager.setCurrentItem(2);
			break;
		case R.id.rb_japan:
			viewpager.setCurrentItem(3);
			break;
		case R.id.rb_america:
			viewpager.setCurrentItem(4);
			break;
		case R.id.rb_india:
			viewpager.setCurrentItem(5);
			break;
		case R.id.rb_tail:
			viewpager.setCurrentItem(6);
			break;

		default:
			Toast.makeText(this, "没有页面加载", Toast.LENGTH_LONG).show();
			break;
		}
	}

	public void onPageScrolled(int position, float positionOffset,
			int positionOffsetPixels) {
		int total = (int) ((position + positionOffset) * rg.getChildAt(0)
				.getWidth());
		int left = (viewpager.getWidth() - rg.getChildAt(0).getWidth()) / 2;
		int scrollX = total - left;
		hsv.scrollTo(scrollX, 0);
		moveLine(position, positionOffset);
	}

	private void moveLine(int position, float positionOffset) {
		// TODO Auto-generated method stub
		RadioButton rb = (RadioButton) rg.getChildAt(position);
		int[] location = new int[2];
		rb.getLocationInWindow(location);
		int toX=(int) (location[0] + positionOffset * rb.getWidth());
		TranslateAnimation animation = new TranslateAnimation(fromX,
				toX, 0, 0);
		animation.setDuration(50);
		animation.setFillAfter(true);
		fromX=toX;
		tvline.startAnimation(animation);
	}

	public void onPageSelected(int position) {
		// TODO Auto-generated method stub

	}

	public void onPageScrollStateChanged(int state) {
		// TODO Auto-generated method stub

	}
}