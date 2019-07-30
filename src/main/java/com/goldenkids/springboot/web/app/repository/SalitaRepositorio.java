package com.goldenkids.springboot.web.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.goldenkids.springboot.web.app.models.Salita;

@Repository
public interface SalitaRepositorio extends JpaRepository<Salita, String>{

}
