/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package execute;
/**
 *
 * @author HENRIQUE
 */
public class Lista {
    
    private Object[] objeto;
    private int tamanho;
    
    public Lista(){
    }
    public Lista(int capacidade){
        this.objeto = new Object[capacidade];
        this.tamanho = 0;
    }
    
    public boolean adicionar(Object elemento){
        if(tamanho < objeto.length){
            objeto[tamanho] = elemento;
            tamanho++;
            return true;
        }return false;
    }
    
    public int buscarElemento1(Object elemento){
        for(int i = 0; i < this.tamanho; i++){
            if(this.objeto[i].equals(elemento)){
                return i;
            }
        }return -1;
    }
    
    public Object buscarElemento2(Object elemento){
        for(int i = 0; i < this.tamanho; i++){
            if(this.objeto[i].equals(elemento)){
                return elemento;
            }
        }return false;
    }
    
    public boolean buscarElemento3(Object elemento){
        for(int  i = 0; i < this.tamanho; i++){
            if(this.objeto[i].equals(elemento)){
                return true;
            }
        }return false;
    }
    
    public boolean addIguais(Object elemento){
        if((tamanho < objeto.length)&& (this.contem(elemento)== false)){
            objeto[tamanho] = elemento;
            tamanho++;
            return true;
        }
        
        return false;
    }
    
    public boolean contem(Object elemento){
        return buscarElemento1(elemento) >= 0;
    }
    
    public int tamanho(){
        return this.tamanho;
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        
        for(int i =0; i < this.tamanho; i++){
            s.append(this.objeto[i]);
            s.append("\n");
        }
        return s.toString();
    }
}
