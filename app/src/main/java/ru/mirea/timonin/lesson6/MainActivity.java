package ru.mirea.timonin.lesson6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private String textGroup;
    private String textNumber;
    private String textFilm;

    private EditText editTextGroup;
    private EditText editTextNumber;
    private EditText editTextFilm;
    private SharedPreferences preferences1;
    private SharedPreferences preferences2;
    private SharedPreferences preferences3;
    private SharedPreferences.Editor editor1;
    private SharedPreferences.Editor editor2;
    private SharedPreferences.Editor editor3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextGroup = findViewById(R.id.editGroup);
        editTextNumber = findViewById(R.id.editNumber);
        editTextFilm = findViewById(R.id.editFilm);
        preferences1 = getPreferences(Context.MODE_PRIVATE);
        preferences2 = getPreferences( Context.MODE_PRIVATE);
        preferences3 = getPreferences( Context.MODE_PRIVATE);
        editor1 = preferences1.edit();
        editor2 = preferences2.edit();
        editor3 = preferences3.edit();


        String name1 = preferences1.getString("GROUP", "Группа");
        editTextGroup.setText(name1);
        String name2 = preferences2.getString("NUMBER", "Номер");
        editTextNumber.setText(name2);
        String name3 = preferences3.getString("Film", "Фильм");
        editTextFilm.setText(name3);


    }

    public void onClickSave(View view){
        textGroup =  editTextGroup.getText().toString();
        textNumber =  editTextGroup.getText().toString();
        textFilm =  editTextGroup.getText().toString();
        editor1.putString("GROUP", textGroup);
        editor2.putString("NUMBER", textNumber);
        editor3.putString("Film", textFilm);
        editor1.apply();
        editor2.apply();
        editor3.apply();



    }

}