package com.app.list;

public class SectionItem implements Item{

	private final String title;
	
	public SectionItem(String title) {
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	@Override
	public boolean isSection() {
		return true;
	}

//	@Override
//	public String get_id() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public String get_subtitle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get_title() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String get_venue() {
		// TODO Auto-generated method stub
		return null;
	}

}
