package javaapplication42;
import java.io.File;
import java.util.ArrayList;
public class AnalizadorArchivos {
    
    private int txt=0, java=0, pdf=0, otros=0;
    private ArrayList<String> resultadoBusqueda;

    public AnalizadorArchivos() {
        resultadoBusqueda=new ArrayList<>();
    }
    
    public void contarArchivos(File directorio){
        File[] archivos=directorio.listFiles();
        if(archivos!=null)
            contarArchivos(archivos, 0);
    }
    
    private void contarArchivos(File[] archivos, int indice){
        if(archivos.length>indice){
            File archivo=archivos[indice];
            if(archivo.isDirectory()){
                contarArchivos(archivo);
            }
            else{
                String nombre=archivo.getName().toLowerCase();
                if(nombre.endsWith(".txt"))
                    txt++;
                else if (nombre.endsWith(".java"))
                    java++;
                else if(nombre.endsWith(".pdf"))
                    pdf++;
                else otros++;
            }
            contarArchivos(archivos, indice+1);
        }
    }
    
    public void buscarArchivos(File directorio, String textoBusqueda){
        File[] archivos=directorio.listFiles();
        if(archivos!=null)
            buscarArchivos(archivos, textoBusqueda, 0);
    }
    
    private void buscarArchivos(File[] archivos, String textoBusqueda, int indice){
        if(archivos.length>indice){
            File archivo=archivos[indice];
            if(archivo.isDirectory()){
                buscarArchivos(archivo, textoBusqueda);
            }
            else{
                String nombre=archivo.getName().toLowerCase();
                    if(nombre.contains(textoBusqueda.toLowerCase()))
                        resultadoBusqueda.add(archivo.getAbsolutePath());
            }
            buscarArchivos(archivos, textoBusqueda, indice+1);
        }
    }

    public int getTxt() {
        return txt;
    }

    public int getJava() {
        return java;
    }

    public int getPdf() {
        return pdf;
    }

    public int getOtros() {
        return otros;
    }

    public ArrayList<String> getResultadoBusqueda() {
        return resultadoBusqueda;
    }
    
    
}
