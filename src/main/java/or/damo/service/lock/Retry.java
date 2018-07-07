package or.damo.service.lock;

/**
 * @author xdp
 * @date 2018/7/4
 */
@FunctionalInterface
public interface Retry<T> {

    T retry(T t);
}
