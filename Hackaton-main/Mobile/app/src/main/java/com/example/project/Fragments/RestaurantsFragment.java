package com.example.project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.google.android.material.navigation.NavigationView;


public class RestaurantsFragment extends Fragment {

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
                Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_loginFragment);
            }
        });

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
    }


}