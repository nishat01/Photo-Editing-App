package utils;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {
	public static final int MAX_HEIGHT = 4000;
	public static final int MAX_WIDTH = 4000;
	protected BufferedImage rawImg;
	protected File file;


	public Image(File f) {
		try {
			this.file = f;
			this.rawImg = ImageIO.read(f);
			if (this.rawImg.getHeight() > MAX_HEIGHT || this.rawImg.getWidth() > MAX_WIDTH) {
				this.resize();
			}
		} catch (IOException e) {
		}
	}
	
	public void resize() {
		int curHeight = rawImg.getHeight();
		int curWidth = rawImg.getWidth();
		int heightPerc = MAX_HEIGHT / curHeight;
		int widthPerc = MAX_WIDTH / curWidth;
		int scale = 0;
		if (curHeight > MAX_HEIGHT && curWidth > MAX_WIDTH) {
			scale = curHeight > curWidth ? heightPerc : widthPerc;
		}
		else if (curHeight > MAX_HEIGHT) {
			scale = heightPerc;
		}
		else {
			scale = widthPerc;
		}
        int scaledWidth = (int) (this.rawImg.getWidth() * scale);
        int scaledHeight = (int) (this.rawImg.getHeight() * scale);
        
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, rawImg.getType());
 
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(this.rawImg, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
        try {
			ImageIO.write(outputImage, getExt(), file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void saveImage() {
		try {
			ImageIO.write((RenderedImage) this.rawImg, this.getExt(), this.file);
		} catch (IOException e) {
		}
	}

	public String getExt() {
		String fileName = this.file.getName();
		String extName = fileName.substring(fileName.indexOf('.') + 1);
		return extName;
	}
	
	public BufferedImage getImage() {
		return this.rawImg;
	}
	
	public File getFile() {
		return this.file;
	}
}
