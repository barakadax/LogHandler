public class Project {
	private int idInfo;
	private String logInfo;
	private String logMsg;

	//constructor:
	public Project(int idInfo, String logInfo, String message) {
		setId(idInfo);
		setInfo(logInfo);
		setMessage(message);
	}//O(1)
	
	//getters:
	public int getId() {
		return idInfo;
	}//O(1)
	
	public String getInfo() {
		return logInfo;
	}//O(1)
	
	public String getMessage() {
		return logMsg;
	}//O(1)
	
	//setters:
	public void setId(int id) {
		this.idInfo = id;
	}//O(1)
	
	public void setInfo(String info) {
		this.logInfo = info;
	}//O(1)
	
	public void setMessage(String message) {
		this.logMsg = message;
	}//O(1)
	
	//others:
	public String toString() {
		return "Id: " + idInfo + ", Info: " + logInfo + ", Message: " + logMsg;
	}//O(1)
}
