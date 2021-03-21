package com.taliano.es28;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Button buttonAggiungi =findViewById(R.id.buttonINSERISCI);
        buttonAggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disco disco = new Disco();
                disco.setTitle(((EditText) findViewById(R.id.editTextTitolo)).getText().toString());
                disco.setDescrizione(((EditText) findViewById(R.id.editTextDescrizione)).getText().toString());
                Intent intent = new Intent(Activity2.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("disco", disco);
                bundle.putInt("index", (Integer.parseInt(((EditText) findViewById(R.id.editTextPosition)).getText().toString())));
                intent.putExtras(bundle);
                Activity2.this.setResult(RESULT_OK, intent);
                Activity2.this.finish();
            }
        });

        Button buttonElimina = findViewById(R.id.buttonELIMINA);
        buttonElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity2.this.finish();
            }
        });
    }

}
