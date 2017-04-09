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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity{

    private List<GDZC> gdzcList = new ArrayList<GDZC>();
    String zcStore;

    String DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/gdzc_db";
    String tb_name = "mipad";
    SQLiteDatabase gdzcDB;
    Cursor cur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        gdzcDB = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);

        //传回数据
        Intent scanit = getIntent();
        zcStore = scanit.getStringExtra("zcstore");
        TextView txvStore = (TextView) findViewById(R.id.da_txvStore);
        txvStore.setText("使用部门：" + zcStore);

        initGdzc();
        GdzcAdapter adapter = new GdzcAdapter(DataActivity.this, R.layout.gdzc_item, gdzcList);
        ListView lstData = (ListView) findViewById(R.id.da_lstData);
        lstData.setAdapter(adapter);

        lstData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GDZC gdzcData = gdzcList.get(i);
                //Toast.makeText(DataActivity.this, gdzcData.getName(), Toast.LENGTH_SHORT).show();
                cur = gdzcDB.rawQuery("SELECT * FROM " + tb_name + " WHERE acode = ?", new String[]{gdzcData.getcAcode()});

                Log.i("gzPan getList:", gdzcData.getcAcode());
                if(cur.moveToFirst()) {
                    String acode = cur.getString(cur.getColumnIndex("acode"));
                    String mcode = cur.getString(cur.getColumnIndex("mcode"));
                    String zctype = cur.getString(cur.getColumnIndex("zctype"));
                    String zcbrand = cur.getString(cur.getColumnIndex("zcbrand"));
                    String zcmodel = cur.getString(cur.getColumnIndex("zcmodel"));
                    String zcname = cur.getString(cur.getColumnIndex("zcname"));
                    String zcuserdep = cur.getString(cur.getColumnIndex("zcuserdep"));
                    String zcstore = cur.getString(cur.getColumnIndex("zcstore"));
                    String picname = cur.getString(cur.getColumnIndex("picname"));
                    String fzcentertime = cur.getString(cur.getColumnIndex("fzcentertime"));
                    String fzcprice = cur.getString(cur.getColumnIndex("fzcprice"));
                    String fzcget = cur.getString(cur.getColumnIndex("fzcget"));
                    String fzcgetdate = cur.getString(cur.getColumnIndex("fzcgetdate"));

                    Intent showit = new Intent(DataActivity.this, ShowActivity.class);
                    showit.putExtra("acode" ,acode);
                    showit.putExtra("mcode",mcode);
                    showit.putExtra("zctype", zctype);
                    showit.putExtra("zcbrand", zcbrand);
                    showit.putExtra("zcmodel", zcmodel);
                    showit.putExtra("zcname", zcname);
                    showit.putExtra("zcuserdep", zcuserdep);
                    showit.putExtra("zcstore", zcstore);
                    showit.putExtra("picname", picname);
                    showit.putExtra("fzcentertime", fzcentertime);
                    showit.putExtra("fzcprice", fzcprice);
                    showit.putExtra("fzcget", fzcget);
                    showit.putExtra("fzcgetdate", fzcgetdate);
                    startActivity(showit);
                }

            }
        });

    }

    private void initGdzc(){

        cur = gdzcDB.rawQuery("SELECT * FROM " + tb_name + " WHERE zcstore = ? ", new String[]{zcStore});

        if(cur.moveToFirst()){
            do{
                GDZC gdzcData = new GDZC(cur.getString(cur.getColumnIndex("zcname")),
                        cur.getString(cur.getColumnIndex("acode")),
                        cur.getString(cur.getColumnIndex("mcode")),
                        cur.getString(cur.getColumnIndex("zcuserdep")),
                        cur.getString(cur.getColumnIndex("zcbrand")),
                        cur.getString(cur.getColumnIndex("zcmodel")),
                        cur.getString(cur.getColumnIndex("picname")));
                gdzcList.add(gdzcData);
            }while (cur.moveToNext());
        }
    }
}
