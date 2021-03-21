package com.taliano.es28;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityDescrizione extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descrizione);

        Button button = findViewById(R.id.buttonChiudiD);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent intent = new Intent(ActivityDescrizione.this, MainActivity.class);
            ActivityDescrizione.this.setResult(RESULT_OK, intent);
            ActivityDescrizione.this.finish();
            }
        });

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Disco disco  = (Disco) bundle.getSerializable("disco");
            Integer index = bundle.getInt("index", 1);
            EditText editTextTitle = findViewById(R.id.editTextTitoloD);
            EditText editTextDescription = findViewById(R.id.editTextDescrizioneD);
            EditText editTextIndex = findViewById(R.id.editTextPositionD);
            if (disco != null) {
                editTextTitle.setText(disco.getTitle().toString());
                editTextDescription.setText(disco.getDescrizione().toString());
                editTextIndex.setText(Integer.toString(index));
            } else {
                alert("disco null");
            }
        } else {
            alert("bundle null");
        }
    }

    public void alert(String s)
    {
        Toast.makeText(ActivityDescrizione.this,s,Toast.LENGTH_LONG).show();
    }

}
