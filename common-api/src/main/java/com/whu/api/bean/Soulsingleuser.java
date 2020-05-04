package com.whu.api.bean;

import javax.persistence.Id;

public class Soulsingleuser {

    private Integer id;

    private String sex;

    private String soulname;

    private String nativeplace;

    private String year;

    private Integer age;

    private Double height;

    private Double weight;

    private String job;

    private String personality;

    private String hobby;

    private String loveexperience;

    private String idealpartner;

    private Double monthlysalary;

    private byte[] photo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getSoulname() {
        return soulname;
    }

    public void setSoulname(String soulname) {
        this.soulname = soulname == null ? null : soulname.trim();
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality == null ? null : personality.trim();
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby == null ? null : hobby.trim();
    }

    public String getLoveexperience() {
        return loveexperience;
    }

    public void setLoveexperience(String loveexperience) {
        this.loveexperience = loveexperience == null ? null : loveexperience.trim();
    }

    public String getIdealpartner() {
        return idealpartner;
    }

    public void setIdealpartner(String idealpartner) {
        this.idealpartner = idealpartner == null ? null : idealpartner.trim();
    }

    public Double getMonthlysalary() {
        return monthlysalary;
    }

    public void setMonthlysalary(Double monthlysalary) {
        this.monthlysalary = monthlysalary;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}