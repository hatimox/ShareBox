package com.app.list;

public class EntryItem implements Item{

	public final String title;
	public final String subtitle;
	public final String venue;
	//public final String image_url;
	public final String distance;
	//public final String id;

	public EntryItem(String title, String subtitle ,String venue /*,String image_url*/, String distance/*,String id*/) {
		this.title = title;
		//this.id = id;
		this.subtitle = subtitle;
		this.venue = venue;
		//this.image_url = image_url;
		this.distance = distance;
	}
	
//	@Override
//	public String get_id() {
//		return id;
//	}
	
	@Override
	public String get_title() {
		return title;
	}
	
	@Override
	public String get_subtitle() {
		return subtitle;
	}
	
	@Override
	public String get_venue() {
		return venue;
	}
	
	@Override
	public boolean isSection() {
		return false;
	}

}
