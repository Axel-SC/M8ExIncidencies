package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import static DB.IncidenciaContract.IncidenciaEntry.*;


public class IncidenciaDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE "
            + INC_TABLE_NAME + "("
            + INC_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + INC_NAME + " TEXT, "
            + INC_PRIORITY + " TEXT, "
            + INC_DESCRIPTION + " TEXT, "
            + INC_STATUS+ " TEXT, "
            + INC_DATE + " TEXT);";


    public IncidenciaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertIncidencia(SQLiteDatabase db, Incidence inc){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();
            //Añado la incidencia y sus valores

            values.put(INC_NAME, inc.getTitle());
            values.put(INC_PRIORITY, inc.getPriority());
            values.put(INC_DATE, inc.getUnixDate());
            values.put(INC_DESCRIPTION, inc.getDescription());
            values.put(INC_STATUS, inc.getStatus());


            db.insert(INC_TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
    }
    public ArrayList<Incidence> getAllIncidences(SQLiteDatabase db){
        db = this.getReadableDatabase();
        ArrayList<Incidence> listIncidences = new ArrayList();

        //Uso un cursor para seleccionar los registros

        //Uso un iterador en el cursor para obtener todos los datos

        Cursor cursor = db.rawQuery("SELECT * FROM " + INC_TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                Incidence inc = new Incidence(cursor.getString(1), cursor.getString(2) );
                inc.setDescription(cursor.getString(3));
                inc.setStatus(cursor.getString(4));
                inc.setID(cursor.getInt(0));
                inc.setUnixDate(Long.parseLong(cursor.getString(5)));

                Log.i("prova", "------------->" + cursor.getString(4));
                listIncidences.add(inc);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return listIncidences;
    }

    public void deleteEntries(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + INC_TABLE_NAME);
    }
    public void deleteSingleInc(SQLiteDatabase db, int incID){
        db.execSQL("DELETE FROM " + INC_TABLE_NAME+ " WHERE id = "+incID);
    }

    public void dropTable(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + INC_TABLE_NAME);
    }

    public static void updateColumn(SQLiteDatabase db, String column, String columnValue, int incID) {
        String sql = "UPDATE "+INC_TABLE_NAME+" SET "+column+" = '" + columnValue + "' WHERE id = "+incID;
        Log.i("prova", sql);

        if (db.isOpen()){

            db.execSQL(sql);

        }else{
            Log.i("prova","Database is closed");
        }

    }
}
