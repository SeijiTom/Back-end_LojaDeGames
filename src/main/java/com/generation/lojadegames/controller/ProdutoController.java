package com.generation.lojadegames.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.lojadegames.model.Produto;
import com.generation.lojadegames.repository.CategoriaRepository;
import com.generation.lojadegames.repository.ProdutoRepository;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<Produto>> getAll() {
		
		return ResponseEntity.ok(produtoRepository.findAll());
		
		//SELECT * FROM tb_postagens;
	}
	
	@GetMapping("/{id}") 
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
		
	}
	
	@GetMapping("/produtos/{produtos}")
	public ResponseEntity<List<Produto>> getByNomeProduto(@PathVariable String nomeProduto) {
	
		return ResponseEntity.ok(produtoRepository.findAllByNomeProdutoContainingIgnoreCase(nomeProduto));
	}	
	
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto) {
		return (categoriaRepository.findById(produto.getCategoria().getId()))
		.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto)))
		.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
	@PutMapping
    public ResponseEntity<Produto> putProduto (@Valid @RequestBody Produto produto){
        if (produtoRepository.existsById(produto.getId())) {
        	if (categoriaRepository.existsById(produto.getCategoria().getId())) 
        		return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
        	
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
           
    }
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id) {
		
		return produtoRepository.findById(id)
				.map(resposta -> {
					produtoRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}
	
}
