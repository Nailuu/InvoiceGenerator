package fr.nailu.invoicegenerator.model;

import fr.nailu.invoicegenerator.util.Timestamp;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Task {
    private String name;
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
    }

    @Override
    public String toString() {
        return this.name + " - " + Timestamp.getTimestampFromMinutes(this.minutes);
    }
}
