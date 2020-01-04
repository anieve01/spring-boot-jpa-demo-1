package com.dayflash.zipcode.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
public class Zipcode {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer zipcode;
	
	private Integer totalPopulation;
	
	private BigDecimal medianAge;
	
	private Integer totalMales;
	
	private Integer totalFemales;
	
	private Integer totalHouseholds;
	
	private BigDecimal averageHouseholdSize;
	
	public Zipcode(Long id, Integer zipcode, Integer totalPopulation, BigDecimal medianAge, Integer totalMales,
			Integer totalFemales, Integer totalHouseholds, BigDecimal averageHouseholdSize) {
		super();
		this.id = id;
		this.zipcode = zipcode;
		this.totalPopulation = totalPopulation;
		this.medianAge = medianAge;
		this.totalMales = totalMales;
		this.totalFemales = totalFemales;
		this.totalHouseholds = totalHouseholds;
		this.averageHouseholdSize = averageHouseholdSize;
	}

	public Zipcode() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getZipcode() {
		return zipcode;
	}

	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}

	public Integer getTotalPopulation() {
		return totalPopulation;
	}

	public void setTotalPopulation(Integer totalPopulation) {
		this.totalPopulation = totalPopulation;
	}
	
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public BigDecimal getMedianAge() {
		return medianAge;
	}

	public void setMedianAge(BigDecimal medianAge) {
		this.medianAge = medianAge;
	}

	public Integer getTotalMales() {
		return totalMales;
	}

	public void setTotalMales(Integer totalMales) {
		this.totalMales = totalMales;
	}

	public Integer getTotalFemales() {
		return totalFemales;
	}

	public void setTotalFemales(Integer totalFemales) {
		this.totalFemales = totalFemales;
	}

	public Integer getTotalHouseholds() {
		return totalHouseholds;
	}

	public void setTotalHouseholds(Integer totalHouseholds) {
		this.totalHouseholds = totalHouseholds;
	}

	public BigDecimal getAverageHouseholdSize() {
		return averageHouseholdSize;
	}

	public void setAverageHouseholdSize(BigDecimal averageHouseholdSize) {
		this.averageHouseholdSize = averageHouseholdSize;
	}

}
