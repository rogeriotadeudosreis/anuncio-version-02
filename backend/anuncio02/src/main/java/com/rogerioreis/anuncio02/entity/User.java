package com.rogerioreis.anuncio02.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;

/*
 *  A classe UserDetails é uma classe do java, 
 *  específica para conter detalhes de um usuário que será autenticado e autorizado
 */

@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	
	private String name = "";

	private String email = "";

	private String password = "";

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "tb_users_profiles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
	inverseJoinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"))
	private List<Profile> profiles = new ArrayList<>();

	private boolean active = false;

	@Column(name = "date_register")
	private LocalDateTime dtRegister;

	@Column(name = "date_update")
	private LocalDateTime dtRegisterUpdate;
	
	public User() {
		
	}

	public User(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.profiles = user.getProfiles();
		this.active = user.isActive();
		this.dtRegister = user.getDtRegister();
		this.dtRegisterUpdate = user.getDtRegisterUpdate();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.profiles;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public List<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(List<Profile> profiles) {
		this.profiles = profiles;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public LocalDateTime getDtRegister() {
		return dtRegister;
	}

	public void setDtRegister(LocalDateTime dtRegister) {
		this.dtRegister = dtRegister;
	}

	public LocalDateTime getDtRegisterUpdate() {
		return dtRegisterUpdate;
	}

	public void setDtRegisterUpdate(LocalDateTime dtRegisterUpdate) {
		this.dtRegisterUpdate = dtRegisterUpdate;
	}

}
