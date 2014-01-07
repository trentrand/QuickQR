package com.Nightmare.QuickQR;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

public final class ApplicationFragment extends Fragment {
	private static final String KEY_CONTENT = "TestFragment:Content";
	Button generateApplicationQR;
//	public ImageView ApplicationQRImage;
	private ListView lView;
	private ArrayList<String> results = new ArrayList<String>();
	public static Drawable theImage;

	public static ApplicationFragment newInstance(String content) {
		ApplicationFragment fragment = new ApplicationFragment();

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
		final View v = inflater.inflate(R.layout.application, container, false);
		
		/** Create listview of applications on phone **/
		lView = (ListView) v.findViewById(R.id.applicationListView);
		PackageManager pm = this.getActivity().getPackageManager();
		Intent intent = new Intent(Intent.ACTION_MAIN, null);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);
		final List<ResolveInfo> list = pm.queryIntentActivities(intent,
				PackageManager.PERMISSION_GRANTED);
		for (ResolveInfo rInfo : list) {
			results.add(rInfo.activityInfo.applicationInfo.loadLabel(pm)
					.toString());
			Log.w("Installed Applications", rInfo.activityInfo.applicationInfo
					.loadLabel(pm).toString());
		}
		lView.setAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, results));
		lView.setOnItemClickListener(new OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view,
		        int position, long id) {
		    	displayQR(QRCreator.createApplication(v, list.get(position).activityInfo.packageName));
		    	Log.d("TAG", list.get(position).activityInfo.packageName);
		    }
		  });
		/** End of list **/
//		this.ApplicationQRImage = (ImageView) v
//				.findViewById(R.id.applicationQR);
//		this.generateApplicationQR = (Button) v
//				.findViewById(R.id.generateApplicationQR);
//		this.generateApplicationQR
//				.setOnClickListener(new View.OnClickListener() {
//					public void onClick(View v) {
//						Log.d("TAG", QRCreator.createEmail(v));
//						displayQR(QRCreator.createEmail(v));
//					}
//				});
		return v;
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

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putString(KEY_CONTENT, mContent);
	}
}
