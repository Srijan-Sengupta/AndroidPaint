package somitsolutions.training.android.androidpaint;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaKotak;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.FloatMath;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class AndroidPaint extends Activity {
    /** Called when the activity is first created. */
	static final int REQUEST_CODE = 1001;
	//This is for the menu selection
	//private Shape GraphicObject;
	
	//each time we create a new graphic object, we create this Shape object called currentGraphicObject
	private Shape currentGraphicObject;
	
	private int ShapeObject_to_be_created;
	
	private static final int ShapeLine = 1;
	private static final int ShapeRect = 2;
	private static final int ShapeCircle = 3;
	private static final int ShapeOval = 4;
	private static final int ShapeFreehand =5;
	private static final int ShapeErase = 6;
	
	private Paint mPaint;
	private Menu mMenu = null;
	private float BrushWidth;
	
	//double[] color;
	int mColor;
	Panel p;
	int number_of_graphicObjects;
	
	boolean shapemenuclicked;
	boolean colormenuclicked;
	//boolean erasemenuclicked;
	boolean brushwidthmenuclicked;
	
	private ArrayList<Shape> graphicobjects;
	
	//private Bitmap wallPaperBitmap;
	static String mImagePath;
	File file;
	File tempFile;
	Canvas bitmapCanvas;
	String savedFilePath = "";
	static String mSavedTempFilePath = "";
	private boolean isFileAlreadySaved = false;
	//static boolean isTempFileAlreadySaved = false;
	//static boolean isTempFileToBeDeleted = false;
	private boolean willTheTempFileForExistingImage = false;
	private String theOriginalFile = "";
	//Bitmap existingImageBitmap;
	Bitmap bitmap;
	//Canvas c;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        
        //color = new double[3];
        
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        
       mColor = 0xff0000ff;
        
       //requestWindowFeature(Window.)
        
        //testing purpose
        graphicobjects = new ArrayList<Shape>();
        
        //Initialization
        currentGraphicObject = new Shape();
        
     // Get the instance of the object that was stored
        // if one exists
       /* if (getLastNonConfigurationInstance() != null)
        {
        	//GraphicObject = (Shape)getLastNonConfigurationInstance();
        	//graphicobjects.set(number_of_graphicObjects-1, (Shape)getLastNonConfigurationInstance());
        	//graphicobjects.addAll((Shape)getLastNonConfigurationInstance());
        	for(int i = 0; i<number_of_graphicObjects; i++){
        		graphicobjects.set(i,(Shape)getLastNonConfigurationInstance());
        	}
        	//= (Shape)getLastNonConfigurationInstance();
        }*/
        mPaint = new Paint();
        
        InitializePaint();
        
        shapemenuclicked = false;
        colormenuclicked = false;
        
        BrushWidth = 3;
        
        File direct = new File(Environment.getExternalStorageDirectory() + "/androidpaint");

        if(!direct.exists())
         {
             if(direct.mkdir());//directory is created;

         }
        mImagePath = Environment.getExternalStorageDirectory() + "/androidpaint";
       // mSavedTempFilePath = mImagePath + "/" + "temp.9.png";
        //WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        
        //Display display = getWindowManager().getDefaultDisplay();
        
        //wallpaperHeight = wallpaperManager.getDesiredMinimumHeight();
        //int wallpaperHeight = display.getHeight();
        //wallpaperWidth = wallpaperManager.getDesiredMinimumWidth();
        //int wallpaperWidth = display.getWidth();
        //path_of_shape_for_WallPaperBitmap = new Path();
        
        //wallPaperBitmap = Bitmap.createBitmap(wallpaperWidth, wallpaperHeight, Bitmap.Config.ARGB_8888);
        //bitmapCanvas = new Canvas(wallPaperBitmap);
    }
    
    //test
    public void onPause(){
    	super.onPause();
    	p.surfaceDestroyed(p.getHolder());
    	//activitypaused = true;
    	/*
    	tempFile = new File(mSavedTempFilePath);
		  FileOutputStream fos;
		  try {
	       fos = new FileOutputStream(tempFile);
	       bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
	       fos.close();
	       isTempFileAlreadySaved = true;
	       if(StartingActivity.isToBeEdited == true){
	    	   StartingActivity.isToBeEdited = false;
	       	}
		  } catch (FileNotFoundException e) {
	       Log.e("Panel", "FileNotFoundException", e);
		  } 
		  catch (IOException e) {
	       Log.e("Panel", "IOEception", e);
	   }
  */
    }
    
    public void onStop(){
    	super.onStop();
    	p.surfaceDestroyed(p.getHolder());
    	
    	/*MenuItem itemSave = mMenu.findItem(R.id.itemSaveImage);
		onOptionsItemSelected(itemSave);*/
		
    	
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	p.surfaceDestroyed(p.getHolder());
    	
    	/*MenuItem itemSave = mMenu.findItem(R.id.itemSaveImage);
		onOptionsItemSelected(itemSave);*/
		
    		graphicobjects.clear();
			number_of_graphicObjects = 0;
    }
    
   //test
   public void onResume(){
	   super.onResume();
	  // p.surfaceCreated(p.getHolder());
	   /*if(isTempFileAlreadySaved== true){
      		//Bitmap
      		BitmapFactory.Options opts = new BitmapFactory.Options();;
      		opts.inMutable = true;
      		bitmap = BitmapFactory.decodeFile(mSavedTempFilePath, opts);
   		}*/
	   
		//moved to onResume
   	Display display = getWindowManager().getDefaultDisplay();
   	Rect rectDisplay = new Rect();
		display.getRectSize(rectDisplay);
		int bitmapWidth = rectDisplay.width();
		int bitmapHeight = rectDisplay.height();
   	
   	if(StartingActivity.isToBeEdited == true){
   		
   		//if(isFileAlreadySaved == true){
   			//willTheTempBeFileForExistingImage = true;
   		//if(isTempFileAlreadySaved == false){
   			BitmapFactory.Options opts = new BitmapFactory.Options();;
   	   		opts.inMutable = true;
   	   		
   	   		bitmap = BitmapFactory.decodeFile(FileList.imageFilePath, opts);
   		//}
   		//Bitmap
   		/*else if(isTempFileAlreadySaved == true){
      		//Bitmap
      		BitmapFactory.Options opts = new BitmapFactory.Options();;
      		opts.inMutable = true;
      		bitmap = BitmapFactory.decodeFile(mSavedTempFilePath, opts);
   		}*/
   	}
   	else{
   		bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
   		//bitmapCanvas = new Canvas(bitmap);
   	}
   	//c = new Canvas(bitmap);
   	
   	//moved to onResume
	bitmapCanvas = new Canvas(bitmap);
	p = new Panel(this);
	ImageView imageView = new ImageView(this);
	imageView.setImageBitmap(bitmap);
	//p.setOnTouchListener(new PanAndZoomListener(p, imageView, Anchor.TOPLEFT));
   //setContentView(new Panel(this));
	setContentView(p);
    }
    
	public void onStart(){
    	super.onStart();
    	//p.surfaceCreated(p.getHolder());
    	
    /*	//moved to onResume
    	Display display = getWindowManager().getDefaultDisplay();
    	Rect rectDisplay = new Rect();
		display.getRectSize(rectDisplay);
		int bitmapWidth = rectDisplay.width();
		int bitmapHeight = rectDisplay.height();
    	
    	if(StartingActivity.isToBeEdited == true){
    		//Bitmap
    		BitmapFactory.Options opts = new BitmapFactory.Options();;
    		opts.inMutable = true;
    		bitmap = BitmapFactory.decodeFile(FileList.imageFilePath, opts);
    	}
    	
    	else if(isTempFileAlreadySaved== true){
       		//Bitmap
       		BitmapFactory.Options opts = new BitmapFactory.Options();;
       		opts.inMutable = true;
       		bitmap = BitmapFactory.decodeFile(mSavedTempFilePath, opts);
    	}
    	else{
    		bitmap = Bitmap.createBitmap(bitmapWidth, bitmapHeight, Bitmap.Config.ARGB_8888);
    		//bitmapCanvas = new Canvas(bitmap);
    	}
    	//c = new Canvas(bitmap);
    	
    	//moved to onResume
		bitmapCanvas = new Canvas(bitmap);
    	p = new Panel(this);
        //setContentView(new Panel(this));
    	setContentView(p);*/
    }
	
	@Override
	public void onBackPressed() {
		/*
		
			
			MenuItem itemSave = mMenu.findItem(R.id.itemSaveImage);
			onOptionsItemSelected(itemSave);
			
	    	}*/
		if(isFileAlreadySaved == false){	
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Do you want to Save the changes...");
			alertDialog.setMessage("Save the changes...");
			//alertDialog.setCancelable(false);
			alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			   public void onClick(DialogInterface dialog, int which) {
			      // TODO Add your code for the button here.
				   MenuItem itemSave = mMenu.findItem(R.id.itemSaveImage);
				   onOptionsItemSelected(itemSave);
				   Intent iStartingActivity = new Intent();
					iStartingActivity.setClassName("somitsolutions.training.android.androidpaint","somitsolutions.training.android.androidpaint.StartingActivity");
					startActivity(iStartingActivity);
			   }
			});
			alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int which) {
			      // TODO Add your code for the button here.
				Intent iStartingActivity = new Intent();
				iStartingActivity.setClassName("somitsolutions.training.android.androidpaint","somitsolutions.training.android.androidpaint.StartingActivity");
				startActivity(iStartingActivity);
			   }
			});
			alertDialog.show();
		}
		
		else if (isFileAlreadySaved == true){
			Intent iStartingActivity = new Intent();
			iStartingActivity.setClassName("somitsolutions.training.android.androidpaint","somitsolutions.training.android.androidpaint.StartingActivity");
			startActivity(iStartingActivity);
		}
	}
	
    public void onSaveInstanceState(Bundle savedInstanceState){
    	//savedInstanceState.p
    }
    
    public void onRestoreInstanceState(Bundle savedInstanceState){
    	
    }
    
    
    
    private void InitializePaint()
    {
    	mPaint.setDither(true);
	        
	    mPaint.setColor(Color.rgb(100, 100,100));

    	mPaint.setStyle(Paint.Style.STROKE);

    	mPaint.setStrokeJoin(Paint.Join.ROUND);

    	mPaint.setStrokeCap(Paint.Cap.ROUND);
    	
    	mPaint.setStrokeWidth(BrushWidth);	
	
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	mMenu = menu;
    	return true;
    	}
