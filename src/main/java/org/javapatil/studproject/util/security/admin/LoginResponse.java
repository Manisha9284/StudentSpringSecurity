/**
 * *********************************************
 * Date         Name                         
 * *********************************************
 * 11-Dec-2019   Sachin Patil aka JavaPatil  
 */

package org.javapatil.studproject.util.security.admin;

public class LoginResponse {

    private String accessToken;
    private String userType;
    //private String gymName;
    private String orgName;
    private String name;

    public LoginResponse(String accessToken, String userType ,String orgName,String name
    ) {
        this.accessToken = accessToken;
        this.userType = userType;
        this.setOrgName(orgName);
        this.name = name;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getUserType() {
        return userType;
    }

  
    public String getName() {
        return name;
    }

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
