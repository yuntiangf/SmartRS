package shengzhe.haiyan.smartrs.habitusdetection;

import android.provider.BaseColumns;

public final class DDatabase {

	private DDatabase() {

	}

	public static final class Detection implements BaseColumns {
		private Detection() {

		}
			
			public static final String Detection_TABLE_NAME="detection";
			public static final String Detection_ID="detection_id";
			public static final String Detection_QUESTION="detection_question";
			public static final String Detection_TYPE="detection_type";
			public static final String Detection_HABITUS="detection_habitus";
			public static final String Detection_CHOOSE="detection_choose";
	}

	public static final class Habitus implements BaseColumns {
		private Habitus() {

		}

		public static final String HABITUS_TABLE_NAME = "table_habitus";
		public static final String HABITUS_ID = "habitus_id";
		public static final String HABITUS_TRAIT = "habitus_trait";
		public static final String HABITUS_FOOD = "habitus_food";
		public static final String HABITUS_AVOID = "habitus_avoid";
		public static final String HABITUS_SPORT = "habitus_sport";
	}
}
