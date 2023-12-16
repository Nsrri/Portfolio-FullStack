package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import model.Occupation;
import model.Viewer;


public  class ViewerService implements IViewer{
	
	public String DB_URL = "jdbc:mysql://localhost:3306/portfolio?user=nasrinjafari&password=Dela9090!";
    Connection con;
    
	  public ViewerService(){
	        try {
	        	// This is deprecated and needs to be corrected
	        	Class.forName("com.mysql.jdbc.Driver");
	            this.con = DriverManager.getConnection(this.DB_URL);
	    
	        }
	        catch (SQLException e) {
	            e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	    }
	@Override
	public Viewer getViewerById(int viewerId) {
		
		Viewer viewer = null;
		
		String selectSqlString = "select * from viewer where viewer_id=?";
		
		try {
			this.con = DriverManager.getConnection(DB_URL);
			PreparedStatement selectViewerWithId = con.prepareStatement(selectSqlString);
			selectViewerWithId.setInt(1, viewerId);
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
				
				// get the occupation object based on id 
				int occupationId = rs.getInt("occupation");
	            viewer.setOccupation(getOccupationById(occupationId));
			}
			con.close();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return viewer;
		
	}
	
	
    @Override
	public int createNewViewerAccount(Viewer viewer) {
    	
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		int occupationExists = 0;
		int status = 0;
		
		String inserSqlString = "insert into viewer(firstname,lastname, birthdate, gender, email, password, retriever, country, occupation)" + "Values(?,?, ?, ?, ?, ?, ?, ?,?)";
		
		try {
			this.con = DriverManager.getConnection(DB_URL);
			PreparedStatement insertNewRecord = con.prepareStatement(inserSqlString, Statement.RETURN_GENERATED_KEYS);
			insertNewRecord.setString(1, viewer.getFirstname());
			insertNewRecord.setString(2, viewer.getLastname());
			insertNewRecord.setDate(3, Date.valueOf(dtf.format(viewer.getBirthdate())));
			insertNewRecord.setString(4, viewer.getGender());
			insertNewRecord.setString(5, viewer.getEmail());
			insertNewRecord.setString(6, viewer.getPassword());
			insertNewRecord.setString(7, viewer.getRetriever());
			insertNewRecord.setString(8, viewer.getCountry());
			insertNewRecord.setInt(9, viewer.getOccupationId());
			
			status = insertNewRecord.executeUpdate();
			
			// this variable is checking if this occupation exists in the occupation table then add new record
			if(getOccupationById(viewer.getOccupationId()) != null && status > 0) {
				
				occupationExists = 1;
				ResultSet rs = insertNewRecord.getGeneratedKeys();
	            
				 rs.next();
				viewer.setViewerId(rs.getInt(1));
				viewer.occupation = getOccupationById(viewer.getOccupationId());
			}
            
			con.close();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return occupationExists;
		
	}
    @Override
    public int updateViewer(String email, int viewerId) {
        int result = 0;
        try {
            this.con = DriverManager.getConnection(DB_URL);
            String updateSql  = "update viewer set email = ? where viewer_id= ?";
        	PreparedStatement updateViewer = con.prepareStatement(updateSql);
        	
			updateViewer.setInt(2, viewerId);
			updateViewer.setString(1, email);
			
           result = updateViewer.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int updateViewerDynamic(Viewer view) {
        int result = 0;
        try {
            this.con = DriverManager.getConnection(DB_URL);
            String updateSql  = "update viewer set email = ? where viewer_id= ?";
        	PreparedStatement updateViewer = con.prepareStatement(updateSql);
//        	
//			updateViewer.setInt(2, viewerId);
//			updateViewer.setString(1, email);
			
           result = updateViewer.executeUpdate();
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
            e.printStackTrace();
        }
        return result;
    }
    
    @Override
    public int deleteViewer(int viewerId) {
        int result = 0;
        Statement stmt = null;
        try {
             this.con = DriverManager.getConnection(this.DB_URL);
               String deleteSql  = "delete from viewer where viewer_id = ?";
               PreparedStatement deleteViewer = con.prepareStatement(deleteSql);
               deleteViewer.setInt(1, viewerId);
               result = deleteViewer.executeUpdate();
                con.close();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
            e.printStackTrace();
            }
    return result;
    }
    
	@Override
	public List<Viewer> getViewerAll() {
		
		Viewer viewer;
		List<Viewer> viewerArray = new ArrayList<Viewer>();
		
		String selectSqlString = "select * from viewer";
		
		try {
			this.con = DriverManager.getConnection(DB_URL);
			PreparedStatement selectViewerWithId = con.prepareStatement(selectSqlString);
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
				
				// It will fetch the referenced occupation 
				int occupationId = rs.getInt("occupation");
	            viewer.setOccupation(getOccupationById(occupationId));
	            
	            viewerArray.add(viewer);
			
			}
			con.close();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return viewerArray;
		
	}
	@Override
	public Occupation getOccupationById(int occupationId) {
		Occupation occupation = null;
		
		String selectSqlString = "select * from occupation where occupation_id=?";
		
		try {
			this.con = DriverManager.getConnection(DB_URL);
			PreparedStatement selectOccupationWithId = con.prepareStatement(selectSqlString);
			selectOccupationWithId.setInt(1, occupationId);
			ResultSet rs = selectOccupationWithId.executeQuery();
			
			while(rs.next()) {
				occupation = new Occupation();
				occupation.setOccupation_id(rs.getInt("occupation_id"));
				occupation.setOccupation_name(rs.getString("occupation_name"));
				
			
			}
			con.close();		
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return (occupation != null) ? occupation : new Occupation();
	
	}
	

	

}
