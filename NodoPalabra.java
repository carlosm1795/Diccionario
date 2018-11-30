
package diccionario;

public class NodoPalabra {
    private NodoPalabra raiz;
    public String datos;
    public int fe;
    public NodoPalabra nodoDer;
    public NodoPalabra nodoIzq;
    
    public NodoPalabra(){
        raiz=null;
    }
    public NodoPalabra(String palabra){
        datos = palabra;
        nodoDer = nodoIzq = null;
        fe=0;
    }
    public NodoPalabra obtenerRaiz(){
        return raiz;
    }
    
    //Buscar el factor de equilibrio
    public int obtenerFe(NodoPalabra nodo){
        if(nodo==null){
            return -1;
        }else{
            return nodo.fe;
        }
    }
    //obtener maxima altura
    public int obtenerProfundidad(){
        int profundidad = 0;
        profundidad = Math.max(obtenerFe(raiz.nodoDer),obtenerFe(raiz.nodoIzq));
        
        return profundidad;
    }
    //Rotacion simple izquierda
    public NodoPalabra rotacionIzquierda(NodoPalabra nodo){
        NodoPalabra auxiliar = nodo.nodoIzq;
        nodo.nodoIzq = auxiliar.nodoDer;
        auxiliar.nodoDer=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.nodoIzq), obtenerFe(nodo.nodoDer))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.nodoIzq),obtenerFe(auxiliar.nodoDer))+1;
        return auxiliar;
    }
    //Rotacion simple derecha
    public NodoPalabra rotacionDerecha(NodoPalabra nodo){
        NodoPalabra auxiliar = nodo.nodoDer;
        nodo.nodoDer = auxiliar.nodoIzq;
        auxiliar.nodoIzq=nodo;
        nodo.fe=Math.max(obtenerFe(nodo.nodoIzq), obtenerFe(nodo.nodoDer))+1;
        auxiliar.fe = Math.max(obtenerFe(auxiliar.nodoIzq),obtenerFe(auxiliar.nodoDer))+1;
        return auxiliar;
    }
    //Rotacion Doble a la derecha
    public NodoPalabra rotacionDobleIzquierda(NodoPalabra nodo){
        NodoPalabra temporal;
        nodo.nodoIzq=rotacionDerecha(nodo.nodoIzq);
        temporal=rotacionIzquierda(nodo);
        return temporal;
    }
    //Rotacion Doble a la Izquierda
    public NodoPalabra rotacionDobleDerecha(NodoPalabra nodo){
        NodoPalabra temporal;
        nodo.nodoDer=rotacionIzquierda(nodo.nodoDer);
        temporal=rotacionDerecha(nodo);
        return temporal;
    }
     // Retorna el char de una posicion en especifico.
    public int retornarCharDeUnString(String pPalabra, int pPosicion){
        int assciiValuewordToInsert= pPalabra.charAt(pPosicion);
        return assciiValuewordToInsert;
    }
    //Metodo para Insertar AVL
    public NodoPalabra insertarAVL(NodoPalabra nuevo, NodoPalabra subAr,Boolean aditionalValidations){
        
        int charPositionToEvaluate =0;
        int palabraMayor=0;
        NodoPalabra nuevoPadre = subAr;
        
        if(aditionalValidations==false){
                if(retornarCharDeUnString(nuevo.datos,charPositionToEvaluate)<retornarCharDeUnString(subAr.datos,charPositionToEvaluate)){
                    if(subAr.nodoIzq==null){
                        subAr.nodoIzq=nuevo;
                    }else{
                        subAr.nodoIzq=insertarAVL(nuevo,subAr.nodoIzq,false);
                        if((obtenerFe(subAr.nodoIzq)-obtenerFe(subAr.nodoDer)==2)){
                            if(retornarCharDeUnString(nuevo.datos,charPositionToEvaluate)<retornarCharDeUnString(subAr.nodoIzq.datos,charPositionToEvaluate)){                        
                                nuevoPadre=rotacionIzquierda(subAr);
                            }else{
                                nuevoPadre = rotacionDobleIzquierda(subAr);
                            }
                        }
                    }
                }else if(retornarCharDeUnString(nuevo.datos,charPositionToEvaluate)> retornarCharDeUnString(subAr.datos,charPositionToEvaluate)){
                    if(subAr.nodoDer==null){
                        subAr.nodoDer=nuevo;
                    }else{
                        subAr.nodoDer=insertarAVL(nuevo,subAr.nodoDer,false);
                        if((obtenerFe(subAr.nodoDer)-obtenerFe(subAr.nodoIzq)==2)){
                            if(retornarCharDeUnString(nuevo.datos,charPositionToEvaluate)>retornarCharDeUnString(subAr.nodoDer.datos,charPositionToEvaluate)){
                                nuevoPadre = rotacionDerecha(subAr);
                            }else{
                                nuevoPadre = rotacionDobleDerecha(subAr);
                            }
                        }
                    }
                }else{
                    
                    insertarAVL(nuevo, subAr,true);
                }
        }else{
            palabraMayor=determinarPalabraMayor(nuevo,subAr);
            if(palabraMayor==1){
                if(subAr.nodoDer==null){
                        subAr.nodoDer=nuevo;
                    }else{
                        subAr.nodoDer=insertarAVL(nuevo,subAr.nodoDer,true);
                        if((obtenerFe(subAr.nodoDer)-obtenerFe(subAr.nodoIzq)==2)){
                            if(palabraMayor==1){
                                nuevoPadre = rotacionDerecha(subAr);
                            }else{
                                nuevoPadre = rotacionDobleDerecha(subAr);
                            }
                        }
                    }
            }else{
                if(subAr.nodoIzq==null){
                        subAr.nodoIzq=nuevo;
                    }else{
                        subAr.nodoIzq=insertarAVL(nuevo,subAr.nodoIzq,true);
                        if((obtenerFe(subAr.nodoIzq)-obtenerFe(subAr.nodoDer)==2)){
                            if(palabraMayor==2){                        
                                nuevoPadre=rotacionIzquierda(subAr);
                            }else{
                                nuevoPadre = rotacionDobleIzquierda(subAr);
                            }
                        }
                    }
            }
        }
        
        //ACtualizando la altura
        if((subAr.nodoIzq==null) && (subAr.nodoDer!=null)){
            subAr.fe=subAr.nodoDer.fe+1;
        }else if((subAr.nodoDer==null)&& (subAr.nodoIzq!=null)){
            subAr.fe=subAr.nodoIzq.fe+1;
        }else{
            subAr.fe=Math.max(obtenerFe(subAr.nodoIzq), obtenerFe(subAr.nodoDer))+1;
        }
        return nuevoPadre;
    }
    //Metodo para determinar cual palabra es mayor
    //1 si la primera palabra es mayor
    //2 si la segunda palabra es mayor
    public int determinarPalabraMayor(NodoPalabra nuevo, NodoPalabra subAr){
        //nuevo.datos.toCharArray()
        int palabraMayor=0;
        for(int index=1;index<nuevo.datos.toCharArray().length;index++){
            if(retornarCharDeUnString(nuevo.datos,index)<retornarCharDeUnString(subAr.datos,index)){
                palabraMayor=2;
                break;
            }else if(retornarCharDeUnString(nuevo.datos,index)>retornarCharDeUnString(subAr.datos,index)){
                palabraMayor=1;
                break;
            }
        }
        if(palabraMayor==0){
            if(nuevo.datos.toCharArray().length<subAr.datos.toCharArray().length){
                 palabraMayor=2;
            }else if(nuevo.datos.toCharArray().length>subAr.datos.toCharArray().length) {
                palabraMayor=1;
            }
        }
        
        return palabraMayor;
    }
    public void insertar( String valorInsertar )
    {
        // inserta en el subárbol izquierdo
        char[] wordToInsert,actualWord;
        
        int charPositionToEvaluate = 0;
        wordToInsert = valorInsertar.toCharArray();
        actualWord = datos.toCharArray();
        
        int assciiValuewordToInsert= (int) wordToInsert[charPositionToEvaluate];
        int assciiValueactualWord= (int) actualWord[charPositionToEvaluate];
        
        if ( assciiValuewordToInsert < assciiValueactualWord ){
            if ( nodoIzq == null ){
                nodoIzq = new NodoPalabra( valorInsertar );
            }else{
                nodoIzq.insertar( valorInsertar );
            }
        }else if (assciiValuewordToInsert > assciiValueactualWord){
            if ( nodoDer == null ){
                nodoDer = new NodoPalabra( valorInsertar );
            }else{
                nodoDer.insertar( valorInsertar );
            }
        }else if(assciiValuewordToInsert==assciiValueactualWord){
            for(int index=1;index<actualWord.length;index++){
                charPositionToEvaluate++;
                assciiValuewordToInsert= (int) wordToInsert[charPositionToEvaluate];
                assciiValueactualWord= (int) actualWord[charPositionToEvaluate];
                if ( assciiValuewordToInsert < assciiValueactualWord ){
                    if ( nodoIzq == null ){
                        nodoIzq = new NodoPalabra( valorInsertar );
                        break;
                    }else{
                        nodoIzq.insertar( valorInsertar );
                        break;
                    }
                }else if (assciiValuewordToInsert > assciiValueactualWord){
                    if ( nodoDer == null ){
                        nodoDer = new NodoPalabra( valorInsertar );
                        break;
                    }else{
                        nodoDer.insertar( valorInsertar );
                        break;
                    }
                }
            }
        }
    } 
     //Metodo para insertar
    public void testing(String value){
        
        
    }
     //Metodo para insertar
    public void insertar2(String value){
        
        NodoPalabra nuevo = new NodoPalabra(value);
        if(raiz==null){
            raiz=nuevo;
        }else{
            raiz=insertarAVL(nuevo, raiz,false);
        }
    }
    //Arbol in order
    public void inOrden(NodoPalabra nodo){
        if(nodo!=null){
            inOrden(nodo.nodoIzq);
            System.out.print(nodo.datos+", ");
            inOrden(nodo.nodoDer);
        }
    }
    // Arbol preorden
    public void preOrden(NodoPalabra nodo){
        if(nodo!=null){
            System.out.print(nodo.datos+", ");
            preOrden(nodo.nodoIzq);
            preOrden(nodo.nodoDer);
        }
    }
    //Arbol postOrden
    public void postOrden(NodoPalabra nodo){
        if(nodo!=null){
            
            postOrden(nodo.nodoIzq);
            postOrden(nodo.nodoDer);
            System.out.print(nodo.datos+", ");
        }
    }
    
   
}
