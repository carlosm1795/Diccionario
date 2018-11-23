/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionario;

import java.io.IOException;

/**
 *
 * @author barbozca2
 */
public class Diccionario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        logica logic = new logica();
        logic.registrarPalabras();
        logic.verEnInOrden();
    }
    
}
