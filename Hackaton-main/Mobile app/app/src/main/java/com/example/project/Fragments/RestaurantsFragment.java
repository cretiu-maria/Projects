package com.example.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Adapters.RecyclerViewAdapterRestaurant;
import com.example.project.R;
import com.example.project.networking.APIs;
import com.example.project.networking.LoginResponse;
import com.example.project.networking.User;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RestaurantsFragment extends Fragment {
    LoginResponse loginResponse;
    private RecyclerView restaurantRV;
    private ArrayList<User> recyclerDataArrayList;
    private RecyclerViewAdapterRestaurant recyclerViewAdapterRestaurant;
    private ProgressBar circularProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_restaurants, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        Button button = requireView().findViewById(R.id.restaurantsButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_productFragment);
            }
        });

        Intent intent = getActivity().getIntent();
        if (intent.getExtras() != null) {
            loginResponse = (LoginResponse) intent.getSerializableExtra("data");
        }

        //navigation
        DrawerLayout drawerLayout = requireView().findViewById(R.id.restaurantsFragment_drawer);
        NavigationView navigationView = requireView().findViewById(R.id.header_navigationView);
        requireView().findViewById(R.id.menu_iamgeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);
                switch (id) {

                    case R.id.aboutFragment:
                        Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_aboutFragment);
                        break;
                    case R.id.contactFragment:
                        Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_contactFragment);
                        break;
                    case R.id.homeFragment:
                        Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_homeFragment);
                        break;

                    default:
                        return true;
                }
                return true;
            }
        });

        restaurantRV = requireView().findViewById(R.id.restaurantsRecycleView);
        circularProgress = requireView().findViewById(R.id.idPBLoading);
        recyclerDataArrayList = new ArrayList<>();
        getAllRestaurants();
    }

    private void getAllRestaurants() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.20.10.6:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIs apIs = retrofit.create(APIs.class);

        Call<ArrayList<User>> call = apIs.getAllRestaurants();

        call.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {

                if (response.isSuccessful()) {
                    circularProgress.setVisibility(View.GONE);
                    recyclerDataArrayList = response.body();

                    for (int i = 0; i < recyclerDataArrayList.size(); i++) {
                        recyclerViewAdapterRestaurant = new RecyclerViewAdapterRestaurant(recyclerDataArrayList, getActivity());

                        LinearLayoutManager manager = new LinearLayoutManager(getActivity());

                        restaurantRV.setLayoutManager(manager);

                        restaurantRV.setAdapter(recyclerViewAdapterRestaurant);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {
                Toast.makeText(getActivity(), "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
