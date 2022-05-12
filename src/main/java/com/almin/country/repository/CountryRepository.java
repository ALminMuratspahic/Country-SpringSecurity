package com.almin.country.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.almin.country.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	@Query(value = "select c from Country c where " + "lower(c.name) like lower(concat('%',:keyword,'%')) "
			+ "or lower(c.capital) like lower(concat('%',:keyword,'%'))")
	public List<Country> filter(@Param("keyword") String keyword);

	@Query(value = "select c from Country c order by c.name ASC")
	List<Country> sortCountryASC();

	@Query(value = "select c from Country c order by c.name DESC")
	List<Country> sortCountryDESC();

}
