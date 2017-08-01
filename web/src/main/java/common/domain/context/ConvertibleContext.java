package common.domain.context;

import java.lang.reflect.Method;

/**
 * Created by wanghao on 2017/7/31.
 */
public final class ConvertibleContext<T, V> {

    private final Class<T> type;
    private final Class<V> valueType;
    private final Method valueMethod;
    private final Method ofMethod;

    private final Object[] zeroParameter = new Object[0];

    ConvertibleContext(final Class<T> type,
                       final Class<V> valueType,
                       final Method valueMethod,
                       final Method ofMethod) {
        this.type = type;
        this.valueType = valueType;
        this.valueMethod = valueMethod;
        this.ofMethod = ofMethod;
    }

    public Class<T> getType() {
        return type;
    }

    public Class<V> getValueType() {
        return valueType;
    }

    public V value(final T source) throws Exception {
        return valueType.cast(valueMethod.invoke(source, zeroParameter));
    }

    public T of(final V value) throws Exception {
        return type.cast(ofMethod.invoke(null, value));
    }

    public static <T, V> ConvertibleContext<T, V> build(final String className)
            throws RuntimeException {
        Class<T> convertibleClazz;
        try {
            convertibleClazz = (Class<T>) Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Class not found", e);
        }
        return build(convertibleClazz);
    }

    public static <T, V> ConvertibleContext<T, V> build(final Class<T> type)
            throws RuntimeException {

        if (!type.isAnnotationPresent(Convertible.class)) {
            throw new RuntimeException("Class should be annotated by Convertible.");
        }

        Convertible convertible = type.getAnnotation(Convertible.class);
        Method valueMethod;
        try {
            valueMethod = type.getMethod(convertible.valueMethod(), new Class[0]);
        } catch (Exception exception) {

            throw new RuntimeException("Failed to obtain method:"
                    + convertible.valueMethod()
                    + "(which should have non-argument).",
                    exception);
        }

        Class<V> valueType = (Class<V>) valueMethod.getReturnType();

        Method ofMethod;
        try {
            ofMethod = type.getMethod(convertible.ofMethod(), new Class[]{valueType});
        } catch (Exception exception) {

            throw new RuntimeException("Failed to obtain method:"
                    + convertible.ofMethod() + "(which should have a(n) "
                    + valueType.getName() + " typed argument)", exception);
        }
        return new ConvertibleContext<T, V>(type, valueType, valueMethod, ofMethod);
    }
}
