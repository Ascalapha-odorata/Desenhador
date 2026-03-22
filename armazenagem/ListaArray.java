package armazenagem;
import java.util.ArrayList;
import java.io.Serializable;
/**
 * Operações com ArrayList
 * 
 * @author Julio Arakaki
 * @version 12/04/2023
 */
public class ListaArray implements IArmazenador, java.io.Serializable{

    private ArrayList <Object> lista;

    public ListaArray(){
        setLista(new <Object>ArrayList());
    }

    /**
     * getLista
     * 
     * @return the vet
     */
    public ArrayList <Object> getLista() {
        return lista;
    }

    /**
     * getQtd
     * 
     * @return the qtd
     */
    public int getQtd() {
        return lista.size();
    }

    /**
     * setLista
     * 
     * @param vet the vet to set
     */
    private void setLista(ArrayList <Object>lista) {
        this.lista = lista;
    }

    /**
     * adicionar
     *
     * @param obj Um parÃ¢metro
     */
    public void adicionar(Object obj){
        lista.add(obj);
    }

    /**
     * remover
     *
     * @param i Um parÃ¢metro
     */
    public Object remover(int i) {
        Object ret = null;

        if (buscar(i) != null){
            ret = lista.get(i);
            lista.remove(i);
        }
        return ret;
    }

    /**
     * buscar
     *
     * @param i Um parÃ¢metro
     * @return O valor de retorno
     */
    public Object buscar (int i){
        Object ret = null;
        if(!lista.isEmpty()&& (i >= 0 && i < getQtd())){
            ret = lista.get(i);
        }
        return ret;
    }

    /**
     * estaVazia
     *
     * @return O valor de retorno
     */
    public boolean estaVazia(){
        return (lista.isEmpty());
    }

    /**
     * toString
     *
     * @return O valor de retorno
     */
    public String toString(){
        String s = "[ ";
        for (int i = 0; i < lista.size(); i++){
            s += lista.get(i).toString() + " ";
        }
        s = s + "]";
        return s;
    }
    
}

