package com.example.multiplex;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;



public class Baza extends AsyncTask<Void, Void, Void>{
	String zapytanie;
	String url;
	String zwrot= "fhsd";
	private HttpClient client;
	private HttpResponse response;
	private HttpEntity entity;
	int flag=0;
	
	public Baza( int f){
		flag =f;
		this.url = "http://156.17.234.46/zapytanie.php";
	}
	
	public void setZapytanie(String zap){
		zapytanie = zap;
	}
	
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		if(flag==0){
			MainActivity.setFilm(zwrot);
		
		}
		else if(flag == 1){
			MainActivity.setSeance(zwrot);
			
		} else if(flag == 2)
			MainActivity.setCennik(zwrot);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		
	}

	@Override
	protected Void doInBackground(Void... params) {
		 // http://http://156.17.234.46/zapytanie.php
		HttpClient httpclient = new DefaultHttpClient();

        HttpGet request = new HttpGet();
        URI website;
		try {
			website = new URI(url+"?zapytanie="+zapytanie);
			request.setURI(website);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
			//Log.v("indexx51",""+ new String(url+"?zapytanie="+zapytanie).charAt(51));
		}
        
        try {
			response = httpclient.execute(request);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		entity = response.getEntity();

		try {
			zwrot =  EntityUtils.toString(entity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	
}
