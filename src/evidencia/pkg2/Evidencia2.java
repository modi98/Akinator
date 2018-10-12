
package evidencia.pkg2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;


public class Evidencia2 {

    static Vector <String> data = new Vector<String>();
    
    public static void main(String[] args) throws IOException {
        
        boolean cycle = false;//Variable booleana para indicar si el ciclo del programa sigue o no
        
        BinaryTree evi = new BinaryTree("Tu animal tiene cuernos?","vaca","pescado");//Se crea la raiz del arbol binario
        Scanner temporal = null;//variable temporal para recibir los datos del archivo
        
        System.out.println("Bienvenido a la Evidencia 2 de Mauricio Alvarado, recuerda que tienes que corregir el directorio del archivo, presiona enter para continuar");
        
        //Aqui se le da valor a la variable temporal con el archivo de datos
        try {
            temporal = new Scanner(new File("C:/Users/Modi/Documents/NetBeansProjects/Evidencia 2/datos.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //Se llena el vector con los datos del archivo
        while (temporal.hasNextLine()) {
            data.add(temporal.nextLine());
        }
        
        //Se reemplazan los datos del arbolBinario con los del vector
        for(int i = 0;i<data.size();i+=3){
            if("".equals(data.elementAt(i))){
                i += 1;
            }
            evi.replace(data.elementAt(i),data.elementAt(i+1),data.elementAt(i+2));
        }
        
        //Aqui es donde empieza el programa
        do{
            evi.run();
            muestraContenido("C:/Users/Modi/Documents/NetBeansProjects/Evidencia 2/temporal.txt");//Metodo para guardar los datos que acabas de agregar en el vector principal
            cycle = evi.salir();//Metodo para saber si quieres seguir jugando o salir y guardar
        }while(cycle == false);
        
        //Apuntadores a variables PrintWriter y FileWriter
        PrintWriter pw = null;
        FileWriter fichero = null;
        
        try{
            //Se declara el fichero con la direccion del archivo pruebas y ese fichero se le aplica a la variable tipo PrintWriter
            fichero = new FileWriter("C:/Users/Modi/Documents/NetBeansProjects/Evidencia 2/datos.txt");
            pw = new PrintWriter(fichero);
            for(int i = 0;i<data.size();i++){
                pw.println(data.elementAt(i));//Se escribe en el archivo de datos todos los valores del vector data
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (null != fichero) fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    
    public static void muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            data.add(cadena);//Se va agregando de 3 en 3 datos al vector data
        }
        b.close();
    }
}