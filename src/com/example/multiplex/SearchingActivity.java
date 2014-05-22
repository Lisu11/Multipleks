package com.example.multiplex;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class SearchingActivity extends TabActivity {
	TabHost tbH;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searching);
		tbH = getTabHost();
		
		TabSpec filmSpec = tbH.newTabSpec("Wyszukaj film");
		filmSpec.setIndicator("Wyszukaj film", null);
		Intent inte = new Intent(this, FilmSearchActivity.class);
		filmSpec.setContent(inte);
		
		TabSpec seanceSpec = tbH.newTabSpec("Wyszukaj seans");
		seanceSpec.setIndicator("Wyszukaj seans", null);
		Intent inte2 = new Intent(this, SeanceSeachActivity.class);
		seanceSpec.setContent(inte2);
		
		tbH.addTab(filmSpec);
		tbH.addTab(seanceSpec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.searching, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	

}
