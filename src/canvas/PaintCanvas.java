package canvas;

// Java Program to create a to create a  
// canvas and mouse listener to the  
// canvas ( a circle of radius 5 will appear 
// at the points where mouse are clicked or 
//  dragged on the canvas) 
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import utils.Image;
import java.io.File;

public class PaintCanvas extends JPanel implements MouseListener, MouseMotionListener {
	private static final String RECTANGLE = "rectangle";
	private static final String SQUARE = "square";
	private static final String CIRC = "circle";
	Canvas c;
	Graphics2D graphics;
	BufferedImage curImg;
	Color curColor = Color.RED;
	int curSize = 0;
	boolean isWaitingForTextAreaClick = false;
	boolean isWaitingForShapeAreaClick = false;
	String curStr;
	int fontSize;
	private String shape;
	private int size;

	public PaintCanvas() {
		super();
		c = new Canvas();

		this.setLayout(new BorderLayout());
		this.setLocation(0, 0);
		c.setSize(4000, 4000);
		c.setLocation(80000, 20);
		c.setBackground(Color.white);
		c.addMouseListener(this);
		c.addMouseMotionListener(this);
		this.add(c);
		this.setVisible(true);
		this.setSize(400, 300);
		this.setAutoscrolls(true);
		
	}

	public void mouseClicked(MouseEvent e) {
		
		if (this.isWaitingForShapeAreaClick) {
			this.setShape(this.shape, e.getX(), e.getY());
			isWaitingForShapeAreaClick = false;
		}
		
		if (isWaitingForTextAreaClick) {
			this.setText(curStr, fontSize, e.getX(), e.getY());
			isWaitingForTextAreaClick = false; 
		}	
		
		Graphics g = c.getGraphics();

		g.setColor(curColor);
		int x, y;
		x = e.getX();
		y = e.getY();
		g.fillOval(x, y, curSize, curSize);
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
		Graphics g = c.getGraphics();
		g.setColor(curColor);
		int x, y;
		x = e.getX();
		y = e.getY();
		g.fillOval(x, y, curSize, curSize);
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public static void main(String args[]) {
		PaintCanvas c = new PaintCanvas();
	}

	
	public void setImage(File f) {
	    	Image i = new Image(f);
	    	this.curImg = i.getImage();
	    	this.graphics = this.curImg.createGraphics();
	    	if (curImg != null) {
                int x = (getWidth() - curImg.getWidth()) / 2;
                int y = (getHeight() - curImg.getHeight()) / 2;
                this.c.getGraphics().drawImage(curImg, x, y, this);
                try {
					Thread.sleep(2000);
					repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
            }
	    }
	
	public void setBrushSize(int size) {
		this.size = size;
		repaint();
	}
	
	public void setText(String text, int fontSize, int x, int y) {
		Graphics2D g2 = (Graphics2D) this.c.getGraphics();
		g2.setFont(new Font("Serif", Font.PLAIN, fontSize)); 
		g2.drawString(text, x, y);
		repaint();
	}
	
	public void setShape(String shape, int x, int y) {
		if (shape == RECTANGLE) {
			this.c.getGraphics().drawRect(x, y, 200, 100);
		}
		else if (shape == SQUARE) {
			this.c.getGraphics().drawRect(x, y, 100, 100);
		}
		else if (shape == CIRC) {
			this.c.getGraphics().drawOval(x, y, 100, 100);
		}
	}
	
	public void readyForShape(String shape) {
		this.shape = shape; 
		this.isWaitingForShapeAreaClick = true;
	}
	
	public void readyForText(String text, int fontSize) {
		this.curStr = text;
		this.fontSize = fontSize;
		this.isWaitingForTextAreaClick = true;
	}
	
	public void rotate(int degs) {

		AffineTransform trans = new AffineTransform();
		trans.setTransform(new AffineTransform());
		trans.rotate( Math.toRadians(degs) );
		trans.translate(240, -150);
		c.setBackground(Color.white);
		((Graphics2D) c.getGraphics()).drawImage(curImg, trans, this);
		try {
			Thread.sleep(2000);
			repaint();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void resizee(int size) {
		try {
			int factor = size/100;
	        BufferedImage outputImage = new BufferedImage(factor * curImg.getWidth(),
	                factor * curImg.getHeight(), curImg.getType());
	 
	        // scales the input image to the output image
	        int x = (getWidth() - factor*curImg.getWidth()) / 2;
	        int y = (getHeight() - factor*curImg.getHeight()) / 2;
	        ((Graphics2D) c.getGraphics()).drawImage(outputImage, x,y, this);
	        ((Graphics2D) c.getGraphics()).drawImage(curImg,factor*x,factor*y, factor * curImg.getWidth(),  factor * curImg.getHeight(), null);
	        try {
				Thread.sleep(3000);
				repaint();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (Exception e) {}
        
	}
	public void setBrushColor(Color newColor, int sz) {
		this.curSize = sz;
		this.curColor = newColor;
		repaint();
	}

	public void setEraserMode(int a) {
		this.curColor = Color.WHITE;
		curSize = a;
		repaint();
	}
	
	public int getCurSize() {
		return this.curSize;
	}
	
}
	