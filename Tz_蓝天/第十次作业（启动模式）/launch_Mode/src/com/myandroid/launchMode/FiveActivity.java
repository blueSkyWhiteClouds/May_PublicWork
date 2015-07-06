package com.myandroid.launchMode;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class FiveActivity extends Activity implements OnClickListener {
	private Button bnt5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.five);
		bnt5=(Button) findViewById(R.id.bnt5);
		bnt5.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Toast.makeText(this, "第五个界面", Toast.LENGTH_LONG).show();
	}
}
