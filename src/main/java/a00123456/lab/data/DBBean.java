package a00123456.lab.data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

import javax.sql.DataSource;

public class DBBean {
	private String queryString = "empty";
	private PreparedStatement statement = null;
	private ResultSet queryResults = null;
	private ResultSetMetaData meta = null;
	private Vector<Vector<String>> vRows = null;
	
	private DataSource ds;
	
	
	public DBBean(DataSource ds ) {
		this.ds = ds;
	}


	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String qs) {

		queryString = qs;
	}

	public void generateResultSet() throws SQLException {
		vRows = new Vector<Vector<String>>();
		int numCols;

		statement = ds.getConnection().prepareStatement(queryString);

		queryResults = statement.executeQuery(queryString);
		meta = queryResults.getMetaData();
		numCols = meta.getColumnCount();

		while (queryResults.next()) {
			Vector<String> vOneRow = new Vector<String>();
			for (int ndx = 1; ndx <= numCols; ndx++) {
				vOneRow.addElement(queryResults.getString(ndx));
			}
			vRows.addElement(vOneRow);
		}

	}

	public void setResultSet(ResultSet rs) {
		queryResults = rs;
	}

	public Vector<Vector<String>> getResults() {

		return vRows;
	}

	public Vector<String> getColumnNames() throws SQLException {

		Vector<String> columnNames = new Vector<String>();
		try {
			for (int i = 0; i < meta.getColumnCount(); i++) {
				columnNames.add(meta.getColumnName(i + 1));
			}
		} catch ( SQLException e ){
			e.printStackTrace();
		}
		return columnNames;
	}

	public void cleanUp() {
		try {
			ds.getConnection().close();
			statement.close();
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
		}
	}

}
