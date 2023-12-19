package service;


import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Viewer;


public  class ViewerService implements IDao<Viewer>{

	@Override
	public Viewer getById(int id) {
		Viewer viewer = null;
		
		System.out.println("Hello");
		
		String selectSqlString = "select * from viewer where viewer_id=?";
		
		try {
			ConnectionSingletone.getConnection();
			PreparedStatement selectViewerWithId = ConnectionSingletone.getConnection().prepareStatement(selectSqlString);
			selectViewerWithId.setInt(1, id);
			ResultSet rs = selectViewerWithId.executeQuery();
			
			while(rs.next()) {
				viewer = new Viewer();
				viewer.setViewerId(rs.getInt("viewer_id"));
				viewer.setFirstname(rs.getString("firstname"));
				viewer.setLastname(rs.getString("lastname"));
				viewer.setBirthdate(rs.getDate("birthdate").toLocalDate());
				viewer.setGender(rs.getString("gender"));
				viewer.setEmail(rs.getString("email"));
				viewer.setPassword(rs.getString("password"));
				viewer.setRetriever(rs.getString("retriever"));
				viewer.setCountry(rs.getString("country"));
				viewer.setOccupationId(rs.getInt("occupation"));
				 OccupationService occupationServ = new OccupationService();
				// get the occupation object based on id 
//				int occupationId = rs.getInt("occupation");
////				System.out.print(occupationServ.getById(occupationId));
//	            viewer.setOccupation(occupationServ.getById(occupationId));
			}
			ConnectionSingletone.closeConnection();
				
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return viewer;
	}
	@Override
	public int addNewRecord(Viewer newRecord) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int occupationExists = 0;
		int status = 0;
		
		String inserSqlString = "insert into viewer(firstname,lastname, birthdate, gender, email, password, retriever, country, occupation)" + "Values(?,?, ?, ?, ?, ?, ?, ?,?)";
		
		try {
			 ConnectionSingletone.getConnection();
			PreparedStatement insertNewRecord =  ConnectionSingletone.getConnection().prepareStatement(inserSqlString, Statement.RETURN_GENERATED_KEYS);
			insertNewRecord.setString(1, newRecord.getFirstname());
			insertNewRecord.setString(2, newRecord.getLastname());
			insertNewRecord.setDate(3, Date.valueOf(dtf.format(newRecord.getBirthdate())));
			insertNewRecord.setString(4, newRecord.getGender());
			insertNewRecord.setString(5, newRecord.getEmail());
			insertNewRecord.setString(6, newRecord.getPassword());
			insertNewRecord.setString(7, newRecord.getRetriever());
			insertNewRecord.setString(8, newRecord.getCountry());
			insertNewRecord.setInt(9, newRecord.getOccupationId());
			
			status = insertNewRecord.executeUpdate();
			 OccupationService occupationServ = new OccupationService();
			// this variable is checking if this occupation exists in the occupation table then add new record
			if(occupationServ.getById(newRecord.getOccupationId()) != null && status > 0) {
				
				occupationExists = 1;
				ResultSet rs = insertNewRecord.getGeneratedKeys();
	            
				 rs.next();
				 newRecord.setViewerId(rs.getInt(1));
				 newRecord.occupation = occupationServ.getById(newRecord.getOccupationId());
			}
            
			ConnectionSingletone.closeConnection();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return occupationExists;
	}
	@Override
	public List<Viewer> updateRecord(Viewer newRecord) {
	       int result = 0;
	        try {
	
	            String updateSql  = "update viewer set email = ? where viewer_id= ?";
	        	PreparedStatement updateViewer = ConnectionSingletone.getConnection().prepareStatement(updateSql);
//	        	
//				updateViewer.setInt(2, viewerId);
//				updateViewer.setString(1, email);
				
	           result = updateViewer.executeUpdate();
	           ConnectionSingletone.closeConnection();	
	        } catch (SQLException e) {
	            System.out.println(e.getErrorCode());
	            e.printStackTrace();
	        }
	        return null;
	}
	@Override
	public int updateRecordSpecific(String newColumnData, int id) {
	     int result = 0;
	       try {
	           String updateSql  = "update viewer set email = ? where viewer_id= ?";
	       	PreparedStatement updateViewer = ConnectionSingletone.getConnection().prepareStatement(updateSql);
	       	
				updateViewer.setInt(2, id);
				updateViewer.setString(1, newColumnData);
				
	          result = updateViewer.executeUpdate();
	      	ConnectionSingletone.closeConnection();	
	       } catch (SQLException e) {
	           System.out.println(e.getErrorCode());
	           e.printStackTrace();
	       }
	       return result;
	}
	@Override
	public int deleteRecord(int id) {
	      int result = 0;
        try {
     
               String deleteSql  = "delete from viewer where viewer_id = ?";
               PreparedStatement deleteViewer = ConnectionSingletone.getConnection().prepareStatement(deleteSql);
               deleteViewer.setInt(1, id);
               result = deleteViewer.executeUpdate();
               ConnectionSingletone.closeConnection();	
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            e.printStackTrace();
            }
    return result;
	}
	@Override
	public List<Viewer> getAllRecords() {
		Viewer viewer;
		List<Viewer> viewerArray = new ArrayList<Viewer>();
		 OccupationService occupationServ = new OccupationService();
		
		String selectSqlString = "select * from viewer";
		
		try {
			 ConnectionSingletone.getConnection();
			PreparedStatement selectViewerWithId = ConnectionSingletone.getConnection().prepareStatement(selectSqlString);
			ResultSet rs = selectViewerWithId.executeQuery();
			
			while(rs.next()) {
				viewer = new Viewer();
				viewer.setViewerId(rs.getInt("viewer_id"));
				viewer.setFirstname(rs.getString("firstname"));
				viewer.setLastname(rs.getString("lastname"));
				viewer.setBirthdate(rs.getDate("birthdate").toLocalDate());
				viewer.setGender(rs.getString("gender"));
				viewer.setEmail(rs.getString("email"));
				viewer.setPassword(rs.getString("password"));
				viewer.setRetriever(rs.getString("retriever"));
				viewer.setCountry(rs.getString("country"));
				viewer.setOccupationId(rs.getInt("occupation"));
//	            viewer.setOccupation(occupationServ.getById(rs.getInt("occupation")));
	            
	            viewerArray.add(viewer);
			
			}
			ConnectionSingletone.closeConnection();			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return viewerArray;
	}
	
}
	

	
	
	

