package application;

import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

import canvas.PaintCanvas;

public class ToolCanvasSplit extends JSplitPane {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToolCanvasSplit(PaintCanvas pc) throws IOException {
		this.setVisible(true);
		setOrientation(JSplitPane.VERTICAL_SPLIT);
		setDividerLocation(90);
		setResizeWeight(0);
		JPanel canvas = new CanvasPanel();
		setBottomComponent(canvas);
		JPanel functionality = new FunctionalityPanel(pc);
		setLeftComponent(functionality);
	}

}
