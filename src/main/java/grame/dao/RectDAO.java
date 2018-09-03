package grame.dao;

import java.util.List;

import grame.pojo.Rect;

public interface RectDAO {
	public Rect getRectById(int rectId,String tablename);
	public boolean newRect(int x,int y, int width, int height,String tablename);
	public boolean newRect(Rect r,String tablename);
	public List<Rect> getBoundingRects(Rect r,String tablename);
	public Rect getRectByParam(int x, int y, int width, int height,String tablename);
	public boolean clearRect(String tablename);
	public boolean newRectTable(String tablename);
}
