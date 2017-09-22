package com.example.ehsanehrari.android_sqlite;

import java.util.LinkedList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmployeeDB";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_Employee_TABLE = "CREATE TABLE employees ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT, "+
                "lastname TEXT )";

        db.execSQL(CREATE_Employee_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS employees");

        this.onCreate(db);
    }


    private static final String TABLE_EMPLOYEES = "employees";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_LASTNAME = "lastname";

    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_LASTNAME};

    public void addEmployee(Employee employee){
        Log.d("addEmployee", employee.toString());
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, employee.getName());
        values.put(KEY_LASTNAME, employee.getLastname());

        db.insert(TABLE_EMPLOYEES,
                null,
                values);
        db.close();
    }

    public Employee getEmployee(int id){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =
                db.query(TABLE_EMPLOYEES,
                        COLUMNS,
                        " id = ?",
                        new String[] { String.valueOf(id) },
                        null,
                        null,
                        null,
                        null);

        if (cursor != null)
            cursor.moveToFirst();

        Employee employee = new Employee();
        employee.setId(Integer.parseInt(cursor.getString(0)));
        employee.setName(cursor.getString(1));
        employee.setLastname(cursor.getString(2));

        Log.d("getEmployee("+id+")", employee.toString());

        return employee;
    }

    public List<Employee> getAllEmployee() {
        List<Employee> employees = new LinkedList<Employee>();

        String query = "SELECT  * FROM " + TABLE_EMPLOYEES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Employee employee = null;
        if (cursor.moveToFirst()) {
            do {
                employee = new Employee();
                employee.setId(Integer.parseInt(cursor.getString(0)));
                employee.setName(cursor.getString(1));
                employee.setLastname(cursor.getString(2));

                employees.add(employee);
            } while (cursor.moveToNext());
        }

        Log.d("getAllEmployee()", employees.toString());

        return employees;
    }

    public int updateEmployee(Employee employee) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", employee.getName());
        values.put("lastname", employee.getLastname());

        int i = db.update(TABLE_EMPLOYEES,
                values,
                KEY_ID+" = ?",
                new String[] { String.valueOf(employee.getId()) });

        db.close();

        return i;

    }

    public void deleteEmployee(Employee employee) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_EMPLOYEES,
                KEY_ID+" = ?",
                new String[] { String.valueOf(employee.getId()) });

        db.close();

        Log.d("deleteEmployee", employee.toString());

    }
}


