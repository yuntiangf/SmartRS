package shengzhe.haiyan.smartrs.habitusdetection;

import shengzhe.haiyan.smartrs.habitusdetection.DDatabase.Detection;
import shengzhe.haiyan.smartrs.habitusdetection.DDatabase.Habitus;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DDatabaseHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "dection.db";
	private static final int DATABASE_VERSION = 1;

	public DDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + Detection.Detection_TABLE_NAME + " ("
				+ Detection.Detection_ID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Detection.Detection_QUESTION + " TEXT,"
				+ Detection.Detection_TYPE + " INTEGER,"
				+ Detection.Detection_HABITUS + " TEXT,"
				+ Detection.Detection_CHOOSE + " TEXT" + ");");

		db.execSQL("CREATE TABLE " + Habitus.HABITUS_TABLE_NAME + " ("
				+ Habitus.HABITUS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
				+ Habitus.HABITUS_TRAIT + " TEXT," + Habitus.HABITUS_FOOD
				+ " TEXT," + Habitus.HABITUS_AVOID + " TEXT,"
				+ Habitus.HABITUS_SPORT + " TEXT" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

}
