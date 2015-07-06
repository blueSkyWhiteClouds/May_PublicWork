package com.myandroid.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	private Button bnt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first);
		bnt1=(Button) findViewById(R.id.bnt1);
		bnt1.setOnClickListener(this);
		
		
	}
	
	
	
	public void onClick(View v) {
		Intent intent=new Intent(this,SecondActivity.class);
		startActivity(intent);
	}

}
