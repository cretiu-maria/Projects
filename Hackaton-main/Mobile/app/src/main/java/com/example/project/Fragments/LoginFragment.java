package com.example.project.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.project.R;


public class LoginFragment extends Fragment {
    private boolean isTextInUsernameField = false;
    private boolean isTextInPasswordField = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        EditText passwordText = requireView().findViewById(R.id.password_field);
        EditText usernameText = requireView().findViewById(R.id.username_field);
        Button button = requireView().findViewById(R.id.login_button);

        passwordText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTextInPasswordField = !passwordText.getText().toString().isEmpty();
                button.setEnabled(isTextInPasswordField && isTextInUsernameField);
                if (isTextInUsernameField && isTextInPasswordField)
                    button.setTextColor(getResources().getColor(R.color.white));
                else {
                    button.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        usernameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isTextInUsernameField = !usernameText.getText().toString().isEmpty();
                button.setEnabled(isTextInPasswordField && isTextInUsernameField);
                if (isTextInUsernameField && isTextInPasswordField)
                    button.setTextColor(getResources().getColor(R.color.white));
                else {
                    button.setTextColor(getResources().getColor(R.color.black));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText usernameField = requireView().findViewById(R.id.username_field);
                String myArg = usernameField.getText().toString();
                Bundle bundle = new Bundle();
                //  bundle.putString("key", myArg);
                //  Navigation.findNavController(view).navigate(R.id.action_login_to_home2, bundle);
            }
        });

        Button buttonLogin = requireView().findViewById(R.id.login_button);
        TextView registerTextView = requireView().findViewById(R.id.register_text_field);
        registerTextView.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment));

        buttonLogin.setOnClickListener(v -> Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_restaurantsFragment));

    }
}