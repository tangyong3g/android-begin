package com.graphics.enginedemo;

import java.lang.reflect.Constructor;

import com.graphics.engine.GLActivity;
import com.graphics.engine.view.GLContentView;
import com.graphics.engine.view.GLView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 
 * <br>类描述: 单个测试程序，支持GLView和View
 * <br>功能详细描述:
 * 
 * @author  dengweiming
 * @date  [2013-5-29]
 */
public class BaseTestActivity extends GLActivity {
	boolean mDetectGLES;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			String className = extras.getString(Main.KEY_CONTENT_VIEW);
			if (className != null) {
				try {
					@SuppressWarnings("rawtypes")
					Class cls = Class.forName(className);
					@SuppressWarnings({ "unchecked", "rawtypes" })
					Constructor constructor = cls.getConstructor(Context.class);
					Object object = constructor.newInstance(this);
					
					if (object instanceof GLView) {
						mDetectGLES = true;
						GLContentView glContentView = new GLContentView(this, true);
						glContentView.setOverlayedViewGroup(new FrameLayout(this));
						setSurfaceView(glContentView, true);
						setContentGlView((GLView) object);
					} else if (object instanceof View) {
						setContentView((View) object);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void handleGLES20UnsupportedError() {
		if (mDetectGLES) {
			super.handleGLES20UnsupportedError();
		}
	}
}
