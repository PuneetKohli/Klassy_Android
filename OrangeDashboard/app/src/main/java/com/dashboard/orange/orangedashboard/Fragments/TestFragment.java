package com.dashboard.orange.orangedashboard.Fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.Toast;

import com.dashboard.orange.orangedashboard.R;

public class TestFragment extends Activity {

    ListView myListView;
    Button getResult;

    private ArrayList<String> dayOfWeekList = new ArrayList<String>();

    private void initDayOfWeekList(){
        dayOfWeekList.add("Sunday");
        dayOfWeekList.add("Monday");
        dayOfWeekList.add("Tuesday");
        dayOfWeekList.add("Wednesday");
        dayOfWeekList.add("Thursday");
        dayOfWeekList.add("Friday");
        dayOfWeekList.add("Saturday");

    }

    MyArrayAdapter myArrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDayOfWeekList();
        setContentView(R.layout.activity_main);

        myListView = (ListView)findViewById(android.R.id.list);

        myArrayAdapter = new MyArrayAdapter(
                this,
                R.layout.example_list_item,
                android.R.id.text1,
                dayOfWeekList
        );

        myListView.setAdapter(myArrayAdapter);
        myListView.setOnItemClickListener(myOnItemClickListener);

        getResult = (Button)findViewById(R.id.button);
        getResult.setOnClickListener(new OnClickListener(){

            @Override
            public void onClick(View v) {
                String result = "";

    /*
    //getCheckedItemPositions
    List<Integer> resultList = myArrayAdapter.getCheckedItemPositions();
    for(int i = 0; i < resultList.size(); i++){
     result += String.valueOf(resultList.get(i)) + " ";
    }
    */

                //getCheckedItems
                List<String> resultList = myArrayAdapter.getCheckedItems();
                for(int i = 0; i < resultList.size(); i++){
                    result += String.valueOf(resultList.get(i)) + "\n";
                }

                myArrayAdapter.getCheckedItemPositions().toString();
                Toast.makeText(
                        getApplicationContext(),
                        result,
                        Toast.LENGTH_LONG).show();
            }});

    }

    OnItemClickListener myOnItemClickListener
            = new OnItemClickListener(){

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            myArrayAdapter.toggleChecked(position);

        }};

    private class MyArrayAdapter extends ArrayAdapter<String>{

        private HashMap<Integer, Boolean> myChecked = new HashMap<Integer, Boolean>();

        public MyArrayAdapter(Context context, int resource,
                              int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);

            for(int i = 0; i < objects.size(); i++){
                myChecked.put(i, false);
            }
        }

        public void toggleChecked(int position){
            if(myChecked.get(position)){
                myChecked.put(position, false);
            }else{
                myChecked.put(position, true);
            }

            notifyDataSetChanged();
        }

        public List<Integer> getCheckedItemPositions(){
            List<Integer> checkedItemPositions = new ArrayList<Integer>();

            for(int i = 0; i < myChecked.size(); i++){
                if (myChecked.get(i)){
                    (checkedItemPositions).add(i);
                }
            }

            return checkedItemPositions;
        }

        public List<String> getCheckedItems(){
            List<String> checkedItems = new ArrayList<String>();

            for(int i = 0; i < myChecked.size(); i++){
                if (myChecked.get(i)){
                    (checkedItems).add(dayOfWeekList.get(i));
                }
            }

            return checkedItems;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;

            if(row==null){
                LayoutInflater inflater=getLayoutInflater();
                row=inflater.inflate(R.layout.example_list_item, parent, false);
            }

            CheckedTextView checkedTextView = (CheckedTextView)row.findViewById(R.id.checkbox);
            checkedTextView.setText(dayOfWeekList.get(position));

            Boolean checked = myChecked.get(position);
            if (checked != null) {
                checkedTextView.setChecked(checked);
            }

            return row;
        }

    }

}
