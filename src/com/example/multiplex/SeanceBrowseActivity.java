package com.example.multiplex;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SeanceBrowseActivity extends Activity {

	private List<Seance> listaSensow;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seance_browse);

		//ustawListeSeansow(getIntent().getExtras().getString("jakie"));
		ustawListe();
		ustawListView();

	}
	private void ustawListe(){
		listaSensow = new ArrayList<Seance>();
		if(getIntent().getExtras().getString("jakie").equals("wszystkie")){
			for(int i=0; i < MainActivity.listaSensow.size(); i++)
				listaSensow.add(MainActivity.listaSensow.get(i));
		} else if(getIntent().getExtras().getString("jakie").equals("jeden")){
			String tyul = getIntent().getExtras().getString("tytul");
			
			for(int i =0; i < MainActivity.listaSensow.size(); i++)
				if(MainActivity.listaSensow.get(i).getTitle() ==  tyul)
					listaSensow.add(MainActivity.listaSensow.get(i));
		} else {
			boolean a=false,b=false,c=false;
			String data = getIntent().getExtras().getString("data");
			String[] din = data.split("/");
			int godzina=0, cena=0;
			if(!data.equals(""))
				a = true;
			try{
				 godzina = Integer.parseInt(getIntent().getExtras().getString("godzina"));
				b = true;
			} catch(Exception e){}
			try{
					cena = Integer.parseInt(getIntent().getExtras().getString("cena"));
					c = true;
			} catch(Exception e){}
			if(a || b || c){
				
				for(int i=0; i < MainActivity.listaSensow.size(); i++)
					listaSensow.add(MainActivity.listaSensow.get(i));
				if(a)
					for(int i=0; i < listaSensow.size(); i++)
						if(listaSensow.get(i).getData_day() != Integer.parseInt(din[0]) &&
						listaSensow.get(i).getData_month() != Integer.parseInt(din[1]))
							listaSensow.remove(i);
				if(b)
					for(int i=0; i < listaSensow.size(); i++)
						if(listaSensow.get(i).getHour() != godzina)
							listaSensow.remove(i);
				if(c)
					for(int i=0; i < listaSensow.size(); i++)
						if(listaSensow.get(i).getPrice() != cena)
							listaSensow.remove(i);
				
			} else
				listaSensow = new ArrayList<Seance>();
		}
	}
	
	private void ustawListView() {
		ArrayAdapter<Seance> adapter = new MyAdapter();
		Log.v("chuj1cpia", "chuj1");
		ListView list = (ListView)findViewById(R.id.SeancelistView);
		if(adapter == null)
			Log.v("chujnull", "chuj");
		Log.v("chuj", "chuj");
		list.setAdapter(adapter);
		try{
			
		} catch (Exception ex) {
			Log.v("exeption","CIPOULKI"+ex.toString());
		}
		Log.v("chuj0", "chuj0");
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Baza b  = new Baza(4);
				int a = MainActivity.listaSensow.get(arg2).getSeanceId();
				b.setZapytanie("INSERT+INTO+Rezerwacje(seansID,+telefon)+VALUES+("+a+",788770572)");
				b.execute();
				Toast toast = Toast.makeText(getApplicationContext(), "wysłano prośbę o rezerwacje\nczekaj na kontakt z pracownikiem", Toast.LENGTH_SHORT);
				toast.show();
				
			}
		});
		Log.v("costam","coscos");
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.seance_browse, menu);
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

	
	private class MyAdapter extends ArrayAdapter<Seance>
	{
		public MyAdapter(){
			
			super(SeanceBrowseActivity.this, R.layout.item_seance_view, listaSensow);
			
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View itemview = convertView;
			if(itemview == null)
				itemview = getLayoutInflater().inflate(R.layout.item_seance_view, parent, false);
			
			TextView tytul = (TextView)itemview.findViewById(R.id.textView1);
			
			tytul.setText(listaSensow.get(position).getTitle());
			
			TextView d = (TextView)itemview.findViewById(R.id.textView2);
			d.setText(listaSensow.get(position).getD());
			
			TextView data = (TextView)itemview.findViewById(R.id.textView3);
			data.setText(""+listaSensow.get(position).getHour()+":"+listaSensow.get(position).getMinute()+"  "+listaSensow.get(position).getData_day()+"."+listaSensow.get(position).getData_month()+".2014");
			
			TextView cena = (TextView)itemview.findViewById(R.id.textView4);
			cena.setText(""+listaSensow.get(position).getPrice()+"zl");
			
			return itemview;
		}
		
	}
}
