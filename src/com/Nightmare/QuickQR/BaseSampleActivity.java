package com.Nightmare.QuickQR;

import java.util.Random;

import android.content.Intent;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.viewpagerindicator.PageIndicator;

public abstract class BaseSampleActivity extends FragmentActivity {
	private static final Random RANDOM = new Random();
	
	TestFragmentAdapter mAdapter;
	ViewPager mPager;
	PageIndicator mIndicator;
	public Vibrator vib;

	
	public boolean onCreateOptionsMenu(Menu menu) {
		vib = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
//		getMenuInflater().inflate(R.menu.menu, menu);
		menu.add(1, 1, 0, "Contact").setIcon(R.drawable.ic_menu_compose);
		menu.add(1, 2, 1, "Review").setIcon(R.drawable.ic_menu_star);
		menu.add(1, 3, 2, "Twitter").setIcon(R.drawable.ic_menu_twitter);
		menu.add(1, 4, 3, "Facebook").setIcon(R.drawable.ic_menu_facebook);
		menu.add(1, 5, 4, "Youtube").setIcon(R.drawable.ic_menu_youtube);
		menu.add(2, 6, 0, "Website").setIcon(R.drawable.ic_menu_mapmode);

		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		// Contact 1
		case 1:
			vib.vibrate(40);
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			String aEmailList[] = { "NightmareApps@gmail.com" };
			emailIntent
					.putExtra(android.content.Intent.EXTRA_EMAIL, aEmailList);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,
					"Quick QR - In App Contact");
			emailIntent.setType("plain/text");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "");
			startActivity(emailIntent);
			return true;
		// Review 2
		case 2:
			vib.vibrate(40);
			Intent marketIntent = new Intent(
					Intent.ACTION_VIEW,
					Uri.parse("market://details?id=com.Nightmare.QuickQR"));
			startActivity(marketIntent);
			return true;
			// Twitter 3
		case 3:
			vib.vibrate(40);
			Intent twitterIntent = new Intent("android.intent.action.VIEW",
					Uri.parse("https://twitter.com/#!/NightmareApps"));
			startActivity(twitterIntent);
			return true;
			// Facebook 4
		case 4:
			vib.vibrate(40);
			Intent facebookIntent = new Intent("android.intent.action.VIEW",
					Uri.parse("https://www.facebook.com/NightmareApps"));
			startActivity(facebookIntent);
			return true;
			// Youtube 5
		case 5:
			vib.vibrate(40);
			Intent youtubeIntent = new Intent("android.intent.action.VIEW",
					Uri.parse("http://www.youtube.com/user/NightmareApps"));
			startActivity(youtubeIntent);
			return true;
			// Website 6
		case 6:
			vib.vibrate(40);
			Intent websiteIntent = new Intent("android.intent.action.VIEW",
					Uri.parse("http://www.NightmareApps.com/"));
			startActivity(websiteIntent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	
}
