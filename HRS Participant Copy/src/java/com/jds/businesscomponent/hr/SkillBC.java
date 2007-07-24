/*
 * Created on Jun 27, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

package com.jds.businesscomponent.hr;

import java.util.*;

import java.sql.*;
import javax.sql.*;

import com.jds.apps.*;
import com.jds.apps.beans.*;
import com.jds.architecture.*;
import com.jds.architecture.exceptions.*;
import com.jds.architecture.logging.*;
//import com.jds.architecture.service.dao.DataAccessObjectInterface;
//import com.jds.architecture.service.dao.stub.DataAccessObjectInterface;
import com.jds.architecture.service.dao.*;
//import com.jds.architecture.service.dao.DAOConstants;
//import com.jds.architecture.service.dao.DAOFactory;
//import com.jds.architecture.service.dao.stub.*;


//import com.jds.architecture.service.dao.EmpAccentureDetailsDAO;
//import com.jds.architecture.service.dao.*;
import com.jds.architecture.service.dao.assembler.*;
import com.jds.architecture.service.dbaccess.*;
import com.jds.architecture.service.idgenerator.*;
//import com.jds.architecture.service.dao.assembler.SkillAssembler;

/**
 * @author Olegz
 * 
 * This is the SkillBC class for the HRS application.
 * 
 */
public class SkillBC {

	private Constants cons;
	private DataAccessObjectInterface skillDao=null;
	private DataAccessObjectInterface catDao = null;
	private DBAccess dbAccess;
	private static Logger log = (Logger) ServiceFactory.getInstance()
	.getService(LoggerService.class);
	
	
	
	public SkillBC ()
	throws HRSSystemException
	{
		System.out.println("<entered SkillBC constructor>");
		
		try {
			// Initialize DAO objects.
			
			System.out.println("<First copnstructor>");
			skillDao = (SkillDAO) DAOFactory.getFactory()
				 .getDAOInstance(DAOConstants.DAO_SKILL);
			
			System.out.println("<Second one>");
			catDao = (SkillCategoryDAO)DAOFactory.getFactory()
					.getDAOInstance(DAOConstants.DAO_SKILLCAT);
			
			   dbAccess = DBAccess.getDBAccess();
			   cons = new Constants();

			   
	
		System.out.println("<Third one>");
		//dbAccess = new DBAccess();
		//cons = new Constants();
		
	   } catch (DBAccessException e) {
	   		throw new HRSSystemException ("intialize.dbaccess.exception",e.getCause());	
	   } catch (DAOException e) {
	   		e.printStackTrace();
	   		throw new HRSSystemException ("intialize.dao.exception",e.getCause());					
	   } catch (Exception e) {
	   		System.out.println("Catch " + e.getClass() + " e in SkillBC Constructor");
	   		e.printStackTrace();
	   		throw new HRSSystemException ("intialize.employee.bc.exception",e.getCause());
	   } 
		
	   System.out.println("<going out of SkillBC constructor>");
		
	} 
	
	public void createSkill (SkillsInformation info)
	throws HRSSystemException, HRSLogicalException,DAOException
	{
		log.info("entered createSkill method");
		
		String id = null;
		Object tempObj = null;
		
		if (info  == null) throw new HRSLogicalException ("invalid.input.exception");
		
		if (info.getSkillName() == null)
			throw new HRSLogicalException ("name.required.exception");
		
		Connection conn = null;					
		

		try {					
		   conn = dbAccess.getConnection();
		   
		   try {tempObj = catDao.findByPK(info.getCategoryId());}
		   //try {tempObj = skillDao.findByPK(info.getId());}
		   catch (Exception e){}
		   
		   
		   if ( tempObj == null) {
			throw new HRSLogicalException("skill.category.no.record.exception"); 
	   } else {
	       
	   	 if (!((SkillCategory)tempObj).getStatus().
	   	         equalsIgnoreCase(cons.getApproval("approved")))
			throw new HRSLogicalException("category.not.approved.exception");
	   }

		   //id = SkillIdGenerator.getInstance().getNextId();
		   //info.setSkillId(String.valueOf(id));
		   
		   id = String.valueOf(
				SkillIdGenerator.getInstance().getNextId());
		   info.setSkillId(id);
		   
		   try{
		   	   skillDao.create(conn, info);
			   RowSet set = skillDao.find(info);
		   }
		   catch (Exception e){
		   		throw new DAOException("", new Throwable());
		   }
		   
		   dbAccess.commitConnection(conn);
		   dbAccess.closeConnection(conn);
		} catch (IdGeneratorException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {}
			throw new HRSSystemException (e.getMessageKey(),e.getCause());			   
		} catch (DBAccessException e) {
			try {
				dbAccess.rollbackConnection(conn);
			} catch (DBAccessException e1) {}
			throw new HRSSystemException (e.getMessageKey(),e.getCause());			   
		} //catch (DAOException e) {
			//try {
				//dbAccess.rollbackConnection(conn);
	//		} 
	//catch (DBAccessException e1) {}
		//	if (e.isLogical()) throw new HRSLogicalException (e.getMessageKey()+".skill");
		//	else throw new HRSSystemException (e.getMessageKey(),e.getCause());
	//	}
	finally {
			try {
				dbAccess.closeConnection(conn);
			} catch (DBAccessException e1) {}  
		}	
		
		
		// Lets user know the method is being left
		log.info("exited createSkill method");

	} // End createSkill
	
	
	
