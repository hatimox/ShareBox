package com.hatim.share_box;

import java.util.ArrayList;

import com.app.list.EntryAdapter;
import com.app.list.EntryItem;
import com.app.list.Item;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ListActivity extends Activity {
	String[] channels_list;
	ListView my_list;
	public EntryAdapter adapter;
	ArrayList<Item> items = new ArrayList<Item>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		channels_list = getResources().getStringArray(R.array.list_channels);
		my_list = (ListView)findViewById(R.id.listView1);
		for (int i=0; i<channels_list.length; i++) {
			items.add(new EntryItem(channels_list[i], "test", "0 Folders", "0 Files"));	
		}
		
		adapter = new EntryAdapter(ListActivity.this, items);
		my_list.setAdapter(adapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
