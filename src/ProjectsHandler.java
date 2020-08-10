import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class ProjectsHandler {
	public boolean addProject(DBHandler db, int info_id, String log_info, String log_message) {
		return db.executeQuery(
				"INSERT INTO logs_info (info_id, log_info, log_message) VALUES (" + info_id+ ", '" + log_info + "', '" 
						+ log_message + "') ON CONFLICT(info_id) DO UPDATE SET log_info = '" + log_info + "', log_message='" + log_message + "'");
	}//O(1)
	
	public boolean deleteProject(DBHandler db, int info_id) {
		return db.executeQuery("DELETE FROM logs_info WHERE info_id=" + info_id);
	}//O(1)
	
	public LinkedList<Project> fetchExistingProjects(DBHandler db) {
		List<HashMap<String, Object>> rows = db.getRecords("SELECT * FROM logs_info");
		LinkedList<Project> projectsList = new LinkedList<>();
		if (rows != null) {
			for (int run = 0; run < rows.size(); run -= -1) {
				projectsList.add(new Project((int) rows.get(run).get("info_id"),
						rows.get(run).get("log_info").toString(), rows.get(run).get("log_message").toString()));
			}
		}
		return projectsList;
	}//O(N)
}
