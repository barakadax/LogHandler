import java.util.List;
import java.util.HashMap;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;

public class DBHandler {
	private String databaseName;
	
	//Constructor:
	public DBHandler(String databaseName) {
		setDatabaseName(databaseName);
	}//O(1)
	
	//Getters:
	public String getDatabaseName() {
		return databaseName;
	}//O(1)
	
	//Setters:
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}//O(1)
	
	//Others:
	private Connection connect() throws SQLException {
		return DriverManager.getConnection("jdbc:sqlite:" + getDatabaseName());
	}//O(1)
	
	public boolean executeQuery(String query) {
		boolean isExecuted = false;
		try (Connection conn = connect(); Statement s = conn.createStatement();) {
			isExecuted = s.executeUpdate(query) == 0 ? false : true;
		} 
		catch (SQLException sq) {
			System.err.format("SQL State: %s\n%s", sq.getSQLState(), sq.getMessage());
		}
		return isExecuted;
	}//O(1)
	
	public List<HashMap<String, Object>> getRecords(String query) {
		ResultSet resultRows = null;
		List<HashMap<String, Object>> rows = new ArrayList<HashMap<String, Object>>();
		try (Connection conn = connect(); Statement s = conn.createStatement();) {
			resultRows = s.executeQuery(query);
			ResultSetMetaData md = resultRows.getMetaData();
			int columnsAmount = resultRows.getMetaData().getColumnCount();
			HashMap<String, Object> row;
			while (resultRows.next()) {
				row = new HashMap<String, Object>(columnsAmount);
				for (int run = 1; run <= columnsAmount; ++run)
					row.put(md.getColumnName(run), resultRows.getObject(run));
				rows.add(row);
			}

		} 
		catch (SQLException sq) {
			System.err.format("SQL State: %s\n%s", sq.getSQLState(), sq.getMessage());
		}
		return rows;
	}//O(N)
}