public boolean onOptionsItemSelected(MenuItem item){
    	
    switch(item.getItemId()){
    case R.id.itemLine:
    	ShapeObject_to_be_created = ShapeLine;
     	//GraphicObject = new Line();
     	shapemenuclicked = true;
     	//erasemenuclicked = false;
     	brushwidthmenuclicked = false;
    	return true;
    
    case R.id.itemFreehand:
    	ShapeObject_to_be_created = ShapeFreehand;
    	//GraphicObject = new FreeHand();
    	shapemenuclicked = true;
    	//erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemRectangle:
    	ShapeObject_to_be_created = ShapeRect;
    	//GraphicObject = new Rectangle();
    	shapemenuclicked = true;
    	//erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemCircle:
    	ShapeObject_to_be_created = ShapeCircle;
    	//GraphicObject = new Circle();
    	shapemenuclicked = true;
    	//erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemOval:
    	ShapeObject_to_be_created = ShapeOval;
    	//GraphicObject = new Oval();
    	shapemenuclicked = true;
    	//erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    case R.id.itemColor:
    	LaunchColorPicker();
    	colormenuclicked = true;
    	//erasemenuclicked = false;
    	brushwidthmenuclicked = false;
    	return true;
    	
    /*case R.id.itemErase:
    	ShapeObject_to_be_created = ShapeErase;
    	//GraphicObject = new Erase();
    	erasemenuclicked = true;
    	shapemenuclicked = false;
    	colormenuclicked = false;
    	brushwidthmenuclicked = false;
    	//BrushWidth = 8;
    	return true;*/
    	
    case R.id.itemBrushWidth3:
    	BrushWidth = 3;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth4:
    	BrushWidth = 4;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth5:
    	BrushWidth = 5;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemBrushWidth6:
    	BrushWidth = 6;
    	brushwidthmenuclicked = true;
    	return true;
    	
    case R.id.itemShareImage:
    	
    	Intent share;
    	File attachment = null;
    	//if(!savedFilePath.equals("")){
    	if(isFileAlreadySaved == true){
    		attachment = new File(savedFilePath);
			boolean isFileThere = attachment.exists();
			if (isFileThere == true){
				share = new Intent(Intent.ACTION_SEND);
				share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(attachment));
				share.setType("image/png");
				startActivity(Intent.createChooser(share, "Share drawing"));
				//return true;
				//after sharing reset the savedFilePath
				//savedFilePath = "";
			}
    	}
		else{
			Toast.makeText(getApplicationContext(), "Please save the image first...", Toast.LENGTH_LONG).show();
		}
		return true;
    
    case R.id.itemSaveImage:
    	if(isFileAlreadySaved == false){
			 Calendar currentDate = Calendar.getInstance();
			  SimpleDateFormat formatter= new SimpleDateFormat("yyyyMMMddHmmss");
			  String dateNow = formatter.format(currentDate.getTime());
			  if(StartingActivity.isToBeEdited == true){
				  savedFilePath = FileList.imageFilePath; 
			  }
			  else{
				  savedFilePath = mImagePath + "/" + dateNow +".9.png";  
			  }
			  file = new File(savedFilePath);
			  FileOutputStream fos;
			  try {
		       fos = new FileOutputStream(file);
		       bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
		       fos.close();
		       isFileAlreadySaved = true;
		       //isTempFileAlreadySaved = false;
		       //isTempFileToBeDeleted = true;
		            
			  } catch (FileNotFoundException e) {
		       Log.e("Panel", "FileNotFoundException", e);
			  } 
			  catch (IOException e) {
		       Log.e("Panel", "IOEception", e);
		   }
		 return true;
    	}
}
   
	return false;
} 

