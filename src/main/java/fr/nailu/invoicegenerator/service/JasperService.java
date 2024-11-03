package fr.nailu.invoicegenerator.service;

import fr.nailu.invoicegenerator.model.Task;
import fr.nailu.invoicegenerator.property.ApplicationProperties;
import fr.nailu.invoicegenerator.property.JasperTemplateProperties;
import fr.nailu.invoicegenerator.util.JasperUtils;
import fr.nailu.invoicegenerator.util.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperService {
    private final ApplicationProperties _applicationProperties;
    private final JasperTemplateProperties _templateProperties;
    private TaskService _taskService;

    @Autowired
    public JasperService(ApplicationProperties applicationProperties, JasperTemplateProperties templateProperties, TaskService taskService) {
        this._applicationProperties = applicationProperties;
        this._templateProperties = templateProperties;
        this._taskService = taskService;
    }

    private JasperReport compile() throws JRException, IOException {
        System.out.println("Compiling Jasper report: " + this._applicationProperties.getJasperTemplatePath());

        InputStream reportStream = new ClassPathResource(this._applicationProperties.getJasperTemplatePath()).getInputStream();
        return JasperCompileManager.compileReport(reportStream);
    }

    public void generateInvoice() throws JRException, IOException, InvocationTargetException, IllegalAccessException, ParseException {
        System.out.println("Generating the invoice..");

        JasperReport report = this.compile();

        Map<String, Object> parameters = new HashMap<>();

        int totalMinutes = this._taskService.getAll().stream().mapToInt(Task::getMinutes).sum();
        parameters.put("TOTAL_HOURS", Timestamp.getTimestampFromMinutes(totalMinutes));
        parameters.put("TOTAL_PRICE", (double)totalMinutes * (this._templateProperties.getHourlyRate() / 60));

        List<Method> getters = JasperUtils.getGetterMethods(this._templateProperties);
        for (Method getter : getters) {
            String name = getter.getName()
                    .substring(3)
                    .toUpperCase();
            Object value = getter.invoke(this._templateProperties);

            parameters.put(name, value);
        }

        List<Task> tasks = this._taskService.getAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(tasks);

        JasperPrint print = JasperFillManager.fillReport(report, parameters, dataSource);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = dateFormat.parse(this._templateProperties.getCreatedAtDate());
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");

        StringBuilder fileName = new StringBuilder()
                .append("INV")
                .append(this._templateProperties.getNumber())
                .append("_")
                .append(this._templateProperties.getRecipientName().toUpperCase().replace(" ", ""))
                .append("_")
                .append(formatter.format(date))
                .append(".pdf");

        JasperExportManager.exportReportToPdfFile(print, fileName.toString());

        System.out.println("Invoice successfully created (" + fileName.toString() + ")");
    }
}
