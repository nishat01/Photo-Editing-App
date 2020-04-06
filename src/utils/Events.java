package utils;

import java.util.Stack;

public class Events {
	private static final int EVENTS_SIZE = 10;
	private Stack<Image> toUndo;
	private Stack<Image> toRedo;
	
	public Events() {
		this.toUndo = new Stack<Image>();
		this.toRedo = new Stack<Image>();
	}
	
	public void addToUndo(Image i) {
		if (this.toUndo.size() + 1 > EVENTS_SIZE) {
			this.toUndo.remove(0);
		}
		this.toUndo.push(i);
	}
	
	public void addToRedo(Image i) {
		if (this.toRedo.size() + 1 > EVENTS_SIZE) {
			this.toRedo.remove(0);
		}
		this.toRedo.push(i);
	}
	
	public Image doUndo() {
		if (this.toUndo.size() > 0) {
			Image undoImage = this.toUndo.pop();
			addToRedo(undoImage);
			return undoImage;
		}
		return null;
	}
	
	public Image doRedo() {
		if (this.toRedo.size() > 0) {
			Image redoImage = this.toRedo.pop();
			addToUndo(redoImage);
			return redoImage;
		}
		return null;
	}
	
	
}
