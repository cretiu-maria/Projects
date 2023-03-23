package com.example.project.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.example.project.networking.AppClient;
import com.example.project.networking.RegisterRequest;
import com.example.project.networking.RegisterResponse;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText passwordText = requireView().findViewById(R.id.password_field_register);
        EditText usernameText = requireView().findViewById(R.id.username_field_register);
        EditText emailText = requireView().findViewById(R.id.email_field_register);
        EditText nameText = requireView().findViewById(R.id.name_fil_register);
        EditText phoneNumberText = requireView().findViewById(R.id.phone_number_field_register);
        EditText rolIdText = requireView().findViewById(R.id.rol_id_field_register);
        Button buttonRegister = requireView().findViewById(R.id.register_button);
        TextView loginTextView = requireView().findViewById(R.id.login_text_field);
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        //navigation
        requireView().findViewById(R.id.back_iamgeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(usernameText.getText().toString()) || TextUtils.isEmpty(passwordText.getText().toString()) || TextUtils.isEmpty(nameText.getText().toString()) || TextUtils.isEmpty(emailText.getText().toString()) || TextUtils.isEmpty(phoneNumberText.getText().toString()) || TextUtils.isEmpty(rolIdText.getText().toString())){
                    String message = "All inputs required ...";
                    Toast.makeText(getActivity() ,message, Toast.LENGTH_SHORT).show();
                }else {
                    RegisterRequest registerRequest = new RegisterRequest();
                    registerRequest.setUsername(usernameText.getText().toString());
                    registerRequest.setPassword(passwordText.getText().toString());
                    registerRequest.setEmail(emailText.getText().toString());
                    registerRequest.setName(nameText.getText().toString());
                    registerRequest.setPhoneNumber(phoneNumberText.getText().toString());
                    registerRequest.setRolId(rolIdText.getText().toString());
                    registerUser(registerRequest);
                }
            }
        });
    }

    public void registerUser(RegisterRequest registerRequest){
        Call<RegisterResponse> registerResponseCall = AppClient.getService().registerUsers(registerRequest);
        registerResponseCall.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if(response.isSuccessful()){
                    String message = "Successful...";
                    Toast.makeText(getActivity() ,message, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), LoginFragment.class));
                }else {
                     String message = "An error occurred please try again later ...";
                    Toast.makeText(getActivity() ,message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });
    }
}