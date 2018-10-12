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
public class Raiz {
    public ArbolBinario left, right = null;
    public String palabra;
    public int id;
    
    public Raiz() {
        this.palabra = "Tu animal tiene cuernos?";
        this.id = 1;
        this.left = new ArbolBinario(this.id+1,"left");
        this.right = new ArbolBinario(this.id+2,"right");
        this.add(2,"vaca","left");
        this.add(3,"pescado","right");
    }
    
    public void add(int id, String param, String dir){
        if(dir == "left"){
            this.left.add(id, param);
        }
        if(dir == "right"){
            this.right.add(id, param);
        }
    }
    
    public void print(int id){
        if(this.left.find(id)){
            left.print(id);
        }else if(this.right.find(id)){
            right.print(id);
        }
    }
    
    public void run(){
        String userParam;
        Scanner scan = new Scanner(System.in);
        do{
            System.out.println(this.palabra);
            userParam = scan.next();
            if("si".equals(userParam)){
                this.left.run();
            }else if("no".equals(userParam)){
                this.right.run();
            }
            userParam = "exit";
        }while(!"exit".equals(userParam));
        this.run();
    }
}
