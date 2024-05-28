package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "cocktails")
public class SearchResult {

    @Id
    @Column(name="id")
    private int id;

    @Column(name = "drinkid")
    private int drinkId;

    @Column(name = "drinkname")
    private String drinkname;

    @Column(name = "IBA")
    private String IBA;

    @Column(name = "Alcoholic")
    private String Alcoholic;

    // Getter 및 Setter 메서드 추가
    public int getId(){return id;}
    public int getdrinkId() {
        return drinkId;
    }

    public String getDrinkname() {
        return drinkname;
    }


    public String getIBA() {
        return IBA;
    }


    public String getAlcoholic() {
        return Alcoholic;
    }



}