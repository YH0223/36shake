package com.example.NewWeb.dto;

import java.util.ArrayList;
import java.util.List;


public class CocktailDTO {


    private int drinkId;


    private String drinkname;

    private String Category;


    private String IBA;

    private String Alcoholic;


    private String Instructions;


    private String drinkthb;


    private String engig1;


    private String engig2;


    private String engig3;


    private String engig4;


    private String engig5;


    private String engig6;


    private String engig7;


    private String engig8;


    private String engig9;


    private String engig10;


    private String engig11;


    private String engig12;

    private String engms1;


    private String engms2;


    private String engms3;


    private String engms4;


    private String engms5;


    private String engms6;


    private String engms7;


    private String engms8;


    private String engms9;


    private String engms10;


    private String engms11;


    private String engms12;

    // Getter 및 Setter 메서드 추가
    public int getdrinkid() {
        return drinkId;
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