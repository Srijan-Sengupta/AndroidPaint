package somitsolutions.training.android.androidpaint;

import android.graphics.Path;

public class Shape extends Object{
	
			//private Paint paint;
			private int[] color_rgb;
			protected Path path_of_shape;
			protected float strokeWidth;
			int mColor;
			public Shape(){
	    		//paint = new Paint();
	    		path_of_shape = new Path();
	    		
	    		color_rgb = new int[3];
	    		color_rgb[0] = 0;
	    		color_rgb[1] = 0;
	    		color_rgb[2] = 0;
	    		strokeWidth = 3;
	    	}
	    	
	    	
	    	public Path getPath(){
	    		return path_of_shape;
	    	}
	    	
	    	public void setPath(Path p){
	    		path_of_shape = p;
	    	}
	    	
	    	/*public void setrgb(int red, int green, int blue){
	    		color_rgb[0] = red;
	    		color_rgb[1] = green;
	    		color_rgb[2] = blue;
	    	}*/
	    	
	    	public void setrgb(int color){
	    		mColor = color;
	    	}
	    	
	    	/*public int[] getrgb(){
	    		return color_rgb;
	    	}*/
	    	
	    	public int getrgb(){
	    		return mColor;
	    	}
	    	
	    	public void setStrokeWidth(float w){
	    		strokeWidth = w;
	    	}
	    	
	    	public float getStrokeWidth(){
	    		return strokeWidth;
	    	}
}
