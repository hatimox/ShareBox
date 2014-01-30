package com.app.list;

import java.util.ArrayList;
import java.util.List;

import com.hatim.share_box.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EntryAdapter extends ArrayAdapter<Item> {

	private Context context;
	private ArrayList<Item> arraylist;
	private LayoutInflater vi;
	private List<Item> items = null;

	public EntryAdapter(Context context, ArrayList<Item> items) {
		super(context, 0, items);
		this.context = context;
		this.items = items;
		this.arraylist = new ArrayList<Item>();
		this.arraylist.addAll(items);
		vi = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		Typeface type = Typeface.createFromAsset(context.getAssets(),
				"fonts/YanoneKaffeesatz-Light-webfont.ttf");
		final Item i = items.get(position);
		if (i != null) {
			if (i.isSection()) {
				SectionItem si = (SectionItem) i;
				v = vi.inflate(R.layout.list_item_section, null);

				v.setOnClickListener(null);
				v.setOnLongClickListener(null);
				v.setLongClickable(false);

				final TextView sectionView = (TextView) v
						.findViewById(R.id.list_item_section_text);
				sectionView.setText(si.getTitle());
				sectionView.setTypeface(type);
			} else {
				EntryItem ei = (EntryItem) i;
				v = vi.inflate(R.layout.list_item_entry, null);
				
				final TextView title = (TextView) v
						.findViewById(R.id.list_item_entry_title);
				title.setTypeface(type);

				final TextView venue = (TextView) v
						.findViewById(R.id.list_item_entry_folders_count);
				venue.setTypeface(type);

				final ImageView img = (ImageView) v
						.findViewById(R.id.list_item_entry_drawable);

				final TextView distance = (TextView) v
						.findViewById(R.id.list_item_entry_files_count);
				distance.setTypeface(type);
				if (title != null)
				{
					title.setText(ei.title);
					if (ei.title.equalsIgnoreCase("Dropbox")) {
						img.setImageResource(R.drawable.dropbox_icon);
					}
					else if (ei.title.equalsIgnoreCase("Google Drive")) {
						img.setImageResource(R.drawable.google_drive_icon);
					}
					else if (ei.title.equalsIgnoreCase("SkyDrive")) {
						img.setImageResource(R.drawable.skydrive_icon);
					}
					else if (ei.title.equalsIgnoreCase("ftp server")) {
						img.setImageResource(R.drawable.ftp_icon);
					}else{
						img.setImageResource(R.drawable.ic_launcher);
					}
				}
				if (venue != null)
					venue.setText(ei.venue);
				if (distance != null)
					distance.setText(ei.distance);

			}
		}
		return v;
	}

}
