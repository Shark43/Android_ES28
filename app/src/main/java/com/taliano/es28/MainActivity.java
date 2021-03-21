package com.taliano.es28;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String path;
    ArrayList<Disco> discoArrayList;
    ListView listView;
    ArrayAdapter<Disco> discoArrayAdapter;
    int selectedIndex = -1;
    private static final int REQUEST_CODE_DESCRIZIONE = 313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path= MainActivity.this.getFilesDir().getPath();
        path+="/disco.txt";

        discoArrayList = new ArrayList<Disco>();

        leggiFile();

        discoArrayAdapter = new ArrayAdapter<Disco>(MainActivity.this, android.R.layout.simple_list_item_1, discoArrayList);
        listView = findViewById(R.id.listView);
        listView.setAdapter(discoArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedIndex = position;
                Intent intent = new Intent(MainActivity.this, ActivityDescrizione.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                Bundle bundle = new Bundle();
                Disco disco = discoArrayList.get(position);
                bundle.putSerializable("disco", disco);
                bundle.putInt("index", position);
                intent.putExtras(bundle);
                MainActivity.this.startActivityForResult(intent, REQUEST_CODE_DESCRIZIONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, 1, "Aggiungi");
        menu.add(Menu.NONE, 2, 2, "Salva");
        return super.onCreateOptionsMenu(menu);
    }

    private static final int REQUEST_CODE = 839;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case 1:
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                MainActivity.this.startActivityForResult(intent, REQUEST_CODE);
                break;
            case 2:
                salva();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salva()
    {
        if(!discoArrayList.isEmpty()) {
            try {
                FileWriter writer=new FileWriter(path,false); //secondo parametro: false overwrite, true append
                BufferedWriter bufferedWriter=new BufferedWriter(writer);

                for(Disco disco:discoArrayList)
                {
                    bufferedWriter.newLine();
                    bufferedWriter.write(disco.getTitle());
                    bufferedWriter.newLine();
                    bufferedWriter.write(disco.getDescrizione());
                    bufferedWriter.newLine();
                }

                bufferedWriter.flush();
                bufferedWriter.close();
                writer.close();

                alert("File salvato CORRETTAMENTE!!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                alert("Lista VUOTA!!");

                FileWriter writer = new FileWriter(path,false);
                BufferedWriter bufferedWriter=new BufferedWriter(writer);

                bufferedWriter.write("");
                bufferedWriter.close();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE && data != null){
            if(resultCode == RESULT_OK){
                alert("start insert");
                Bundle bundle = data.getExtras();
                Disco disco = (Disco) bundle.getSerializable("disco");
                discoArrayList.add(bundle.getInt("index", 1) - 1, disco);
                discoArrayAdapter.notifyDataSetChanged();
            } else {
                alert("not ok");
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void leggiFile() {
        File file=new File(path);
        if(file.isFile())
        {
            try {
                FileReader reader=new FileReader(file);

                BufferedReader bufferedReader=new BufferedReader(reader); //riga per riga

                String tmp;
                while((tmp=bufferedReader.readLine())!=null)
                {
                    Disco disco = new Disco();
                    disco.setTitle(bufferedReader.readLine());
                    disco.setDescrizione(bufferedReader.readLine());
                    discoArrayList.add(disco);
                }

                bufferedReader.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            alert("File non TROVATO!!");
    }

    public void alert(String s)
    {
        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
    }
}
