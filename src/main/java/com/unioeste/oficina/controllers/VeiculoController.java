package com.unioeste.oficina.controllers;

import java.util.List;
import com.unioeste.oficina.repositories.VeiculoDAO;
import com.unioeste.oficina.model.Veiculo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController{

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<Veiculo> ListaVeiculo() throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        List<Veiculo> list = veiculoDAO.BuscaVeiculoList();
        return list;
    }

    @RequestMapping("/{idUsuario}")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> BuscaVeiculos(@PathVariable("idUsuario") int id) {
        Veiculo veiculo = null;
        try {
            veiculo = new VeiculoDAO().BuscaVeiculoByIdCliente(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (veiculo != null) return ResponseEntity.ok().body(veiculo);
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public void AlteraVeiculo(@RequestBody Veiculo veiculo) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.AlteraVeiculo(veiculo);
    }


}
