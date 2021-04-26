package com.dnomaid.mqtt.client;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import com.dnomaid.mqtt.R;
import com.dnomaid.mqtt.global.ConnectionConstants;
import com.dnomaid.mqtt.global.Notify;

public class Persistence extends SQLiteOpenHelper implements BaseColumns {
  private Context context;
  public static final int DATABASE_VERSION = 1;
  public static final String DATABASE_NAME = "connection.db";
  public static final String TABLE_CONNECTION = "connection";
  public static final String COLUMN_HOST = "host";
  public static final String COLUMN_client_ID = "clientID";
  public static final String COLUMN_port = "port";
  public static final String COLUMN_CLEAN_SESSION = "cleanSession";

  private static final String TEXT_TYPE = " TEXT";
  private static final String INT_TYPE = " INTEGER";
  private static final String COMMA_SEP = ",";

  private static final String SQL_CREATE_ENTRIES =
          "CREATE TABLE " + TABLE_CONNECTION +
                  " (" +
                  _ID + " INTEGER PRIMARY KEY," +
                  COLUMN_HOST + TEXT_TYPE + COMMA_SEP +
                  COLUMN_client_ID + TEXT_TYPE + COMMA_SEP +
                  COLUMN_port + INT_TYPE + COMMA_SEP +
                  COLUMN_CLEAN_SESSION + " INTEGER" +
                  ");";

  private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_CONNECTION;
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
  public void persistConnection() {
    SQLiteDatabase db = getWritableDatabase();
    ContentValues values = new ContentValues();

    values.put(COLUMN_HOST, ConnectionConstants.getInst().getServer());
    values.put(COLUMN_port, ConnectionConstants.getInst().getPort());
    values.put(COLUMN_client_ID, ConnectionConstants.getInst().getClientId());
    values.put(COLUMN_CLEAN_SESSION, ConnectionConstants.getInst().isCleanSession() ? 1 : 0);

    db.delete(TABLE_CONNECTION, null, null);
    db.close();
  }
  public void restoreConnection(Context context) throws PersistenceException {
    Notify.toast(context,context.getString(R.string.restoreConnection));
    String[] connectionColumns = {
            COLUMN_HOST,
            COLUMN_port,
            COLUMN_client_ID,
            COLUMN_CLEAN_SESSION,
            _ID
    };
    String sort = COLUMN_HOST;
    SQLiteDatabase db = getReadableDatabase();
    Cursor c = db.query(TABLE_CONNECTION, connectionColumns, null, null, null, null, sort);
    for (int i = 0; i < c.getCount(); i++) {
      if (!c.moveToNext()) {
        Notify.toast(context,context.getString(R.string.failedPersistRestConn));
        throw new PersistenceException(context.getString(R.string.failedPersistConn) + " - count: " + c.getCount() + "loop iteration: " + i);
      }
      String host = c.getString(c.getColumnIndexOrThrow(COLUMN_HOST));
      ConnectionConstants.getInst().setServer(host);
      String clientID = c.getString(c.getColumnIndexOrThrow(COLUMN_client_ID));
      ConnectionConstants.getInst().setClientId(clientID);
      int port = c.getInt(c.getColumnIndexOrThrow(COLUMN_port));
      ConnectionConstants.getInst().setPort(port);
      boolean cleanSession = c.getInt(c.getColumnIndexOrThrow(COLUMN_CLEAN_SESSION)) == 1 ? true : false;
      ConnectionConstants.getInst().setCleanSession(cleanSession);
    }
    c.close();
    db.close();
  }
  public void deleteConnection(Connection connection) {
    Notify.toast(context,context.getString(R.string.deleteConnection));
    SQLiteDatabase db = getWritableDatabase();
    db.delete(TABLE_CONNECTION, _ID + "=?", new String[]{String.valueOf(connection.persistenceId())});
    db.close();
  }
}
