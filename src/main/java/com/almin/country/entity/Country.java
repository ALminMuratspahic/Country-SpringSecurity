package com.almin.country.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String name;
	String capital;

	public Country() {

	}

	public Country(String name, String capita) {
		this.name = name;
		this.capital = capita;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

}
