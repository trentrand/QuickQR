package com.Nightmare.QuickQR;

import java.net.URI;
import java.net.URISyntaxException;

import android.util.Log;
import android.view.View;

public class QRCreator {

	public static String baseURL = "http://google.com/m";
	public static String urlBase = "http://chart.apis.google.com/chart?cht=qr&chs=500x500&chld=L&choe=UTF-8&chl=http%3A%2F%2F";
	public static String emailBase = "http://chart.apis.google.com/chart?cht=qr&chs=500x500&chld=L&choe=UTF-8&chl=mailto%3A";
	public static String applicationBase = "http://chart.apis.google.com/chart?cht=qr&chs=500x500&chld=L&choe=UTF-8&chl=market%3A%2F%2Fdetails%3Fid%3D";
	public static String contactBase = "http://chart.apis.google.com/chart?cht=qr&chs=500x500&chld=L&choe=UTF-8&chl=MECARD:";
	
	public static String createWebsite(View v) {
		String websiteUrl = urlBase
				+ WebsiteFragment.websiteURL.getText().toString().toLowerCase();
		return websiteUrl;

	}

	public static String createEmail(View v) {
		String emailUrl = emailBase
				+ EmailFragment.emailAddress.getText().toString();
		return emailUrl;
	}

	public static String createApplication(View v, String packageName) {
		String applicationUrl = applicationBase + packageName;
		return applicationUrl;
	}
	
	public static String createContact(View v) {
		String contactUrl = contactBase;
		if (ContactFragment.lastNameText.getText().length() > 0) {
			contactUrl = contactUrl + "N:" + ContactFragment.lastNameText.getText().toString();
		}
		if (ContactFragment.firstNameText.getText().length() > 0) {
			contactUrl = contactUrl + "," + ContactFragment.firstNameText.getText().toString();
		}
		if (ContactFragment.companyText.getText().length() > 0) {
			contactUrl = contactUrl + ";ORG:" + ContactFragment.companyText.getText().toString();
		}
		if (ContactFragment.phoneNumberText.getText().length() > 0) {
			contactUrl = contactUrl + ";TEL:" + ContactFragment.phoneNumberText.getText().toString();
		}
		if (ContactFragment.emailText.getText().length() > 0) {
			contactUrl = contactUrl + ";EMAIL:" + ContactFragment.emailText.getText().toString();
		}
		if (ContactFragment.websiteText.getText().length() > 0) {
			contactUrl = contactUrl + ";URL:" + ContactFragment.websiteText.getText().toString();
		}
		if (ContactFragment.addressText.getText().length() > 0) {
			contactUrl = contactUrl + ";ADR:" + ContactFragment.addressText.getText().toString();
		}
		if (ContactFragment.cityText.getText().length() > 0) {
			contactUrl = contactUrl + " " + ContactFragment.cityText.getText().toString();
		}
		if (ContactFragment.stateText.getText().length() > 0) {
			contactUrl = contactUrl + " " + ContactFragment.stateText.getText().toString();
		}
		if (ContactFragment.zipText.getText().length() > 0) {
			contactUrl = contactUrl + " " + ContactFragment.zipText.getText().toString();
		}
		try {
			URI uri = new URI(contactUrl.replace(" ", "%20"));
			contactUrl = uri.toString();
		} catch (URISyntaxException e) {
			Log.d("TAG", "URISyntaxException");
			e.printStackTrace();
		}
		Log.d("TAG", contactUrl);
		return contactUrl;
	}
}
