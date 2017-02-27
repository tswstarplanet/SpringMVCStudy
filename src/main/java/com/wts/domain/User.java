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
    private Long userid;

	@Column(name = "username")
    private String username;

	@Column(name = "password")
    private String password;

	@Transient
	private String passwordConfirm;

	@Column(name = "email")
    private String email;

	@Column(name = "online_status")
	private int onlineStatus;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Spittle> spittles;

	@OneToMany(targetEntity = Friend.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Friend> friends;

	@OneToMany(targetEntity = Friend.class, mappedBy = "friend", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Friend> companies;

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
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
	        this.userid = user.userid;
	        this.username = user.username;
	        this.email = user.email;       
	        this.password = user.password;
	        this.passwordConfirm = user.passwordConfirm;
	}

	public int getOnlineStatus() {
		return onlineStatus;
	}

	public void setOnlineStatus(int onlineStatus) {
		this.onlineStatus = onlineStatus;
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

	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	public Set<Friend> getCompanies() {
		return companies;
	}

	public void setCompanies(Set<Friend> companies) {
		this.companies = companies;
	}
}