package uy.edu.utec.view.Interfaces.Equipo;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.Base64Utils;
import uy.edu.utec.servers.utils.DateMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ModificarEquipoInterfaz {
    private JTextField NomEquipoInterfaz;
    private JTextField NumSerieInput;
    private JButton guardarButton;
    private JButton cancelarButton;
    private JPanel panel;
    private JComboBox comboBoxIdEquipo;
    private JComboBox comboBoxTipoEquipo;
    private JComboBox comboBoxMarca;
    private JComboBox comboBoxModelo;
    private JDateChooser dataChooserGarantia;
    private JComboBox comboBoxPais;
    private JComboBox comboBoxProveedor;
    private JDateChooser dateChooser;
    private JComboBox comboBoxUbicacion;
    private JFileChooser fileChooser;
    private JTextField textFieldIdInterna;
    private JLabel labelImgen;
    private JButton modificarImagenButton;
    private JPanel panelContenedorImagen;
    private JPanel imgPanel;

    ControllerGeneral controllerGeneral;

    List<Component> componentes = new ArrayList<>();

    public ModificarEquipoInterfaz(ControllerGeneral controllerGeneral) throws SQLException, ServiciosException {
        this.controllerGeneral = controllerGeneral;

        componentes.add(comboBoxTipoEquipo);
        componentes.add(comboBoxMarca);
        componentes.add(comboBoxModelo);
        componentes.add(dataChooserGarantia);
        componentes.add(comboBoxPais);
        componentes.add(comboBoxProveedor);
        componentes.add(dateChooser);
        componentes.add(comboBoxUbicacion);
        componentes.add(fileChooser);
        setEnabled(false);

        fileChooser.setVisible(false);

        //Combobox Id Equipo
        DefaultComboBoxModel<String> comboBoxModelIdEquipo = new DefaultComboBoxModel<>();
        comboBoxModelIdEquipo.addElement(" ");
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOList = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipoDTOList){
            comboBoxModelIdEquipo.addElement(String.valueOf(equipoDTO.getIdEquipo()));
        }
        comboBoxIdEquipo.setModel(comboBoxModelIdEquipo);

        //tipoEquipo
        DefaultComboBoxModel<String> comboBoxModelTipoEquipo = new DefaultComboBoxModel<>();
        TipoEquipoController tipoEquipoController = new TipoEquipoController(controllerGeneral);
        List<TipoEquipoDTO> tipoEquipoDTOList = tipoEquipoController.obtenerTipoEquiposActivos();
        for(TipoEquipoDTO tipoEquipoDTO : tipoEquipoDTOList){
            comboBoxModelTipoEquipo.addElement(String.valueOf(tipoEquipoDTO.getNomTipo()));
        }
        comboBoxTipoEquipo.setModel(comboBoxModelTipoEquipo);

        //Proveedores
        DefaultComboBoxModel<String> comboBoxModelProveedor = new DefaultComboBoxModel<>();
        ProveedorController proveedorController = new ProveedorController(controllerGeneral);
        List<ProveedorDTO> proveedorDTOList = proveedorController.obtenerTodosLosProveedoresActivos();
        for(ProveedorDTO proveedorDTO: proveedorDTOList){
            comboBoxModelProveedor.addElement(proveedorDTO.getNomProveedor());
        }
        comboBoxProveedor.setModel(comboBoxModelProveedor);

        //Modelos
        DefaultComboBoxModel<String> comboBoxModelModelos = new DefaultComboBoxModel<>();
        ModeloController modeloController = new ModeloController(controllerGeneral);
        List<ModeloDTO> modeloDTOList = modeloController.obtenerTodosLosModelos();
        for(ModeloDTO modeloDTO: modeloDTOList){
            comboBoxModelModelos.addElement(modeloDTO.getNombreModelo());
        }
        comboBoxModelo.setModel(comboBoxModelModelos);

        //Marcas
        DefaultComboBoxModel<String> comboBoxModelMarca = new DefaultComboBoxModel<>();
        MarcaController marcaController = new MarcaController(controllerGeneral);
        List<MarcaDTO> marcaList = marcaController.obtenerTodasLasMarcas();
        for(MarcaDTO marca: marcaList){
            comboBoxModelMarca.addElement(marca.getNombreMarca());
        }
        comboBoxMarca.setModel(comboBoxModelMarca);

        //Paises
        DefaultComboBoxModel<String> comboBoxModelPais = new DefaultComboBoxModel<>();
        PaisController paisController = new PaisController(controllerGeneral);
        List<PaisDTO> paisList = paisController.obtenerTodosLosPaisesActivos();
        for(PaisDTO paisDTO : paisList){
            comboBoxModelPais.addElement(paisDTO.getNombre());
        }
        comboBoxPais.setModel(comboBoxModelPais);

        //Ubicaciones
        DefaultComboBoxModel<String> comboBoxModelUbicacion = new DefaultComboBoxModel<>();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        List<UbicacionDTO> ubicacionList = ubicacionController.obtenerTodasLasUbicaciones();
        for(UbicacionDTO  ubicacion : ubicacionList){
            comboBoxModelUbicacion.addElement(ubicacion.getNombre());
        }
        comboBoxUbicacion.setModel(comboBoxModelUbicacion);

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
                try {
                    preRellenarCampos();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ServiciosException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        modificarEquipo();
                        mostrarMensaje("Equipo modificado correctamente.");
                        volverPrincipal();
                    } catch (ServiciosException ex) {
                        JOptionPane.showMessageDialog(null,
                            "Ha ocurrido un error al modificar un equipo: \n" + ex.getMessage());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    mostrarMensaje("Se ha cancelado el ingreso del equipo con éxito.");
                }
            }
        });
        comboBoxIdEquipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    preRellenarCampos();
                    fileChooser.setVisible(false);
                    modificarImagenButton.setVisible(true);
                    panelContenedorImagen.setVisible(true);


                } catch (SQLException | ServiciosException ex) {
                    throw new RuntimeException(ex);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        modificarImagenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarImagenButton.setVisible(false);
                panelContenedorImagen.setVisible(false);
                fileChooser.setVisible(true);
            }
        });
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //Mostrar imagen
                    File imagen = fileChooser.getSelectedFile();
                    BufferedImage bufferedImage = null;
                    bufferedImage = ImageIO.read(imagen);
                    Image imagenRedimensionada = bufferedImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                    labelImgen.setIcon(new ImageIcon(imagenRedimensionada));
                    setEnabled(true);
                    modificarImagenButton.setVisible(true);
                    panelContenedorImagen.setVisible(true);
                    fileChooser.setVisible(false);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

    };

    public void preRellenarCampos() throws SQLException, ServiciosException, IOException {
        EquipoController equipoController = new EquipoController(controllerGeneral);
        if (comboBoxIdEquipo.getSelectedItem() != null && !comboBoxIdEquipo.getSelectedItem().toString().isEmpty()){
            EquipoDTO equipoOriginal = equipoController.obtenerEquipoPorId(Integer.parseInt(comboBoxIdEquipo.getSelectedItem().toString()));
            comboBoxIdEquipo.setSelectedItem(equipoOriginal.getIdEquipo());
            NomEquipoInterfaz.setText(equipoOriginal.getNomEquipo());
            comboBoxTipoEquipo.setSelectedItem(controllerGeneral.tipoEquipoController().obtenerTipoEquipoPorId(equipoOriginal.getTipoEquipoId()).getNomTipo());
            comboBoxMarca.setSelectedItem(controllerGeneral.getMarcaController().obtenerMarcaPorId(equipoOriginal.getMarcaId()).getNombreMarca());
            ModeloController modeloController = new ModeloController(controllerGeneral);
            comboBoxModelo.setSelectedItem(modeloController.obtenerModeloPorId(equipoOriginal.getModeloId()).getNombreModelo());
            NumSerieInput.setText(equipoOriginal.getNumSerie().toString());
            dataChooserGarantia.setDate(DateMapper.convertLocalDateToDate(equipoOriginal.getGarantia()));
            comboBoxPais.setSelectedItem(controllerGeneral.getPaisController().obtenerPaisPorId(equipoOriginal.getPaisId()).getNombre());
            comboBoxProveedor.setSelectedItem(controllerGeneral.getProveedorController().obtenerProveedorPorId(equipoOriginal.getProveedorId()).getNomProveedor());
            dateChooser.setDate(DateMapper.convertLocalDateToDate(equipoOriginal.getFecAdquisicion()));
            textFieldIdInterna.setText(equipoOriginal.getIdInterna().toString());

            //ubicacion
            MovEquipoController movEquipoController = new MovEquipoController(controllerGeneral);
            String ubicacionActualEquipo =
                    controllerGeneral.getUbicacionController().obtenerUbicacionPorId(movEquipoController.obtenerUbicacionActualByEquipoId(equipoOriginal.getIdEquipo()).getUbicacionId()).getNombre();
            comboBoxUbicacion.setSelectedItem(ubicacionActualEquipo);

            //Mostrar imagen
            File imagen = Base64Utils.base64ToImage(equipoOriginal.getImagen());
            BufferedImage bufferedImage = ImageIO.read(imagen);
            Image imagenRedimensionada = bufferedImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            labelImgen.setIcon(new ImageIcon(imagenRedimensionada));
            setEnabled(true);
        }
    }

    public void modificarEquipo() throws ServiciosException, SQLException {
        MovEquipoController movEquipoController = new MovEquipoController(controllerGeneral);
        EquipoController equipoController = new EquipoController(controllerGeneral);
        Integer idEquipo = Integer.parseInt(comboBoxIdEquipo.getSelectedItem().toString());
        EquipoDTO equipoModificado = equipoController.obtenerEquipoPorId(idEquipo);
        equipoModificado.setIdInterna(Integer.valueOf(textFieldIdInterna.getText()));
        equipoModificado.setNomEquipo(NomEquipoInterfaz.getText());
        TipoEquipoController tipoEquipoController = new TipoEquipoController(controllerGeneral);
       // equipoModificado.setTipoEquipoId(controllerGeneral.tipoEquipoController().obtenerTipoEquipoPorNombre(comboBoxTipoEquipo.getSelectedItem().toString()).getIdEquipo());
        equipoModificado.setTipoEquipoId(tipoEquipoController.obtenerTipoEquipoPorNombre(Objects.requireNonNull(comboBoxTipoEquipo.getSelectedItem()).toString()).getId());
        System.out.println(comboBoxTipoEquipo.getSelectedItem().toString());
        equipoModificado.setMarcaId(controllerGeneral.getMarcaController().obtenerMarcaPorNombre(comboBoxMarca.getSelectedItem().toString()).getIdMarca());
        ModeloController modeloController = new ModeloController(controllerGeneral);
        equipoModificado.setModeloId(modeloController.obtenerModeloPorNombre(comboBoxModelo.getSelectedItem().toString()).getIdModelo());
        equipoModificado.setNumSerie(Integer.valueOf(NumSerieInput.getText()));

        Date fecha_garantia = dataChooserGarantia.getDate();
        if (fecha_garantia == null) {
            throw new ServiciosException("Debe ingresar una garantia");
        }
        equipoModificado.setGarantia(DateMapper.convertDateToLocalDate(fecha_garantia));

        equipoModificado.setPaisId(controllerGeneral.getPaisController().obtenerPaisPorNombre((String) comboBoxPais.getSelectedItem()).getID_Pais());
        equipoModificado.setProveedorId(controllerGeneral.getProveedorController().obtenerProveedorPorNombre(comboBoxProveedor.getSelectedItem().toString()).getIdProveedor());

        Date fecha_adquisicion = dateChooser.getDate();
        if (fecha_adquisicion == null) {
            throw new ServiciosException("Debe ingresar una fecha de adquisiscion");
        }
        equipoModificado.setFecAdquisicion(DateMapper.convertDateToLocalDate(fecha_adquisicion));
        String ubicacionActualEquipo =
                controllerGeneral.getUbicacionController().obtenerUbicacionPorId(movEquipoController.obtenerUbicacionActualByEquipoId(idEquipo).getUbicacionId()).getNombre();
        if (!comboBoxUbicacion.getSelectedItem().toString().equals(ubicacionActualEquipo)){
            agregarMovimiento();
        }

        if (fileChooser.getSelectedFile() != null){
            File selectedFile = fileChooser.getSelectedFile();
            equipoModificado.setImagen(Base64Utils.imagenToBase64(selectedFile.getAbsolutePath()));
        }

        controllerGeneral.getEquipoController().modificarEquipo(equipoModificado);
    }

    private void createUIComponents() {
        dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("dd/MM/yyyy");
        dataChooserGarantia = new JDateChooser();
        dataChooserGarantia.setDateFormatString("dd/MM/yyyy");
        fileChooser = new JFileChooser();
        FileNameExtensionFilter imageFilter = new FileNameExtensionFilter(
                "Archivos de Imagen", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(imageFilter);
    }
    public JPanel getPanel() {
        return panel;
    }

    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
    }

    public void setEnabled(boolean enable){
        for (Component component : componentes){
            component.setEnabled(enable);
        }
    }

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void agregarMovimiento() throws ServiciosException {
        CrearMovEquipoDTO movEquipoDTO = new CrearMovEquipoDTO();
        MovEquipoController movEquipoController = new MovEquipoController(controllerGeneral);

        //ID DE EQUIPO
        movEquipoDTO.setEquipoId(Integer.parseInt(comboBoxIdEquipo.getSelectedItem().toString()));

        //OBSERVACIONES
        movEquipoDTO.setObservaciones("Modificación de ubicación de equipo");

        //ID DE USUARIO
        Integer idUsuario = controllerGeneral.getUsuarioController().getUsuarioActivo().getID_Usuario();
        movEquipoDTO.setUsuarioId(idUsuario);

        // ID DE UBICACION
        String ubicacion = comboBoxUbicacion.getSelectedItem().toString();
        UbicacionController ubicacionController = new UbicacionController(controllerGeneral);
        Integer idUbicacion = ubicacionController.buscarUbicacionPorNombre(ubicacion).getIdUbicacion();
        movEquipoDTO.setUbicacionId(idUbicacion);


        //FECHA DE SALIDA
        LocalDate fechaSalida = LocalDate.now();
        movEquipoDTO.setFecEntrada(fechaSalida);

        movEquipoController.crearMovEquipo(movEquipoDTO);
    }

    public void actualizarComboBox() throws ServiciosException, SQLException {
        DefaultComboBoxModel<String> comboBoxModelIdEquipo = new DefaultComboBoxModel<>();
        comboBoxModelIdEquipo.addElement(" ");
        EquipoController equipoController = new EquipoController(controllerGeneral);
        List<EquipoDTO> equipoDTOList = equipoController.obtenerTodosLosEquipos();
        for(EquipoDTO equipoDTO : equipoDTOList){
            comboBoxModelIdEquipo.addElement(String.valueOf(equipoDTO.getIdEquipo()));
        }
        comboBoxIdEquipo.setModel(comboBoxModelIdEquipo);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
