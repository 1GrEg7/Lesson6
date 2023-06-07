package ru.mirea.timonin.employeedb;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText superHeroName;
    private EditText superHeroAge;
    private EditText superHeroId;
    EmployeeDao employeeDao;
    List<Employee> employees;
    private ListView listView;
    ArrayList<String> Names = new ArrayList<String>();
    ArrayList<ArrayList<Object>> liiist = new ArrayList<ArrayList<Object>>();

    AppDatabase db;

    List<Integer> Ages = new ArrayList<Integer>();
    List<Integer> ID =  new ArrayList<Integer>();;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        superHeroAge = findViewById(R.id.editTextAge);
        superHeroName = findViewById(R.id.editTextName);
        superHeroId = findViewById(R.id.editTextId);
        listView = findViewById(R.id.list_view);



        //AppDatabase db = App.getInstance().getDatabase();
        db = App.getInstance().getDatabase();
        employeeDao = db.employeeDao();

        Employee employee = new Employee();
        /*employee.id = 1;
        employee.name = "Герой по умолчанию";
        employee.salary = 20;*/
// запись сотрудников в базу
        employeeDao.insert(employee);
// Загрузка всех работников
        employees = employeeDao.getAll();
// Получение определенного работника с id = 1
        employee = employeeDao.getById(1);
// Обновление полей объекта
/*        employee.salary = 21;
        employeeDao.update(employee);*/
        //Log.d(TAG, employee.name + " " + employee.salary);

        employeeDao.deleteAll();
    }


    public void onClickSave(View view){
        Employee superHero = new Employee();
        Integer age;
        String name;
        Integer id;
        try {
            age = Integer.parseInt(superHeroAge.getText().toString());
            name = superHeroName.getText().toString();
            id = Integer.parseInt( superHeroId.getText().toString());

        }catch (Exception e){
            age = 0;
            name= " ";
            id = -1;
            Toast toast = Toast.makeText(this, "Для сохранения необходимо заполнить поля!", Toast.LENGTH_LONG);
            toast.show();
        }





        try {
            if (id!=-1){
                superHero.id = id;
                superHero.salary =age;
                superHero.name = name;
                employeeDao.insert(superHero);
                String superHeroInfo = id+": " + name + "                 Возраст: " + age ;
                Names.add(superHeroInfo);
            }

        }catch (Exception e){
            Toast toast = Toast.makeText(this, "Такой ID уже есть, введите другой!", Toast.LENGTH_LONG);
            toast.show();
        }



        // Ages.add(age);
        //ID.add(id);

    }

    public void onClickShow(View view){
        if (Names.size()>0){
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, Names);
            listView.setAdapter(adapter);
        }
        else {
            Toast toast = Toast.makeText(this, "Список супергероев пуст", Toast.LENGTH_LONG);
            toast.show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        employeeDao.deleteAll();
    }
}