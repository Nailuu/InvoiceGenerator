package fr.nailu.invoicegenerator.model;

import fr.nailu.invoicegenerator.util.Timestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Scope("prototype")
public class Task {
    private String name;
    private String time;
    private Date date;
    private int minutes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
        this.time = Timestamp.getTimestampFromMinutes(minutes);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.time + " - " + this.date.toString();
    }
}
