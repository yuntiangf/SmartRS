package shengzhe.haiyan.smartrs.habitusdetection;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class DetectionActivity extends Activity {

	protected DDatabaseHelper mDatabase = null;
	protected Cursor mCursor = null;
	protected SQLiteDatabase mDB = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mDatabase = new DDatabaseHelper(this.getApplicationContext());
		mDB = mDatabase.getWritableDatabase();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (mDB != null) {
			mDB.close();
		}
		if (mDatabase != null) {
			mDatabase.close();
		}
	}
}
