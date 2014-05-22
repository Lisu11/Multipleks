package com.example.multiplex;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class ImageDownloader extends AsyncTask {
	private List<Film> lista;
	
	
	public ImageDownloader(ArrayList<Film> al){
		lista = new ArrayList<Film>();
	
	}
	
	@Override
	protected Object doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		deleteUnused();
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Object... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	

	private Bitmap getBitmapFromURL(String link) {
 
	   try {
	       URL url = new URL(link);
	       HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	       connection.setDoInput(true);
	       connection.connect();
	       
	       InputStream input = connection.getInputStream();
	       Bitmap bitmap = BitmapFactory.decodeStream(input);
	       return bitmap;
	
	   } catch (IOException e) {
	       e.printStackTrace();
	       Log.e("Error: ", e.getMessage().toString());
	       return null;
	   }
	}
	
	private void saveImage(Bitmap bmp, String name){
		 ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		 
		 File file = new File("/data/data/com.example.multiplex/"+name);
		 
		 try {
		     file.createNewFile();
		 } catch (IOException e) {
		     e.printStackTrace();
		 }
		 
		 FileOutputStream fos;
		 try{
			 fos = new FileOutputStream(file);
			 fos.write(baos.toByteArray());
			 fos.close();
		 } catch(FileNotFoundException ex){
			 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	private boolean fileExists(String name){
		File file = new File("/data/data/com.example.multiplex/");
		File[] fileList = file.listFiles();
		for(int i=0; i < fileList.length; i++)
			if(fileList[i].isFile() && fileList[i].getName().equals(name))
				return true;
		return false;
	}
	
	private void deleteUnused(){
		File file = new File("/data/data/com.example.multiplex/");
		File[] fileList = file.listFiles();
		
		for(int i =0; i < fileList.length; i++)
			if(filmRemoved(fileList[i].getName()))
				fileList[i].delete();
	}
	
	private boolean filmRemoved(String name){
		
		for(int i =0; i < lista.size(); i++)
			if(lista.get(i).getName().equals(name))
				return false;
		return true;
		
	}
}
