package com.Nightmare.QuickQR;

import java.io.InputStream;
import java.net.URL;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public final class EmailFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	Button generateQREmail;
	public static EditText emailAddress;
	public static Drawable theImage;

	public static EmailFragment newInstance(String content) {
		EmailFragment fragment = new EmailFragment();

		return fragment;
	}

	private String mContent = "???";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if ((savedInstanceState != null)
				&& savedInstanceState.containsKey(KEY_CONTENT)) {
			mContent = savedInstanceState.getString(KEY_CONTENT);
		}
		View v = inflater.inflate(R.layout.email, container, false);
		this.emailAddress = (EditText) v.findViewById(R.id.emailAddress);
		this.generateQREmail = (Button) v
				.findViewById(R.id.generateEmailQR);
		this.generateQREmail.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Log.d("TAG", QRCreator.createEmail(v));
				 displayQR(QRCreator.createEmail(v));
			}
		});
		return v;
	}

	public void displayQR(String QRurl) {
		Object content = null;
		try {
			URL url = new URL(QRurl);
			content = url.getContent();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		InputStream is = (InputStream) content;
		theImage = Drawable.createFromStream(is, "src");
		makeDialog();
	}

	public void makeDialog() {
		//set up dialog
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.qrdialog);
        dialog.setCancelable(true);
        ImageView imgv = (ImageView) dialog.findViewById(R.id.ImageView01);
        imgv.setImageDrawable(theImage);

		Button cancel = (Button) dialog.findViewById(R.id.Button01);
		cancel.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
	            dialog.dismiss();
			}
		});
        
        dialog.show();
    }
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}
}
