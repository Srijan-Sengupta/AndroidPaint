package somitsolutions.training.android.androidpaint;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ColorPicker extends Activity implements OnClickListener{

	private Button okBtn;
	private SeekBar redSeekBar;
	private SeekBar greenSeekBar;
	private SeekBar blueSeekBar;
	private EditText redEditText;
	private EditText greenEditText;
	private EditText blueEditText;
	private TableLayout tableLayout;
	
	private int redProgress = 0;
	private int greenProgress = 0;
	private int  blueProgress = 0;
	
	private int redValue = 0;
	private int greenValue = 0;
	private int blueValue = 0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colorpicker);
        
        tableLayout = (TableLayout)findViewById(R.id.TableLayout01);
        okBtn = (Button)findViewById(R.id.Button01);
        
        //Seekbars
        redSeekBar = (SeekBar)findViewById(R.id.SeekBarRed);
        greenSeekBar = (SeekBar)findViewById(R.id.SeekBarGreen);
        blueSeekBar = (SeekBar)findViewById(R.id.SeekBarBlue);
        
        //EditTexts
        redEditText = (EditText)findViewById(R.id.EditTextRed);
        greenEditText = (EditText)findViewById(R.id.EditTextGreen);
        blueEditText = (EditText)findViewById(R.id.EditTextBlue);
      
        
        redSeekBar.setOnSeekBarChangeListener(OnSeekBarProgress);
        greenSeekBar.setOnSeekBarChangeListener(OnSeekBarProgress);
        blueSeekBar.setOnSeekBarChangeListener(OnSeekBarProgress);
        
        //okBtn.setBackgroundDrawable(Utility.resizeImage(this,R.drawable.picture, 32, 32));
        okBtn.setOnClickListener(this);
        
        //set the maximum of seekbars as 100%
        redSeekBar.setMax(254);
        greenSeekBar.setMax(254);
        blueSeekBar.setMax(254);
    }
    
    OnSeekBarChangeListener OnSeekBarProgress =
    	new OnSeekBarChangeListener() {

    	public void onProgressChanged(SeekBar s, int progress, boolean touch){
    	
        	if(touch){
    	
        	if(s == redSeekBar){
    		
    		redProgress = progress;
    		redEditText.setText(Integer.toString(progress*100/254));
    	}

        	if(s == greenSeekBar ){
    		greenProgress = progress;
    		greenEditText.setText(Integer.toString(greenProgress*100/254));
    	}

        	if(s == blueSeekBar ){
    		blueProgress = progress;
    		blueEditText.setText(Integer.toString(blueProgress*100/254));
    	}

        	int color = Color.rgb(redProgress, greenProgress, blueProgress);
    	
        	tableLayout.getRootView().setBackgroundColor(color);

    	}
        	
    	}

    	public void onStartTrackingTouch(SeekBar s){

    	}

    	public void onStopTrackingTouch(SeekBar s){

    	}
    	};
    public void onClick(View v){
    	
    	if(v == okBtn) {
    	
    		String redText = redEditText.getText().toString();
    		String greenText = greenEditText.getText().toString();
    		String blueText = blueEditText.getText().toString();
    		
    		   		
    		if(!("".equals(redText))){
    			try{
    				redValue = Integer.parseInt(redText);
    			}
    			catch(NumberFormatException e){	
    			}
    		}
    		
    		else
    			redValue = 0;
    		
    		if(!("".equals(greenText))){
    			try {
    				greenValue = Integer.parseInt(greenText);
    			}
    			catch(NumberFormatException e){	
    			}
    		}
    		else
    			greenValue = 0;
    		
    		if(!("".equals(blueText))){
    			try {
    				blueValue = Integer.parseInt(blueText);
    			}
    			catch(NumberFormatException e){	
    			}
    		}
    		else
    			blueValue = 0;
    		
    		if( redValue>=0 && redValue<=100) {
    			redProgress = 254*redValue/100;
    			redSeekBar.setProgress(redProgress);
    		}
    		
    		else {
    				new AlertDialog.Builder(this)
    				.setTitle("Alert!")
    				.setMessage("Please enter a value between 0 and 100 for Red...")
    				.setNeutralButton("Close", new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dlg, int sumthin) {
    				// do nothing � it will close on its own
    				}
    				})
    				.show(); 
    				}
    		
    	
	    	if( greenValue>=0 && greenValue<=100) {
				greenProgress = 254*greenValue/100;
				greenSeekBar.setProgress(greenProgress);
			}
			
			else {
					new AlertDialog.Builder(this)
					.setTitle("Alert!")
					.setMessage("Please enter a value between 0 and 100 for Green...")
					.setNeutralButton("Close", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dlg, int sumthin) {
					// do nothing � it will close on its own
					}
					})
					.show(); 
					}
			
	    	
	    	if( blueValue>=0 && blueValue<=100) {
				blueProgress = 254*blueValue/100;
				blueSeekBar.setProgress(blueProgress);
			}
			
			else {
					new AlertDialog.Builder(this)
					.setTitle("Alert!")
					.setMessage("Please enter a value between 0 and 100 for Blue...")
					.setNeutralButton("Close", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dlg, int sumthin) {
					// do nothing � it will close on its own
					}
					})
					.show(); 
					}
	    	
	     	int color = Color.rgb(redProgress, greenProgress, blueProgress);
	    	tableLayout.getRootView().setBackgroundColor(color);
	    	
	    	if(!((redValue > 100) || (greenValue>100) || (blueValue >100))){
	    	Intent i = new Intent();
	    	double[] data_to_be_passed = {redProgress,greenProgress,blueProgress};
	    	i.putExtra("somitsolutions.training.android.colorpicker.color_of_the_shape",data_to_be_passed);
	    	setResult(RESULT_OK,i);
	    	finish();
	    	}
	    	
			}	
    }
}
