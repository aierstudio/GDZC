package studio.aier.ishc.gdzc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class ShowActivity extends AppCompatActivity
    implements View.OnClickListener{

    String acode;
    String mcode;
    String zctype;
    String zcname;
    String zcbrand;
    String zcmodel;
    String zcuserdep;
    String zcstore;
    String picname;
    String fzcentertime;
    String fzcprice;
    String fzcget;
    String fzcgetdate;


    String PicDir = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/images/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        Button btnBack = (Button) findViewById(R.id.show_btnBack);
        btnBack.setOnClickListener(this);

        //传回数据
        Intent scanit = getIntent();
        acode = scanit.getStringExtra("acode");
        mcode = scanit.getStringExtra("mcode");
        zctype = scanit.getStringExtra("zctype");
        zcname = scanit.getStringExtra("zcname");
        zcbrand = scanit.getStringExtra("zcbarnd");
        zcmodel = scanit.getStringExtra("zcmodel");
        zcuserdep = scanit.getStringExtra("zcuserdep");
        zcstore = scanit.getStringExtra("zcstore");
        picname = scanit.getStringExtra("picname");
        fzcentertime = scanit.getStringExtra("fzcentertime");
        fzcprice = scanit.getStringExtra("fzcprice");
        fzcget = scanit.getStringExtra("fzcget");
        fzcgetdate = scanit.getStringExtra("fzcgetdate");

        TextView txvAcode = (TextView) findViewById(R.id.show_txvAcodeR);
        TextView txvMcode = (TextView) findViewById(R.id.show_txvMcodeR);
        TextView txvType = (TextView) findViewById(R.id.show_txvTypeR);
        TextView txvName = (TextView) findViewById(R.id.show_txvNameR);
        TextView txvBrand = (TextView) findViewById(R.id.show_txvBrandR);
        TextView txvModel = (TextView) findViewById(R.id.show_txvModelR);
        TextView txvUdep = (TextView) findViewById(R.id.show_txvUDepR);
        TextView txvStore = (TextView) findViewById(R.id.show_txvStoreR);
        TextView txvCTime = (TextView) findViewById(R.id.show_txvCTimeR);
        TextView txvPrice = (TextView) findViewById(R.id.show_txvPriceR);
        TextView txvGet = (TextView) findViewById(R.id.show_txvGetR);
        TextView txvGTime = (TextView) findViewById(R.id.show_txvGTimeR);
        ImageView imgPic = (ImageView) findViewById(R.id.show_imgPic);

        if(picname.equals("")){

        }else{
            String ivStr = PicDir + picname;
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 1;
            Bitmap bm = BitmapFactory.decodeFile(ivStr, options);
            imgPic.setImageBitmap(bm);
        }


        txvAcode.setText(acode);
        txvMcode.setText(mcode);
        txvType.setText(zctype);
        txvName.setText(zcname);
        txvBrand.setText(zcbrand);
        txvModel.setText(zcmodel);
        txvUdep.setText(zcuserdep);
        txvStore.setText(zcstore);
        txvCTime.setText(fzcentertime);
        txvPrice.setText(fzcprice);
        txvGet.setText(fzcget);
        txvGTime.setText(fzcgetdate);

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
