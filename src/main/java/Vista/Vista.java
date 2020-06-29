package Vista;

import Controlador.Controlador;
import Excepciones.ExcepcionYaExisteReceta;
import Excepciones.NoExisteReceta;
import Modelo.Datos.Dificultad;
import Modelo.Datos.Receta;
import Modelo.Datos.Tipo;
import Modelo.ImplementacionModelo;
import com.sun.xml.internal.bind.WhiteSpaceProcessor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vista implements InformaVista,InterrogaVista{
    private ImplementacionModelo modelo;
    private Controlador controlador;
    private JFrame ventana;
    Container contenedor;
    JPanel top=null;
    JPanel mid=null;
    JPanel bot=null;
    JPanel dificultadPanel=null;
    JPanel ingredientesPanel=null;
    JTextField ingredientesFiltro=null;
    JRadioButton principianteFiltro=null;
    JRadioButton normalFiltro=null;
    JRadioButton expertoFiltro=null;
    JRadioButton todosFiltro=null;
    JButton filtrar=null;

    JPanel lista=null;
    //    JList<Tarea>=null;
    JTable jt=null;
    JTextField nombre=null;
    JTextField preparacion=null;
    JTextField ingredientes=null;

    JRadioButton sopas=null;
    JRadioButton entrantes=null;
    JRadioButton pasta=null;
    JRadioButton pescados=null;
    JRadioButton carnes=null;
    JRadioButton postres=null;
    JRadioButton principiante=null;
    JRadioButton normal=null;
    JRadioButton experto=null;
    JButton nuevo=null;
    JButton actualiza=null;
    JButton borra=null;
    DefaultTableModel model=null;

    List<Receta> recetas=null;
    List<Receta> recetasLista=new ArrayList<Receta>();
    public Vista() {

    }
    public void setModelo(ImplementacionModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }
    public void iniciaGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VistaPrincipal();

            }
        });
    }
    public void VistaPrincipal(){
        ventana=new JFrame("Examen David Nieto");
        recetas=controlador.getRecetas();

        contenedor=ventana.getContentPane();
        top=new JPanel();
        top.setBackground(Color.darkGray);
        mid=new JPanel();
        mid.setBackground(Color.darkGray);
        mid.setSize(400,200);
        bot=new JPanel();
        bot.setBackground(Color.darkGray);
//        cargarRecetas();
        contenedor.add(top,BorderLayout.NORTH);
        contenedor.add(mid,BorderLayout.CENTER);
        contenedor.add(bot,BorderLayout.SOUTH);
        VistaFiltros();
        VistaLista();
        VistaInfo();
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
        ventana.setSize(1200,800);
        top.updateUI();

        //EN VEZ DE GUARDAR LOS DATOS AL CERRAR LA APLICACIÓN, GUARDO LOS DATOS CADA VEZ QUE SE MODIFICA ALGÚN VALOR EN LAS TAREAS.
        //HAGO ESTO POR SI SE PRODUCE ALGÚN CIERRE INESPERADO DE LA APLICACIÓN QUE NO SE PIERDEN LOS DATOS.
        //AL TRATARSE DE DATOS CON MUY POCO PESO NO HAY NINGÚN INCONVENIENTE AL REALIZAR EL GUARDADO MÁS FRECUENTEMENTE.
        //LO HE VISTO CÓMO UNA MEJORA

    }

    private void VistaFiltros() {
        dificultadPanel=new JPanel();

        dificultadPanel.setBackground(Color.darkGray);
        //FILTRO PRIORIDAD
        ButtonGroup dificultad=new ButtonGroup();
//        EscuchadorFiltrar listener=new EscuchadorFiltrar();
        principianteFiltro=new JRadioButton("Principiante");
//        principiante.addActionListener(listener);
        principianteFiltro.setForeground(Color.white);
        principianteFiltro.setBackground(Color.darkGray);
        normalFiltro=new JRadioButton("Normal");
//        normalFiltro.addActionListener(listener);
        normalFiltro.setForeground(Color.white);
        normalFiltro.setBackground(Color.darkGray);
        expertoFiltro=new JRadioButton("Experto");
//        expertoFiltro.addActionListener(listener);
        expertoFiltro.setForeground(Color.white);
        expertoFiltro.setBackground(Color.darkGray);
        todosFiltro=new JRadioButton("TODOS");
//        todosFiltro.addActionListener(listener);
        todosFiltro.setForeground(Color.white);
        todosFiltro.setBackground(Color.darkGray);
        todosFiltro.setSelected(true);
        dificultad.add(principianteFiltro);
        dificultad.add(normalFiltro);
        dificultad.add(expertoFiltro);
        dificultad.add(todosFiltro);
        dificultadPanel.add(principianteFiltro);
        dificultadPanel.add(normalFiltro);
        dificultadPanel.add(expertoFiltro);
        dificultadPanel.add(todosFiltro);
        //FILTRO Ingredientes

        ingredientesPanel=new JPanel();
        ingredientesPanel.setBackground(Color.DARK_GRAY);
        JLabel ingredientesLabel= new JLabel("Ingredientes");
        ingredientesLabel.setForeground(Color.WHITE);
        ingredientesFiltro=new JTextField(50);
        ingredientesPanel.add(ingredientesLabel);
        ingredientesPanel.add(ingredientesFiltro);




        dificultadPanel.setLayout(new BoxLayout(dificultadPanel,BoxLayout.Y_AXIS));
        ingredientesPanel.setLayout(new BoxLayout(ingredientesPanel,BoxLayout.Y_AXIS));

        top.add(dificultadPanel,BorderLayout.NORTH);
        top.add(ingredientesPanel,BorderLayout.CENTER);

        dificultadPanel.updateUI();
        ingredientesPanel.updateUI();
        top.updateUI();
    }

    private void VistaLista(){
//        mid.removeAll();


        mid.removeAll();


        model= new DefaultTableModel();

        model.addColumn("NOMBRE");
        model.addColumn("TIPO");
        model.addColumn("DIFICULTAD");
        model.addColumn("PREPARACION");
        model.addColumn("INGREDIENTES");


        for(Receta t:recetasLista){

            model.addRow(new Object[]{t.getNombre(),t.getTipo(),t.getDificultad(),t.getPreparacion(),t.getIngredientes()});

        }

        jt=new JTable(model);
        jt.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                // do some actions here, for example
                // print first column value from selected row
                System.out.println(jt.getSelectedRow()+"YEEEEEEPA");
                if(jt.getSelectedRow()==-1)
                    return;
                nombre.setText(jt.getValueAt(jt.getSelectedRow(), 0).toString());
                preparacion.setText(jt.getValueAt(jt.getSelectedRow(), 3).toString());
                String ings="";
                for(String t: (List<String>)jt.getValueAt(jt.getSelectedRow(), 4))
                    ings+=t.trim();
                ingredientes.setText(ings);


                if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Sopas)){
                    sopas.setSelected(true);
                    sopas.updateUI();
                }else if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Entrantes)){
                    entrantes.setSelected(true);
                    entrantes.updateUI();
                }else if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Pasta)){
                    pasta.setSelected(true);
                    pasta.updateUI();
                }else if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Pescados)){
                    pescados.setSelected(true);
                    pescados.updateUI();
                }else if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Carnes)){
                    carnes.setSelected(true);
                    carnes.updateUI();
                }else if((jt.getValueAt(jt.getSelectedRow(), 1)).equals(Tipo.Postres)){
                    postres.setSelected(true);
                    postres.updateUI();
                }
                if((jt.getValueAt(jt.getSelectedRow(), 2).equals(Dificultad.Principiante))){
                    principiante.setSelected(true);
                }else if(jt.getValueAt(jt.getSelectedRow(), 2).equals(Dificultad.Normal)) {
                    normal.setSelected(true);
                }else if(jt.getValueAt(jt.getSelectedRow(), 2).equals(Dificultad.Experto)) {
                    experto.setSelected(true);
                }

            }
        });
