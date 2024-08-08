package com.app.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.Entities.Filter;
import com.app.Entities.Size;

public interface FilterDao extends JpaRepository<Filter, Long> {

	@Query("SELECT i.imgid FROM Filter i WHERE (:color IS NULL OR i.color = :color) AND (:size IS NULL OR i.size = :size)")
	List<Long> findByColorAndSize(@Param("color") String color, @Param("size") Size size);}