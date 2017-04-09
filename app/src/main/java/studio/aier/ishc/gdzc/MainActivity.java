package studio.aier.ishc.gdzc;



import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener {

    IntentIntegrator integrator = new IntentIntegrator(this);
    String DB_NAME = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/gdzc_db";
    String tb_name = "mipad";
    SQLiteDatabase gdzcDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnScan = (ImageButton) findViewById(R.id.m_ibtnScan);
        Button btnBro = (Button) findViewById(R.id.m_btnBdata);
        Button btnSet = (Button) findViewById(R.id.m_btnSet);
        btnBro.setOnClickListener(this);
        btnScan.setOnClickListener(this);
        btnSet.setOnClickListener(this);

        gdzcDB = openOrCreateDatabase(DB_NAME, Context.MODE_PRIVATE, null);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.m_ibtnScan:
                integrator.setCaptureActivity(ScanActivity.class);
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES); //条码类型
                integrator.setPrompt("扫描资产二维码"); //底部的提示文字，设为""可以置空
                integrator.setCameraId(0);  //前置或者后置摄像头
                integrator.setBeepEnabled(true); //扫描成功的「哔哔」声，默认开启
                integrator.setBarcodeImageEnabled(false); //保存图像
                integrator.setOrientationLocked(false); //方向锁定，默认不锁
                integrator.initiateScan();
                break;
            case R.id.m_btnBdata:
                startActivity(new Intent(this, UdepActivity.class));
                break;
            case R.id.m_btnSet:
                //startActivity(new Intent(this, SetActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null ){
            if(result.getContents() == null) {
                Toast.makeText(this, "取消扫描！", Toast.LENGTH_LONG).show();
                //txvD.setText("取消扫描！");
            } else {
                String QRCode = result.getContents();
                Intent showit = new Intent(this, ShowActivity.class);

                Cursor cur = gdzcDB.rawQuery("SELECT * FROM " + tb_name + " WHERE kpQRC = ? OR bqQRC = ?", new String[]{QRCode,QRCode});
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

                }else{
                    Toast.makeText(this, "无此二维码数据，请查证！",Toast.LENGTH_SHORT).show();
                }
            }
        }else{

        }
    }

}

