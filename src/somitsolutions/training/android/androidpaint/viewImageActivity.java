package somitsolutions.training.android.androidpaint;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;


public class viewImageActivity extends Activity {
	
	//private PinchImageView view;
	private Bitmap mBitmap;
	//private String imageFilePath;
     
     /** Called when the activity is first created. */
 public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
    // setContentView(R.layout.viewpage);
   
    // view = (PinchImageView) findViewById(R.id.image);
     mBitmap = BitmapFactory.decodeFile(FileList.imageFilePath);
     //view.setImageBitmap(mBitmap);*/
     
     ZoomableImageView zoomableImageView = new ZoomableImageView(this);
     
     zoomableImageView.setImageBitmap(mBitmap);
     
     setContentView(zoomableImageView);
       
 }
 
 @Override
 public boolean onCreateOptionsMenu(Menu menu) {
 	MenuInflater inflater = getMenuInflater();
 	inflater.inflate(R.menu.menuforimageview, menu);
 	//mMenu = menu;
 	return true;
 	}
 
 public boolean onOptionsItemSelected(MenuItem item){
	 
	 switch(item.getItemId()){
	 case R.id.itemShareImageForViewImage:
	    	
	    	Intent share;
	    	File attachment = null;
	    	attachment = new File(FileList.imageFilePath);
				boolean isFileThere = attachment.exists();
				if (isFileThere == true){
					share = new Intent(Intent.ACTION_SEND);
					share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
					share.setType("image/png");
					startActivity(Intent.createChooser(share, "Share drawing"));
					
				}
	    	}
			
			return true;
	 }
	 
 }

