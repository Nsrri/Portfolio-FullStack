package model;

import java.util.List;

public class Occupation {
	private int occupation_id;
	private String occupation_name;
	
	private List<Viewer> viewerList;

	public Occupation() {
		
	}
	
	
	public int getOccupation_id() {
		return occupation_id;
	}


	public void setOccupation_id(int occupation_id) {
		this.occupation_id = occupation_id;
	}


	public String getOccupation_name() {
		return occupation_name;
	}

	public void setOccupation_name(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	

	public Occupation(String occupation_name) {
		this.occupation_name = occupation_name;
	}
	
	
	public List<Viewer> getViewerList() {
		return viewerList;
	}

	public void setViewerList(List<Viewer> viewerList) {
		this.viewerList = viewerList;
	}
	
	public void addViewer(Viewer viewer) {
		this.viewerList.add(viewer);
	}


	@Override
	public String toString() {
		return "Occupation [occupation_id=" + occupation_id + ", occupation_name=" + occupation_name + ", viewerList="
				+ viewerList + "]";
	}

	

}
