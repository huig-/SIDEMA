package icaro.aplicaciones.ventana;

import icaro.aplicaciones.SIDEMA.informacion.Celda;
import icaro.aplicaciones.SIDEMA.informacion.Mapa;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class Ventana extends JFrame{
	
	/*Creacion de la ventana principal de simulacion.
	 * Eleccion del tama��o de la ventana en funcion del tama��o del monitor. 
	 * Creaci��n de la vista principal.
	 */
	
	public Ventana(/*Escenario escLog*/){
		
		//Creacion de la ventana
		super("SIDEMA - Escenario Simulacion");
		dim=super.getToolkit().getScreenSize();
        dim.setSize(dim.getWidth()*0.8, dim.getHeight()*0.8);
		this.setSize(dim);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//Creacion de la barra de menus
		menu1 = new JMenu("Archivo");
		menu2 = new JMenu("Edicion");
		
		item1 = new JMenuItem("Guardar");
		item3 = new JMenuItem("Elegir Escenario");
		item1.setEnabled(true);
		item1.setActionCommand("Guardar");
		item3.setActionCommand("Cargar");
		item3.setEnabled(true);
		
		item2 = new JMenuItem("Crear Escenario");
		item1.setActionCommand("Crear");
		item2.setEnabled(true);
		
		barra = new JMenuBar();
		
		barra.add(menu1);
		barra.add(menu2);
		menu1.add(item1);
		menu1.add(item3);
		menu2.add(item2);
		
		this.setJMenuBar(barra);
		
		this.setLayout(new BorderLayout());
		
		//Creacion del mapa de simulacion.
		//Lo quitaremos cuando este creado el controlador y se crear�� al pulsar cargar
		centro = new JPanel();
		
		//Creacion de un mapa por defecto
		Celda[][] celdas = new Celda[2][2];
		celdas[0][0] = new Celda(0,0,true,false);
		celdas[0][1] = new Celda(0,1,true,false);
		celdas[1][0] = new Celda(1,0,true,false);
		celdas[1][1] = new Celda(1,1,true,true);
		
		
		escenario = new VisualEscenario(new Mapa(celdas));
		centro.add(escenario);
		this.add(escenario,BorderLayout.CENTER);
		
		botonera = new JPanel(new FlowLayout());
		simular = new Boton("Simular");
		simular.setActionCommand("Simular");
		simular.setText("Simular");
		
		botonera.add(simular);
		terminar = new Boton("Terminar");
		terminar.setActionCommand("Terminar");
		terminar.setText("Terminar Simulacion");
		botonera.add(terminar);
		datos = new JTextPane();
		datos.setEditable(false);
		datos.setText("");
		datos.setSize(50, 80);
		botonera.add(datos);
		
		this.add(botonera, BorderLayout.SOUTH);
		
	}
	/*
	public void cargarEscenario(Escenario esc){
		escenario = new VisualEscenario();
		this.add(escenario,BorderLayout.CENTER);
	}
	
	public void ObtenerDatos(Escenario esc){
		//procesar escenario
		datos.setText("");
	}
	*/
	
	private Dimension dim;
	private JMenu menu1, menu2;
	private JMenuBar barra;
	private JMenuItem item1,item2,item3;
	private Boton simular, terminar;
	private JTextPane datos;
	private JPanel botonera,centro;
	private VisualEscenario escenario;
}
