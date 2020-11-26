package pattern.singleton;

public class LazySingleton {
    private static LazySingleton singleton = null;

    private LazySingleton() {
    }

    public static LazySingleton getSingleton() {
        if(singleton == null) singleton = new LazySingleton();
        return singleton;
    }
}
