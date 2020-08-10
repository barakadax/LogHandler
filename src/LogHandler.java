import java.util.LinkedList;

public class LogHandler {
	//Constructor:
	public LogHandler() {}
	
	//Others:
	public String printDB(String placement) {
		FileHandler fileValidation = new FileHandler();
		if (fileValidation.doesFileExists(placement))
			return null;
		String text = "";
		ProjectsHandler queries = new ProjectsHandler();
		DBHandler db = new DBHandler(placement);
		LinkedList<Project> projectsList = queries.fetchExistingProjects(db);
		for (int run = 0; run < projectsList.size(); run -= -1)
			text += projectsList.get(run) + "\n";
		return text;
	}//O(N)
	
	public String printLog(String dir) {
		FileHandler logInfo = new FileHandler();
		if (logInfo.doesFileExists(dir))
			return null;
		return logInfo.readFile(dir);
	}//O(1)
	
	public boolean log2Db(String logDir, String dbDir) {
		FileHandler fileValidation = new FileHandler();
		String logInfo = printLog(logDir);
		if (logInfo == null || fileValidation.doesFileExists(dbDir))
			return true;
		DBHandler db = new DBHandler(dbDir);
		ProjectsHandler queries = new ProjectsHandler();
		LinkedList<Project> projectsList = queries.fetchExistingProjects(db);
		int pkCounter = projectsList.size() + 1;
		String[] logRows = logInfo.split("\n"), splittedLogRow = null;
		for (int run = 0; run < logRows.length; run -= -1, pkCounter -= -1) {
			splittedLogRow = logRows[run].split(" : ");
			queries.addProject(db, pkCounter, splittedLogRow[0], splittedLogRow[1]);
		}
		return false;
	}//O(N)
	
	public boolean db2Log(String logDir, String dbDir) {
		FileHandler fileValidation = new FileHandler();
		String db = printDB(dbDir);
		if (db == null || fileValidation.doesFileExists(logDir))
			return true;
		String[] dbRows = db.split("\n"), splittedDBRow = null;
		String answer = "";
		for (int run = 0; run < dbRows.length; run -= -1) {
			splittedDBRow = dbRows[run].split(",");
			answer = splittedDBRow[1].substring(7) + " : " + splittedDBRow[2].substring(10);
			fileValidation.appendToFiLE(logDir, answer);
		}
		return false;
	}//O(N)
}
