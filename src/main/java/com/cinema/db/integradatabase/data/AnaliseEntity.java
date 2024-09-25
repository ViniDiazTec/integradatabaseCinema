package com.cinema.db.integradatabase.data;

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table; 
import lombok.Data; 

@Data
@Entity
@Table (name = "Analise")
public class AnaliseEntity {

     @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id; 
     
     @ManyToOne
    @JoinColumn(name = "filme_id")
    private FilmeEntity filme; 
    private String comentario; 
    private int nota;

   
}
