package com.somitsolutions.training.android.camera;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.List;


@SuppressLint("DrawAllocation")
	public class Panel extends SurfaceView implements SurfaceHolder.Callback{
 
    	public Panel(Context context) {
    		super(context);
    		
    		getHolder().addCallback(this);
    	}
    	
    	public Panel(Context context, AttributeSet attrs) {
            super(context, attrs);
            getHolder().addCallback(this);
            // TODO Auto-generated constructor stub
        }

        public Panel(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            getHolder().addCallback(this);
            // TODO Auto-generated constructor stub
        }

        @SuppressLint("NewApi")
		public void surfaceChanged(SurfaceHolder holder,
															 int format, int width,
															 int height) {
			
			 Camera.Parameters parameters = PictureDemo.camera.getParameters();
			 List<Camera.Size> previewSizes = parameters.getSupportedPreviewSizes();
			 
			  // You need to choose the most appropriate previewSize for your app
			 Camera.Size previewSize = previewSizes.get(0);

			 parameters.setPreviewSize(previewSize.width, previewSize.height);
			 PictureDemo.camera.setParameters(parameters);
			 PictureDemo.camera.startPreview();
		}


    	public void surfaceCreated(SurfaceHolder holder) {
    		PictureDemo.camera=Camera.open();
			
			try {
				PictureDemo.camera.setPreviewDisplay(getHolder());
			}
			catch (Throwable t) {

			}
		}
    	public void surfaceDestroyed(SurfaceHolder holder) {
    		PictureDemo.camera.stopPreview();
    		PictureDemo.camera.release();
    		PictureDemo.camera=null;
		}
          	
}//Class ends