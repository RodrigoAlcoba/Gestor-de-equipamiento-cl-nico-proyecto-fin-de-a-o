package uy.edu.utec.view.Interfaces.Equipo;

import com.toedter.calendar.JDateChooser;
import uy.edu.utec.controller.*;
import uy.edu.utec.servers.dtos.*;
import uy.edu.utec.servers.dtos.creacionales.CrearEquipoDTO;
import uy.edu.utec.servers.dtos.creacionales.CrearMovEquipoDTO;
import uy.edu.utec.servers.entidades.Ubicacion;
import uy.edu.utec.servers.enums.EstadoGeneral;
import uy.edu.utec.servers.excepciones.ServiciosException;
import uy.edu.utec.servers.utils.Base64Utils;
import uy.edu.utec.servers.utils.DateMapper;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class IngresoEquipo {
    private JTextField NomEquipoInput;
    private JTextField numSerieField;
    private JTextField PaisOrigenInput;
    private JTextField FecAdquisicionInput;
    private JTextField IdentificacionInternaInput;
    private JButton cancelarButton;
    private JButton ingresarButton;
    private JPanel panel;
    private JDateChooser dateChooser;
    private JDateChooser dataChooserGarantia;
    private JComboBox comboBoxTipoEquipo;
    private JComboBox comboBoxProveedor;
    private JComboBox comboBoxModelo;
    private JComboBox comboBoxMarca;
    private JFileChooser fileChooser;
    private JComboBox comboBoxPais;
    private JComboBox comboBoxUbicacion;

    //
    ControllerGeneral controllerGeneral;

    public IngresoEquipo(ControllerGeneral controllerGeneral) throws ServiciosException {
        this.controllerGeneral = controllerGeneral;

        actualizarComboBox();


        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controllerGeneral.mostrarMenuInterfaz(true);
                panel.setVisible(false);
                mostrarMensaje("Se ha cancelado la creación de equipo");
            }
        });
        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mensajeConfirmacion()) {
                    try {
                        crearEquipo();
                        mostrarMensaje("Equipo ingresado con éxito");
                        volverPrincipal();
                        actualizarComboBox();
                    } catch (ServiciosException  ex) {
                        JOptionPane.showMessageDialog(null,
                              "Ha ocurrido un error al ingresar un equipo: \n" + ex.getMessage());
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    mostrarMensaje("Se ha cancelado el ingreso del equipo con éxito.");
                }
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public void actualizarComboBox() throws ServiciosException {
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
        List<ModeloDTO> modeloDTOList = modeloController.obtenerTodosLosModelosActivos();
        for(ModeloDTO modeloDTO: modeloDTOList){
            comboBoxModelModelos.addElement(modeloDTO.getNombreModelo());
        }
        comboBoxModelo.setModel(comboBoxModelModelos);

        //Marcas
        DefaultComboBoxModel<String> comboBoxModelMarca = new DefaultComboBoxModel<>();
        MarcaController marcaController = new MarcaController(controllerGeneral);
        List<MarcaDTO> marcaList = marcaController.obtenerTodasLasMarcasActivas();
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
    }
    public void crearEquipo() throws ServiciosException, SQLException, NumberFormatException {
        CrearEquipoDTO crearEquipoDTO = new CrearEquipoDTO();
        crearEquipoDTO.setNomEquipo(NomEquipoInput.getText().trim());

        // NOMBRE EQUIPO

        if (NomEquipoInput.getText().isEmpty()) {
            throw new ServiciosException("No puede dejar el nombre del equipo vacío.");
        }

        if (NomEquipoInput.getText().length() < 2 || NomEquipoInput.getText().length() > 80) {
            throw new ServiciosException("El nombre del equipo debe tener entre 2 y 80 caracteres.");
        }

        if (NomEquipoInput.getText().matches("[^[a-zA-Z0-9 ]+$]+")) {
            throw new ServiciosException("En el nombre del equipo no se admiten caracteres especiales.");
        }

        //  NÚMERO DE SERIE

        if (!numSerieField.getText().trim().matches("[0-9]+")) {
            throw new ServiciosException("El número de serie debe ser numérico y no puede estar vacío");
        }

        // IDENTIFICACIÓN INTERNA

        if (!IdentificacionInternaInput.getText().trim().matches("[0-9]+")) {
            throw new ServiciosException("El número de identificación interna debe ser numérica y no puede estar vacía.");
        }

        crearEquipoDTO.setNumSerie(Integer.valueOf(numSerieField.getText().trim()));
        crearEquipoDTO.setIdInterna(Integer.parseInt(IdentificacionInternaInput.getText().trim()));
        //fecha adquisicion
        Date fecha_adq = dateChooser.getDate();
        if (fecha_adq == null) {
            throw new ServiciosException("Debe ingresar una fecha de adquisición.");
        }
        System.out.println(fecha_adq);
        crearEquipoDTO.setFecAdquisicion(DateMapper.convertDateToLocalDate(fecha_adq));


        //fecha garantia
        Date fecha_garantia = dataChooserGarantia.getDate();
        if (fecha_garantia == null) {
            throw new ServiciosException("Debe ingresar una garantía.");
        }
        System.out.println(fecha_garantia);
        crearEquipoDTO.setGarantia(DateMapper.convertDateToLocalDate(fecha_garantia));

        //tipoEquipo
        String nomTipoEquipo = comboBoxTipoEquipo.getSelectedItem().toString();
        TipoEquipoDTO tipoEquipoDTO = controllerGeneral.tipoEquipoController().obtenerTipoEquipoPorNombre(nomTipoEquipo);
        crearEquipoDTO.setTipoEquipoId(tipoEquipoDTO.getId());

        //proveedores
        crearEquipoDTO.setProveedorId(controllerGeneral.getProveedorController().obtenerProveedorPorNombre
                (Objects.requireNonNull(comboBoxProveedor.getSelectedItem()).toString()).getIdProveedor());

        //Modelos
        ModeloController modeloController = new ModeloController(controllerGeneral);
        crearEquipoDTO.setModeloId(modeloController.obtenerModeloPorNombre
                (Objects.requireNonNull(comboBoxModelo.getSelectedItem()).toString()).getIdModelo());

        //Marcas
        MarcaController marcaController = new MarcaController(controllerGeneral);
        System.out.println(marcaController.obtenerMarcaPorNombre("3M"));
        crearEquipoDTO.setMarcaId(marcaController.obtenerMarcaPorNombre
                (Objects.requireNonNull(comboBoxMarca.getSelectedItem()).toString()).getIdMarca());

        //Paises
        crearEquipoDTO.setPaisId(controllerGeneral.getPaisController().obtenerPaisPorNombre
                (Objects.requireNonNull(comboBoxPais.getSelectedItem()).toString()).getID_Pais());

        //Ubicacionen
        CrearMovEquipoDTO movEquipoDTO = new CrearMovEquipoDTO();
        movEquipoDTO.setObservaciones("Ingreso de equipo.");

        movEquipoDTO.setFecEntrada(LocalDate.now());
        movEquipoDTO.setUbicacionId(controllerGeneral.getUbicacionController().obtenerUbicacionPorNombre(Objects.requireNonNull(comboBoxUbicacion.getSelectedItem()).toString()).getIdUbicacion());
        movEquipoDTO.setUsuarioId(controllerGeneral.getUsuarioController().getUsuarioActivo().getID_Usuario());

        crearEquipoDTO.setCrearMovEquipoDTO(movEquipoDTO);

        if (fileChooser.getSelectedFile() == null) {
            throw new ServiciosException("Debe ingresar una imagen.");
        }
        //file chooser
        File selectedFile = fileChooser.getSelectedFile();

        crearEquipoDTO.setImagen(Base64Utils.imagenToBase64(selectedFile.getAbsolutePath()));
        controllerGeneral.getControllerEquipo().crearEquipo(crearEquipoDTO);
    }
    public void volverPrincipal(){
        controllerGeneral.mostrarMenuInterfaz(true);
        panel.setVisible(false);
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

    public void mostrarMensaje(String mensaje){
        JOptionPane.showMessageDialog(null, mensaje);
    }

    public Boolean mensajeConfirmacion() {
        Object[] opciones = {"Confirmar", "Cancelar"};
        int respuesta = JOptionPane.showOptionDialog(null, "¿Desea confirmar la acción?", "Confirmación", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        return respuesta == JOptionPane.YES_OPTION;
    }
}
