package com.myandroid.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ThreeAcitivity extends Activity implements OnClickListener {
	private Button bnt3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.three);
		bnt3 = (Button) findViewById(R.id.bnt3);
		bnt3.setOnClickListener(this);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, FourActivity.class);
		startActivity(intent);
	}


}
