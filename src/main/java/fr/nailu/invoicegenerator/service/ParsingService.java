package fr.nailu.invoicegenerator.service;

import fr.nailu.invoicegenerator.model.Task;
import fr.nailu.invoicegenerator.property.ApplicationProperties;
import fr.nailu.invoicegenerator.util.Parser;
import fr.nailu.invoicegenerator.util.Timestamp;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ParsingService {
    private final ApplicationProperties _properties;
    private final Parser _parser;
    private TaskService _taskService;

    @Autowired
    public ParsingService(ApplicationProperties properties, Parser parser, TaskService taskService) {
        this._properties = properties;
        this._parser = parser;
        this._taskService = taskService;
    }

    private List<Task> parseTasksFromCSV(CSVParser csvParser) {
        List<Task> tasks = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap();

        for (CSVRecord record : csvParser) {
            String name = record.get("NOTES");
            String value = record.get("HOURS");

            if (name.isEmpty() || value.isEmpty()) {
                continue;
            }

            int minutes;

            try {
                minutes = Timestamp.getMinutesFromTimestamp(value);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            map.compute(name, (k, v) -> v == null ? minutes : v + minutes);
        }

        map.forEach((k, v) -> {
            Task task = new Task();

            task.setName(k);
            task.setMinutes(v);

            tasks.add(task);
        });

        return tasks;
    }

    public void process() throws Exception {
        String path = ResourceUtils.getFile(this._properties.getCSVPath()).getAbsolutePath();

        System.out.println("Parsing CSV file: " + path);

        CSVParser csvParser = this._parser.init(path);

        List<Task> tasks = this.parseTasksFromCSV(csvParser);
        this._taskService.addAll(tasks);

        csvParser.close();
    }
}
