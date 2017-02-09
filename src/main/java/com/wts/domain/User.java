package com.wts.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)    
    @Column(name="userid")
    private Long userId;

	@Column(name = "username")
    private String username;

	@Column(name = "password")
    private String password;

	@Transient
	private String passwordConfirm;

	@Column(name = "email")
    private String email;
    
	@Column(name ="enabled")
	private int enabled;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Spittle> spittles;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<Spittle> getSpittles() {
		return spittles;
	}

	public void setSpittles(Set<Spittle> spittles) {
		this.spittles = spittles;
	}

	public User(){
		
	}
	
	public User(User user) {
	        this.userId = user.userId;
	        this.username = user.username;
	        this.email = user.email;       
	        this.password = user.password;
	        this.enabled = user.enabled;
	        this.passwordConfirm = user.passwordConfirm;
	}
	
	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}	

	public Long getUserid() {
		return userId;
	}

	public void setUserid(Long userid) {
		this.userId = userid;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
}