package io.applova.health.service;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonConverterHelper {

    // Converts a bean to a JSON string
    public static String beanToJson(Object bean) {
        try {
            // Create a JSONObject from the bean
            JSONObject jsonObject = new JSONObject();
            java.lang.reflect.Field[] fields = bean.getClass().getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true); // Make private fields accessible
                jsonObject.put(field.getName(), field.get(bean)); // Add field and its value
            }
            return jsonObject.toString();
        } catch (IllegalAccessException | JSONException e) {
            //todo use proper log
            return null; // Return null in case of error
        }
    }

    // Converts a JSON string to a bean
    public static <T> T jsonToBean(String jsonString, Class<T> beanClass) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            T bean = beanClass.newInstance(); // Create a new instance of the bean
            java.lang.reflect.Field[] fields = beanClass.getDeclaredFields();
            for (java.lang.reflect.Field field : fields) {
                field.setAccessible(true); // Make private fields accessible
                if (jsonObject.has(field.getName())) {
                    Object value = jsonObject.get(field.getName());
                    // Set the field value
                    field.set(bean, value);
                }
            }
            return bean;
        } catch (JSONException | IllegalAccessException | InstantiationException e) {
            //todo proper logging
            return null; // Return null in case of error
        }
    }
}