	public SkillsInformation searchSkill (String id)
	throws HRSSystemException, HRSLogicalException { 
        
        log.info("entered searchSkill method");
            
        if (id == null) 
         throw new HRSLogicalException ("id.required.exception");

        SkillsInformation data = null;
        AccentureDetails accData = null;
        //Object temp = null;
        SkillCategory category = null;
    
        try{
            data = (SkillsInformation) skillDao.findByPK(id);
            
            if (data == null) 
                throw new HRSLogicalException ("skill.no.record.exception");

            //temp = empAccDao.findByPK(id);
                
            //if (temp == null) 
            //    throw new HRSLogicalException ("accenture.details.no.record.exception");
                
            //accData = (AccentureDetails)temp;
            //data.setAccentureDetails(accData);      
            category = (SkillCategory)catDao.findByPK(data.getCategoryId());
			data.setCategoryName(category.getCategoryName());

        } //catch (DAOException e) {
          //  throw new HRSSystemException (e.getMessageKey(),e.getCause());
    //    }
	catch (Exception e) {
            throw new HRSSystemException ("business.component.exception",e.getCause());
        }       
        
        log.info("exited searchSkill method");
        
        return data;
	}
	
	
	
	public Collection searchApprovedSkills (SkillsInformation dataFind)
	throws HRSSystemException, HRSLogicalException
	{
		log.info("entering searchApprovedSkill method");
		
		try
		{
			return searchReferenceData(dataFind, dataFind.getSkillId());
		}
		catch (Exception e)
		{
			throw new HRSLogicalException("");
		}
		
		//log.info("exiting searchApprovedSkill method");
		
		//log.info("exiting searchApprovedSkill method");
		//return null;
	}
	
	
	public Collection searchReferenceData (AbstractReferenceData dataFind, String approvalType)
	throws HRSSystemException, HRSLogicalException
	{
		ResultSet set = null;
		ArrayList list = new ArrayList();
		SkillsInformation skills = null;
		SkillCategory category = null;
		String status = null;

		try{
			if (dataFind == null) 
				set = skillDao.findByAll(); 
			else 
				set = skillDao.find(dataFind);;

			while (set.next()){
				//skills = SkillAssembler.getInfo(set);
				category = (SkillCategory)catDao.findByPK(skills.getCategoryId());
				skills.setCategoryName(category.getCategoryName());			
				status = skills.getStatus();
				
				if (approvalType.equalsIgnoreCase(cons.getApproval("All"))) {
					list.add(skills); 
					
				}else if (status.equalsIgnoreCase(cons.getApproval(approvalType))) {
					list.add(skills); 
				} 
			} 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new HRSSystemException ("sql.exception",e.getCause());
			
		} catch (DAOException e) {
			throw new HRSSystemException (e.getMessageKey(),e.getCause());
			
		} 
		
		catch (Exception e) {
			e.printStackTrace();
			throw new HRSSystemException ("business.component.exception",e.getCause());
		}
		return list;
	}
	
	public void updateSkill (SkillsInformation info)
	throws HRSSystemException, HRSLogicalException
	{
		log.info("Entering updateSkill method");
		
		if (info == null)
		{
			throw new HRSLogicalException ("invalid.input.exception");
		}
		
		else if (info.getId() == null)
		{
			throw new HRSLogicalException ("id.required.exception");
		}
			Connection conn = null;
			
			try
			{
				conn = dbAccess.getConnection();
				
				
				//String objSet = info.getCategoryName();
				//Object objWhere = info.getCategoryId();
				
				
				
				SkillsInformation data = (SkillsInformation) (skillDao.findByPK(info.getSkillId()));
				
				skillDao.update(conn, data, info);
				
				dbAccess.commitConnection(conn);
				dbAccess.closeConnection(conn);
				log.info("Skill Information Updated!");
			}
			
			catch (Exception e)
			{
				try {
					dbAccess.rollbackConnection(conn);
					dbAccess.closeConnection(conn);
				}
				catch (Exception e2)
				{
					throw new HRSLogicalException ("id.required.exception");
				}
			}
			
			finally {
				try {
					dbAccess.closeConnection(conn);
				} catch (DBAccessException e1) {}
				
			}
	}
	
}