private void LaunchColorPicker(){
	/*Intent launchcolorpicker = new Intent();
	launchcolorpicker.setClassName("somitsolutions.training.android.androidpaint", "somitsolutions.training.android.androidpaint.ColorPicker");
	launchcolorpicker.setAction("somitsolutions.training.android.androidpaint.android.intent.action.COLORPICKER");
	launchcolorpicker.addCategory("CATEGORY_DEFAULT");
	launchcolorpicker.setType("vnd.somitsolutions.color/vnd.somitsolutions.color-value");
	
	try {
    	startActivityForResult(launchcolorpicker,REQUEST_CODE);
    
    }
    catch(ActivityNotFoundException e){
    	Log.e("IntentExample", "Activity could not be started...");
    }*/ 
	int initialColor = 0xff0000ff;
	AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, initialColor,  new OnAmbilWarnaListener() {
        public void onOk(AmbilWarnaDialog dialog, int color) {
                // color is the color selected by the user.
        	mColor = color;
        }
                
        public void onCancel(AmbilWarnaDialog dialog) {
                // cancel was selected by the user
        }
    });

    dialog.show();
}

public void onActivityResult(int requestcode, int resultcode, Intent result ) {
	
	/*if(requestcode == REQUEST_CODE){
    	if(resultcode == RESULT_OK){
    		color[0] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[0];
    		color[1] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[1];
    		color[2] = (result.getDoubleArrayExtra("somitsolutions.training.android.colorpicker.color_of_the_shape"))[2];
    		
    	}
	}*/
}


    class Panel extends SurfaceView implements SurfaceHolder.Callback {
        //private TutorialThread _thread;
    	//test
    	public TutorialThread _thread;
    	//private ArrayList<Shape> graphicobjects;
    	//private Canvas canvas;
    	//private Canvas canvas;// = new Canvas(wallPaperBitmap);
        public Panel(Context context) {
            super(context);
            getHolder().addCallback(this);
            _thread = new TutorialThread(getHolder(), this);
            //graphicobjects = new ArrayList<Shape>();
            //canvas = new Canvas(wallPaperBitmap);
            setFocusable(true);
           // wallPaperBitmap = Bitmap.createBitmap(this.getWidth(),this.getHeight(),Bitmap.Config.ARGB_8888);
           setDrawingCacheEnabled(true);
        }
        
        
        public ArrayList<Shape> getGraphicObjects(){
        	return graphicobjects;
        }
        /*private void setGraphicObject(Shape s){
        	GraphicObject = s;
        }
        
        private Shape getGraphicObject(){
        	return GraphicObject;
        }*/
        
        /*public Canvas getWallPaperCanvas(){
        	return canvas;
        }*/
 
        @Override
        public boolean onTouchEvent(MotionEvent event) {
        	//if(shapemenuclicked == true && erasemenuclicked == false){
        	isFileAlreadySaved = false;
	        	synchronized (_thread.getSurfaceHolder()) {
	        		
	        		if(event.getAction() == MotionEvent.ACTION_DOWN){
	        			
	        			if(shapemenuclicked == true/* && erasemenuclicked == false*/){
		        			//if(GraphicObject instanceof Line){
		        			if(ShapeObject_to_be_created == ShapeLine){
		        				currentGraphicObject = new Line();
		        				
		        				((Line) currentGraphicObject).getBegin().setX(event.getX());
		        				//((Line)(GraphicObject).getBegin().setX(event.getX()));//.setX(event.getX());
		            			//begin.setY(event.getY());
		        				((Line) currentGraphicObject).getBegin().setY(event.getY());
		        			}
		        			
		        			//if(GraphicObject instanceof FreeHand){
		        			if(ShapeObject_to_be_created == ShapeFreehand){	
		        				currentGraphicObject = new FreeHand();
		        				
		        				((FreeHand)currentGraphicObject).getPath().moveTo(event.getX(), event.getY());
		
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				
		        			}
		        			
		        			//if(GraphicObject instanceof Rectangle){
		        			if(ShapeObject_to_be_created == ShapeRect){	
		        				//(Rectangle)GraphicObject.set
		        				//It will be later decided whether this end is upper-left/lower-left/upper-right
		        				//lower-right
		        				currentGraphicObject = new Rectangle();
		        				Point temp = new Point(event.getX(), event.getY());
		        				((Rectangle) currentGraphicObject).settemppointOfOneEndRectangle(temp);
		        			}
		        			
		        			//if(GraphicObject instanceof Circle){
		        			if(ShapeObject_to_be_created == ShapeCircle){	
		        				currentGraphicObject = new Circle();
		        				((Circle)currentGraphicObject).getOneEndOfTheCircle().setX(event.getX());
		        				((Circle)currentGraphicObject).getOneEndOfTheCircle().setY(event.getY());
		        			}
		        			
		        			//if(GraphicObject instanceof Oval){
		        			if(ShapeObject_to_be_created == ShapeOval){
		        				currentGraphicObject = new Oval();
		        				((Oval)currentGraphicObject).getoneEndOfTheOval().setX(event.getX());
		        				((Oval)currentGraphicObject).getoneEndOfTheOval().setY(event.getY());
		        			}
		        			
	        			}
	        			
	        		/*	if(shapemenuclicked == false && erasemenuclicked == true){
	                		//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){	
	            				currentGraphicObject = new Erase();
	            				
	            				((Erase)currentGraphicObject).getPath().moveTo(event.getX(), event.getY());

	            				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
	            				
	            			}
	        			}*/
	        			
	        		}
	        		
	       
	        		else if(event.getAction() == MotionEvent.ACTION_MOVE){
	        			
	        			if(shapemenuclicked == true /*&& erasemenuclicked == false*/){
		        			
		        			//if(GraphicObject instanceof FreeHand){
		        			if(ShapeObject_to_be_created == ShapeFreehand){
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        			}
		        			
		        			/*if(GraphicObject instanceof Rectangle){
		        			}*/
		        			if(ShapeObject_to_be_created == ShapeLine){
		        				
		        			}
		        			/*if(GraphicObject instanceof Circle){
		        				
		        			}*/
		        			
		        			if(ShapeObject_to_be_created == ShapeRect){
		        				
		        			}
		        			if(ShapeObject_to_be_created == ShapeCircle){
		        				
		        			}
		        			/*
		        			if(GraphicObject instanceof Oval){
		        				
		        			}*/
		        			if(ShapeObject_to_be_created == ShapeOval){
		        				
		        			}
	        			}
	        			
	        			/*if(shapemenuclicked == false && erasemenuclicked == true){
	        				//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){
		        				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        			}
	        				
	        			}*/
	        			
	        		}
	        		
	        		
	        		else if(event.getAction() == MotionEvent.ACTION_UP){
	        			
	        			if(shapemenuclicked == true /*&& erasemenuclicked == false*/){
	        			
		        			//if(GraphicObject instanceof Line){
	        				if(ShapeObject_to_be_created == ShapeLine){	
		        				//create a new Line... Add it to the  ArrayList
		        				//currentGraphicObject = new Line();
		        				
		        				((Line) currentGraphicObject).getEnd().setX(event.getX());
		        				((Line) currentGraphicObject).getEnd().setY(event.getY());
		            			
		        				Point temp_begin = ((Line)currentGraphicObject).getBegin();
		        				Point temp_end = ((Line)currentGraphicObject).getEnd();
		            			((Line) currentGraphicObject).setBegin(temp_begin);
		            			
		            			((Line)currentGraphicObject).getPath().moveTo(temp_begin.getX(), temp_begin.getY());
		            			((Line)currentGraphicObject).getPath().lineTo(temp_end.getX(), temp_end.getY());
		            			//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof FreeHand){
	        				if(ShapeObject_to_be_created == ShapeFreehand){
		        				((FreeHand)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				//_graphics.add(((FreeHand)GraphicObject).getPath());
		        				((FreeHand)currentGraphicObject).getGraphicsPath().add(((FreeHand)currentGraphicObject).getPath());
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Rectangle){
	        				if(ShapeObject_to_be_created == ShapeRect){
		        				float tempX = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getX();
		        				//the X co-ordinate of the first up
		        				float tempY = ((Rectangle) currentGraphicObject).gettemppointOfOneEndRectangle().getY();
		        				float tempX1 = event.getX();
		        				float tempY1 = event.getY();
		        				if(tempX<tempX1 && tempY>tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY1, tempX1, tempY, Path.Direction.CW);
		        				}
		        				if(tempX<tempX1 && tempY<tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX, tempY, tempX1, tempY1, Path.Direction.CW);
		        				}
		        				if(tempX>tempX1  && tempY>tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY1, tempX, tempY, Path.Direction.CW);
		        				}
		        				if(tempX>tempX1 && tempY<tempY1){
		        					((Rectangle)currentGraphicObject).getPath().addRect(tempX1, tempY, tempX, tempY1, Path.Direction.CW);
		        				}
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Circle){
	        				if(ShapeObject_to_be_created == ShapeCircle){
		        				float tempX1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getX();
		        				float tempY1 = ((Circle)currentGraphicObject).getOneEndOfTheCircle().getY();
		        				float tempX2 = event.getX();
		        				float tempY2 = event.getY();
		        				double temp = Math.pow((tempX1-tempX2),2) + Math.pow((tempY1-tempY2),2);
		        				float radius = (float)Math.sqrt(temp)/2;
		        				((Circle)currentGraphicObject).getPath().addCircle((tempX1 + tempX2)/2,(tempY1 + tempY2)/2, radius, Path.Direction.CW);
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
		        			
		        			//if(GraphicObject instanceof Oval){
	        				if(ShapeObject_to_be_created == ShapeOval){	
		        				float tempX = ((Oval)currentGraphicObject).getoneEndOfTheOval().getX();
		        				float tempY = ((Oval)currentGraphicObject).getoneEndOfTheOval().getY();
		        				float tempX1 = event.getX();
		        				float tempY1 = event.getY();
		        				
		        				if(tempX<=tempX1 && tempY>=tempY1){
		           					((Oval)currentGraphicObject).getRectangle().set(tempX,tempY1,tempX1,tempY);	
		        				}
		        				
		        				if(tempX<=tempX1 && tempY<=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX,tempY,tempX1,tempY1);
		        					
		        				}
		        				
		        				if(tempX>=tempX1  && tempY>=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY1,tempX,tempY);
		        					
		        				}
		        				
		        				if(tempX>=tempX1 && tempY<=tempY1){
		        					
		        					((Oval)currentGraphicObject).getRectangle().set(tempX1,tempY,tempX,tempY1);
		        				}
		        				
		        				//RectF r = ((Oval)GraphicObject).getRectangle();
		        				((Oval)currentGraphicObject).getPath().addOval(((Oval)currentGraphicObject).getRectangle(), Path.Direction.CW);
		        				//((Oval)GraphicObject).getPath().addOval(r, Path.Direction.CW);
		        			}
		        		}
	        			
	        			/*if(shapemenuclicked == false && erasemenuclicked == true){
	        				//if(GraphicObject instanceof Erase){
	        				if(ShapeObject_to_be_created == ShapeErase){
		        				((Erase)currentGraphicObject).getPath().lineTo(event.getX(), event.getY());
		        				//_graphics.add(((FreeHand)GraphicObject).getPath());
		        				((Erase)currentGraphicObject).getGraphicsPath().add(((Erase)currentGraphicObject).getPath());
		        				//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			}
	        			}*/
	        			
	        			if(colormenuclicked == false && shapemenuclicked == true){
		        			currentGraphicObject.setrgb(mColor);	
		        		}
		        		if(colormenuclicked == true && shapemenuclicked == true){
		        			//currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
		        			currentGraphicObject.setrgb(mColor);
		        		}
		        		
		        		/*if(erasemenuclicked == true && colormenuclicked == false){
		        			currentGraphicObject.setrgb(0,0,0);
		        			//mPaint.setStrokeWidth(6);
		        		}*/
		        		
		        		if(brushwidthmenuclicked == true){//  || erasemenuclicked == true){
		        			currentGraphicObject.setStrokeWidth(BrushWidth);
		        		}
		        		graphicobjects.add(currentGraphicObject);
		        		number_of_graphicObjects++;
		        		//isFileAlreadySaved = false;
	        			
	        		}
	        		//add the GraphicObject to the ArrayList graphicobjects
	        		//GraphicObject.setPaintColor((int)color[0], (int)color[1], (int)color[2]);
	        		/*if(colormenuclicked == false && shapemenuclicked == true){
	        			currentGraphicObject.setrgb(100,100,100);	
	        		}
	        		if(colormenuclicked == true && shapemenuclicked == true){
	        			currentGraphicObject.setrgb((int)color[0],(int)color[1],(int)color[2]);
	        		}
	        		
	        		if(erasemenuclicked == true && colormenuclicked == false){
	        			currentGraphicObject.setrgb(0,0,0);
	        			//mPaint.setStrokeWidth(6);
	        		}
	        		
	        		if(brushwidthmenuclicked == true){//  || erasemenuclicked == true){
	        			currentGraphicObject.setStrokeWidth(BrushWidth);
	        		}
	        		graphicobjects.add(currentGraphicObject);
	        		number_of_graphicObjects++;*/
	        	}
        	 //}
        	
	      return true;
    
        }
 
        @Override
        public void onDraw(Canvas canvas) {
        	//if(number_of_graphicObjects != 0){
	        	if(StartingActivity.isToBeEdited == true /*|| isTempFileAlreadySaved == true*/){
	        		canvas.drawBitmap(bitmap, 0, 0, mPaint);
	        		bitmapCanvas.drawBitmap(bitmap, 0, 0, mPaint);
	        		//StartingActivity.isToBeEdited = false;
	        	}
        		for(int i = 0; i<number_of_graphicObjects; i++){
	        		
	        		Shape currentGraphicObject = graphicobjects.get(i);
	        		
	        		//if colormenu is clicked then only change the color of the brush
	        		//else take the color from InitializePaint
	        		//if(colormenuclicked == true){
	        			//mPaint.setColor(Color.rgb(currentGraphicObject.getrgb()[0], currentGraphicObject.getrgb()[1], currentGraphicObject.getrgb()[2]));
	        			mPaint.setColor(currentGraphicObject.getrgb());
	        			mPaint.setStrokeWidth(currentGraphicObject.getStrokeWidth());
	        		//}
	        		
	        		if(currentGraphicObject instanceof FreeHand){
	        			for (Path path : ((FreeHand)currentGraphicObject).getGraphicsPath()) {
	                	    canvas.drawPath(path, mPaint);
	        				//c.drawPath(path, mPaint);
	                	    bitmapCanvas.drawPath(path,mPaint);
	                	    //existingImageBitmap.drawPath(path,mPaint);
	                	    currentGraphicObject = null;
	            		}
	        		}
	        		
	        		/*else if(currentGraphicObject instanceof Erase){
	        			for (Path path : ((Erase)currentGraphicObject).getGraphicsPath()) {
	                	    canvas.drawPath(path, mPaint);
	                	    bitmapCanvas.drawPath(path,mPaint);
	                	    currentGraphicObject = null;
	            		}
	        			
	        		}*/
	        		
	        		else{
	        			canvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			//c.drawPath(currentGraphicObject.getPath(),mPaint);
	        			bitmapCanvas.drawPath(currentGraphicObject.getPath(),mPaint);
	        			currentGraphicObject = null;
	        		}
	        	}
        	//}
	      }
 
       
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            // TODO Auto-generated method stub
        	//invalidate();
        	
        }
 
        
        public void surfaceCreated(SurfaceHolder holder) {
            _thread.setRunning(true);
            _thread.start();
        }
 
        
        public void surfaceDestroyed(SurfaceHolder holder) {
            // simply copied from sample application LunarLander:
            // we have to tell thread to shut down & wait for it to finish, or else
            // it might touch the Surface after we return and explode
            boolean retry = true;
            _thread.setRunning(false);
            while (retry) {
                try {
                    _thread.join();
                    retry = false;
                } catch (InterruptedException e) {
                    // we will try it again and again...
                }
            }
        }
    }
 
    class TutorialThread extends Thread {
        private SurfaceHolder _surfaceHolder;
        private Panel _panel;
        private boolean _run = false;
 
        public TutorialThread(SurfaceHolder surfaceHolder, Panel panel) {
            _surfaceHolder = surfaceHolder;
            _panel = panel;
        }
 
        public void setRunning(boolean run) {
            _run = run;
        }
 
        public SurfaceHolder getSurfaceHolder() {
            return _surfaceHolder;
        }
 
		@SuppressLint("WrongCall")
		@Override
        public void run() {
            Canvas c;
            while (_run) {
                c = null;
                try {
                    //c = _surfaceHolder.lockCanvas(null);
                	///Som+++
                	c = _surfaceHolder.lockCanvas();
                	///++Som
                    synchronized (_surfaceHolder) {
                        _panel.onDraw(c);
                    	
                    }
                } finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        _surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
            }
        }
    }
  }
