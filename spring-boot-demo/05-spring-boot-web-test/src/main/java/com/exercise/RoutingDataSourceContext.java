package com.exercise;

public class RoutingDataSourceContext {
    static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    /**
     * 指派数据源是db1,还是db2
     * @param key
     */
    public RoutingDataSourceContext(String key) {
        threadLocal.set(key);
    }

    public static String getDataSourceKey() {
        String key = threadLocal.get();
        return key == null ? "db1DataSource": key;
    }

    public void close() {
        threadLocal.remove();
    }

}
