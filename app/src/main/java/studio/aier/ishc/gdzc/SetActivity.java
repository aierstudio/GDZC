package studio.aier.ishc.gdzc;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SetActivity extends AppCompatActivity
    implements View.OnClickListener{

    String db_name = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/gdzc_db";
    String tb_name = "mipad";
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        db = openOrCreateDatabase(db_name, Context.MODE_PRIVATE, null);
        Button btnSQL = (Button) findViewById(R.id.set_btnSQL);
        Button btnTest = (Button) findViewById(R.id.set_btnTest);
        Button btnSave = (Button) findViewById(R.id.set_btnSave);
        btnSQL.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.set_btnTest:

                break;
            case R.id.set_btnSave:

                break;
            case R.id.set_btnSQL:
                String ceateTable = "CREATE TABLE IF NOT EXISTS " + tb_name +
                        "( _id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "acode TEXT(32), " +
                        "mcode TEXT(64), " +
                        "zctype TEXT(32), " +
                        "zcname TEXT(32), " +
                        "zcbrand TEXT(32), " +
                        "zcmodel TEXT(32), " +
                        "zcuserdep TEXT(64), " +
                        "zcstore TEXT(64), " +
                        "picname TEXT(64), " +
                        "bqQRC TEXT(255), " +
                        "kpQRC TEXT(255), " +
                        "fzcentertime TEXT(32), " +
                        "fzcprice TEXT(32), " +
                        "fzcget TEXT(32), " +
                        "fzcgetdate TEXT(32))";
                db.execSQL(ceateTable);
                break;
        }
    }
}
