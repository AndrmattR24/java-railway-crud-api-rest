package com.andrmatt.apirest.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andrmatt.apirest.apirest.entities.Producto;
import com.andrmatt.apirest.apirest.repositories.ProductoRepository;

@RestController
@RequestMapping("API/products")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/list")
    public List<Producto> getAllProducts() {
        return productoRepository.findAll();
    }

    @GetMapping("/product/{id}")
    public Producto getProductById(@PathVariable long id) {
        return productoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found By Id"));
    }

    @PostMapping("/create")
    public Producto createProduct(@RequestBody Producto producto) {
        return productoRepository.saveAndFlush(producto);
    }

    @PutMapping("/update/{id}")
    public Producto updateProduct(@PathVariable long id, @RequestBody Producto detalleProducto) {
        Producto productFound = productoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found By Id"));

        productFound.setNombre(detalleProducto.getNombre());
        productFound.setPrecio(detalleProducto.getPrecio());
        return productoRepository.saveAndFlush(productFound);

    }

    @DeleteMapping("/delete/{id}")
    public Producto deleteProduct(@PathVariable long id) {

        Producto productFound = productoRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found By Id"));

        productoRepository.delete(productFound);
        productoRepository.flush();
        return productFound;
    }
}
