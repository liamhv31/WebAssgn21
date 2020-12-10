/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab4;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;
import javax.enterprise.inject.Instance;
import java.util.HashMap;
import javax.enterprise.inject.spi.CDI;

/**
 *
 * @author Liam
 */
@Entity
@Table(name = "APPUSER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appuser.findAll", query = "SELECT a FROM Appuser a"),
    @NamedQuery(name = "Appuser.findById", query = "SELECT a FROM Appuser a WHERE a.id = :id"),
    @NamedQuery(name = "Appuser.findByUserid", query = "SELECT a FROM Appuser a WHERE a.userid = :userid"),
    @NamedQuery(name = "Appuser.findByPassword", query = "SELECT a FROM Appuser a WHERE a.password = :password"),
    @NamedQuery(name = "Appuser.findByGroupname", query = "SELECT a FROM Appuser a WHERE a.groupname = :groupname"),
    @NamedQuery(name = "Appuser.findByBirthday", query = "SELECT a FROM Appuser a WHERE a.birthday = :birthday"),
    @NamedQuery(name = "Appuser.findByMobilephone", query = "SELECT a FROM Appuser a WHERE a.mobilephone = :mobilephone")})
public class Appuser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "USERID")
    private String userid;
    @Basic(optional = false)
    @NotNull
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "GROUPNAME")
    private String groupname;
    @Column(name = "BIRTHDAY")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Size(max = 10)
    @Column(name = "MOBILEPHONE")
    private String mobilephone;

    public Appuser() {
    }

    public Appuser(Long id) {
        this.id = id;
    }

    public Appuser(Long id, String userid, String password, String groupname) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.groupname = groupname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return "";
    }

    public void setPassword(String password) {
        // initialize a PasswordHash object which will generate password hashes
        Instance<? extends PasswordHash> instance = CDI.current().select(Pbkdf2PasswordHash.class);
        PasswordHash passwordHash = instance.get();
        passwordHash.initialize(new HashMap<String,String>());  // todo: are the defaults good enough?
        // now we can generate a password entry for a given password
        password = passwordHash.generate(password.toCharArray());
        //at this point, passwordEntry refers to a salted/hashed password entry corresponding to mySecretPassword

        this.password = password;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Appuser)) {
            return false;
        }
        Appuser other = (Appuser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.lab4.Appuser[ id=" + id + " ]";
    }
    
}
