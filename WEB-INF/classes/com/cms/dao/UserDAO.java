package com.cms.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.cms.dbutility.DBConnection;
import com.cms.dto.Forum;
import com.cms.dto.Room;
import com.cms.dto.User;
import com.mysql.cj.jdbc.Blob;

public class UserDAO {
	public User checkUser(String table_name, String email, String password) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
	    User u = null;
		
		final String SELECT_QUERY = "select *from " + table_name + " where " + table_name.charAt(0) + "email = ? and " + table_name.charAt(0) + "password = ?";  
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
		    pst.setString(1, email);
		    pst.setString(2, password);
		    
		    rst = pst.executeQuery();
		    
		    if(rst.next()) {
		   	 u=new User();
			 u.setUserId(rst.getInt(1));
			 u.setFullName(rst.getString(2));
			 u.setEmail(rst.getString(4));
			 u.setPassword(rst.getString(3));
			 u.setTable_name(table_name);
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		  
	return u;
	}
	
	public ArrayList<Room> getInstructorCourses(int iid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
	    ArrayList<Room> roomList = new ArrayList<>();
	    Room r = null;
	    
	    final String SELECT_QUERY = "select *from room where iid = ?";
	    
	    try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
		    pst.setInt(1, iid);
		    
		    rst = pst.executeQuery();
		    
		    while(rst.next()) {
		    	r = new Room();
		    	r.setRid(rst.getInt(1));
		    	r.setRname(rst.getString(2));
		    	r.setRdesc(rst.getString(4));
		    	roomList.add(r);
		    }
			    
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
	    
		return roomList;
	}
	
	public ArrayList<Room> getStudentCourses(int sid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
	    ArrayList<Room> roomList = new ArrayList<>();
	    Room r = null;
	    
	    final String SELECT_QUERY = "select *from room where rid in (select rid from studentCourses where sid=?)";
	    
	    try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
		    pst.setInt(1, sid);
		    
		    rst = pst.executeQuery();
		    
		    while(rst.next()) {
		    	r = new Room();
		    	r.setRid(rst.getInt(1));
		    	r.setRname(rst.getString(2));
		    	r.setRdesc(rst.getString(4));
		    	roomList.add(r);
		    }
			    
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		return roomList;
	}
	
	public ArrayList<Forum> displayForum(int fid) {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
	    ArrayList<Forum> msgs = new ArrayList<>();
	    Forum f = null;
	    
	    final String SELECT_QUERY = "select *from forum where fid=?";
	    
	    try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
		    pst.setInt(1, fid);
		    
		    rst = pst.executeQuery();
		    
		    while(rst.next()) {
		    	f = new Forum();
		    	f.setFid(rst.getInt(1));
		    	if(rst.getBlob(2) != null) {
		    		f.setFileName((Blob) rst.getBlob(3));
		    	}
		    	
		    	f.setFName(rst.getString(5));
		    	f.setMsg(rst.getString(4));
		    	f.setUserId(rst.getInt(3));
		    	f.setUserType(rst.getString(6));
		    	msgs.add(f);
		    }
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	    return msgs;
	}
	
	public int newForumMessage(int fid, int userId, String newMsg, String FName, String userType) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		Forum f = null;
		
		final String INSERT_QUERY = "insert into forum(fid, userId, msg, FName, userType) values(?,?,?,?,?)";
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
		    pst.setInt(1, fid);	
		    pst.setInt(2, userId);
		    pst.setString(3, newMsg);
		    pst.setString(4, FName);
		    pst.setString(5, userType);
		    
