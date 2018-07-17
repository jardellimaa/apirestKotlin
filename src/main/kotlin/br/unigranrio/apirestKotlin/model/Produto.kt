package br.unigranrio.apirestKotlin.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Produto (@Id
                    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CODIGO_PRODUTO")
                    @SequenceGenerator(name = "CODIGO_PRODUTO", sequenceName = "SEQ_CODIGO_PRODUTO", allocationSize = 1)
                    val codigo: Int? = null,
                    
                    @Column(nullable = false)
                    val nome: String = "",
                    
                    @Column(name = "codigo_barras", nullable = false)
                    val codigoBarras: String = "",
                    
                    @Column(nullable = false)
                    val tempo: LocalDateTime? = LocalDateTime.now())
