package com.androidsrc.server;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity {

	Server server;
	TextView infoip, msg;
	TextView messageTextview;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		infoip = (TextView) findViewById(R.id.infoip);
		msg = (TextView) findViewById(R.id.msg);
		messageTextview = (TextView) findViewById(R.id.messageTextview);
		server = new Server(this);
		infoip.setText(server.getIpAddress()+":"+server.getPort());

		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		server.onDestroy();
	}

	
}