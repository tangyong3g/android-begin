package com.ty.exsample_unit_5.translate;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.sny.tangyong.androiddemo.R;

public class TranslateActivity extends ListActivity{
	
	String[] units = new String[] { "orthCamera[正交投影]", "perCamera[透视投影]","cullFace[背面裁剪]" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(this, R.layout.main_items,
				units));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {

		super.onListItemClick(l, v, position, id);
		Class cls = null;
		switch (position) {
		case 0:
			cls = OrthCameraActivity.class;
			break;
		case 1:
			cls = PerCameraActivity.class;
			break;
		case 2:
			cls = CullFaceActivity.class;
			break;
		case 3:
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		default:
			break;
		}
		intentToActivity(cls);
	}

	private void intentToActivity(Class cls) {
		Intent intent = new Intent();
		intent.setClass(this, cls);

		startActivity(intent);
	}


}
