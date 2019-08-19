package com.simon.java.test;

public class RetryDemo {
    // 最长延迟间隔，单位是毫秒
    private static int MAX_WAIT_INTERVAL = 100000;
    // 最大重试次数
    private static int MAX_RETRIES = 5;

    public enum Results {
        SUCCESS,
        NOT_READY,
        THROTTLED,
        SERVER_ERROR
    }

    public static void main(String[] args) {
        doOperationAndWaitForResult();
    }

    // 指数退避 算法
    public static void doOperationAndWaitForResult() {

        try {
            int retries = 0;
            boolean retry = false;

            do {
                long waitTime = Math.min(getWaitTimeExp(retries), MAX_WAIT_INTERVAL);

                System.out.print("等待时间：" + waitTime + " ms \n");

                // Wait for the result.
                Thread.sleep(waitTime);

                // Get the result of the asynchronous operation.
                Results result = getAsyncOperationResult();

                if (Results.SUCCESS == result) {
                    retry = false;
                } else if (Results.NOT_READY == result) {
                    retry = true;
                } else if (Results.THROTTLED == result) {
                    retry = true;
                } else if (Results.SERVER_ERROR == result) {
                    retry = true;
                }
                else {
                    retry = false;
                }

            } while (retry && (retries++ < MAX_RETRIES));
        }
        catch (Exception ex) {
        }
    }

    // 假设每次都返回 SERVER_ERROR
    public static Results getAsyncOperationResult() {
        return Results.SERVER_ERROR;
    }

    // 根据重试的次数，返回 2 的指数的等待时间，单位是毫秒
    public static long getWaitTimeExp(int retryCount) {

        long waitTime = ((long) Math.pow(2, retryCount) * 100L);

        return waitTime;
    }
}
