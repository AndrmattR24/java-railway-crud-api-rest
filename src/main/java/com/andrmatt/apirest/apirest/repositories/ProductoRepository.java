package com.andrmatt.apirest.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.andrmatt.apirest.apirest.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
