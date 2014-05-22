package com.example.multiplex;

import java.util.Calendar;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

public class SeanceSeachActivity extends Activity {

	EditText date;
	EditText godzina;
	EditText cena;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_seance);
		
		date = (EditText)findViewById(R.id.editText11);
		godzina = (EditText)findViewById(R.id.editText21);
		
		cena = (EditText)findViewById(R.id.editText31);
		//cena.setInputType(InputType.);
		date.addTextChangedListener(new TextWatcher() {
			 private String current = "";
			    private String ddmmyyyy = "DDMMYYYY";
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (!s.toString().equals(current)) {
		            String clean = s.toString().replaceAll("[^\\d.]", "");
		            String cleanC = current.replaceAll("[^\\d.]", "");
		            Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		            int cl = clean.length();
		            int sel = cl;
		            for (int i = 2; i <= cl && i < 6; i += 2) {
		                sel++;
		            }
		            //Fix for pressing delete next to a forward slash
		            if (clean.equals(cleanC)) sel--;

		            if (clean.length() < 8){
		               clean = clean + ddmmyyyy.substring(clean.length());
		            }else{
		               //This part makes sure that when we finish entering numbers
		               //the date is correct, fixing it otherways
		               int day  = Integer.parseInt(clean.substring(0,2));
		               int mon  = Integer.parseInt(clean.substring(2,4));
		               int year = Integer.parseInt(clean.substring(4,8));

		               if(mon > 12) mon = 12;
		               cal.set(Calendar.MONTH, mon-1);
		               day = (day > cal.getActualMaximum(Calendar.DATE))? cal.getActualMaximum(Calendar.DATE):day;
		               year = (year<1900)?1900:(year>2100)?2100:year;
		               clean = String.format("%02d%02d%02d",day, mon, year);
		            }

		            clean = String.format("%s/%s/%s", clean.substring(0, 2),
		                clean.substring(2, 4),
		                clean.substring(4, 8));
		            current = clean;
		            date.setText(current);
		            date.setSelection(sel < current.length() ? sel : current.length());
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,	int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	
	public void szukaj(View view){
		
	}
}
