package br.feevale.freqmax;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.LinkedHashMap;

public class HeartRateAdapter extends BaseAdapter {
    LayoutInflater inflater;
    // < hash< FCM + Nome, object> //
    LinkedHashMap<String, HeartRate> data;

    public HeartRateAdapter(LinkedHashMap<String, HeartRate> data, Context context){
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

    public Object getElementAt(LinkedHashMap map, int index){
        return map.get((map.keySet().toArray())[index]);
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.getOrDefault(position, null);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.heartrate_item, null);
        TextView txtNameData = v.findViewById(R.id.txtNameR);
        TextView txtFCMData = v.findViewById(R.id.txtFCM);

        HeartRate hr = (HeartRate) this.getElementAt(this.data, position);
        txtNameData.setText(hr.getName());
        txtFCMData.setText(hr.toString());

        return v;
    }
}
