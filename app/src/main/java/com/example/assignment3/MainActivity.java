package com.example.assignment3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText nameEditText, emailEditText, phoneEditText;
    private TextInputEditText passwordEditText;
    private Spinner bookSpinner;
    private String name, email, phone, password, selectedBook;
    private Button submitButton;
    private Pattern namePattern = Pattern.compile("[a-zA-Z. ]+");
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+$");
    private Pattern phonePattern = Pattern.compile("\\d{11}");
    private LinearLayout inputLayout, outputLayout;
    private TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing views
        nameEditText = findViewById(R.id.name);
        emailEditText = findViewById(R.id.email);
        phoneEditText = findViewById(R.id.num);
        passwordEditText = findViewById(R.id.pass);
        bookSpinner = findViewById(R.id.itemSpinner);
        submitButton = findViewById(R.id.submit);
        inputLayout = findViewById(R.id.inputLayout);
        outputLayout = findViewById(R.id.outputLayout);
        outputText = findViewById(R.id.outputText);

        // Spinner data
        String[] books = new String[]{"Select Book", "Book 1", "Book 2", "Book 3", "Book 4"};
        bookSpinner.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, books));
        bookSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBook = bookSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        // Submit button action
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Getting form values
                name = nameEditText.getText().toString();
                email = emailEditText.getText().toString();
                phone = phoneEditText.getText().toString();
                password = passwordEditText.getText().toString();

                // Validation checks
                if (name.isEmpty()) {
                    nameEditText.setError("Please enter your name");
                    nameEditText.requestFocus();
                } else if (!namePattern.matcher(name).matches()) {
                    nameEditText.setError("Invalid name format");
                    nameEditText.requestFocus();
                } else if (email.isEmpty()) {
                    emailEditText.setError("Please enter your email");
                    emailEditText.requestFocus();
                } else if (!emailPattern.matcher(email).matches()) {
                    emailEditText.setError("Invalid email format");
                    emailEditText.requestFocus();
                } else if (phone.isEmpty()) {
                    phoneEditText.setError("Please enter your phone number");
                    phoneEditText.requestFocus();
                } else if (!phonePattern.matcher(phone).matches()) {
                    phoneEditText.setError("Phone number must be 11 digits");
                    phoneEditText.requestFocus();
                } else if (password.isEmpty()) {
                    passwordEditText.setError("Please enter a password");
                    passwordEditText.requestFocus();
                } else if (password.length() < 4) {
                    passwordEditText.setError("Password must be at least 4 characters");
                    passwordEditText.requestFocus();
                } else if (Objects.equals(selectedBook, "Select Book")) {
                    Toast.makeText(getApplicationContext(), "Please select a book", Toast.LENGTH_SHORT).show();
                } else {
                    // Displaying the form data
                    inputLayout.setVisibility(View.GONE);
                    outputLayout.setVisibility(View.VISIBLE);
                    String result = "Name: " + name + "\nEmail: " + email + "\nPhone: " + phone + "\nBook: " + selectedBook;
                    outputText.setText(result);
                }
            }
        });
    }
}
