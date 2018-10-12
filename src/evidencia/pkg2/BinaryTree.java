/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evidencia.pkg2;

import java.util.Scanner;
import java.io.*;
import java.util.Vector;

/**
 *
 * @author Modi
 */
public class BinaryTree {
    public BinaryTree left,right = null;
    public String palabra;
    public FileWriter fichero = null;
    public PrintWriter pw = null;
    public Vector<String> data = new Vector<String>();
    
    public BinaryTree(String palabra) {//Constructor para nodo
        this.palabra = palabra;
    }
    
    public BinaryTree(String palabra,String left,String right) {//Constructor para raiz
        this.palabra = palabra;
        this.left = new BinaryTree(left);
        this.right = new BinaryTree(right);
    }
    
    //Metodo para agregar nodos al arbolBinario
    public void add(String param, String dir){
        if ("left".equals(dir)){//Si la direccion es igual a left entonces se revisa que left sea igual a null, si no lo es entonces se aplica this.left.add()
            if(this.left == null){
                this.left = new BinaryTree(param);
            }else{
                this.left.add(param, dir);
            }
        }else if("right".equals(dir)){//Igual pero para right
            if(this.right == null){
                this.right = new BinaryTree(param);
            }else{
                this.right = new BinaryTree(param);
            }
        }
    }
    
    //Metodo para encontrar alguna palabra en el arbolBinario
    public boolean find(String palabra){
        boolean result = false;
        if(this.palabra.equals(palabra)){//Si la palabra que estas buscando es igual a la palabra de este nodo entonces se regresa un true
            result = true;
        }else{//Si es diferente se revisa que left y right que sean diferentes a null, si encuentra la palabra en alguno de ellos se aplica un break y se regresa true
            if(this.left != null ){
                if(this.left.find(palabra)){
                    return true;
                }
            }if(this.right != null){
                if(this.right.find(palabra)){
                    return true;
                }
            }
        }
        return result;
    }
    
    //Metodo para reemplazar la palabra de un nodo con otro y ademas agregarle un left y right, esto se usa para cuando 
    public void replace(String actual, String newPregunta, String newRespuesta){
        if(this.palabra.equals(actual)){
            this.palabra = newPregunta;
            this.left = new BinaryTree(newRespuesta);
            this.right = new BinaryTree(actual);
        }else if(this.left.find(actual)){
            this.left.replace(actual, newPregunta, newRespuesta);
        }else if(this.right.find(actual)){
            this.right.replace(actual, newPregunta, newRespuesta);
        }
    }
    
    //Metodo que regresa un booleano cuando el usuario quiera salir del programa
    public boolean salir(){
        boolean result = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Quieres seguir jugando?");
        String userParam = scan.next();
        if("no".equals(userParam)){
            result = true;
        }
        System.out.println();
        return result;
    }
    
    //Metodo principal que corre el programa
    public void run(){
        String userParam;//Variable para todos los parametros del usuario
        String newRespuesta;//Variable para nueva respuesta que quiera agregar el usuario
        String newPregunta;//Variable para nueva pregunta que quiera agregar el usuario
        String temp;//Variable temporal para guardar this.palabra
        Scanner scan = new Scanner(System.in);
            
        if(this.left == null && this.right == null){//si left y right son nulls entonces imprime primero "acasu tu animal es: " para que se vea mas bonito el programa
            System.out.println("Acaso tu animal es: "+this.palabra);
        }else{
            System.out.println(this.palabra);//si no son nulls entonces te imprime this.palabra
        }
        userParam = scan.nextLine();
        
        //Si el parametro del usuario es si entonces se hace lo siguiente
        if("si".equals(userParam)){
            if(this.left != null){//si left es diferente a null entonces se hace left.run()
                this.left.run();
            }else{//Si es igual a null entonces se acaba el programa y te adivino tu animal
                System.out.println("Te adivine tu animal");
                System.out.println();
            }
        }
        
        //Si el parametro del usuario es no entonces se hace lo siguiente
         else if("no".equals(userParam)){
            if(this.right != null){//Si right es diferente a null entonces right.run()
                this.right.run();
            }else{//Si no entonces significa que el animal no esta en el archivo de texto o en el arbolBinario
                System.out.println("Ese animal no esta en mi base de datos, favor de agregarlo");
                newRespuesta = scan.nextLine();
                System.out.println("Agrega una pregunta para ese animal");
                newPregunta = scan.nextLine();
                
                //Se guardan los valores de nueva pregunta, nueva respuesta y respuesta actual
                temp = this.palabra;
                this.palabra = newPregunta;
                this.left = new BinaryTree(newRespuesta);
                this.right = new BinaryTree(temp);
                System.out.println();
                
                //Se escriben los datos en el archivo temporal
                PrintWriter pw = null;
                FileWriter fichero = null;
                try{
                    fichero = new FileWriter("C:/Users/Modi/Documents/NetBeansProjects/Evidencia 2/temporal.txt");
                    pw = new PrintWriter(fichero);
                    pw.println(temp);
                    pw.println(newPregunta);
                    pw.println(newRespuesta);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    try {
                        if (null != fichero)
                        fichero.close();
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
