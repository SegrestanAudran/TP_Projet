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
public class OrderEntity {

   private int order_num;
   private int id_client;
   private int id_produit;
   private int quantite;
   private Double frais;
   private String date_achat;
   private String date_envoi;
   private String compagnie;

    public OrderEntity(int order_num, int id_client, int id_produit, int quantite, Double frais, String date_achat, String date_envoi, String compagnie) {
        this.order_num = order_num;
        this.id_client = id_client;
        this.id_produit = id_produit;
        this.quantite = quantite;
        this.frais = frais;
        this.date_achat = date_achat;
        this.date_envoi = date_envoi;
        this.compagnie = compagnie;
    }

    /**
     * Get the value of customerId
     *
     * @return the value of customerId
     */
    public int getOrder_num() {
        return order_num;
    }

   
    public int getId_client() {
        return id_client;
    }

    
    public int getId_produit() {
        return id_produit;
    }
    
    public int getQuantite(){
        return quantite;
    }
    
    public Double getFrais(){
        return frais;
    }
    
    public String getDate_achat(){
        return date_achat;
    }
    
    public String getDate_envoi(){
        return date_envoi;
    }
    
    public String getCompagnie(){
        return compagnie;
    }
}
