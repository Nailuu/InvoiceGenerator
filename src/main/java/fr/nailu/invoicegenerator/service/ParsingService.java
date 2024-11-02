package fr.nailu.invoicegenerator.service;

import fr.nailu.invoicegenerator.model.Task;
import fr.nailu.invoicegenerator.util.Parser;
import fr.nailu.invoicegenerator.util.Timestamp;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ParsingService {
    private final Parser _parser;
    private TaskService _taskService;

    private CSVParser CSVParser;

    @Autowired
    public ParsingService(Parser parser, TaskService taskService) {
        this._parser = parser;
        this._taskService = taskService;
    }

    private List<Task> parseTasksFromCSV() {
        List<Task> tasks = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap();

        for (CSVRecord record : this.CSVParser) {
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

    public void process(String path) throws Exception {
        this.CSVParser = this._parser.init(path);

        List<Task> tasks = this.parseTasksFromCSV();
        this._taskService.addAll(tasks);

        System.out.println(this._taskService.getAll());
        System.out.println("Total: " + Timestamp.getTimestampFromMinutes(this._taskService.getAll().stream().mapToInt(Task::getMinutes).sum()));

        this.CSVParser.close();
    }
}
