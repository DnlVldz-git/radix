
public class main {
    
    public static void main(String[] args){
        
        lista listita = new lista();
        
        System.out.println("\n Lista de numeros desordenados \n");
        
        for(int i = 0; i <1_000_000;i++){
            int num = (int) (Math.random()*100+1);
            listita.insertar(num);
            System.out.println("#"+i+" :"+num+", ");
        }
        
        listita.radix();
        System.out.println("\n Ordenados: \n");
        listita.recorrer();
    }
}

class lista{
    
    nodo inicio = null, fin = null;
    nodo[] a = new nodo[10];
    
    public void insertar(int dato){ //método que inserta el valor a una lista
        nodo nuevo = new nodo();
        nuevo.dato = dato;
        nuevo.sig = null;
        nuevo.prev = null;
        if(inicio == null){
            inicio = nuevo;
            fin = nuevo;
        }else{
            fin.sig = nuevo;
            nuevo.prev = fin;
            fin = fin.sig;
        }
    }
    
    public void recorrer(){ //función que recorre la nueva lista
        nodo aux = inicio;
        while(aux != null){
            System.out.println(aux.dato+", ");
            aux = aux.sig;
        }
    }
    
    public int num_digitos(nodo raiz){ //función que obtiene el número de dígitos del número con más dígitos
        int max = raiz.dato;
        int digitos = 0;
        
        while(raiz != null){
            if(raiz.dato > max) max = raiz.dato;
            raiz = raiz.sig;
        }
        while(max != 0){
            max /= 10;
            digitos++;
        }      
        return digitos;
    }
      
    public void radix(){ //método que ordena númetos con radix
        int digitos = num_digitos(inicio);
        int exp = 1;
        nodo aux = inicio;
        lista ordenada = new lista(); //se crea una nueva lista ordenada
        lista valores[] = new lista[10]; //se crea un arreglo listas de para almacenar los números
        
        for(int i = 0; i < digitos; i++){ //se repetirá el número de veces que tenga el mayor dígito
            lista temp = new lista(); //se crea una relación temporal
            while(aux != null){ //se recorrerá la lista 
                int posicion = (aux.dato / exp) % 10;  //se calcula la posición
                if(valores[posicion] == null){ 
                    valores[posicion] = new lista();
                }
                valores[posicion].insertar(aux.dato); //se agrega en las listas dependiendo de la posición del número
                aux = aux.sig; 
            }
            for(int j = 0; j < 10; ++j){ //se recorre el arreglo de listas para ponernos en la lista temporal
                while(valores[j] != null && valores[j].inicio != null){  
                    temp.insertar(valores[j].inicio.dato);  
                    valores[j].inicio = valores[j].inicio.sig;
                }
            }
            exp *= 10;  //se multiplica el exponente por 
            aux = temp.inicio;  //se pone aux en el inicio de la lista temporal
        }
        
        while(aux != null){ //ya que ha terminado insertará todo en la lista ordenada
            ordenada.insertar(aux.dato);
            aux = aux.sig;
        }
        
        inicio = ordenada.inicio;
    }
}

class nodo{
    int dato;
    nodo sig;
    nodo prev;
}
