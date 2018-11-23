
package diccionario;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class logica {
    
    NodoPalabra raiz = new NodoPalabra();
    
    public logica(){
    
    }
    
    public void  registrarPalabras() throws IOException{
        
        String rutaFile="C:\\Users\\barbozca2\\Desktop\\Book1.csv";
        String[] palabras = muestraContenido(rutaFile);
        for(String word : palabras){
            raiz.insertar2(word);
            
            //System.out.println(word);
        }
    }
    
    public void verEnInOrden(){
        raiz.inOrden(raiz.obtenerRaiz());
    }
    //Codigo que lee las palabras de un archivo csv o txt y devuelve un arreglo de String con las letras en mayuscula
    public  static String[] muestraContenido(String archivo) throws FileNotFoundException, IOException {
        String cadena;
        int cantWords = 0;
        int index =0;
        String palabras[];    //declaring array
        
        FileReader f = new FileReader(archivo);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            
            cantWords++;
        }
        
        b.close();
        
        f = new FileReader(archivo);
        b = new BufferedReader(f);
        palabras = new String[cantWords];
        while((cadena = b.readLine())!=null) {
            
            palabras[index]=cadena.toUpperCase();
            index++;
        }
        b.close();
        
        return palabras;
    }
    
}
