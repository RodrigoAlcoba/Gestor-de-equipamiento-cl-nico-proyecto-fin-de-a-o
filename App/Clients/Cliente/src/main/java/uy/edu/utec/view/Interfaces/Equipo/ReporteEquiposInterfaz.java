package uy.edu.utec.view.Interfaces.Equipo;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.Base64Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ReporteEquiposInterfaz {
    private JPanel panel;
    private JTextField inputNombre;
    private JButton buscarButton;
    private JButton cancelarButton;
    private JComboBox comboBoxTipoEquipo;
    private JDateChooser dateChooser;
    private JComboBox comboBoxUbicacion;
    private JTextPane textPaneEquipos;
    private JRadioButton cbNombre;
    private JRadioButton cbTipoEquipo;
    private JRadioButton cbMarca;
    private JRadioButton cbModelo;
    private JRadioButton cbNumSerie;
    private JRadioButton cbPais;
    private JRadioButton cbProveedor;
    private JRadioButton cbFecAdq;
    private JRadioButton cbIdInterna;
    private JRadioButton cbUbicacion;
    private JComboBox comboboxMarca;
    private JComboBox comboboxModelo;
    private JTextField inputNumSerie;
    private JComboBox comboboxPais;
    private JComboBox comboboxProveedor;
    private JTextField inputIdInterna;
    private JTable tablaEquipos;
    private JScrollPane scrollPane;
    private JButton limpiarButton;
    private JLabel labelEquipo;


    //
    private ButtonGroup radioButtons = new ButtonGroup();

    private List<EquipoDTO> equipos;
    private ControllerGeneral controllerGeneral;
    private JFrame frame;

    public ReporteEquiposInterfaz(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.controllerGeneral = controllerGeneral;
        frame = new JFrame("Reporte de Equipos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setSize(600, 500);

        radioButtons.add(cbNombre);
        radioButtons.add(cbTipoEquipo);
        radioButtons.add(cbMarca);
        radioButtons.add(cbModelo);
        radioButtons.add(cbNumSerie);
        radioButtons.add(cbPais);
        radioButtons.add(cbProveedor);
        radioButtons.add(cbFecAdq);
        radioButtons.add(cbIdInterna);
        radioButtons.add(cbUbicacion);

        //JScrollPane
        scrollPane.createHorizontalScrollBar();

        //tipoEquipo
        DefaultComboBoxModel<String> comboBoxModelTipoEquipo = new DefaultComboBoxModel<>();
        TipoEquipoController tipoEquipoController = new TipoEquipoController(controllerGeneral);
        List<TipoEquipoDTO> tipoEquipoDTOList = tipoEquipoController.obtenerTipoEquiposActivos();
        for(TipoEquipoDTO tipoEquipoDTO : tipoEquipoDTOList){
            comboBoxModelTipoEquipo.addElement(String.valueOf(tipoEquipoDTO.getNomTipo()));
        }
        comboBoxTipoEquipo.setModel(comboBoxModelTipoEquipo);

        //Marcas
        DefaultComboBoxModel<String> comboBoxModelMarca = new DefaultComboBoxModel<>();
        MarcaController marcaController = new MarcaController(controllerGeneral);
        List<MarcaDTO> marcaList = marcaController.obtenerTodasLasMarcas();
        for(MarcaDTO marca: marcaList){
            comboBoxModelMarca.addElement(marca.getNombreMarca());
        }
        comboboxMarca.setModel(comboBoxModelMarca);

        //Modelos
        DefaultComboBoxModel<String> comboBoxModelModelos = new DefaultComboBoxModel<>();
        ModeloController modeloController = new ModeloController(controllerGeneral);
        List<ModeloDTO> modeloDTOList = modeloController.obtenerTodosLosModelos();
        for(ModeloDTO modeloDTO: modeloDTOList){
            comboBoxModelModelos.addElement(modeloDTO.getNombreModelo());
        }
        comboboxModelo.setModel(comboBoxModelModelos);

        //Paises
        DefaultComboBoxModel<String> comboBoxModelPais = new DefaultComboBoxModel<>();
        PaisController paisController = new PaisController(controllerGeneral);
        List<PaisDTO> paisList = paisController.obtenerTodosLosPaisesActivos();
        for(PaisDTO paisDTO : paisList){
            comboBoxModelPais.addElement(paisDTO.getNombre());
        }
        comboboxPais.setModel(comboBoxModelPais);

        //Proveedores
        DefaultComboBoxModel<String> comboBoxModelProveedor = new DefaultComboBoxModel<>();
        ProveedorController proveedorController = new ProveedorController(controllerGeneral);
        List<ProveedorDTO> proveedorDTOList = proveedorController.obtenerTodosLosProveedoresActivos();
        for(ProveedorDTO proveedorDTO: proveedorDTOList){
            comboBoxModelProveedor.addElement(proveedorDTO.getNomProveedor());
        }
        comboboxProveedor.setModel(comboBoxModelProveedor);

        //Ubicaciones
        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionList = ubicacionController.obtenerTodasLasUbicaciones();
        for(UbicacionDTO  ubicacion : ubicacionList){
            comboBoxModelUbicacion.addElement(ubicacion.getNombre());
        }
        comboBoxUbicacion.setModel(comboBoxModelUbicacion);

        //JTable

        DefaultTableModel modeloTabla = new DefaultTableModel(
                new Object[]{"Imagen",
                        "Nombre", "Tipo", "Modelo",
                        "Num-serie", "País", "Proveedor",
                        "Fecha-adq", "Id-Interna", "Ubicacion", "Sector"}, 0){
            //se indican las celdas que no son String
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) {
                    return ImageIcon.class;
                }
                return super.getColumnClass(columnIndex);
            }
        };
        //agregar altura y ancho automatico
        tablaEquipos.setRowHeight(40);
        tablaEquipos.setAutoCreateRowSorter(true);
        tablaEquipos.setRowMargin(5);
        tablaEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

        scrollPane.setViewportView(tablaEquipos);


       tablaEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
       tablaEquipos.setModel(modeloTabla);
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
            }
        });
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    EquipoController equipoController = new EquipoController(controllerGeneral);
                   equipos = new ArrayList<>();
                    if (cbNombre.isSelected()){
                        equipos = equipoController.obtenerEquiposPorNombre(inputNombre.getText().trim());
                    }else if(cbTipoEquipo.isSelected()){
                        equipos =
                                equipoController.obtenerEquiposPorTipoDeEquipo(controllerGeneral.tipoEquipoController().obtenerTipoEquipoPorNombre(Objects.requireNonNull(comboBoxTipoEquipo.getSelectedItem()).toString()).getId());
                    }else if(cbMarca.isSelected()){
                        equipos = equipoController.obtenerEquiposPorMarca(controllerGeneral.getMarcaController().obtenerMarcaPorNombre(comboboxMarca.getSelectedItem().toString()).getIdMarca());
                    }else if(cbModelo.isSelected()){
                        ModeloController modeloController = new ModeloController(controllerGeneral);
                        equipos =
                               equipoController.obtenerEquiposPorModelo(modeloController.obtenerModeloPorNombre(comboboxModelo.getSelectedItem().toString()).getIdModelo());
                    }else if(cbNumSerie.isSelected()){
                        equipos =
                                equipoController.obtenerEquiposPorNumeroSerie(Integer.parseInt(inputNumSerie.getText()));
                    }else if(cbPais.isSelected()){
                        equipos =
                                equipoController.obtenerEquiposPorPais(controllerGeneral.getPaisController().obtenerPaisPorNombre(comboboxPais.getSelectedItem().toString()).getID_Pais());
                    }else if(cbProveedor.isSelected()){
                        equipos =
                                equipoController.obtenerEquiposPorProveedor(controllerGeneral.getProveedorController().obtenerProveedorPorNombre(comboboxProveedor.getSelectedItem().toString()).getIdProveedor());
                    }else  if(cbFecAdq.isSelected()){
                        Date fecha_nac = dateChooser.getDate();
                        if (fecha_nac == null) {
                            throw new ServiciosException("Debe ingresar una fecha de nacimiento");
                        }
                        equipos = equipoController.obtenerEquiposPorFecAdquisicion(fecha_nac);
                    }else if(cbIdInterna.isSelected()){
                        equipos =
                                equipoController.buscarEquiposPoridInterna(Integer.valueOf(inputIdInterna.getText().trim()));
                    }else if(cbUbicacion.isSelected()){
                        equipos =
                                equipoController.obtenerEquiposPorUbicacion(controllerGeneral.getUbicacionController().
                                        obtenerUbicacionPorNombre(comboBoxUbicacion.getSelectedItem().toString()).getIdUbicacion());
                    }else{
                        equipos = equipoController.obtenerTodosLosEquipos();
                    }

                } catch (ServiciosException | SQLException ex) {
                    throw new RuntimeException(ex);
                }
                try {
                    mostrarListadoEquipos(equipos);
                } catch (IOException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               radioButtons.clearSelection();
                ((DefaultTableModel) tablaEquipos.getModel()).setRowCount(0);
            }
        });
    }

    public void setVisible(boolean visible){
        frame.setVisible(visible);
    }

    public void mostrarListadoEquipos(List<EquipoDTO> equipos) throws IOException, ServiciosException {
        ((DefaultTableModel) tablaEquipos.getModel()).setRowCount(0);

        Image imagenRedimensionada;
        ModeloController modeloController = new ModeloController(controllerGeneral);


        for (EquipoDTO equipoDTO : equipos) {
            //Imagen
            File imagen;
            BufferedImage bufferedImage = null;

            if (equipoDTO.getImagen() != null) {
                imagen = Base64Utils.base64ToImage(equipoDTO.getImagen());
                bufferedImage = ImageIO.read(imagen);
            }

            if (bufferedImage != null) {
                imagenRedimensionada = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            } else {
                imagen = new File("App/Clients/Cliente/src/Imagenes/imagenPrueba.png");
                bufferedImage = ImageIO.read(imagen);
                imagenRedimensionada = bufferedImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            }

            //Para añadir una fila nueva con los datos de un equipo

            Object[] filaEquipo = {
                    new ImageIcon(imagenRedimensionada),
                    equipoDTO.getNomEquipo(),
                    controllerGeneral.tipoEquipoController().obtenerTipoEquipoPorId(equipoDTO.getTipoEquipoId()).getNomTipo(),
                    modeloController.obtenerModeloPorId(equipoDTO.getModeloId()).getNombreModelo(),
                    equipoDTO.getNumSerie().toString(),
                    controllerGeneral.getPaisController().obtenerPaisPorId(equipoDTO.getPaisId()).getNombre(),
                    controllerGeneral.getProveedorController().obtenerProveedorPorId(equipoDTO.getProveedorId()).getNomProveedor(),
                    equipoDTO.getFecAdquisicion(),
                    equipoDTO.getIdInterna().toString(),
                    controllerGeneral.getUbicacionController().obtenerUbicacionPorId
                            (controllerGeneral.movEquipoController().obtenerUbicacionActualByEquipoId(equipoDTO.getIdEquipo()).getUbicacionId()).getNombre(),
                    controllerGeneral.getSectorController().buscarPorId(controllerGeneral.getUbicacionController().obtenerUbicacionPorId
                            (controllerGeneral.movEquipoController().obtenerUbicacionActualByEquipoId(equipoDTO.getIdEquipo()).getUbicacionId()).getSectorId()).getNombre()
            };

            ((DefaultTableModel)  tablaEquipos.getModel()).addRow(filaEquipo);

        }
    }

    public JPanel getPanel(){
        return panel;
    }

    private void createUIComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
    }
}
