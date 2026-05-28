package javaapplication42;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
public class VentanaPrincipal extends JFrame{
    
    private JPanel panelSuperior;
    private JTextField txtRuta;
    private JTextField txtBusqueda;
    private JTextArea areaResultados;
    private JButton btnAnalizar;
    private JLabel lblRuta;
    private JLabel lblBusqueda;
    private JScrollPane scroll;
    
    public VentanaPrincipal() {
        setSize(700,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        panelSuperior=new JPanel();
        panelSuperior.setLayout(new GridLayout(3,2,10,10));
        
        lblRuta=new JLabel("Ruta del Directorio");
        txtRuta=new JTextField();
        
        lblBusqueda=new JLabel ("Nombre a Buscar");
        txtBusqueda=new JTextField();
        
        btnAnalizar=new JButton("Analizar");
        btnAnalizar.addActionListener(e->{
            analizar();
        });
        
        panelSuperior.add(lblRuta);
        panelSuperior.add(txtRuta);
        panelSuperior.add(lblBusqueda);
        panelSuperior.add(txtBusqueda);
        panelSuperior.add(new JLabel(""));
        
        panelSuperior.add(btnAnalizar);
        
        areaResultados=new JTextArea();
        areaResultados.setEditable(false);
        
        scroll=new JScrollPane(areaResultados);
        
        add(panelSuperior, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        
        setVisible(true);
    }
    
    private void analizar(){
        areaResultados.setText("");
        
        String ruta=txtRuta.getText();
        String textoBusqueda=txtBusqueda.getText();
        
        File directorio=new File(ruta);
        
        if(!directorio.exists()){
            JOptionPane.showMessageDialog(this, "La ruta no existe");
            return;
        }
        
        if(!directorio.isDirectory()){
            JOptionPane.showMessageDialog(this, "Este no es un directorio");
            return;
        }
        
        AnalizadorArchivos analizador=new AnalizadorArchivos();
        analizador.contarArchivos(directorio);
        
        areaResultados.append("CONTEO DE ARCHIVOS \n");
        areaResultados.append(".txt: "+analizador.getTxt()+"\n");
        areaResultados.append(".java: "+analizador.getJava()+"\n");
        areaResultados.append(".pdf: "+analizador.getPdf()+"\n");
        areaResultados.append("Otros: "+analizador.getOtros()+"\n");
        
        analizador.buscarArchivos(directorio, textoBusqueda);
        
        areaResultados.append("\nRESULTADOS BUSQUEDA\n");
        if(analizador.getResultadoBusqueda().isEmpty()){
            areaResultados.append("No se encontraron archivos que cumplan con los criterios");
            return;
        }
        mostrarRutasEncontradas(analizador.getResultadoBusqueda(), 0);
        
        
    }
    
    private void mostrarRutasEncontradas(ArrayList<String> resultadosBusqueda, int indice){
        if(resultadosBusqueda.size()>indice){
            String rutaEncontrada=resultadosBusqueda.get(indice);
            areaResultados.append(rutaEncontrada+" \n");
            mostrarRutasEncontradas(resultadosBusqueda, indice+1);
        }
    }
    
}
