package com.example.farmtender;

import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.farmtender.fragments.profile.ProfileFragment;
import com.example.farmtender.interfaces.CoffeeApi;
import com.example.farmtender.models.CoffeeModel;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterScreenActivity extends AppCompatActivity {

    public static final String TAG = "Register";
    Toolbar toolbar;
    String password, confirm_password;
    String[] coffeeTitles;
    String selectedCoffee = "", previousCoffee = "";
    Button registerButton;
    TextInputEditText state, firstName, lastName, mobile, email, pass, confirmPass, address, postalCode;
    TextInputLayout confirmPassLayout, passwrodLayout;
    int checkedItem = 0, previousCheckItem = 0;
    CheckBox termsCheck;
    boolean isFirstNameValid, isLastNameValid, isMobileValid, isEmailValid, isPasswordValid, isAddressValid, isPinCodeValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        toolbar = findViewById(R.id.toolBar);
        registerButton = findViewById(R.id.registerBtn);
        state = findViewById(R.id.state);
        firstName = findViewById(R.id.firstNameText);
        lastName = findViewById(R.id.lastNameText);
        mobile = findViewById(R.id.mobileText);
        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPass);
        passwrodLayout = findViewById(R.id.password);
        confirmPass = findViewById(R.id.confirm_pass);
        confirmPassLayout = findViewById(R.id.confirm_password);
        address = findViewById(R.id.addressText);
        postalCode = findViewById(R.id.postalCodeText);
        termsCheck = findViewById(R.id.terms);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.toolbar);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.lightGrey)));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.sampleapis.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CoffeeApi coffeeApi = retrofit.create(CoffeeApi.class);
        Call<List<CoffeeModel>> call = coffeeApi.getCoffee();

        call.enqueue(new Callback<List<CoffeeModel>>() {
            @Override
            public void onResponse(Call<List<CoffeeModel>> call, Response<List<CoffeeModel>> response) {
                coffeeTitles = new String[]{};
                List<CoffeeModel> coffees = response.body();
                for(CoffeeModel coffee: coffees){
                    coffeeTitles = addElement(coffeeTitles,coffee.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<CoffeeModel>> call, Throwable t) {

            }
        });

        firstName.addTextChangedListener(firstNameTextWatcher);
        lastName.addTextChangedListener(lastNameTextWatcher);
        mobile.addTextChangedListener(mobileTextWatcher);
        email.addTextChangedListener(emailTextWatcher);
        pass.addTextChangedListener(passTextWatcher);
        confirmPass.addTextChangedListener(confirmPassTextWatcher);
        address.addTextChangedListener(addressTextWatcher);
        postalCode.addTextChangedListener(postalCodeTextWatcher);
    }

    private void checkValidity() {
        if (isFirstNameValid && isLastNameValid && isMobileValid && isEmailValid && isPasswordValid && isAddressValid && isPinCodeValid) {
            registerButton.setBackgroundColor(getResources().getColor(R.color.darkgrey));
        } else {
            registerButton.setBackgroundColor(getResources().getColor(R.color.disabledButtonColor));
        }
    }

    private void showListDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterScreenActivity.this);
        builder.setTitle("Choose");
        builder.setSingleChoiceItems(coffeeTitles, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                previousCoffee = selectedCoffee;
                previousCheckItem = checkedItem;
                selectedCoffee = coffeeTitles[i];
                checkedItem = i;
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                state.setText(selectedCoffee);
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedCoffee = previousCoffee;
                checkedItem = previousCheckItem;
                dialogInterface.dismiss();
            }
        });
        AlertDialog alert = builder.create();
        alert.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                alert.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.black));
                alert.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.black));
            }
        });
        alert.show();
    }

    public void onRegisterClicked(View view) {
        if (!isFirstNameValid) {
            firstName.setError("Enter a name");
        } else if (!isLastNameValid) {
            lastName.setError("Enter last name");
        } else if (!isMobileValid) {
            mobile.setError("Enter correct mobile number");
        } else if (!isEmailValid) {
            email.setError("Enter correct emailId");
        } else if (!isPasswordValid) {
            pass.setError("Enter password");
        } else if (!password.contentEquals(confirm_password)) {
            confirmPass.setError("Passwords do not match");
            confirmPass.requestFocus();
        } else if (!isAddressValid) {
            address.setError("Enter address");
        } else if (!isPinCodeValid) {
            postalCode.setError("Enter postal code");
        } else if (selectedCoffee == "") {
            state.setError("Select a state");
        } else if (!termsCheck.isChecked()) {
            termsCheck.setError("Please click the checkbox");
            termsCheck.requestFocus();
        } else {
            firstName.setError(null);
            lastName.setError(null);
            mobile.setError(null);
            email.setError(null);
            pass.setError(null);
            confirmPass.setError(null);
            address.setError(null);
            postalCode.setError(null);
            state.setError(null);
            termsCheck.setError(null);
        }
    }

    public void onStateClicked(View view) {
        showListDialog();
    }

    TextWatcher firstNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                isFirstNameValid = true;
                checkValidity();
            } else {
                isFirstNameValid = false;
                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher lastNameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                isLastNameValid = true;
                checkValidity();
            } else {
                isLastNameValid = false;
                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher mobileTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0 && charSequence.length() == 10) {
                isMobileValid = true;
                checkValidity();
            } else {
                isMobileValid = false;
                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0 && ProfileFragment.isValidEmail(charSequence)) {
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

    TextWatcher passTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                password = charSequence.toString();
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

    TextWatcher confirmPassTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                confirm_password = charSequence.toString();
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

    TextWatcher addressTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                isAddressValid = true;
                checkValidity();
            } else {
                isAddressValid = false;
                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    TextWatcher postalCodeTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() > 0) {
                isPinCodeValid = true;
                checkValidity();
            } else {
                isPinCodeValid = false;
                checkValidity();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    String[] addElement(String[] org, String added) {
        String[] result = Arrays.copyOf(org, org.length + 1);
        result[org.length] = added;
        return result;
    }
}