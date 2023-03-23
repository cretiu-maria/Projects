package com.example.project.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project.R;
import com.example.project.networking.AppClient;
import com.example.project.networking.LoginRequest;
import com.example.project.networking.LoginResponse;

import java.security.acl.LastOwnerException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText passwordText = requireView().findViewById(R.id.password_field);
        EditText usernameText = requireView().findViewById(R.id.username_field);
        Button buttonLogin = requireView().findViewById(R.id.login_button);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(usernameText.getText().toString()) || TextUtils.isEmpty(passwordText.getText().toString())){
                    String message = "All inputs required ...";
                    Toast.makeText(getActivity() ,message, Toast.LENGTH_SHORT).show();

                }else{
                    LoginRequest loginRequest = new LoginRequest();
                    loginUser(loginRequest);
                }
            }
        });


        TextView registerTextView = requireView().findViewById(R.id.register_text_field);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });

        //navigation
        requireView().findViewById(R.id.back_iamgeView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_restaurantsFragment);
            }
        });
    }

    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = AppClient.getService().AllLoginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(getActivity(),RestaurantsFragment.class).putExtra("data", loginResponse));

                }else{
                    String message = "An error occurred please try again later ...";
                    Toast.makeText(getActivity() ,message, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message= t.getLocalizedMessage();
                Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
