package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "igen")
    private String igen;

    @Column(name = "igkor")
    private String igkor;

    @Column(name = "igyes")
    private int igyes;


    // Getter 및 Setter 메서드 추가
    public int getid() {
        return id;
    }

    public String getigEn() {
        return igen;
    }

    public String getIgKor() {
        return igkor;
    }
    public int getigYes() {return igyes;}




}