//        jt.getColumnModel().getColumn(3).setMaxWidth(0);
//        jt.getColumnModel().getColumn(3).setMinWidth(0);
//        jt.getColumnModel().getColumn(3).setPreferredWidth(0);
//        jt.getColumnModel().getColumn(3).setResizable(false);
//
//        jt.getColumnModel().getColumn(4).setMaxWidth(0);
//        jt.getColumnModel().getColumn(4).setMinWidth(0);
//        jt.getColumnModel().getColumn(4).setPreferredWidth(0);
//        jt.getColumnModel().getColumn(4).setResizable(false);

//        jt.setPreferredSize(false);
//        jt.setBounds(5,5,100,50);
        JScrollPane sp=new JScrollPane(jt);



        mid.add(sp);
        jt.updateUI();
        mid.updateUI();


    }
    public void VistaInfo(){
        JPanel info=new JPanel();
        info.setBackground(Color.darkGray);
        JPanel nombrePanel=new JPanel();
        nombrePanel.setBackground(Color.DARK_GRAY);
        JLabel nombreLabel=new JLabel("Nombre");
        nombreLabel.setForeground(Color.white);
        nombre=new JTextField(30);
        nombrePanel.add(nombreLabel);
        nombrePanel.add(nombre);


        JPanel ingredientesInfoPanel= new JPanel();
        JLabel ingredientesLabelInfo= new JLabel("Ingredientes: ");
        ingredientes= new JTextField(50);
        ingredientesInfoPanel.add(ingredientesLabelInfo);
        ingredientesInfoPanel.add(ingredientes);

        JPanel preparacionPanel= new JPanel();
        preparacionPanel.setBackground(Color.DARK_GRAY);
        preparacion=new JTextField(80);
//        preparacion.setForeground();



        JLabel preparacionLabel=new JLabel("Preparación");
        preparacionLabel.setForeground(Color.white);

        preparacionPanel.add(preparacionLabel);

        preparacionPanel.add(preparacion);

        JPanel tipoPanel=new JPanel();
        tipoPanel.setBackground(Color.darkGray);
        JLabel tipo=new JLabel("Tipo");
        tipo.setForeground(Color.white);
        sopas=new JRadioButton("Sopas");
        sopas.setSelected(true);
        sopas.setBackground(Color.darkGray);
        sopas.setForeground(Color.white);

        entrantes=new JRadioButton("Entrantes");
        entrantes.setBackground(Color.darkGray);
        entrantes.setForeground(Color.white);

        pasta=new JRadioButton("Pasta");
        pasta.setBackground(Color.darkGray);
        pasta.setForeground(Color.white);

        pescados=new JRadioButton("Pescados");
        pescados.setBackground(Color.darkGray);
        pescados.setForeground(Color.white);

        carnes=new JRadioButton("Carnes");
        carnes.setBackground(Color.darkGray);
        carnes.setForeground(Color.white);

        postres=new JRadioButton("Postres");
        postres.setBackground(Color.darkGray);
        postres.setForeground(Color.white);

        ButtonGroup tipoGroup= new ButtonGroup();
        tipoGroup.add(sopas);
        tipoGroup.add(entrantes);
        tipoGroup.add(pasta);
        tipoGroup.add(pescados);
        tipoGroup.add(carnes);
        tipoGroup.add(postres);

        tipoPanel.add(tipo);
        tipoPanel.add(sopas);
        tipoPanel.add(entrantes);
        tipoPanel.add(pasta);
        tipoPanel.add(pescados);
        tipoPanel.add(carnes);
        tipoPanel.add(postres);

        JPanel dificultadPanel= new JPanel();
        ButtonGroup prin= new ButtonGroup();
        principiante=new JRadioButton("Principiante");
        principiante.setBackground(Color.darkGray);
        principiante.setForeground(Color.white);
        normal=new JRadioButton("Normal");
        normal.setBackground(Color.darkGray);
        normal.setForeground(Color.white);
        experto=new JRadioButton("Pasta");
        experto.setBackground(Color.darkGray);
        experto.setForeground(Color.white);
        prin.add(principiante);
        prin.add(normal);
        prin.add(experto);

        dificultadPanel.add(principiante);
        dificultadPanel.add(normal);
        dificultadPanel.add(experto);




        JPanel botones=new JPanel();
        botones.setBackground(Color.DARK_GRAY);
        Escuchador listener=new Escuchador();
        nuevo=new JButton("NUEVO");
        nuevo.setBackground(Color.lightGray);
        nuevo.addActionListener(listener);
        actualiza=new JButton("ACTUALIZA");
        actualiza.setBackground(Color.lightGray);
        actualiza.addActionListener(listener);
        borra=new JButton("BORRA");
        borra.setBackground(Color.lightGray);
        borra.addActionListener(listener);

        botones.add(nuevo);
        botones.add(actualiza);
        botones.add(borra);

//
        info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.add(nombrePanel);
        info.add(preparacionPanel);
        info.add(ingredientesInfoPanel);
        info.add(tipoPanel);
        info.add(dificultadPanel);
//        info.add(prioridadPanel,new BoxLayout(prioridadPanel,BoxLayout.Y_AXIS));
        info.add(botones);

        bot.add(info,new BoxLayout(bot,BoxLayout.Y_AXIS));
        bot.updateUI();
    }

    public void actualizaLista() {
        recetasLista=controlador.getRecetas();
    }

    class Escuchador implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            JButton boton = (JButton)e.getSource();
            String texto = boton.getText();
            if ("NUEVO".equals(texto)) {
                System.out.println("QUE PASAS");
                try {
                    controlador.crearReceta();
                    reiniciaTabla();

                } catch (ExcepcionYaExisteReceta excepcionYaExisteReceta) {
                    excepcionYaExisteReceta.printStackTrace();
                }
            } else if ("ACTUALIZA".equals(texto)) {
                try {
                    controlador.eliminarReceta();
                } catch (NoExisteReceta noExisteReceta) {
                    noExisteReceta.printStackTrace();

                }
                try {
                    controlador.crearReceta();

                    reiniciaTabla();

                } catch (ExcepcionYaExisteReceta excepcionYaExisteReceta) {
                    excepcionYaExisteReceta.printStackTrace();
                }
            } else if ("BORRA".equals(texto)) {
                try {
                    controlador.eliminarReceta();

                    reiniciaTabla();
                } catch (NoExisteReceta noExisteReceta) {
                    noExisteReceta.printStackTrace();
                }
            }
        }
    }




    public void nuevaReceta() {

    }

    public void eliminaReceta() {

    }

