package com.myandroid.launchMode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class SecondActivity extends Activity implements OnClickListener {

	private Button bnt2;
	private String text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second);
		bnt2=(Button) findViewById(R.id.bnt2);
		bnt2.setOnClickListener(this);
		
	}
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	
	
	public void onClick(View v) {
		Intent intent=new Intent(this,ThreeAcitivity.class);
		startActivity(intent);
	}
}
