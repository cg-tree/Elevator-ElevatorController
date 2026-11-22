package common;

public interface BusMessage<T> {
    int topic();
    int body();
    T fromBody(int body);
}