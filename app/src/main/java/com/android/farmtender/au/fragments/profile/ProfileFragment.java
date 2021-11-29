package com.android.farmtender.au.fragments.profile;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.farmtender.au.R;
import com.android.farmtender.au.RegisterScreenActivity;
import com.android.farmtender.au.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {

    boolean isEmailValid = false, isPasswordValid = false;
    FragmentProfileBinding fragmentProfileBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentProfileBinding = FragmentProfileBinding.inflate(inflater,container,false);
        return fragmentProfileBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentProfileBinding.emailText.addTextChangedListener(emailTextWatcher);
        fragmentProfileBinding.passwordText.addTextChangedListener(passwordTextWatcher);
        fragmentProfileBinding.loginButton.setOnClickListener(loginClickListener);
        fragmentProfileBinding.register.setOnClickListener(registerClickListener);
    }

    View.OnClickListener registerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getActivity(), RegisterScreenActivity.class));
        }
    };

    TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0 && isValidEmail(charSequence)) {
                isEmailValid = true;

                checkValidity();
            } else {
                isEmailValid = false;

                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                isPasswordValid = true;

                checkValidity();
            } else {
                isPasswordValid = false;

                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    View.OnClickListener loginClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (!isEmailValid) {
                fragmentProfileBinding.emailTextInputLayout.setError("Enter correct Email");
            }
            else {
                fragmentProfileBinding.emailTextInputLayout.setErrorEnabled(false);
            }
            if(!isPasswordValid){
                fragmentProfileBinding.passwordTextInputLayout.setError("Enter Password");
            }else{
                fragmentProfileBinding.passwordTextInputLayout.setErrorEnabled(false);
            }
        }
    };

    private void checkValidity() {
        if (isEmailValid && isPasswordValid) {
            fragmentProfileBinding.loginButton.setBackgroundColor(getResources().getColor(R.color.darkgrey));
        } else {
            fragmentProfileBinding.loginButton.setBackgroundColor(getResources().getColor(R.color.disabledButtonColor));
        }
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}