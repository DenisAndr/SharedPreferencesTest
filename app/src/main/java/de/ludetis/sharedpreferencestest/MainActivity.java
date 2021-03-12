package de.ludetis.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    public static final String SETTINGS_FILE = "my_settings";
    public static final String SWITCH_KEY = "SWITCH_KEY";
    public static final String CHECKBOX_KEY = "CHECKBOX_KEY";
    public static final String EDIT_TEXT_KEY = "EDIT_TEXT_KEY";
    public static final String RADIO_BUTTON_KEY = "RADIO_BUTTON_KEY";

    Switch aSwitch;
    CheckBox checkBox;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch = findViewById(R.id.switch1);
        checkBox = findViewById(R.id.checkBox);

        radioButton1 = findViewById(R.id.radioButton3);
        radioButton2 = findViewById(R.id.radioButton4);
        radioButton3 = findViewById(R.id.radioButton5);

        editText = findViewById(R.id.editTextTextPersonName);

        loadSetings();

        aSwitch.setOnCheckedChangeListener(this);
        checkBox.setOnCheckedChangeListener(this);

        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                saveSetings();
            }
        });
    }

    private void loadSetings() {
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS_FILE, MODE_PRIVATE);

        aSwitch.setChecked(sharedPreferences.getBoolean(SWITCH_KEY, true));
        checkBox.setChecked(sharedPreferences.getBoolean(CHECKBOX_KEY, false));
        editText.setText(sharedPreferences.getString(EDIT_TEXT_KEY, "Имя не задано"));

        int radioButtonIndex = sharedPreferences.getInt(RADIO_BUTTON_KEY, -1);
        if (radioButtonIndex == 1) {
            radioButton1.setChecked(true);
        } else if (radioButtonIndex == 2) {
            radioButton2.setChecked(true);
        } else if (radioButtonIndex == 3) {
            radioButton3.setChecked(true);
        }

    }

    private void saveSetings() {
        SharedPreferences sharedPreferences = getSharedPreferences(SETTINGS_FILE, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(SWITCH_KEY, aSwitch.isChecked());
        edit.putBoolean(CHECKBOX_KEY, checkBox.isChecked());

        int radioButtonIndex = -1;
        if (radioButton1.isChecked()) {
            radioButtonIndex = 1;
        } else if (radioButton2.isChecked()) {
            radioButtonIndex = 2;
        } else if (radioButton3.isChecked()) {
            radioButtonIndex = 3;
        }

        edit.putInt(RADIO_BUTTON_KEY, radioButtonIndex);
        edit.putString(EDIT_TEXT_KEY, editText.getText().toString());
        edit.apply();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        saveSetings();
    }
}