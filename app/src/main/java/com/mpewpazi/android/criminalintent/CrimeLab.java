package com.mpewpazi.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mpewpazi.android.criminalintent.database.CrimeBaseHelper;
import com.mpewpazi.android.criminalintent.database.CrimeCursorWrapper;
import com.mpewpazi.android.criminalintent.database.CrimeDbSchema.CrimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by mpewpazi on 3/29/16.
 */
public class CrimeLab {

    private static CrimeLab sCrimeLab;


    private SQLiteDatabase mDatabase;
    private Context mContext;


    public static CrimeLab get(Context context){
        if(sCrimeLab==null){
            sCrimeLab=new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context){
        mContext=context.getApplicationContext();
        mDatabase=new CrimeBaseHelper(mContext)
                .getWritableDatabase();

    }

    public void addCrime(Crime c){
        ContentValues values=getContentValues(c);

        mDatabase.insert(CrimeTable.NAME,null,values);
    }


    public List<Crime> getCrimes(){
        List<Crime> crimes=new ArrayList<>();
        CrimeCursorWrapper cursor=queryCrimes(null,null);
        try{
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return crimes;
    }

    public Crime getCrime(UUID id){
        CrimeCursorWrapper cursor=queryCrimes(CrimeTable.Cols.UUID+ "=?",
                new String[] {id.toString()});
        try{
            if(cursor.getCount()==0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        }finally {
            cursor.close();
        }



    }

    public void updateCrime(Crime crime){
        String uuidString=crime.getId().toString();
        ContentValues values=getContentValues(crime);

        mDatabase.update(CrimeTable.NAME,values,CrimeTable.Cols.UUID+" = ?",new String[]{uuidString});
    }

    private static ContentValues getContentValues(Crime crime){
        ContentValues values=new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE,crime.getTitle());
        values.put(CrimeTable.Cols.DATE,crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());

        return values;
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs){

        Cursor cursor=mDatabase.query(
                CrimeTable.NAME,
                null, // select all the column
                whereClause,
                whereArgs,
                null, //group by
                null, //having
                null//order by
        );

        return new CrimeCursorWrapper(cursor);
    }


}