		    rst = pst.executeUpdate();
		    

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 1;
		
	}
	
	public void uploadAssignment(InputStream inputStream, int userId, String fileName, int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		
		final String INSERT_QUERY = "insert into assignments (file, iid, fileName, rid) values (?, ?, ?, ?)";
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
			if (inputStream != null) {
            	pst.setBlob(1, inputStream);
            	pst.setInt(2,userId);
                pst.setString(3, fileName);
                pst.setInt(4,rid);
            }
			
			int row = pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> viewAssignments(int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
		ArrayList<String> fileNames = new ArrayList<String>();
		
		final String SELECT_QUERY = "select fileName from assignments where rid=?";
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setInt(1,rid);
			
		    rst = pst.executeQuery();
		    while(rst.next()) {
		    	fileNames.add(rst.getString(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		}
		return fileNames;
	}
	
	public String readBlob(String fileName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String data = "";
		
		final String SELECT_QUERY = "select file from assignments where fileName=?";
			
		try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setString(1,fileName);
			
		    rst = pst.executeQuery();
		    
		    while (rst.next()) {
		    	java.sql.Blob blob = rst.getBlob(1);	
		      //  InputStream input = rst.getBinaryStream(1);
		        data = new String(blob.getBytes(1l, (int) blob.length()));
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		}
		return data;
	}
	
	public ArrayList<Integer> getFileIds(int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;  
		ArrayList<Integer> fileIds = new ArrayList<Integer>();
		
		final String SELECT_QUERY = "select aid from assignments where rid=?";
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setInt(1,rid);
			
		    rst = pst.executeQuery();
		    while(rst.next()) {
		    	fileIds.add(rst.getInt(1));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		}
		return fileIds;
	}
	
	public void studentSubmit(InputStream inputStream, int userId, int aid, int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		
		final String INSERT_QUERY = "insert into studentSubmissions (sid, aid, file, rid) values (?, ?, ?, ?)";
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
			if (inputStream != null) {
            	pst.setInt(1, userId);
            	pst.setInt(2, aid);
                pst.setBlob(3, inputStream);
                pst.setInt(4,rid);
            }
			
			int row = pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> viewStudentUpload(int aid, int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		ArrayList<String> students = new ArrayList<String>();
		
		final String SELECT_QUERY = "select sname from student where sid in (select sid from studentSubmissions where aid=? and rid=?)";
			
		try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setInt(1,aid);
			pst.setInt(2, rid);
			
		    rst = pst.executeQuery();
		    
		    while (rst.next()) {
		    	students.add(rst.getString(1));
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		}
		return students;
	}
	
	public String readStudentBlob(int rid, int aid, String studentName) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rst = null;
		String data = "";
		
		final String SELECT_QUERY = "select file from studentSubmissions where rid=? and aid=? and sid in (select sid from student where sname=?)";
			
		try {	
			con = DBConnection.getConnection();
			pst = con.prepareStatement(SELECT_QUERY);
			pst.setInt(1,rid);
			pst.setInt(2,aid);
			pst.setString(3, studentName);
			
		    rst = pst.executeQuery();
		    
		    while (rst.next()) {
		    	java.sql.Blob blob = rst.getBlob(1);	
		        data = new String(blob.getBytes(1l, (int) blob.length()));
		    }
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e) {
		e.printStackTrace();
		}
		return data;
	}
	
	public void newStudent(String sname, String spassword, String semail) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		Forum f = null;
		
		final String INSERT_QUERY = "insert into student(sname, spassword, semail) values(?,?,?)";
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
		    pst.setString(1, sname);	
		    pst.setString(2, spassword);
		    pst.setString(3, semail);
		    
		    rst = pst.executeUpdate();
		    

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	public void newInstructor(String iname, String ipassword, String iemail) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		Forum f = null;
		
		final String INSERT_QUERY = "insert into instructor(iname, ipassword, iemail) values(?,?,?)";
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
		    pst.setString(1, iname);	
		    pst.setString(2, ipassword);
		    pst.setString(3, iemail);
		    
		    rst = pst.executeUpdate();
		    

		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public int deleteStudent(int userId) {
		Connection con = null;
		PreparedStatement pst1 = null;
		PreparedStatement pst2 = null;
		int rst = 0;
		
		final String DELETE_QUERY1 = "delete from studentCourses where sid=?";
		final String DELETE_QUERY2 = "delete from student where sid=?";
		
		try {
			con = DBConnection.getConnection();
			pst1 = con.prepareStatement(DELETE_QUERY1);
			pst1.setInt(1, userId);
			rst=pst1.executeUpdate();
			
			pst2 = con.prepareStatement(DELETE_QUERY2);
			pst2.setInt(1, userId);
			rst=pst2.executeUpdate();
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	public int deleteInstructor(int userId) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		
		final String DELETE_QUERY = "delete from instructor where iid=?";
		
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement(DELETE_QUERY);
			
			pst.setInt(1, userId);
			rst=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	public int deleteRoom(int userId) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		
		final String DELETE_QUERY = "delete from room where rid=?";
		
		try {
			con = DBConnection.getConnection();
			pst = con.prepareStatement(DELETE_QUERY);
			
			pst.setInt(1, userId);
			rst=pst.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	public void newRoom(String rname, int iid, String rdesc) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		Forum f = null;
		
		final String INSERT_QUERY = "insert into room(rname, iid, rdesc) values(?,?,?)";
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);
		    pst.setString(1, rname);	
		    pst.setInt(2, iid);
		    pst.setString(3, rdesc);
		    
		    rst = pst.executeUpdate();
		    

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void studentEnroll(int sid, int rid) {
		Connection con = null;
		PreparedStatement pst = null;
		int rst = 0;
		Forum f = null;
		
		final String INSERT_QUERY = "insert into studentCourses(sid, rid) values(?,?)";
		
		try {
			
			con = DBConnection.getConnection();
			pst = con.prepareStatement(INSERT_QUERY);	
		    pst.setInt(1, sid);
		    pst.setInt(2, rid);
		    
		    rst = pst.executeUpdate();
		    

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

