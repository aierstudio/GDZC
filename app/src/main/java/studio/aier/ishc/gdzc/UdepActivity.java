package studio.aier.ishc.gdzc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UdepActivity extends AppCompatActivity {


    String DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/gdzc_db";
    String tb_name = "mipad";
    SQLiteDatabase gdzcDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_udep);
        gdzcDB = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        Cursor cur = gdzcDB.rawQuery("SELECT zcuserdep FROM " + tb_name + " GROUP by zcuserdep", null);
        final List<String> lstData = new ArrayList<String>();
        if(cur.moveToFirst()){
            do{
                lstData.add(cur.getString(0));
            }while (cur.moveToNext());

        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(UdepActivity.this, android.R.layout.simple_list_item_1, lstData);
        final ListView lstUdep =(ListView) findViewById(R.id.ud_lstData);
        lstUdep.setAdapter(adapter);

        lstUdep.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String zcUdep = lstData.get(i);
                //Toast.makeText(UdepActivity.this, zcUdep, Toast.LENGTH_SHORT).show();
                Intent showit = new Intent(UdepActivity.this, StoreActivity.class);
                showit.putExtra("zcuserdep", zcUdep);
                Log.i("gdzc Put", zcUdep);
                startActivity(showit);

            }
        });
    }
}
