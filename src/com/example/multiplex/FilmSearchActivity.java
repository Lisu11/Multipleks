package com.example.multiplex;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FilmSearchActivity extends Activity {
	EditText tytul;
	EditText rezyser;
	EditText gatunek;
	EditText ocena;
	EditText rok;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_film);
		
		tytul = (EditText)findViewById(R.id.editText1);
		gatunek = (EditText)findViewById(R.id.editText2);
		rezyser = (EditText)findViewById(R.id.editText3);
		ocena = (EditText)findViewById(R.id.editText4);
		rok = (EditText)findViewById(R.id.editText5);
		 
	}
	public void szukaj(View view){
		
	}
}
