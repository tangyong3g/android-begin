package com.graphics.enginedemo;

import com.graphics.engine.view.GLLinearLayout;
import com.graphics.engine.view.GLViewWrapper;
import com.graphics.engine.widget.GLEditText;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;

/*
import android.graphics.Rect;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.go.gl.view.GLView;
public class EditTextTestView extends LinearLayout {

	public EditTextTestView(Context context) {
		super(context);

		//setOrientation(GLLinearLayout.VERTICAL);
		//setPadding(0, 600, 0, 0);

		EditText text = new EditText(context);
		addView(text, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	}


}
*/
///*

//CHECKSTYLE IGNORE 1 LINES
public class EditTextTestView extends GLLinearLayout {
	
	
	public EditTextTestView(Context context) {
		super(context);
		
		//setOrientation(GLLinearLayout.VERTICAL);
		//setPadding(0, 600, 0, 0);
		
		GLViewWrapper viewWrapper = new GLEditText(context);
		addView(viewWrapper, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		
	}
	
	
}
//*/

