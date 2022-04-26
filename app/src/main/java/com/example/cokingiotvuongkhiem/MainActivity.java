package com.example.cokingiotvuongkhiem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import com.ekn.gruzer.gaugelibrary.ArcGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.example.cokingiotvuongkhiem.adapter.DevicesAdapter;
import com.example.cokingiotvuongkhiem.model.AirConditioner;
import com.example.cokingiotvuongkhiem.model.Devices;
import com.example.cokingiotvuongkhiem.model.Home;
import com.example.cokingiotvuongkhiem.model.Room;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    GridView gridView;
    DevicesAdapter devicesAdapter;
    ArrayList<Devices> devicesArrayList;
    HashMap<String, Devices> listRoom, listRoomX;
    HashMap<String, Object> listHome;
    AirConditioner airConditioner1, airConditioner2, airConditioner3, airConditioner4;
    Devices devices1, devices2, devices3;
    Room room1, room2;
    Home home;
    ArcGauge gauge, gauge2;
//    List<Devices> listDevices = new ArrayList<>();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRoom = database.getReference("Living");
    DatabaseReference myHome = database.getReference("Home");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gauge = findViewById(R.id.idGauge);
        gauge2 = findViewById(R.id.idGauge2);
        List<Range> rangeList = new ArrayList<Range>();

        gauge.setValueColor(Color.RED);
        gauge2.setValueColor(Color.BLUE);
//        gauge.setGaugeBackGroundColor(Color.GREEN);
//        gauge2.setGaugeBackGroundColor(Color.CYAN);

        Range range1 = new Range();
        Range range2 = new Range();
        Range range3 = new Range();

        rangeList.add(range1);
        rangeList.add(range2);
        rangeList.add(range3);
        range1.setFrom(0.0);
        range1.setTo(32);
        range1.setColor(Color.parseColor("#AB47BC"));
        range2.setFrom(33);
        range2.setTo(66);
        range2.setColor(Color.parseColor("#E91E63"));
        range3.setFrom(67);
        range3.setTo(100);
        range3.setColor(Color.parseColor("#00B20B"));

        gauge2.setRanges(rangeList);
        gauge.setRanges(rangeList);

        airConditioner1 = new AirConditioner(1,"Dieu Hoa 1");
        airConditioner2 = new AirConditioner(2,"Dieu Hoa 2");
        airConditioner3 = new AirConditioner(3,"Dieu Hoa 3");
        airConditioner4 = new AirConditioner(4,"Dieu Hoa 4");
        room1 = new Room("Living");
        room2 = new Room("Bed");
        home = new Home("Home");
        home.addDevices(airConditioner1.getDeviceName().toLowerCase().replaceAll(" ",""),airConditioner1);
        home.addDevices(airConditioner2.getDeviceName().toLowerCase().replaceAll(" ",""),airConditioner2);
        home.addDevices(airConditioner3.getDeviceName().toLowerCase().replaceAll(" ",""),airConditioner3);


        devices1 = new Devices(5,"Fan");
        devices2 = new Devices(6,"Light");
        devices3 = new Devices(7,"Window");
        room2.addDevices(devices1.getDeviceName(),devices1);
        room2.addDevices(devices2.getDeviceName(),devices2);
        room2.addDevices(devices3.getDeviceName(),devices3);
        home.addDevices(devices1.getDeviceName().toLowerCase().replaceAll(" ",""),devices1);
        home.addDevices(devices2.getDeviceName().toLowerCase().replaceAll(" ",""),devices2);
        listRoom = room2.getListDevices();
        listHome = home.getDictDevices();
        myRoom.setValue(listRoom);
        myHome.setValue(listHome);
        // Read from the database
//        myRoom.child("Fan").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Devices devices = snapshot.getValue(Devices.class) ;
//              //  Log.d("FAN: ", devices.toString());
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        myRoom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.toString();
                Log.e("Value is:", "mss" + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });

        myHome.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
//                String value = dataSnapshot.toString();
//                String value = x.getClass().getName();
//                listRoomX.get("Dieu Hoa 2") = dataSnapshot;
                int currentHumidity = Integer.parseInt(dataSnapshot.child("dieuhoa1").child("currentHumidity").getValue().toString());
                int currentTemp = Integer.parseInt(dataSnapshot.child("dieuhoa1").child("currentTemp").getValue().toString());
                gauge.setValue(currentTemp);
                gauge2.setValue(currentHumidity);



                Log.e("Value is Home:", "temp is: " + currentTemp);
                Log.e("Value is Home:", "Humidity is: " + currentHumidity);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w( "Failed to read value.", error.toException());
            }
        });




///////////////////////////////////////////////////////
        gridView = findViewById(R.id.gridID);
        devicesArrayList = new ArrayList<Devices>();
        devicesArrayList.add(new Devices(11,"Light", R.drawable.light));
        devicesArrayList.add(new Devices(12,"Router", R.drawable.rou));
        devicesArrayList.add(new Devices(13,"Cooling Fan", R.drawable.fan));
        devicesArrayList.add(new Devices(14,"Font Dooor", R.drawable.lock));
        devicesArrayList.add(new Devices(15,"Humidity", R.drawable.hum));
        devicesArrayList.add(new Devices(16,"Temprature", R.drawable.temp));
        devicesArrayList.add(new Devices(17,"Air Condition", R.drawable.air));
        devicesArrayList.add(new Devices(18,"Television", R.drawable.tele));
        devicesArrayList.add(new Devices(19,"Kitchen", R.drawable.kit));
        devicesArrayList.add(new Devices(20,"Light 2", R.drawable.light));
        devicesAdapter = new DevicesAdapter(this, devicesArrayList);
        gridView.setAdapter(devicesAdapter);

        DevicesAdapter.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    DevicesAdapter.OnCheckedChangeListener mOnCheckedChangeListener = new DevicesAdapter.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(int position, boolean isChecked) {
            Log.e("xxx", ">>> position : " + position + ", isChecked : " + isChecked + " " + devicesArrayList.get(position).getDeviceName());

            int idOnChecked = devicesArrayList.get(position).getID();
            UpdateDevicesArrayList(devicesArrayList,idOnChecked,isChecked);
            UpdateDevices(room2,devicesArrayList);
            myRoom.setValue(room2.getListDevices());

        }
    };

    public void UpdateDevicesArrayList(ArrayList<Devices> ArrListDe, int id, boolean IsChecked){
//        for(Devices i:ArrListDe){
//
//        }
        for (int j = 0; j < ArrListDe.size(); j++){
            if( ArrListDe.get(j).getID() == id ){
                Devices devicesTG = ArrListDe.get(j);
                devicesTG.setChecked(IsChecked);
                ArrListDe.set(j,devicesTG);
            }

        }
    }
    public void UpdateDevices(Room room, ArrayList<Devices> ArrListD){

        room.updateListDevices(ArrListD);
    }

    public void setGauge(ArcGauge gauges){
        gauges.setMinValue(0);
        gauges.setMaxValue(100);

        gauges.setValueColor(Color.BLUE);

    }



}