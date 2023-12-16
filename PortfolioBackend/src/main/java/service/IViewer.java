package service;

import java.util.ArrayList;
import java.util.List;

import model.Occupation;
import model.Viewer;

public interface IViewer {
	public Viewer getViewerById(int viewerId);
	public int createNewViewerAccount(Viewer viewer);
	public int updateViewer(String email, int viewerId);
	public int deleteViewer(int viewerId);
	public List<Viewer> getViewerAll();
	public int updateViewerDynamic(Viewer view);
	public Occupation getOccupationById(int occupationId);
	
}