//    public void cargarRecetas() {
//        List<Receta> r= controlador.getRecetas();
//        recetasLista=new ArrayList<Receta>();
//        for(Receta receta:r)
//            recetasLista.add(receta);
//
//    }

    public Receta getReceta() {
        List<String> ing= Arrays.asList(ingredientes.getText().trim().split(","));
        Receta rece= new Receta(nombre.getText(),preparacion.getText(),ing);
        if(sopas.isSelected()){
            rece.setTipo(Tipo.Sopas);
        }else if(entrantes.isSelected()){
            rece.setTipo(Tipo.Entrantes);
        }else if(pasta.isSelected()){
            rece.setTipo(Tipo.Pasta);
        }else if(pescados.isSelected()){
            rece.setTipo(Tipo.Pescados);
        }else if(carnes.isSelected()){
            rece.setTipo(Tipo.Carnes);
        }else if(postres.isSelected()){
            rece.setTipo(Tipo.Postres);
        }

        if(principiante.isSelected()){
            rece.setDificultad(Dificultad.Principiante);
        }else if(normal.isSelected()){
            rece.setDificultad(Dificultad.Normal);
        }else if(experto.isSelected()){
            rece.setDificultad(Dificultad.Experto);
        }

        return rece;
    }
    public void reiniciaTabla(){
        for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
            System.out.println("BORRADO ");
            model.removeRow(i);
        }
        System.out.println("ACTUALIZO LISTA ");
        actualizaLista();
        System.out.println("EJECUTO VISTA LISTA ");
        VistaLista();
        jt.updateUI();
    }
}
