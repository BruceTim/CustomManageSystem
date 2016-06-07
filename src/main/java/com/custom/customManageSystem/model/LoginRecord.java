package com.custom.customManageSystem.model;

import java.util.Date;

public class LoginRecord {
    private Integer recordid;

    private int uid;
    
    private String uname;
    
    private int urole;

    private Date logintime;

    private String ipaddress;

    public Integer getRecordid() {
        return recordid;
    }

    public void setRecordid(Integer recordid) {
        this.recordid = recordid;
    }

    public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getUrole() {
		return urole;
	}

	public void setUrole(int urole) {
		this.urole = urole;
	}

	public Date getLogintime() {
		return logintime;
	}

	public void setLogintime(Date logintime) {
		this.logintime = logintime;
	}

	public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress == null ? null : ipaddress.trim();
    }
}