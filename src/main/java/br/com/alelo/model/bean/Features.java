/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.alelo.model.bean;

/**
 *
 * @author atomic
 */
public class Features {
    
    private String name;
    private int projId;

//    public Features(String name, int projId) {
//        this.name = name;
//        this.projId = projId;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }
    
}
