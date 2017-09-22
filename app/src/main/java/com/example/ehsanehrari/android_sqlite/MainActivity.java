package com.example.ehsanehrari.android_sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MySQLiteHelper db = new MySQLiteHelper(this);


        db.addEmployee(new Employee("Bagher", "Hazara"));
        db.addEmployee(new Employee("Ehsan", "Ehrari"));
        db.addEmployee(new Employee("Bilal", "Raouf"));
        db.addEmployee(new Employee("Anil", "Khan"));

        List<Employee> list = db.getAllEmployee();

        db.deleteEmployee(list.get(0));

        db.getAllEmployee();

    }
}


