package studio.aier.ishc.gdzc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

/**
 * Created by Jarod on 2016/11/15.
 */

public class GDZC {
    private String name;
    private String acode;
    private String mcode;
    private String udep;
    private String picname;
    private String brand;
    private String model;
    public GDZC(String name, String acode, String mcode, String udep, String brand, String model, String picname){
        this.name = name;
        this.acode = acode;
        this.mcode = mcode;
        this.udep = udep;
        this.brand = brand;
        this.model = model;
        this.picname = picname;

    }

    public String getName(){
        return "资产名称：" + name;
    }

    public String getcAcode(){
        return acode;
    }

    public String getAcode(){
        return "实物编号：" + acode;
    }

    public String getMcode(){
        return "行政编号：" + mcode;
    }

    public String getUdep(){
        return "使用部门：" + udep;
    }

    public String getBrand(){
        return "品牌："+ brand;
    }
    public  String getModel(){
        return "规格型号：" + model;
    }

    public Bitmap getImagebm(){
        String PicDir = Environment.getExternalStorageDirectory().getPath() + "/gdzc.online/images/";
        if(picname.equals("")){

        }else{
            PicDir = PicDir + picname;
        }
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        return BitmapFactory.decodeFile(PicDir, options);
    }
}
