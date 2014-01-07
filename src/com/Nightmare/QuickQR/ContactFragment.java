package com.Nightmare.QuickQR;

import java.io.InputStream;
import java.net.URL;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public final class ContactFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	Button generate;
	public static TextView firstNameText;
	public static TextView lastNameText;
	public static TextView companyText;
	public static TextView phoneNumberText;
	public static TextView emailText;
	public static TextView addressText;
	public static TextView cityText;
	public static TextView stateText;
	public static TextView zipText;
	public static TextView websiteText;
	public ImageView ContactQRImage;
	AlertDialog.Builder builder;
	AlertDialog alertDialog;
	public static Drawable theImage;

	public static ContactFragment newInstance(String content) {
		ContactFragment fragment = new ContactFragment();

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
		View v = inflater.inflate(R.layout.contact, container, false);

		this.ContactQRImage = (ImageView) v.findViewById(R.id.contactQRImage);

		ContactFragment.firstNameText = (TextView) v
				.findViewById(R.id.firstNameTF);
		ContactFragment.lastNameText = (TextView) v
				.findViewById(R.id.lastNameTF);
		ContactFragment.companyText = (TextView) v.findViewById(R.id.companyTF);
		ContactFragment.phoneNumberText = (TextView) v
				.findViewById(R.id.phoneNumberTF);
		ContactFragment.emailText = (TextView) v.findViewById(R.id.emailTF);
		ContactFragment.addressText = (TextView) v.findViewById(R.id.addressTF);
		ContactFragment.cityText = (TextView) v.findViewById(R.id.cityTF);
		ContactFragment.stateText = (TextView) v.findViewById(R.id.stateTF);
		ContactFragment.zipText = (TextView) v.findViewById(R.id.zipTF);
		ContactFragment.websiteText = (TextView) v.findViewById(R.id.websiteTF);

		this.generate = (Button) v.findViewById(R.id.generateqr);
		this.generate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				displayQR(QRCreator.createContact(v));
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
//		startActivity(i);
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
//		AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
//	    builder.setCancelable(true);
//	    builder.setIcon(R.drawable.ic_menu_facebook);
//	    builder.setTitle("a");
//	    builder.setInverseBackgroundForced(true);
//	    builder.setPositiveButton("Accept",new DialogInterface.OnClickListener()
//	    {
//
//	        @Override
//	        public void onClick(DialogInterface dialog, int which) 
//	        {
//	            dialog.dismiss();
//	        }
//	    });
//	    builder.setNegativeButton("Reject",new DialogInterface.OnClickListener()
//	    {
//
//	        @Override
//	        public void onClick(DialogInterface dialog, int which)
//	        {
//	            dialog.dismiss();
//	        }
//	    });
//	    AlertDialog alert=builder.create();
//	    alert.show();
//	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}
}
