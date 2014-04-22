package com.hatim.share_box;

import java.util.ArrayList;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.SubMenu;
import com.app.list.EntryAdapter;
import com.app.list.EntryItem;
import com.app.list.Item;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
//import com.dropbox.sync.android.DbxAccountManager;

public class ListActivity  extends SherlockActivity {
	String[] channels_list;
	ListView my_list;
	public EntryAdapter adapter;
	ArrayList<Item> items = new ArrayList<Item>();
	
	// dropbox declarations 

	
	final static private String APP_KEY = "u6j54gp0w9bhjzo";
	final static private String APP_SECRET = "szpb7mhw51gtsxr";
	final static private AccessType ACCESS_TYPE = AccessType.DROPBOX;
	private DropboxAPI<AndroidAuthSession> mDBApi;
	
	// You don't need to change these, leave them alone.
    final static private String ACCOUNT_PREFS_NAME = "dropbox_prefs";
    final static private String ACCESS_KEY_NAME = "ACCESS_KEY";
    final static private String ACCESS_SECRET_NAME = "ACCESS_SECRET";
    final static private String ACCESS_USER_NAME = "USER_SECRET";
    final static private String ACCESS_USER_ID = "USER_ID";
    final static private String ACCESS_ACCOUNT_TYPE = "ACCOUNT_TYPE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
		AndroidAuthSession session = new AndroidAuthSession(appKeys, ACCESS_TYPE);
		mDBApi = new DropboxAPI<AndroidAuthSession>(session);
		
		
		channels_list = getResources().getStringArray(R.array.list_channels);
		my_list = (ListView) findViewById(R.id.listView1);
		for (int i = 0; i < channels_list.length; i++) {
			items.add(new EntryItem(channels_list[i], "test", "0 Folders",
					"0 Files"));
		}

		adapter = new EntryAdapter(ListActivity.this, items);
		my_list.setAdapter(adapter);
		
		//mDBApi.getSession().startAuthentication(ListActivity.this);

	}
	
	protected void onResume() {
	    super.onResume();

	    if (mDBApi.getSession().authenticationSuccessful()) {
	        try {
	            // Required to complete auth, sets the access token on the session
	        	mDBApi.getSession().finishAuthentication();

	            AccessTokenPair accessToken = mDBApi.getSession().getAccessTokenPair();
	            Log.e("log_tag", "Accesstoken key : " + accessToken.key.toString());
	            Log.e("log_tag", "Accesstoken secret : " + accessToken.secret.toString());
	            SharedPreferences prefs = getSharedPreferences(ACCOUNT_PREFS_NAME, 0);
	            Editor edit = prefs.edit();
	            edit.putString(ACCESS_KEY_NAME, accessToken.key);
	            edit.putString(ACCESS_SECRET_NAME, accessToken.secret);
	            edit.commit();
	        } catch (IllegalStateException e) {
	            Log.i("DbAuthLog", "Error authenticating", e);
	        }
	    }
	}

	

	public boolean onCreateOptionsMenu(Menu menu) {
		
		SubMenu my_events_submenu = menu.addSubMenu("New Account");
		my_events_submenu.add("DropBox").setIcon(R.drawable.dropbox_icon);
		my_events_submenu.add("Google Drive").setIcon(R.drawable.google_drive_icon);
		my_events_submenu.add("One Drive").setIcon(R.drawable.skydrive_icon);
		my_events_submenu.add("Ftp Account").setIcon(R.drawable.ftp_icon);
		my_events_submenu.add("WebDav");
		
		MenuItem subMenu1Item = my_events_submenu.getItem();
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS
				| MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		
		SubMenu my_account_submenu = menu.addSubMenu("Set Password");
		my_account_submenu.add("My Profile");
		my_account_submenu.add("Settings");
		my_account_submenu.add("Log Out");

		
		return true;
	}
	
public boolean onOptionsItemSelected(MenuItem item) {
		
		if (item.getItemId()== android.R.id.home)
		{
	      Intent intent = new Intent(this, ListActivity.class);
	      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	      startActivity(intent);

		} else if (item.getTitle().toString() == "DropBox") {
			
			mDBApi.getSession().startAuthentication(ListActivity.this);

		} else if (item.getTitle().toString() == "Google Drive") {
			

		} else if (item.getTitle().toString() == "Search") {

		} else if (item.getTitle().toString() == "My Profile") {
			

		} else if (item.getTitle().toString() == "Settings") {
			

		} else if (item.getTitle().toString() == "Log Out") {
			
		}
		return true;
	}


}
