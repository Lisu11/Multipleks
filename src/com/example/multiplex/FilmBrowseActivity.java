package com.example.multiplex;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FilmBrowseActivity extends Activity {
	
	private List<Film> listaFilmow =null;
	Bundle savedInstanceState;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_film_browse);
		try{
		if(getIntent().getExtras().getString("szukaj").equals("zsuka"));
		}catch(Exception e){}
		listaFilmow = MainActivity.listaFilmow;
		ustawListView();
	}


	public void ustawListView(){
		ArrayAdapter<Film> adapter = new MyListAdapter();
		ListView list = (ListView)findViewById(R.id.FilmsListView);
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast toast = Toast.makeText(getApplicationContext(), listaFilmow.get(arg2).getName(), Toast.LENGTH_SHORT);
				toast.show();
				Intent i = new Intent(FilmBrowseActivity.this, FilmDetailsActivity.class);
				Log.v("chuj","na resorach");
				i.putExtra("co", listaFilmow.get(arg2).getName());
				startActivity(i);
				
			}
		});
		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Intent intent = new Intent(FilmBrowseActivity.this, SeanceBrowseActivity.class);
				intent.putExtra("jakie", "jeden");
				intent.putExtra("tytul",listaFilmow.get(arg2).getName());
				startActivity(intent);
				return true;
			}
		});
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.film_browse, menu);
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
	
	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_film_browse,
					container, false);
			return rootView;
		}
	}
	
	
	private class MyListAdapter extends ArrayAdapter<Film>
	{
		public MyListAdapter(){
			super(FilmBrowseActivity.this, R.layout.item_view, listaFilmow);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Log.v("nosz","kurwa");
			View itemview = convertView;
			if(itemview == null)
				itemview = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			
			Film currentFilm = listaFilmow.get(position);
			ImageView imV = (ImageView)itemview.findViewById(R.id.imageView1);
			//imV.setImageURI(uri);
			if((Math.round(Math.random()*3.0)) == 0)
				imV.setImageResource(R.drawable.centipede1);
			else if((Math.round(Math.random()*3.0)) == 0)
				imV.setImageResource(R.drawable.centipede2);
			else 
				imV.setImageResource(R.drawable.centipede3);
			TextView tytul = (TextView)itemview.findViewById(R.id.Title);
			tytul.setText(currentFilm.getName());
			
			TextView o_tytul = (TextView)itemview.findViewById(R.id.OrginalTitle);
			o_tytul.setText(currentFilm.getorg_name());
			
			TextView gatunki = (TextView)itemview.findViewById(R.id.genes);
			String ga = "";
			String g = currentFilm.getGenes();
			
			gatunki.setText(g);
			
			RatingBar Rb = (RatingBar)itemview.findViewById(R.id.ratingBar1);
			Rb.setActivated(false);
			Rb.setRating(currentFilm.getRating());
			
			return itemview;
		}
		
	}
}
