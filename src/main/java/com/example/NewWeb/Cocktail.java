package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cocktails")
public class Cocktail {

    @Id
    @Column(name = "drinkid")
    private int drinkid;

    @Column(name = "drinkname")
    private String drinkname;

    @Column(name = "Category")
    private String Category;

    @Column(name = "IBA")
    private String IBA;

    @Column(name = "Alcoholic")
    private String Alcoholic;

    @Column(name = "Instructions")
    private String Instructions;

    @Column(name = "drinkthb")
    private String drinkthb;

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

    @Column(name = "engms1")
    private String engms1;

    @Column(name = "engms2")
    private String engms2;

    @Column(name = "engms3")
    private String engms3;

    @Column(name = "engms4")
    private String engms4;

    @Column(name = "engms5")
    private String engms5;

    @Column(name = "engms6")
    private String engms6;

    @Column(name = "engms7")
    private String engms7;

    @Column(name = "engms8")
    private String engms8;

    @Column(name = "engms9")
    private String engms9;

    @Column(name = "engms10")
    private String engms10;

    @Column(name = "engms11")
    private String engms11;

    @Column(name = "engms12")
    private String engms12;

    // Getter 및 Setter 메서드 추가
    public int getdrinkid() {
        return drinkid;
    }

    public String getDrinkname() {
        return drinkname;
    }

    public String getCategory() {
        return Category;
    }


    public String getIBA() {
        return IBA;
    }


    public String getAlcoholic() {
        return Alcoholic;
    }


    public String getInstructions() {
        return Instructions;
    }


    public String getdrinkthb() {
        return drinkthb;
    }


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

    public List<String> getEngMeasures() {
        List<String> engMeasures = new ArrayList<>();
        engMeasures.add(engms1);
        engMeasures.add(engms2);
        engMeasures.add(engms3);
        engMeasures.add(engms4);
        engMeasures.add(engms5);
        engMeasures.add(engms6);
        engMeasures.add(engms7);
        engMeasures.add(engms8);
        engMeasures.add(engms9);
        engMeasures.add(engms10);
        engMeasures.add(engms11);
        engMeasures.add(engms12);
        return engMeasures;
    }


}