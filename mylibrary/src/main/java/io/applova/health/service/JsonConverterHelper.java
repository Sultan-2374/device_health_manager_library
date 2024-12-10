package io.applova.health.service;

import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonConverterHelper {

    private static final String TAG = "JsonConverterHelper";

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
            Log.d(TAG, "beanToJson: Successfully converted bean to JSON");
            return jsonObject.toString();
        } catch (IllegalAccessException e) {
            //todo use proper log
            Log.e(TAG, "beanToJson: IllegalAccessException occurred", e);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "beanToJson: Exception occurred", e);
            return null;
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
            Log.d(TAG, "jsonToBean: Successfully converted JSON to bean");
            return bean;
        } catch (JSONException e) {
            Log.e(TAG, "jsonToBean: JSONException occurred", e);
            return null;
        } catch (IllegalAccessException e) {
            Log.e(TAG, "jsonToBean: IllegalAccessException occurred", e);
            return null;
        } catch (Exception e) {
            Log.e(TAG, "jsonToBean: Exception occurred", e);
            return null;
        }
    }
}