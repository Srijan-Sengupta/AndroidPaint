package somitsolutions.training.android.androidpaint;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

public class StartingActivity extends Activity implements View.OnClickListener{
	Button newImage;
	Button editImage;
	Button viewImage;
	Intent i;
	static boolean isToBeEdited = false;
	static boolean isToBeViewd = false;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startpage);
        
        newImage = (Button)findViewById(R.id.buttonNew);
        editImage = (Button)findViewById(R.id.buttonEdit);
        viewImage = (Button)findViewById(R.id.buttonViewImage);
        newImage.setOnClickListener(this);
        editImage.setOnClickListener(this);
        viewImage.setOnClickListener(this);
        
        i = new Intent();
        
        i.setClassName("somitsolutions.training.android.androidpaint", "somitsolutions.training.android.androidpaint.AndroidPaint");
        
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == newImage){
		startActivity(i);
		isToBeEdited  = false;
		isToBeViewd = false;
		}
		
		if(arg0 == editImage){
			isToBeEdited = true;
			isToBeViewd = false;
			Intent iFileList = new Intent();
			iFileList.setClassName("somitsolutions.training.android.androidpaint","somitsolutions.training.android.androidpaint.FileList");
			startActivity(iFileList);
			//isToBeEdited = false;
		}
		
		if(arg0 == viewImage){
			isToBeEdited  = false;
			isToBeViewd = true;
			Intent iFileList = new Intent();
			iFileList.setClassName("somitsolutions.training.android.androidpaint","somitsolutions.training.android.androidpaint.FileList");
			startActivity(iFileList);
			//isToBeEdited = false;
		}
	}
	@Override
	public void onBackPressed() {
		/*File tempFile = new File(AndroidPaint.mSavedTempFilePath);
		if(tempFile.exists() == true){
			tempFile.delete();
			AndroidPaint.isTempFileAlreadySaved = false;
		}*/
		//tempFile.delete();
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
}
