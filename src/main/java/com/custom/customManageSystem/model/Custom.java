package com.custom.customManageSystem.model;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class Custom {
    private Integer customid;

    private String licenseplates;

    private String idcard;

    private String agencycode;

    private String phonenum;

    private String carowner;

    private String insurer;

    private String insured;

    private String carmodel;

    private String carframecode;

    private String enginecode;

    @JSONField(format="yyyy/MM/dd")
    private Date firsttime;
    @JSONField(format="yyyy/MM/dd")
    private Date starttime;
    @JSONField(format="yyyy/MM/dd")
    private Date endtime;

    private String insurance;

    private String insurancecode;

    private String cardamage;

    private String robbery;

    private String three20;

    private String three30;
    
    private String three50;

    private String three100;

    private String three150;

    private String driver;

    private String passenger;

    private String foreignglass;

    private String domesticglass;

    private String nick2;

    private String nick5;

    private String nick10;

    private String nick15;

    private String autoignition;

    private String wading;
    
    private String remark;

    public Integer getCustomid() {
        return customid;
    }

    public void setCustomid(Integer customid) {
        this.customid = customid;
    }

    public String getLicenseplates() {
        return licenseplates;
    }

    public void setLicenseplates(String licenseplates) {
        this.licenseplates = licenseplates == null ? null : licenseplates.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getAgencycode() {
        return agencycode;
    }

    public void setAgencycode(String agencycode) {
        this.agencycode = agencycode == null ? null : agencycode.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? null : phonenum.trim();
    }

    public String getCarowner() {
        return carowner;
    }

    public void setCarowner(String carowner) {
        this.carowner = carowner == null ? null : carowner.trim();
    }

    public String getInsurer() {
        return insurer;
    }

    public void setInsurer(String insurer) {
        this.insurer = insurer == null ? null : insurer.trim();
    }

    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured == null ? null : insured.trim();
    }

    public String getCarmodel() {
        return carmodel;
    }

    public void setCarmodel(String carmodel) {
        this.carmodel = carmodel == null ? null : carmodel.trim();
    }

    public String getCarframecode() {
        return carframecode;
    }

    public void setCarframecode(String carframecode) {
        this.carframecode = carframecode == null ? null : carframecode.trim();
    }

    public String getEnginecode() {
        return enginecode;
    }

    public void setEnginecode(String enginecode) {
        this.enginecode = enginecode == null ? null : enginecode.trim();
    }

    public Date getFirsttime() {
		return firsttime;
	}

	public void setFirsttime(Date firsttime) {
		this.firsttime = firsttime;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance == null ? null : insurance.trim();
    }

    public String getInsurancecode() {
        return insurancecode;
    }

    public void setInsurancecode(String insurancecode) {
        this.insurancecode = insurancecode == null ? null : insurancecode.trim();
    }

    public String getCardamage() {
        return cardamage;
    }

    public void setCardamage(String cardamage) {
        this.cardamage = cardamage == null ? null : cardamage.trim();
    }

    public String getRobbery() {
        return robbery;
    }

    public void setRobbery(String robbery) {
        this.robbery = robbery == null ? null : robbery.trim();
    }

    public String getThree20() {
        return three20;
    }

    public void setThree20(String three20) {
        this.three20 = three20 == null ? null : three20.trim();
    }

    public String getThree30() {
		return three30;
	}

	public void setThree30(String three30) {
		this.three30 = three30;
	}

	public String getThree50() {
        return three50;
    }

    public void setThree50(String three50) {
        this.three50 = three50 == null ? null : three50.trim();
    }

    public String getThree100() {
        return three100;
    }

    public void setThree100(String three100) {
        this.three100 = three100 == null ? null : three100.trim();
    }

    public String getThree150() {
        return three150;
    }

    public void setThree150(String three150) {
        this.three150 = three150 == null ? null : three150.trim();
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver == null ? null : driver.trim();
    }

    public String getPassenger() {
        return passenger;
    }

    public void setPassenger(String passenger) {
        this.passenger = passenger == null ? null : passenger.trim();
    }

    public String getForeignglass() {
        return foreignglass;
    }

    public void setForeignglass(String foreignglass) {
        this.foreignglass = foreignglass == null ? null : foreignglass.trim();
    }

    public String getDomesticglass() {
        return domesticglass;
    }

    public void setDomesticglass(String domesticglass) {
        this.domesticglass = domesticglass == null ? null : domesticglass.trim();
    }

    public String getNick2() {
        return nick2;
    }

    public void setNick2(String nick2) {
        this.nick2 = nick2 == null ? null : nick2.trim();
    }

    public String getNick5() {
        return nick5;
    }

    public void setNick5(String nick5) {
        this.nick5 = nick5 == null ? null : nick5.trim();
    }

    public String getNick10() {
        return nick10;
    }

    public void setNick10(String nick10) {
        this.nick10 = nick10 == null ? null : nick10.trim();
    }

    public String getNick15() {
        return nick15;
    }

    public void setNick15(String nick15) {
        this.nick15 = nick15 == null ? null : nick15.trim();
    }

    public String getAutoignition() {
        return autoignition;
    }

    public void setAutoignition(String autoignition) {
        this.autoignition = autoignition == null ? null : autoignition.trim();
    }

    public String getWading() {
        return wading;
    }

    public void setWading(String wading) {
        this.wading = wading == null ? null : wading.trim();
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}