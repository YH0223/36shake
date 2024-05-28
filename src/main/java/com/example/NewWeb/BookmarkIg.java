package com.example.NewWeb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bookmarkig")
public class BookmarkIg {

    @Id
    @Column(name = "bmigno")
    private int bmigno;

    @Column(name = "bmigid")
    private int bmigid;

    @Column(name = "userid")
    private String userid;



    // Getter 및 Setter 메서드 추가
    public int getbmigno() {
        return bmigno;
    }

    public int getbmigid() {
        return bmigid;
    }
    public String getuserid() {
        return userid;
    }


}