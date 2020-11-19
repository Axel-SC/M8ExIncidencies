package DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import DB.IncidenciaContract.*;
import static DB.IncidenciaContract.IncidenciaEntry.*;
import androidx.annotation.Nullable;

import com.example.exincidencies.Incidencia;

import java.util.ArrayList;



public class IncidenciaDBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "incidencies.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + TABLE_NAME + "(" +
            IncidenciaEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            IncidenciaEntry.COLUMN_NAME_TITLE + "TEXT, " +
            IncidenciaEntry.COLUMN_NAME_PRIORITY + " TEXT)";


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

    public void insertIncidencia(SQLiteDatabase db, Incidencia i){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Añado la incidencia y sus valores
            values.put(IncidenciaEntry.COLUMN_NAME_TITLE, i.getTittle());
            values.put(IncidenciaEntry.COLUMN_NAME_PRIORITY, i.getPrioritat());

            db.insert(TABLE_NAME, null, values);
        }else{
            Log.d("sql","Database is closed");
        }
    }
    public ArrayList<Incidencia> getAllIncidencias(SQLiteDatabase db){
        String query = "SELECT * FROM" + db;
        ArrayList<Incidencia> listIncidencies = new ArrayList();

        //Uso un cursor para seleccionar los registros
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        //Uso un iterador en el cursor para obtener todos los datos
        while(cursor.moveToNext()){
            String tittle = cursor.getString(cursor.getColumnIndex(IncidenciaEntry.COLUMN_NAME_TITLE));
            String priority = cursor.getString(cursor.getColumnIndex(IncidenciaEntry.COLUMN_NAME_PRIORITY));

            Incidencia incidencia = new Incidencia(tittle, priority);
            listIncidencies.add(incidencia);

        }
        cursor.close();
        return listIncidencies;
    } public void deleteEntries(SQLiteDatabase db){
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
