package com.example.multiplex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	public static List<Film> listaFilmow = new ArrayList<Film>();
	public static List<Seance> listaSensow = new ArrayList<Seance>();
	static boolean flagFilm = false;
	static boolean flagSeance = false;
	static List<Pair<Integer, Integer>> cennik = new ArrayList<Pair<Integer, Integer>>();
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        Toast toast; 
		
        if(sprawdzPolaczenie()){
        	
        	toast = Toast.makeText(getApplicationContext(),"jest polaczenie", Toast.LENGTH_LONG);
        }
        else
        	toast = Toast.makeText(getApplicationContext(), "brak połączenia internetowego", Toast.LENGTH_SHORT);
        toast.show();
        Baza b = new Baza(2);
		b.setZapytanie("SELECT+*+FROM+Cennik");
		b.execute();
        Baza b1 = new Baza( 0);
		b1.setZapytanie("SELECT+*+FROM+Filmy");
		b1.execute();
		Baza b2 = new Baza(1);
		b2.setZapytanie("SELECT+*+FROM+Seanse");
		b2.execute();
    }

	public static void setFilm(String json){
		//Toast.makeText(this, json, Toast.LENGTH_SHORT).show();
		JSONParser pr = new JSONParser();
		try {
			Object obj = pr.parse(json);
			JSONArray array=(JSONArray)obj;
			Iterator i = array.iterator();
			while(i.hasNext()){
				JSONObject ja = (JSONObject)i.next();
				String s = ""+ja.get("tytul");
				try{
					listaFilmow.add(new Film(""+ja.get("tytul"), ""+ja.get("tytul"), ""+ja.get("link_do_obrazka"), 
						Integer.parseInt(""+ja.get("ocena")), ""+ja.get("nasz_opis"), ""+ja.get("gatunek"), 
						Integer.parseInt(""+ja.get("filmID")), ""+ja.get("link_do_filmweb"),
						Integer.parseInt(""+ja.get("rok_produkcji"))));//sprawdzic czy rok_produkcji jest w bazie
				} catch(NumberFormatException e){Log.v("obsl", e.toString());}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		flagFilm = true;
	}
	
	public static void setCennik(String json){
		JSONParser pr = new JSONParser();
		try {
			Object obj = pr.parse(json);
			JSONArray array=(JSONArray)obj;
			Iterator i = array.iterator();
			while(i.hasNext()){
				JSONObject ja = (JSONObject)i.next();
				
				try{
					cennik.add(new Pair<Integer, Integer>(Integer.parseInt(""+ja.get("cennikID")), Integer.parseInt(""+ja.get("cena"))));
				} catch(NumberFormatException e){Log.v("obsl", e.toString());}
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void setSeance(String json){
		JSONParser pr = new JSONParser();
		try {
			Object obj = pr.parse(json);
			JSONArray array=(JSONArray)obj;
			Iterator i = array.iterator();
			boolean f = true;
			while(i.hasNext()){
				JSONObject ja = (JSONObject)i.next();
				
				String datRozp = ""+ja.get("data_rozpoczecia");
				String[] dr = datRozp.split(" ");
				
				String[] dataRCzas = dr[1].split(":");
				String[] dataRDzni = dr[0].split("-");
				
				Integer cena;
				String tytul;
				try{
					cena = getCena( Integer.parseInt(""+ja.get("cennikID")) );
					tytul = getTytul( Integer.parseInt(""+ja.get("filmID")) );
				}catch(Exception e){
					break;
				}
				try{
					listaSensow.add(new Seance(cena, Integer.parseInt(dataRCzas[0]), Integer.parseInt(dataRCzas[1])
							, Integer.parseInt(dataRDzni[1]), Integer.parseInt(dataRDzni[2]), tytul,
							""+ja.get("numerSali"),Integer.parseInt(""+ja.get("seansID")) ));
				} catch(NumberFormatException e){Log.v("obsl", e.toString());}
			}
			if(f)
				flagSeance = true;
			else{
				listaSensow = new ArrayList<Seance>();
				setSeance(json);
				return;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private static String getTytul(int filmID) throws Exception{
		for(int i=0; i < listaFilmow.size(); i++)
			if(filmID == listaFilmow.get(i).getFilmID())
				return listaFilmow.get(i).getName();
		throw new Exception();
	}
	
	private static Integer getCena(Integer cennikId) throws Exception{
		for(int i=0; i < cennik.size(); i++)
			if(cennikId == cennik.get(i).first)
				return cennik.get(i).second;
		throw new Exception();
	}
	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
    
    public void przeglad_filmow(View v){
    	if(flagFilm && flagSeance){
	    	Intent i = new Intent(MainActivity.this, FilmBrowseActivity.class);
		    MainActivity.this.startActivity(i);
    	}else
    		Toast.makeText(this, "trwa pobieranie iinformacji proszę czekać", Toast.LENGTH_SHORT).show();
    }
    
    public void przeglad_seansow(View v){
    	if(flagFilm && flagSeance){
	    	Intent i = new Intent(MainActivity.this, SeanceBrowseActivity.class);
	    	i.putExtra("jakie", "wszystkie");
		    MainActivity.this.startActivity(i);
    	}else
    		Toast.makeText(this, "trwa pobieranie iinformacji proszę czekać", Toast.LENGTH_SHORT).show();
    }
    
    public void wyszukiwanie(View v){
    	if(flagFilm && flagSeance){
    		Intent i = new Intent(MainActivity.this, SearchingActivity.class);
	    	MainActivity.this.startActivity(i);
    	} else
    		Toast.makeText(this, "trwa pobieranie iinformacji proszę czekać", Toast.LENGTH_SHORT).show();
    }
    
    public void info(View v){
    	
    	Intent i = new Intent(MainActivity.this, InfoActivity.class);
	    MainActivity.this.startActivity(i);
    }
 
    
    private boolean sprawdzPolaczenie(){
    	ConnectivityManager conM = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
    	NetworkInfo netInfo = conM.getActiveNetworkInfo();
    	if(netInfo != null && netInfo.isConnected())
    		return true;
    	return false;
    }
}
