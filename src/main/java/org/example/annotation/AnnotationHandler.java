package org.example.annotation;

import java.lang.reflect.Field;

public class AnnotationHandler {
    public static void handleNullableWarnings(Object object) {
        Class<?> objClass = object.getClass();

        while (objClass != null) {
            Field[] fields = objClass.getDeclaredFields();
            handleFields(fields, object);
            objClass = objClass.getSuperclass();
        }
    }

    private static void handleFields(Field[] fields, Object object) {
        for (Field field : fields) {
            NullableWarning annotation = field.getAnnotation(NullableWarning.class);

            if (annotation != null) {
                try {
                    field.setAccessible(true);

                    if (field.get(object) == null) {
                        System.out.println("Variable [" + field.getName() + "] is null in [" + object.getClass().getName() + "]!\n");
                    }

                    field.setAccessible(false);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
