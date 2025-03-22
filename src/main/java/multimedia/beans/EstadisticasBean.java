package multimedia.beans;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class EstadisticasBean implements Serializable {
    
    private PieChartModel pieModel;
    private BarChartModel barModel;
    private LineChartModel lineModel;
    private List<Dato> datos;
    
    @PostConstruct
    public void init() {
        // Inicializar datos de ejemplo
        datos = new ArrayList<>();
        datos.add(new Dato(1L, "Producto A", 150));
        datos.add(new Dato(2L, "Producto B", 300));
        datos.add(new Dato(3L, "Producto C", 200));
        
        createPieModel();
        createBarModel();
        createLineModel();
    }
    
    private void createPieModel() {
        pieModel = new PieChartModel();
        for (Dato dato : datos) {
            pieModel.set(dato.getNombre(), dato.getValor());
        }
        pieModel.setTitle("Gráfica de Pie");
        pieModel.setShowDataLabels(true);
        pieModel.setLegendPosition("w");
    }
    
    private void createBarModel() {
        barModel = new BarChartModel();
        ChartSeries series = new ChartSeries();
        series.setLabel("Valores");
        
        for (Dato dato : datos) {
            series.set(dato.getNombre(), dato.getValor());
        }
        
        barModel.addSeries(series);
        barModel.setTitle("Gráfica de Barras");
        barModel.setLegendPosition("ne");
    }
    
    private void createLineModel() {
        lineModel = new LineChartModel();
        ChartSeries series = new ChartSeries();
        series.setLabel("Tendencia");
        
        for (Dato dato : datos) {
            series.set(dato.getNombre(), dato.getValor());
        }
        
        lineModel.addSeries(series);
        lineModel.setTitle("Gráfica de Línea");
        lineModel.setLegendPosition("e");
    }
    
    // Clase interna para los datos
    public static class Dato implements Serializable {
        private Long id;
        private String nombre;
        private double valor;
        
        public Dato(Long id, String nombre, double valor) {
            this.id = id;
            this.nombre = nombre;
            this.valor = valor;
        }
        
        // Getters y setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
        public double getValor() { return valor; }
        public void setValor(double valor) { this.valor = valor; }
    }
    
    // Getters para los modelos
    public PieChartModel getPieModel() { return pieModel; }
    public BarChartModel getBarModel() { return barModel; }
    public LineChartModel getLineModel() { return lineModel; }
    public List<Dato> getDatos() { return datos; }
} 