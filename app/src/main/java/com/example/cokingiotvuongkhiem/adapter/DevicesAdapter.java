package com.example.cokingiotvuongkhiem.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cokingiotvuongkhiem.R;
import com.example.cokingiotvuongkhiem.model.Devices;

import java.util.ArrayList;

public class DevicesAdapter extends ArrayAdapter<Devices> {
    private static OnCheckedChangeListener onItemCheckedListener;
    Context context;
    ArrayList<Devices> listDevices;

    public interface OnCheckedChangeListener {
        void onCheckedChanged(int position , boolean isChecked);
    }



    public static void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
        onItemCheckedListener = listener;
    }




    public DevicesAdapter(@NonNull Context context, ArrayList<Devices> listDevices) {
        super(context, 0, listDevices);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_sub, parent, false);
            holder = new ViewHolder();
            holder.imageView = listItemView.findViewById(R.id.idImgView);
            holder.textView = listItemView.findViewById(R.id.idTextView);
            holder.swt = listItemView.findViewById(R.id.idSwitch);
            listItemView.setTag(holder);
        } else {
            holder = (ViewHolder) listItemView.getTag();
        }
        Devices devices = getItem(position);
        Log.i("position", String.valueOf(position));
        holder.textView.setText(devices.getDeviceName());
        holder.imageView.setImageResource(devices.getImgID());

        holder.swt.setTag(position);
        holder.swt.setOnCheckedChangeListener(mOnCheckedChangeListener);

        holder.imageView.setTag(position);

        return listItemView;
    }

    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();

        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomGridView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        TextView textView;
        ImageView imageView;
        Switch swt;
    }


    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            int position = (int) buttonView.getTag();

            if (onItemCheckedListener != null) {
                onItemCheckedListener.onCheckedChanged(position, isChecked);
            }
        }
    };
}
