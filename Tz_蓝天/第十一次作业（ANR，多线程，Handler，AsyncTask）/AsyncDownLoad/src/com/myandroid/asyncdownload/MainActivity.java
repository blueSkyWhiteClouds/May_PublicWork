package com.myandroid.asyncdownload;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button downLoadImage;
	private Button downLoadAPK;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		initView();
	}

	private void initView() {
		downLoadImage = (Button) findViewById(R.id.downLoadImage);
		downLoadAPK = (Button) findViewById(R.id.downLoadAPK);
		downLoadImage.setOnClickListener(this);
		downLoadAPK.setOnClickListener(this);
	}

	public void onClick(View v) {
		Intent intent=null;
		switch (v.getId()) {
		case R.id.downLoadImage:
			intent=new Intent(MainActivity.this,DownLoadImageActivity.class);
			startActivity(intent);
			break;
		case R.id.downLoadAPK:
			intent=new Intent(MainActivity.this,DownLoadAPKActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
}
