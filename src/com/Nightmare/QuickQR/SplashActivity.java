package com.Nightmare.QuickQR;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class SplashActivity extends Activity {

	public ImageButton website;
	Timer timer = new Timer();
	public TimerTask timerTask;
	final Handler handler = new Handler();

	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		timerTask = new TimerTask() {
			public void run() {
				handler.post(new Runnable() {
					public void run() {
						Intent intent = new Intent(SplashActivity.this,
								SampleTabsStyled.class);
						startActivity(intent);
						System.exit(0);
					}
				});
			}
		};
		timer = new Timer();
		timer.schedule(timerTask, 3000, 100000);

		website = (ImageButton) findViewById(R.id.splashButton);
		website.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent iternetIntent = new Intent("android.intent.action.VIEW",
						Uri.parse("http://www.NightmareApps.com/"));
				startActivity(iternetIntent);
			}
		});
	}
}
