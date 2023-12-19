package service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Occupation;

public class OccupationService implements IDao<Occupation> {

	@Override
	public Occupation getById(int id) {
		Occupation occupation = null;
		
		String selectSqlString = "select * from occupation where occupation_id=?";
		
		try {
			 ConnectionSingletone.getConnection();
			PreparedStatement selectOccupationWithId = ConnectionSingletone.getConnection().prepareStatement(selectSqlString);
			selectOccupationWithId.setInt(1, id);
			ResultSet rs = selectOccupationWithId.executeQuery();
			
			while(rs.next()) {
				ViewerService viewerServ = new ViewerService();
				occupation = new Occupation();
				occupation.setOccupation_id(rs.getInt("occupation_id"));
				occupation.setOccupation_name(rs.getString("occupation_name"));
//				occupation.addViewer(viewerServ.getById(id));
		
		}
			ConnectionSingletone.closeConnection();		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return occupation;
	}

	@Override
	public int addNewRecord(Occupation newRecord) {
		int status = 0;
		
		String inserSqlString = "insert into occupation(occupation_name)" + "Values(?)";
		
		try {
			 ConnectionSingletone.getConnection();
			PreparedStatement insertNewRecord =  ConnectionSingletone.getConnection().prepareStatement(inserSqlString, Statement.RETURN_GENERATED_KEYS);
			insertNewRecord.setString(1, newRecord.getOccupation_name());
			
			status = insertNewRecord.executeUpdate();
				ResultSet rs = insertNewRecord.getGeneratedKeys();
	            
				 rs.next();
				 newRecord.setOccupation_id(rs.getInt(1));
			
            
			ConnectionSingletone.closeConnection();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public List<Occupation> updateRecord(Occupation newRecord) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateRecordSpecific(String newColumnData, int id) {
		   int result = 0;
	       try {
	           String updateSql  = "update occupation set occupation_name = ? where occupation_id= ?";
	       	PreparedStatement updateOccupation = ConnectionSingletone.getConnection().prepareStatement(updateSql);
	       	
	       	updateOccupation.setInt(2, id);
	       	updateOccupation.setString(1, newColumnData);
				
	          result = updateOccupation.executeUpdate();
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
	     
	               String deleteSql  = "delete from occupation where occupation_id = ?";
	               PreparedStatement deleteOccupation = ConnectionSingletone.getConnection().prepareStatement(deleteSql);
	               deleteOccupation.setInt(1, id);
	               result = deleteOccupation.executeUpdate();
	               ConnectionSingletone.closeConnection();	
	            } catch (SQLException e) {
	                System.out.println(e.getErrorCode());
	            e.printStackTrace();
	            }
	    return result;
	}

	@Override
	public List<Occupation> getAllRecords() {
		Occupation occupation = null;
		List<Occupation> occupationList = new ArrayList<Occupation>();
		
		String selectSqlString = "select * from occupation";
		
		try {
			PreparedStatement selectOccupationWithId = ConnectionSingletone.getConnection().prepareStatement(selectSqlString);
			ResultSet rs = selectOccupationWithId.executeQuery();
			
			while(rs.next()) {
				occupation = new Occupation();
				occupation.setOccupation_id(rs.getInt("occupation_id"));
				occupation.setOccupation_name(rs.getString("occupation_name"));
				occupationList.add(occupation);
			}
			ConnectionSingletone.closeConnection();			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return  occupationList;
	}
	

}
