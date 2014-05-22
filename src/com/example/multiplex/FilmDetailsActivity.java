package com.example.multiplex;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.Build;

public class FilmDetailsActivity extends Activity {
	String tytul;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_film_details);
		
		if (savedInstanceState == null) {
			//getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
		tytul = getIntent().getExtras().getString("co");
		Film f=null;
		for(int i =0; i < MainActivity.listaFilmow.size(); i++)
			if(MainActivity.listaFilmow.get(i).getName().equals(tytul)){
				f = MainActivity.listaFilmow.get(i);
				break;
			}
		if(f != null){
			ImageView imageView1 = (ImageView)findViewById(R.id.imageView1);
		//ustaw obrazek
			TextView tytul = (TextView) findViewById(R.id.titleView1);
			tytul.setText(f.getName());
			TextView tytul2 = (TextView) findViewById(R.id.orgTitleView2);
			tytul2.setText(f.getorg_name());
			TextView gene = (TextView) findViewById(R.id.geneView3);
			tytul2.setText(f.getGenes());
			TextView link = (TextView) findViewById(R.id.geneView3);
			tytul2.setText(f.getWeb());
			TextView des = (TextView) findViewById(R.id.opisView2);
			tytul2.setText(f.getDescription());
			RatingBar rb = (RatingBar)findViewById(R.id.ratiBar1);
			rb.setMax(10);
			rb.setRating(f.getRating());
		}
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.film_details, menu);
		return true;
	}

	public void seanseZtymFilmem(View view){
		Intent intent = new Intent(FilmDetailsActivity.this, SeanceBrowseActivity.class);
		intent.putExtra("jakie", "jeden");
		intent.putExtra("tytul", tytul);
		startActivity(intent);
	}

}
