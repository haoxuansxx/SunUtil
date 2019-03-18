package future;

/**
 * @CompletableFuture 组合式异步编程  -- Api
 *
 * @Author Sun
 * @date 2019-03-16
 */
public class CompletableFutureApi {

    /**
     * 将两个异步计算合并为一个——这两个异步计算之间相互独立，同时第二个又依赖于第一个的结果。
     *
     * 等待 Future 集合中的所有任务都完成。
     *
     * 仅等待 Future集合中最快结束的任务完成（有可能因为它们试图通过不同的方式计算同一个值），并返回它的结果。
     *
     * 通过编程方式完成一个Future任务的执行（即以手工设定异步操作结果的方式）。
     *
     * 应对 Future 的完成事件（即当 Future 的完成事件发生时会收到通知，并能使用 Future 计算的结果进行下一步的操作，不只是简单地阻塞等待操作的结果）
     */
    public static void main(String... args) {

        System.out.println("@CompletableFuture 组合式异步编程！");

    }

}
