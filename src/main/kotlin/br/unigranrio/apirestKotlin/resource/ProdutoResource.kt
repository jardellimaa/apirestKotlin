package br.unigranrio.apirestKotlin.resource

import br.unigranrio.apirestKotlin.model.Produto
import br.unigranrio.apirestKotlin.repository.Produtos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.time.LocalDateTime

@CrossOrigin
@RestController
@RequestMapping("/produtos")
class ProdutoResource {

    @Autowired
    lateinit var produtos : Produtos;

    @GetMapping
    fun list(): ResponseEntity<List<Produto>>{
        return ResponseEntity.ok(produtos.findAllByOrderByCodigoAsc().toList())
    }

    @GetMapping(value = "/{codigo}")
    fun findOne(@PathVariable("codigo") codigo : Int): ResponseEntity<Produto> {
        if(produtos.existsById(codigo)){
            return ResponseEntity.ok(produtos.findById(codigo).get())
        }
        return ResponseEntity.notFound().build()
    }

    @GetMapping(value="/last")
    fun findLast(): ResponseEntity<Produto>{
        return ResponseEntity.ok(produtos.findLast())
    }

    @PostMapping
    fun add(@RequestBody produto: Produto): ResponseEntity<Produto>{
        val produto : Produto = produtos.save(produto)
        val uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
                .buildAndExpand(produto.codigo).toUri()
        return ResponseEntity.created(uri).body(produto)
    }

    @PutMapping(value = "/{codigo}")
    fun update(@PathVariable("codigo") codigo: Int, @RequestBody produto: Produto): ResponseEntity<Produto>{
        if(produtos.existsById(codigo)){
            val produto : Produto = produto.copy(codigo, produto.nome, produto.codigoBarras, LocalDateTime.now())
            return ResponseEntity.accepted().body(produtos.save(produto))
        }
        return ResponseEntity.notFound().build()
    }

    @DeleteMapping(value = "/{codigo}")
    fun delete(@PathVariable("codigo") codigo: Int): ResponseEntity<Void>{
        if(produtos.existsById(codigo)){
            produtos.deleteById(codigo)
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.notFound().build()
    }

}
