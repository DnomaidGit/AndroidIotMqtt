package com.dnomaid.mqtt.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.device.DeviceConfig;
import com.dnomaid.mqtt.global.Constants;
import com.dnomaid.mqtt.global.Notify;

import java.util.ArrayList;

public class DevicesPersistence extends SQLiteOpenHelper implements BaseColumns {
  private Context context;
  public static final int DATABASE_VERSION = 2;
  public static final String DATABASE_NAME = "devices.db";

  public static final String TABLE_DEVICE = "device";
  public static final String COLUMN_TYPEDEVICE = "typeDevice";
  public static final String COLUMN_NUMBERDEVICE = "numberDevice";
  public static final String COLUMN_ALIAS = "alias";

  private static final String TEXT_TYPE = " TEXT";
  private static final String COMMA_SEP = ",";

  private static final String SQL_CREATE_ENTRIES =
          "CREATE TABLE " + TABLE_DEVICE +
                  " (" +
                  _ID + " INTEGER PRIMARY KEY," +
                  COLUMN_TYPEDEVICE + TEXT_TYPE + COMMA_SEP +
                  COLUMN_NUMBERDEVICE + TEXT_TYPE + COMMA_SEP +
                  COLUMN_ALIAS + TEXT_TYPE +
                  ");";
  private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_DEVICE;
  public DevicesPersistence(Context context) {
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
    db.execSQL(SQL_DELETE_ENTRIES);
    onCreate(db);
  }
  @Override
  public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Notify.toast(context,context.getString(R.string.onDowngradeSQLiteDatabase));
    onUpgrade(db, oldVersion, newVersion);
  }
  public DeviceConfig newDevice(Constants.TypeDevice typeDevice, String numberDevice, String alias) throws PersistenceException {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();
    DeviceConfig deviceConfig = new DeviceConfig(typeDevice, numberDevice, alias);

    values.put(COLUMN_TYPEDEVICE, deviceConfig.getTypeDevice().toString());
    values.put(COLUMN_NUMBERDEVICE, deviceConfig.getNumberDevice());
    values.put(COLUMN_ALIAS, deviceConfig.getAlias());
    long newRowId = db.insert(TABLE_DEVICE, null, values);
    if (newRowId == -1) {
      Notify.toast(context,context.getString(R.string.failedPersistDev));
        throw new PersistenceException("Failed to persist ic_menu_device");
    }
    else {
      deviceConfig.assignPersistenceId(newRowId);
    }
    db.close();
    Notify.toast(context,context.getString(R.string.newDevice));
    return deviceConfig;
  }
  public ArrayList<DeviceConfig> restoreDevice(Context context) throws PersistenceException {
    Notify.toast(context,context.getString(R.string.restoreDevice));
    String[] deviceColumns = {
            COLUMN_TYPEDEVICE,
            COLUMN_NUMBERDEVICE,
            COLUMN_ALIAS,
            _ID
    };
    String sort = COLUMN_TYPEDEVICE;
    SQLiteDatabase db = getReadableDatabase();
    Cursor c = db.query(TABLE_DEVICE, deviceColumns, null, null, null, null, sort);
    ArrayList<DeviceConfig> list = new ArrayList<>(c.getCount());
    for (int i = 0; i < c.getCount(); i++) {
      if (!c.moveToNext()) {
        Notify.toast(context,context.getString(R.string.failedPersistRestDev));
        throw new PersistenceException("Failed restoring connection - count: " + c.getCount() + "loop iteration: " + i);
      }
      Long id = c.getLong(c.getColumnIndexOrThrow(_ID));
      String typeDevice = c.getString(c.getColumnIndexOrThrow(COLUMN_TYPEDEVICE));
      String numberDevice = c.getString(c.getColumnIndexOrThrow(COLUMN_NUMBERDEVICE));
      String alias = c.getString(c.getColumnIndexOrThrow(COLUMN_ALIAS));
      //store it in the list
      DeviceConfig deviceConfig = new DeviceConfig(Constants.TypeDevice.valueOf(typeDevice),numberDevice,alias);
      deviceConfig.assignPersistenceId(id);
      list.add(deviceConfig);
    }
    c.close();
    db.close();
    return list;
  }
  public void deleteDevice(DeviceConfig deviceConfig) {
    Notify.toast(context,context.getString(R.string.deleteDevice));
    SQLiteDatabase db = getWritableDatabase();
    db.delete(TABLE_DEVICE, _ID + "=?", new String[]{String.valueOf(deviceConfig.persistenceId())});
    db.close();
  }
}
