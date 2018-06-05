package com.unioeste.oficina.controllers;


import java.util.List;
import com.unioeste.oficina.model.Cliente;
import com.unioeste.oficina.repositories.ClienteDAO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Cliente> ListaCliente() {
        List<Cliente> listaCliente = null;
        try {
            listaCliente = new ClienteDAO().BuscaLista();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return listaCliente;
    }

    @PostMapping()
    public ResponseEntity<?> CadastraCliente(@RequestBody Cliente cliente) {
        ClienteDAO clienteDAO = new ClienteDAO();

        try {
            if(clienteDAO.BuscaCliente(cliente.getNome(), cliente.getCpf()) == null){
                clienteDAO.CadastraCliente(cliente);
                return new ResponseEntity<>(cliente, HttpStatus.CREATED);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(cliente, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping("/{id}")
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> BuscaCliente(@PathVariable("id") int id) {
        Cliente cliente = null;
        try {
            cliente = new ClienteDAO().BuscaClienteID(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (cliente != null) return ResponseEntity.ok().body(cliente);

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @PutMapping
    public ResponseEntity<?> AlteraCliente(@RequestBody Cliente cliente) {
        Cliente put = null;
        try {

            put = new ClienteDAO().AlteraCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (put == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.accepted().body(put);
    }
//    @RequestMapping("/{id}")
//    @DeleteMapping()
//    public void ExcluirCliente(@PathVariable("id") int id) throws SQLException {
//        ClienteDAO clienteDao = new ClienteDAO();
//        clienteDao.ExcluirCliente(id);
//
//    }



}
