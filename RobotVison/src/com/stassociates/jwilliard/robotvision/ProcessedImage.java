/**
 * 
 */
package com.stassociates.jwilliard.robotvision;

import java.awt.Graphics;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;

/**
 * @author Jack Williard
 *
 */
public class ProcessedImage {

	BufferedImage bImage = null;
	BufferedImage grayImage = null;
	//BufferedImage threshImage = null;
	
	void loadImage (File fileName) throws IOException {
		try {
			this.bImage = ImageIO.read(fileName);
		} finally {
			
		}
	}
	
	void convertToGreyscale() {
		this.grayImage = new BufferedImage(
				bImage.getWidth(), 
				bImage.getHeight(), 
				BufferedImage.TYPE_BYTE_GRAY);
		Graphics g = this.grayImage.getGraphics();
		g.drawImage(this.bImage,  0,  0,  null);
		g.dispose();
	}
	
	void thresholdImage (int level) {
		int width  = this.bImage.getWidth();
		int height = this.bImage.getHeight();
				
		//this.threshImage = new BufferedImage(
		//		bImage.getWidth(), 
		//		bImage.getHeight(), 
		//		BufferedImage.TYPE_BYTE_GRAY);
		
		//Graphics g = this.grayImage.getGraphics();
		//g.
//		WritableRaster grayRaster = this.grayImage.copyData(null);
//		
//	    for (int x = 0; x < width; ++x)
//	        for (int y = 0; y < height; ++y)
//	        {
//	        	int[] pixel;
//	        	pixel = grayRaster.getPixel(x, y, pixel);
//	        	if (pixel[0] < level) { 
//	        		pixel[0] = 0; 
//	        	} else { 
//	        		pixel[0] = 0xFF; 
//	        	}
//	        	grayRaster.setPixel(x, y, pixel);
//	        }
//	    
//	    this.threshImage = new BufferedImage(BufferedImage.TYPE_BYTE_BINARY, grayRaster, false, null);
		
	    for (int x = 0; x < width; ++x) {
	    	for (int y = 0; y < height; ++y) {
	    		int pixel = grayImage.getRGB(x, y) & 0xFF;
	    		if (pixel > level) {bImage.setRGB(x,y,0xFFFFFF);}
	    		else { bImage.setRGB(x,y,0); }	    		
	    	}
	    }	
	    		
		
	}
	
}
