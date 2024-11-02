package fr.nailu.invoicegenerator.util;

import org.springframework.stereotype.Component;

@Component
public abstract class Timestamp {
    public static int getMinutesFromTimestamp(String timestamp) throws Exception {
        String[] tmp = timestamp.split(":");
        if (tmp.length != 2) {
            throw new Exception("Invalid timestamp: " + timestamp);
        }

        return Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
    }

    public static String getTimestampFromMinutes(int input) {
        StringBuilder builder = new StringBuilder();

        String hours = Integer.toString(input / 60);
        String minutes = Integer.toString(input % 60);

        builder.append(hours.length() > 1 ? hours : "0" + hours);
        builder.append(":");
        builder.append(minutes.length() > 1 ? minutes : "0" + minutes);

        return builder.toString();
    }
}
