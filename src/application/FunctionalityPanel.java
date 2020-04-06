package application;

//import javax.swing.JTextPane;
import javax.swing.JButton;

import javax.swing.JColorChooser;
//atempt to pull
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileSystemView;

import canvas.PaintCanvas;

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTextPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class FunctionalityPanel extends JPanel {
	
protected JFileChooser imageJFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
File image;
protected PaintCanvas pc;
protected JButton textBtn;
protected BufferedImage bimage;

	public FunctionalityPanel(PaintCanvas pc) throws IOException {
		this.pc = pc;
		JToolBar toolBar = new JToolBar();
		
		ImageIcon newfileImage = new ImageIcon("src/application/add.png");
		JButton btnNewFile = new JButton("File", newfileImage);
		btnNewFile.setToolTipText("Add new file");
		btnNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					imageJFileChooser();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		toolBar.add(btnNewFile);
		ImageIcon saveImage = new ImageIcon("src/application/save.png");
		
		
		JButton Brushes = new JButton("");
		Brushes.setToolTipText("Brush");
		Brushes.setIcon(new ImageIcon("src/application/iconfinder_brush_61761.png"));
		Brushes.setSelectedIcon(new ImageIcon("src/application/brush.png"));
		Brushes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ans = Integer.parseInt(JOptionPane.showInputDialog("Select size"));
					pc.setBrushColor(Color.RED, ans);
					pc.setBrushSize(ans);
				}
				catch ( NumberFormatException er) {}
			}
		});
		
		textBtn = new JButton("");
		textBtn.setIcon(new ImageIcon("src/application/text.png"));
		textBtn.setToolTipText("Add text");
		textBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						JTextField sz = new JTextField();
						JTextField txt = new JTextField();
						Object[] message = {
						    "Size:", sz,
						    "Text:", txt
						};
						int opt = JOptionPane.showConfirmDialog(null, message, "Text", JOptionPane.OK_CANCEL_OPTION);
						if (opt == JOptionPane.OK_OPTION) {
							String curStr = txt.getText();
							int fontSize = Integer.parseInt(sz.getText());
							pc.readyForText(curStr, fontSize);
						}
						
					}
					catch (NumberFormatException err) {err.printStackTrace();}
				}
			});
		
		
		
		
		JButton btnShapes = new JButton("");
		btnShapes.setToolTipText("Select Region");
		btnShapes.setIcon(new ImageIcon("src/application/iconfinder_select_37292.png"));
		btnShapes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] values = {"rectangle", "circle", "square"};

				Object selected = JOptionPane.showInputDialog(null, "Pick a shape.", "Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
				if ( selected != null ){//null if the user cancels. 
				    pc.readyForShape(selected.toString());
				    }
			}
		});
		JButton btnFilters = new JButton("");
		btnFilters.setToolTipText("Adjustments");
		btnFilters.setIcon(new ImageIcon("src/application/adjustments.png"));
		btnFilters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] values = {"Brightness", "Contrast", "Shadow", "Exposure", "Saturation", "Sharpness", "Intensity"};
				Object selected = JOptionPane.showInputDialog(null, "Select an option to edit", "Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
			}
		});
		
		JButton btnTextures = new JButton("");
		btnTextures.setIcon(new ImageIcon("src/application/flip.png"));
		btnTextures.setToolTipText("Flip");
		
		JButton btnCrop = new JButton("");
		btnCrop.setToolTipText("Crop");
		btnCrop.setIcon(new ImageIcon("src/application/crop.png"));
		
		JButton btnRotate = new JButton("");
		btnRotate.setIcon(new ImageIcon("src/application/rotate.png"));
		btnRotate.setToolTipText("Rotate");
		btnRotate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ans = Integer.parseInt(JOptionPane.showInputDialog("Enter rotation in degrees."));
					pc.rotate(ans);
				}
				catch (NumberFormatException rre) {}
				
			}
			
		});
		
		JButton btnResize = new JButton("");
		btnResize.setToolTipText("Resize");
		btnResize.setIcon(new ImageIcon("src/application/resize.png"));
		btnResize.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int ans = Integer.parseInt(JOptionPane.showInputDialog("Enter a percentage to resize."));
					pc.resizee(ans);
				}
				catch (NumberFormatException rre) {}
				
			}
			
		});
		
		JButton btnTouchUp = new JButton("");
		btnTouchUp.setIcon(new ImageIcon("src/application/filters.png"));
		btnTouchUp.setToolTipText("Filters");
		
		JButton eraserBtn = new JButton("");
		eraserBtn.setIcon(new ImageIcon("src/application/iconfinder_package-purge_24217.png"));
		eraserBtn.setToolTipText("Eraser");
		eraserBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int ans = Integer.parseInt(JOptionPane.showInputDialog("Select a size"));
					pc.setEraserMode(ans);
				}
				catch (NumberFormatException er) {}
			}
		});
		
		
		JTextArea txtrSearch = new JTextArea();
		txtrSearch.setWrapStyleWord(true);
		txtrSearch.setTabSize(14);
		txtrSearch.setText("Search");
		
		JButton button = new JButton("");
		button.setToolTipText("Color");
		button.setIcon(new ImageIcon("src/application/color.png"));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					triggerJColorChooser();
			}
		});
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("src/application/iconfinder_edit-undo_118755 (1).png"));
		btnNewButton.setToolTipText("Undo");
		
		JButton button_1 = new JButton("");
		button_1.setIcon(new ImageIcon("src/application/iconfinder_edit-redo_118924.png"));
		button_1.setToolTipText("Redo");
		
		JButton button_2 = new JButton("");
		button_2.setToolTipText("TouchUp");
		button_2.setIcon(new ImageIcon("src/application/iconfinder_icon-27-one-finger-click_315854.png"));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] values = {"Smoothen", "FaceTune", "Lighten"};
				Object selected = JOptionPane.showInputDialog(null, "Select an effect", "Selection", JOptionPane.DEFAULT_OPTION, null, values, "0");
			}});
		
		//add text	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnShapes, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCrop, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTextures, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnResize, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRotate, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnFilters, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnTouchUp, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(Brushes, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(eraserBtn, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textBtn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrSearch, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
					.addGap(670))
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 1566, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnTouchUp, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnFilters, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnTextures, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnCrop, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnShapes, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnResize, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnRotate, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Brushes, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(eraserBtn, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
						.addComponent(textBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtrSearch))
					.addGap(348))
		);
		
		JToggleButton toggleButton = new JToggleButton("");
		toggleButton.setIcon(new ImageIcon("src/application/save.png"));
		toggleButton.setToolTipText("Save");
		toolBar.add(toggleButton);
		toggleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showSaveFileDialog();
			}} );
		
		
		JButton btnReset = new JButton("Reset");
		toolBar.add(btnReset);
		JButton btnShareVia = new JButton("Share via...");
		btnShareVia.setIcon(null);
		toolBar.add(btnShareVia);
		
		JButton btnHelp = new JButton("Help");
		toolBar.add(btnHelp);
		setLayout(groupLayout);
		

	}
	
	
	
	public void imageJFileChooser() throws IOException {
		imageJFileChooser.setDialogTitle("Choose the image you wish to edit.");
		imageJFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		imageJFileChooser.setAcceptAllFileFilterUsed(false);
		imageJFileChooser.setVisible(true);
		int returnValue = imageJFileChooser.showOpenDialog(this);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File image = imageJFileChooser.getSelectedFile();
			this.pc.setImage(image);
			this.repaint();
		}
	}
	
	
	public void triggerJColorChooser() {
		Color col = JColorChooser.showDialog(this, "Choose Brush Color", Color.red);
        if (col != null) {
        	pc.setBrushColor(col, this.pc.getCurSize());
        }
	}
	

	public void showSaveFileDialog(){
		int returnVal = imageJFileChooser.showSaveDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {  
			String filen=imageJFileChooser.getSelectedFile().toString(); 
			this.saveToFile(filen);  

		}
	}
	public void saveToFile(String filename){
		String ftype=filename.substring(filename.lastIndexOf('.')+1);
		try{
			ImageIO.write(bimage,ftype,new File(filename));
		}
		catch(IOException e)
		{System.out.println("Error in saving the file");
		};
		}
}
