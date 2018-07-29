package br.unigranrio.apirestKotlin.repository

import br.unigranrio.apirestKotlin.model.Produto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface Produtos : JpaRepository<Produto, Int> {

    fun findAllByOrderByCodigoAsc(): List<Produto>

    @Query(value = "SELECT * FROM PRODUTO p order by p.codigo desc limit 1", nativeQuery = true)
    fun findLast(): Produto

    fun existsByCodigoBarras(codigoBarras: String): Boolean
}