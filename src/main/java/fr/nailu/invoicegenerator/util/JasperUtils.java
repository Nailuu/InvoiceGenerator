package fr.nailu.invoicegenerator.util;

import fr.nailu.invoicegenerator.property.JasperTemplateProperties;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class JasperUtils {
    public static List<Method> getGetterMethods(JasperTemplateProperties jtp) {
        Method[] methods = jtp.getClass().getMethods();
        List<Method> getters = new ArrayList<>();

        for (Method method : methods) {
            if (method.getName() != "getClass" && method.getName().startsWith("get") && method.getParameterCount() == 0 && !void.class.equals(method.getReturnType())) {
                getters.add(method);
            }
        }
        return getters;
    }
}
