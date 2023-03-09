package com.example.proiect_scd_mobile;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proiect_scd_mobile.Adapters.RecyclerViewAdapterNFT;
import com.example.proiect_scd_mobile.networking.APIs;
import com.example.proiect_scd_mobile.networking.ItemClickListener;
import com.example.proiect_scd_mobile.networking.collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements ItemClickListener {

    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    String yachtKey = "yacht";
    String kennelClubKey = "kennel";
    String moonBircKey = "moonBird";
    Boolean yachtNotifOn = false;
    Boolean kennelNotifOn = false;
    Boolean moonNotifOn = false;
    private RecyclerView NftRV;
    private ArrayList<String> recyclerDataArrayList;
    private ArrayList<String> priceList;
    private int notificationId = 1;
    private RecyclerViewAdapterNFT recyclerViewAdapterNft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = this.getSharedPreferences(
                "com.example.proiect_scd_mobile", Context.MODE_PRIVATE);
        editor = prefs.edit();
        createNotificationChannel();
        recyclerDataArrayList = new ArrayList<>();
        NftRV = findViewById(R.id.nftRecycleView);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                getAllCollectionList();
            }

        }, 0, 60000);
    }

    private void setUpRecyclerView() {
        recyclerViewAdapterNft = new RecyclerViewAdapterNFT(recyclerDataArrayList, this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        NftRV.setLayoutManager(manager);
        NftRV.setAdapter(recyclerViewAdapterNft);
        recyclerViewAdapterNft.setClickListener(this);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "nft";
            String description = "nft notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("1", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onClick(View view, int position) {
        if (position == 0) {
            yachtNotifOn = !yachtNotifOn;
            editor.putBoolean("yachtNotif",yachtNotifOn);
            editor.apply();
            if (yachtNotifOn) {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.grey));
            }
        } else if (position == 1) {
            kennelNotifOn = !kennelNotifOn;
            editor.putBoolean("kennelNotif",kennelNotifOn);
            editor.apply();
            if (kennelNotifOn) {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.grey));
            }
        } else if (position == 2) {
            moonNotifOn = !moonNotifOn;
            editor.putBoolean("moonNotif",moonNotifOn);
            editor.apply();
            if (moonNotifOn) {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.green));
            } else {
                view.findViewById(R.id.cardViewLayout).setBackgroundColor(getResources().getColor(R.color.grey));
            }
        }
    }

    private void sendNotification(String title, float pricePercentage) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle("Nft price change")
                .setContentText(title + " price has dropped by " + pricePercentage + "%")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

        notificationManager.notify(notificationId, builder.build());
        notificationId++;
    }

    private float comparePrices(float oldPrice, float newPrice) {
        float percentage;
        percentage = ((oldPrice - newPrice) / oldPrice) * 100;
        return percentage;
    }

    private void getAllCollectionList() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.6:4000/api/collections/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIs apIs = retrofit.create(APIs.class);

        Call<ArrayList<collection>> call = apIs.getAllCollectionList();

        call.enqueue(new Callback<ArrayList<collection>>() {
            @Override
            public void onResponse(Call<ArrayList<collection>> call, Response<ArrayList<collection>> response) {

                if (response.isSuccessful()) {
                    Log.e("cretiu", "successful");
                    ArrayList<collection> listaNft = response.body();

                    for (collection c : listaNft) {
                        recyclerDataArrayList.add(c.getName());
                        if (c.getName().equals("boredapeyachtclub")) {
                            float price = Float.parseFloat(c.getFloorPrice());
                            float percentage = comparePrices(prefs.getFloat(yachtKey, 0f), price);
                            if (percentage > 10 && yachtNotifOn) {
                                sendNotification(c.getName(), percentage);
                            }
                            editor.putFloat(yachtKey, price);
                            editor.apply();
                        }
                        if (c.getName().equals("bored-ape-kennel-club")) {
                            float price = Float.parseFloat(c.getFloorPrice());
                            float percentage = comparePrices(prefs.getFloat(kennelClubKey, 0f), price);
                            if (percentage > 10 && kennelNotifOn) {
                                sendNotification(c.getName(), percentage);
                            }
                            editor.putFloat(kennelClubKey, price);
                            editor.apply();
                        }
                        if (c.getName().equals("proof-moonbirds")) {
                            float price = Float.parseFloat(c.getFloorPrice());
                            float percentage = comparePrices(prefs.getFloat(moonBircKey, 0f), price);
                            if (percentage > 10 && moonNotifOn) {
                                sendNotification(c.getName(), percentage);
                            }
                            editor.putFloat(moonBircKey, price);
                            editor.apply();
                        }
                    }
                    Set<String> set = new HashSet<>(recyclerDataArrayList);
                    recyclerDataArrayList.clear();
                    recyclerDataArrayList.addAll(set);
                    setUpRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<collection>> call, Throwable t) {
                Log.e("sd", t.getMessage());
            }
        });
    }
}