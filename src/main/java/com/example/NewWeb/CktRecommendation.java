package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cocktails")
public class CktRecommendation {

    @Id
    @Column(name = "drinkid")
    private int drinkid;
    @Column(name = "id")
    private int id;

    @Column(name = "drinkname")
    private String drinkname;

    @Column(name="Alcoholic")
    private String alcoholic;


    @Column(name = "engig1")
    private String engig1;

    @Column(name = "engig2")
    private String engig2;

    @Column(name = "engig3")
    private String engig3;

    @Column(name = "engig4")
    private String engig4;

    @Column(name = "engig5")
    private String engig5;

    @Column(name = "engig6")
    private String engig6;

    @Column(name = "engig7")
    private String engig7;

    @Column(name = "engig8")
    private String engig8;

    @Column(name = "engig9")
    private String engig9;

    @Column(name = "engig10")
    private String engig10;

    @Column(name = "engig11")
    private String engig11;

    @Column(name = "engig12")
    private String engig12;



    // Getter 및 Setter 메서드 추가
    public int getid() {
        return id;
    }
    public int getdrinkid() {
        return drinkid;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public String getAlcoholic(){return alcoholic;}

    public List<String> getEngIngredients() {
        List<String> engIngredients = new ArrayList<>();
        engIngredients.add(engig1);
        engIngredients.add(engig2);
        engIngredients.add(engig3);
        engIngredients.add(engig4);
        engIngredients.add(engig5);
        engIngredients.add(engig6);
        engIngredients.add(engig7);
        engIngredients.add(engig8);
        engIngredients.add(engig9);
        engIngredients.add(engig10);
        engIngredients.add(engig11);
        engIngredients.add(engig12);
        return engIngredients;
    }


}