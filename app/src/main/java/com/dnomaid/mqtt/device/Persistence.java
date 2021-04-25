package com.dnomaid.mqtt.device;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.global.Notify;

public class Persistence extends SQLiteOpenHelper implements BaseColumns {
  private Context context;
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "devices.db";

  public static final String TABLE_DEVICE = "device";
  public static final String COLUMN_TYPEDEVICE = "typeDevice";
  public static final String COLUMN_NUMBERDEVICE = "numberDevice";
  /** Table column for **/

  //sql lite data types
  private static final String TEXT_TYPE = " TEXT";
  //private static final String INT_TYPE = " INTEGER";
  private static final String COMMA_SEP = ",";

  /** Create tables query **/
  private static final String SQL_CREATE_ENTRIES =
          "CREATE TABLE " + TABLE_DEVICE +
                  " (" +
                  _ID + " INTEGER PRIMARY KEY," +
                  COLUMN_TYPEDEVICE + TEXT_TYPE + COMMA_SEP +
                  COLUMN_NUMBERDEVICE + TEXT_TYPE +
                  ");";

  /** Delete tables entry **/
  private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_DEVICE;
  public Persistence(Context context) {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    this.context = context;
  }
  @Override
  public void onCreate(SQLiteDatabase db) {
    Notify.toast(context,context.getString(R.string.onCreateSQLiteDatabase));
    db.execSQL(SQL_CREATE_ENTRIES); }
  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Notify.toast(context,context.getString(R.string.onUpgradeSQLiteDatabase));
    db.execSQL(SQL_DELETE_ENTRIES); }
  @Override
  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Notify.toast(context,context.getString(R.string.onDowngradeSQLiteDatabase));
    onUpgrade(db, oldVersion, newVersion);
  }
  public void persistDevice() throws PersistenceException {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    //put the column values object
    values.put(COLUMN_TYPEDEVICE, Devices.getInst().getDevicesConfig().get(0).getTypeDevice().toString());
    values.put(COLUMN_NUMBERDEVICE, Devices.getInst().getDevicesConfig().get(0).getNumberDevice());
    //delete
    db.delete(TABLE_DEVICE, null, null);
    //insert the values into the tables, returns the ID for the row
    long newRowId = db.insert(TABLE_DEVICE, null, values);
    db.close(); //close the db then deal with the result of the query
  }
  public void restoreDevice(Context context) throws PersistenceException {
    Notify.toast(context,context.getString(R.string.restoreDevice));
    //columns to return
    String[] deviceColumns = {
            COLUMN_TYPEDEVICE,
            COLUMN_NUMBERDEVICE,
            _ID
    };
    Devices.getInst().getDevicesConfig().clear();
    if(Devices.getInst().getDevicesConfig().isEmpty()) {
      //how to sort the data being returned
      String sort = COLUMN_TYPEDEVICE;
      SQLiteDatabase db = getReadableDatabase();
      Cursor c = db.query(TABLE_DEVICE, deviceColumns, null, null, null, null, sort);
      for (int i = 0; i < c.getCount(); i++) {
        if (!c.moveToNext()) { //move to the next item throw persistence exception, if it fails
          Notify.toast(context, context.getString(R.string.failedPersistRestDev));
          throw new PersistenceException(context.getString(R.string.failedPersistRestDev) + " - count: " + c.getCount() + "loop iteration: " + i);
        }
        //get data from cursor
        Long id = c.getLong(c.getColumnIndexOrThrow(_ID));
        //basic client information
        String typeDevice = c.getString(c.getColumnIndexOrThrow(COLUMN_TYPEDEVICE));
        String numberDevice = c.getString(c.getColumnIndexOrThrow(COLUMN_NUMBERDEVICE));
        Devices.getInst().newDevice(Constants.TypeDevice.valueOf(typeDevice), numberDevice);
        //get all values that need converting and convert integers to booleans in line using "condition ? trueValue : falseValue"
      }
      //close the cursor now we are finished with it
      c.close();
      db.close();
    }
  }
  public void deleteDevice(int number) {
    Notify.toast(context,context.getString(R.string.deleteDevice));
    SQLiteDatabase db = getWritableDatabase();
    db.delete(TABLE_DEVICE, _ID + "=?", new String[]{String.valueOf(number)});
    db.close();
  }
}
