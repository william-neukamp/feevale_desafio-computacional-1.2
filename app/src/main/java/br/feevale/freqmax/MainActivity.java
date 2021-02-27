package br.feevale.freqmax;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class MainActivity extends AppCompatActivity {
    ListView listData;
    LinkedHashMap<String, HeartRate> data = new LinkedHashMap<String, HeartRate>();
    HeartRateAdapter adapter;
    EditText edtName;
    EditText edtAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.edtName = findViewById(R.id.edtName);
        this.edtAge = findViewById(R.id.edtAge);

        listData = findViewById(R.id.listData);
        adapter = new HeartRateAdapter(this.data, getBaseContext());
        listData.setAdapter(adapter);
    }

    // reorganiza dicionario de forma decrescente ao FCM by neukamp //
    private LinkedHashMap<String, HeartRate> sortList(){
        ArrayList<String> sorted = new ArrayList<>(this.data.keySet());
        Collections.sort(sorted, Collections.reverseOrder());
        LinkedHashMap<String, HeartRate> result = new LinkedHashMap<String, HeartRate>();
        for (int i=0;i<sorted.size();i++){
            if (this.data.containsKey(sorted.get(i))){
                result.put(sorted.get(i), this.data.getOrDefault(sorted.get(i), null));
            }
        }
        return result;
    }

    private void clearFields(){
        this.edtName.getText().clear();
        this.edtAge.getText().clear();
    }

    public void onClickAdd(View v){
        HeartRate hr = new HeartRate(this.edtName.getText().toString(), Integer.parseInt(this.edtAge.getText().toString()));
        this.data.put(hr.getFrequency() + hr.getName(), hr);

        // nao achei maneira "sutil" para limpar os dados //
        if (this.data.size() > 1){
            this.listData.setAdapter(null);
            adapter.notifyDataSetChanged();

            this.data = sortList();

            this.adapter.data = this.data;
            this.listData.setAdapter(this.adapter);
        }
        this.adapter.notifyDataSetChanged();
        Toast.makeText(getBaseContext(), "Adicionado", Toast.LENGTH_LONG).show();
        clearFields();
    }
}