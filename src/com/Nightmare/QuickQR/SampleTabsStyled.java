package com.Nightmare.QuickQR;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitleProvider;

public class SampleTabsStyled extends BaseSampleActivity {
	private static final String[] CONTENT = new String[] { "Contact",
			"Website", "Email", "Application" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_tabs);
		mAdapter = new GoogleMusicAdapter(getSupportFragmentManager());

		mPager = (ViewPager) findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);

		mIndicator = (TabPageIndicator) findViewById(R.id.indicator);
		mIndicator.setViewPager(mPager);
//		toastify("Thank you for donating!");

	}
	
	public void toastify(String msg) {
		Toast error = Toast.makeText(this, msg, Toast.LENGTH_LONG);
		error.show();
	}

	class GoogleMusicAdapter extends TestFragmentAdapter implements
			TitleProvider {
		public GoogleMusicAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			if (position == 0) {
				return ContactFragment
						.newInstance(SampleTabsStyled.CONTENT[position
								% SampleTabsStyled.CONTENT.length]);

			} else if (position == 1) {
				return WebsiteFragment
						.newInstance(SampleTabsStyled.CONTENT[position
								% SampleTabsStyled.CONTENT.length]);
			} else if (position == 2) {
				return EmailFragment
						.newInstance(SampleTabsStyled.CONTENT[position
								% SampleTabsStyled.CONTENT.length]);
			} else if (position == 3) {
				return ApplicationFragment
						.newInstance(SampleTabsStyled.CONTENT[position
								% SampleTabsStyled.CONTENT.length]);
			} else {
				return null;
			}
		}

		@Override
		public int getCount() {
			return SampleTabsStyled.CONTENT.length;
		}

		@Override
		public String getTitle(int position) {
			return SampleTabsStyled.CONTENT[position
					% SampleTabsStyled.CONTENT.length].toUpperCase();
		}
	}
}
