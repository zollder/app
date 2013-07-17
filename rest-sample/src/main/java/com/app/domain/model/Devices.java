package com.app.domain.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.app.web.utils.Documentation;



//--------------------------------------------------------------------------------------------------------------------------------
/** Abstract base class for entities that require implementing PrimaryKey and Version (coming soon) support. */
//--------------------------------------------------------------------------------------------------------------------------------
@Entity
@Table(name = "devices")
public class Devices implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
	@Basic
	@Column(name = "dev_ip")
	@NotNull
	@Documentation(caption = "dev_ip", comment = "IP number")
    private char dev_ip;
    
    @Id
	@Basic
	@Column(name = "dev_id")
	@Documentation(caption = "dev_id", comment = "identification code.")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int dev_id;
    
    private char mac;

    //@Column(name = "ID", nullable = false)
   // @Column(name = "FIRST_NAME", nullable = false, length = 50)
    
    
    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }
}