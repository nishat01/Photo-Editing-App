package application;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import canvas.PaintCanvas;

public class Application extends JFrame {


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application window = new Application();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Application() throws IOException {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Photo Editor 3461");
		PaintCanvas pc = new PaintCanvas();
		JSplitPane pane = new ToolCanvasSplit(pc);
		pane.setRightComponent(pc);
		this.setContentPane(pane);
		this.setBounds(500, 300, 1200, 800);
		this.setVisible(true);
	}
	
	

//	/**
//	 * Initialize the contents of the frame.
//	 */
//	private void initialize() {
//		/** frame = new JFrame();
//		frame.setBounds(100, 100, 1200, 800);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); **/
//		JSplitPane frame = new ToolCanvasSplit();
//		frame.setVisible(true);
//		frame.setBounds(100, 100, 1200, 800);
//	}

}
