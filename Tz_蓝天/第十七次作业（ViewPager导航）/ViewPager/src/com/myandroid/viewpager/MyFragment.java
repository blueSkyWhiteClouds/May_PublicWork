package com.myandroid.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyFragment extends Fragment {
	int position;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ImageView img=new ImageView(this.getActivity());
		Bundle bundle=this.getArguments();
		int image=bundle.getInt("flags");
		position=bundle.getInt("position");
		Log.i("INFO", "创建fragment:"+position);
		img.setImageResource(image);
		return img;
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("INFO", "摧毁fragment:"+position);
	}
	
}
