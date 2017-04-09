package studio.aier.ishc.gdzc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StoreActivity extends AppCompatActivity {

    String DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/gdzc_db";
    String tb_name = "mipad";
    SQLiteDatabase gdzcDB;
    String zcUdep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);

        //传回数据
        Intent scanit = getIntent();
        zcUdep = scanit.getStringExtra("zcuserdep");

        gdzcDB = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
        Cursor cur = gdzcDB.rawQuery("SELECT zcstore FROM " + tb_name + " WHERE zcuserdep = ? GROUP BY zcstore", new String[]{zcUdep});
        final List<String> lstData = new ArrayList<String>();

        if(cur.moveToFirst()) {
            do {
                lstData.add(cur.getString(0));
            } while (cur.moveToNext());

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lstData);
        final ListView lstStore =(ListView) findViewById(R.id.st_lstData);
        lstStore.setAdapter(adapter);

        lstStore.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String zcStore = lstData.get(i);
                Toast.makeText(StoreActivity.this, zcStore, Toast.LENGTH_SHORT).show();
                Intent showit = new Intent(StoreActivity.this, DataActivity.class);
                showit.putExtra("zcstore", zcStore);
                startActivity(showit);
            }
        });
    }
}
