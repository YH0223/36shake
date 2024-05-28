package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookmark")
public class Bookmark {

    @Id
    @Column(name = "bookmarkno")
    private int bookmarkno;

    @Column(name = "drinkno")
    private int drinkno;

    @Column(name = "userid")
    private String userid;



    // Getter 및 Setter 메서드 추가
    public int getbookmarkno() {
        return bookmarkno;
    }

    public int getdrinkno() {
        return drinkno;
    }

    public String getuserid() {
        return userid;
    }


}