/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tp_Projet;

/**
 *
 * @author Yasmina
 */
public class ProductEntity {
     private int id;
     private String name;
     
public ProductEntity(int id,String name){
     this.id=id;
     this.name=name;
}

public int getid() {
        return id;
    }

public String getname(){
    return name;
}

}
