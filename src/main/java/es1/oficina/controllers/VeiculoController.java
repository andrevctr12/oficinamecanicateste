package es1.oficina.controllers;

import DAO.VeiculoDAO;
import Model.Veiculo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/veiculo")
public class VeiculoController{

    @GetMapping(produces = "application/json")
    public @ResponseBody ArrayList<Veiculo> ListaVeiculo() throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        ArrayList<Veiculo> list = veiculoDAO.BuscaVeiculoList();
        return list;
    }

    @RequestMapping("/{id}")
    @GetMapping(produces = "application/json")
    public @ResponseBody Veiculo BuscaVeiculo(@PathVariable("id") int id) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        return veiculoDAO.BuscaVeiculoByIdCliente(id);
    }

    @PutMapping
    public void AlteraVeiculo(@RequestBody Veiculo veiculo) throws SQLException {
        VeiculoDAO veiculoDAO = new VeiculoDAO();
        veiculoDAO.AlteraVeiculo(veiculo);
    }


}
