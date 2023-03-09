package com.example.project.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;


public class HomeFragment extends Fragment {
    boolean isUserLogedIn = false;
    float v = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        DrawerLayout drawerLayout = requireView().findViewById(R.id.homeFragment_drawer);
        NavigationView navigationView = requireView().findViewById(R.id.header_navigationView);
        requireView().findViewById(R.id.menu_iamgeView).setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            item.setChecked(true);
            drawerLayout.closeDrawer(GravityCompat.START);
            switch (id) {
//                    case R.id.homeFragment:
//                        replaceFragment(new HomeFragment()); break;
                case R.id.aboutFragment:
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutFragment);
                    break;
                case R.id.contactFragment:
                    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_contactFragment);

                    break;
                case R.id.restaurantsFragmment: {
                    if (!isUserLogedIn) {
                        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_loginFragment);
                        LoginDialogFragment restaurantsDialog = new LoginDialogFragment();
                        FragmentManager fm = getParentFragmentManager();
                        restaurantsDialog.show(fm, "about");
                    } else {
                        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_restaurantsFragment);
                    }
                }
                break;
                default:
                    return true;
            }
            return true;
        });

        FloatingActionButton facebookButton = requireView().findViewById(R.id.facebook_floatingActionButton);
        FloatingActionButton instagramButton = requireView().findViewById(R.id.instagram_floatingActionButton);
        FloatingActionButton linkdinButton = requireView().findViewById(R.id.linkdin_floatingActionButton);

        facebookButton.setTranslationY(400);
        instagramButton.setTranslationY(400);
        linkdinButton.setTranslationY(400);

        facebookButton.setAlpha(v);
        instagramButton.setAlpha(v);
        linkdinButton.setAlpha(v);

        facebookButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(450).start();
        instagramButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(450).start();
        linkdinButton.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(450).start();


    }

}