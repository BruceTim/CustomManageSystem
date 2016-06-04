package com.custom.customManageSystem.model;

public class User {
    private Integer uid;

    private String uname;

    private String upwd;

    private Integer role;
    
    private String lasttime;
    
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
    
    public String getLasttime() {
		return lasttime;
	}

	public void setLasttime(String lasttime) {
		this.lasttime = lasttime;
	}

	public User() {
    	
	}
    
    public User(String uname, String upwd){
    	this.uname = uname;
    	this.upwd = upwd;
    }
    
    public User(String uname, String upwd, int role){
    	this.uname = uname;
    	this.upwd = upwd;
    	this.role = role;
    }
}