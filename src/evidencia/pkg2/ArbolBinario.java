/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencia.pkg2;

import java.util.Scanner;

/**
 *
 * @author Modi
 */
public class ArbolBinario {
    public ArbolBinario left,right = null;
    public int id;
    public String palabra;
    public String dir;

    public ArbolBinario(int id, String dir) {
        this.id = id;
        this.dir = dir;
    }
    
    public void add (int id, String param){
        if (this.getId()==id){
            this.setPalabra(param);
        }else{
            if(this.left == null){
                this.left = new ArbolBinario(this.id+1,"left");
                left.add(id,param);
            }else if(left.find(id)){
                left.add(id,param);
            }
            
            if(this.right == null){
                this.right = new ArbolBinario (this.id+2,"right");
                right.add(id,param);
            }else if (right.find(id)){
                right.add(id,param);
            }
        }
    }
    
    public boolean find(int id){
        boolean result = false;
        if(this.id == id){
            result = true;
        }else{
            if(this.left != null && left.find(id)){
                result = true;
            }else if(this.right != null && right.find(id)){
                result = true;
            }
        }
        return result;
    }
    
    public String print(int id){
        String result = "";
        if(this.id == id){
            result = (this.palabra);
        }else if(this.left.find(id)){
            left.print(id);
        }else if(this.right.find(id)){
            right.print(id);
        }else{
            System.out.println("No se encontro");
        }
        return result;
    }
    
    public void createNodes(int id1, String palabra1,int id2, String palabra2){
        this.right = new ArbolBinario(id1,palabra1);
        this.left = new ArbolBinario(id2,palabra2);
    }
    
    public void run(){
        String userParam;
        String newPregunta;
        String newRespuesta;
        String temp;
        int id =1;
        Scanner scan = new Scanner(System.in);
        System.out.println(this.palabra);
        userParam = scan.next();
        do{
            id += 1;
            if("si".equals(userParam)){
                if(this.left == null){
                    System.out.println("He ganado");
                    userParam = "exit";
                }else{
                    this.left.run();
                }
            }
            
            else if("no".equals(userParam)){
                if(this.right == null){
                    System.out.println("No tengo ese animal en mi base de datos, dime que animal era");
                    newRespuesta = scan.nextLine();
                    scan.nextLine();
                    System.out.println("Dime una pregunta para adivinar ese animal");
                    newPregunta = scan.nextLine();
                    scan.nextLine();
                    temp = this.palabra;
                    this.setPalabra(newPregunta);
                    this.createNodes(id+2, temp, id+1, newRespuesta);
                    userParam = "exit";
                }else{
                    this.right.run();
                }
            }
        }while(!"exit".equals(userParam));
    }
    
    public ArbolBinario getLeft() {
        return left;
    }

    public void setLeft(ArbolBinario left) {
        this.left = left;
    }

    public ArbolBinario getRight() {
        return right;
    }

    public void setRight(ArbolBinario right) {
        this.right = right;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }
    
    
    
    
}
