package grame.pojo;

public class Rect {
	private int x;
	private int y;
	private int width;
	private int height;
	private int rectid;
	public Rect(int x, int y, int width, int height,int rectid) {
		this.setX(x);
		this.setY(y);
		this.setWidth(width);
		this.setHeight(height);
		this.setRectid(rectid);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getRectid() {
		return rectid;
	}
	public void setRectid(int rectid) {
		this.rectid = rectid;
	}
}
