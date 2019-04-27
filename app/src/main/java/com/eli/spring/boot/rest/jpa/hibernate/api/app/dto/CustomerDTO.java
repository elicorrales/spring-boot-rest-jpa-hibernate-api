package com.eli.spring.boot.rest.jpa.hibernate.api.app.dto;

public class CustomerDTO {

    private int id;
    private String fname;
    private String lname;
    private String email;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFname() { return fname; }
    public void setFname(String fname) { this.fname = fname; }

    public String getLname() { return lname; }
    public void setLname(String lname) { this.lname = lname; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public CustomerDTO(int id, String fname, String lname, String email) {
        this.id = id; this.fname = fname; this.lname = lname; this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [email=" + email + ", fname=" + fname + ", id=" + id + ", lname=" + lname + "]";
    }

}