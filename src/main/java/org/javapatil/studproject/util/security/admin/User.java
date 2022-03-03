/**
 * *********************************************
 * Date         Name                         
 * *********************************************
 * 11-Dec-2019   Sachin Patil aka JavaPatil  
 */

package org.javapatil.studproject.util.security.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class User {

//    private String userName;
//    private String password;
//    private String userType;
//    private String status;
//    private Long orgId;
//    private String orgName;
//    private String name;
//
//    public User(String userName, String password, String userType, String status, Long orgId,String orgName,String name) {
//        this.userName = userName;
//        this.password = password;
//        this.userType = userType;
//        this.status = status;
//        this.orgId=orgId;
//        this.orgName=orgName;
//        this.name = name;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public String getUserType() {
//        return userType;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public Long getOrgId() {
//        return orgId;
//    }
//
//    public String getOrgName() {
//        return orgName;
//    }
//
//    public String getName() {
//        return name;
//    }   
	
	private String userName;
	private int organizationId;
	private String organizationName;
	private String pass;
	private String status;
	private String emailId;
	private String mobileNo;
	private String roleName;
	
	public String toString()
	{
		return this.userName + " " + this.organizationId + " " + this.organizationName + " " + this.pass + " " + this.status + " " + this.emailId + " " + this.mobileNo + " " + this.roleName;
	}
}
