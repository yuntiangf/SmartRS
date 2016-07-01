package shengzhe.haiyan.smartrs.habitusdetection;

public class Tb_detection {
	private int detection_id;
	private String detection_question;
	private int detection_type;
	private String detection_habitus;
	private String detection_choose;
	
	public Tb_detection() {
		super();
	}

	public Tb_detection(int detection_id, String detection_question,
			int detection_type, String detection_habitus,
			String detection_choose) {
		super();
		this.detection_id = detection_id;
		this.detection_question = detection_question;
		this.detection_type = detection_type;
		this.detection_habitus = detection_habitus;
		this.detection_choose = detection_choose;
	}

	public int getDetection_id() {
		return detection_id;
	}

	public void setDetection_id(int detection_id) {
		this.detection_id = detection_id;
	}

	public String getDetection_question() {
		return detection_question;
	}

	public void setDetection_question(String detection_question) {
		this.detection_question = detection_question;
	}

	public int getDetection_type() {
		return detection_type;
	}

	public void setDetection_type(int detection_type) {
		this.detection_type = detection_type;
	}

	public String getDetection_habitus() {
		return detection_habitus;
	}

	public void setDetection_habitus(String detection_habitus) {
		this.detection_habitus = detection_habitus;
	}

	public String getDetection_choose() {
		return detection_choose;
	}

	public void setDetection_choose(String detection_choose) {
		this.detection_choose = detection_choose;
	}
	
}
