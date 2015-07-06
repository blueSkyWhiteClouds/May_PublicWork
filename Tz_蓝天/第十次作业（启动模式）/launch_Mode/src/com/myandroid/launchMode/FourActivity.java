package com.myandroid.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FourActivity extends Activity implements OnClickListener {
	private Button bnt4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.four);
		bnt4=(Button) findViewById(R.id.bnt4);
		bnt4.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(this,FiveActivity.class);
		startActivity(intent);
	}
}
