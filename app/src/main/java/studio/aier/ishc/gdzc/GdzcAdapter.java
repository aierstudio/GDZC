package studio.aier.ishc.gdzc;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jarod on 2016/11/15.
 */

public class GdzcAdapter extends ArrayAdapter<GDZC> {

    private int resourcedId;

    public GdzcAdapter(Context context, int textViewResourceID, List<GDZC> objects){
        super(context, textViewResourceID, objects);
        resourcedId = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        GDZC gdzc = getItem(position); //获取当前项的Gdzc实例
        View view = LayoutInflater.from(getContext()).inflate(resourcedId, null);
        ImageView gdzcImage = (ImageView) view.findViewById(R.id.gdzc_image);
        TextView gdzcName = (TextView) view.findViewById(R.id.gdzc_name);
        TextView gdzcAcode = (TextView) view.findViewById(R.id.gdzc_acode);
        TextView gdzcMcode = (TextView) view.findViewById(R.id.gdzc_mcode);
        TextView gdzcUdep = (TextView) view.findViewById(R.id.gdzc_udep);
        TextView gdzcBrand = (TextView) view.findViewById(R.id.gdzc_brand);
        TextView gdzcModel = (TextView) view.findViewById(R.id.gdzc_model);
        gdzcImage.setImageBitmap(gdzc.getImagebm());
        gdzcName.setText(gdzc.getName());
        gdzcAcode.setText(gdzc.getAcode());
        gdzcMcode.setText(gdzc.getMcode());
        gdzcUdep.setText(gdzc.getUdep());
        gdzcBrand.setText(gdzc.getBrand());
        gdzcModel.setText(gdzc.getModel());

        return view;
    }
}
