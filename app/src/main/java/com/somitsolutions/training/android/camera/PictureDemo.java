
package com.somitsolutions.training.android.camera;

import android.app.Activity;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;


public class PictureDemo extends Activity implements OnClickListener{
	Panel preview;
	public static Camera camera=null;
	Button takePicture;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		takePicture = (Button) findViewById(R.id.buttonTakePicture);
		preview=(Panel)findViewById(R.id.preview);
		takePicture.setOnClickListener(this);
		
	}
	
	private void takePicture() {
		camera.takePicture(null, null, photoCallback);
	}

	
	Camera.PictureCallback photoCallback=new Camera.PictureCallback() {
		public void onPictureTaken(byte[] data, Camera camera) {
			new SavePhotoTask().execute(data);
			camera.startPreview();
		}
	};
	
	class SavePhotoTask extends AsyncTask<byte[], String, String> {
		@Override
		protected String doInBackground(byte[]... jpeg) {
			File photo=new File(Environment.getExternalStorageDirectory(),
													"photo.jpg");
			
			if (photo.exists()) {
				photo.delete();
			}
			
			try {
				FileOutputStream fos=new FileOutputStream(photo.getPath());
				
				fos.write(jpeg[0]);
				fos.close();
			}
			catch (java.io.IOException e) {
				Log.e("PictureDemo", "Exception in photoCallback", e);
			}
			
			return(null);
		}
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		takePicture();
	}
}
