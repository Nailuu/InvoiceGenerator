package fr.nailu.invoicegenerator.repository;

import fr.nailu.invoicegenerator.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskRepository {
    private JdbcTemplate _jdbc;
    private RowMapper<Task> _mapper;

    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this._jdbc = jdbcTemplate;

        this._mapper = (rs, rowNum) -> {
            Task task = new Task();
            task.setName(rs.getString("name"));
            task.setMinutes(rs.getInt("minutes"));
            task.setDate(rs.getDate("date"));

            return task;
        };
    }

    public void save(Task task) {
        String sql = "INSERT INTO task (name, minutes, date) values (?, ?, ?)";

        this._jdbc.update(sql, task.getName(), task.getMinutes(), task.getDate());
    }

    public void saveAll(List<Task> tasks) {
        for (Task task : tasks) {
            this.save(task);
        }
    }

    public List<Task> findAll() {
        String sql = "SELECT * FROM task ORDER BY date ASC";

        return this._jdbc.query(sql, this._mapper);
    }
}
