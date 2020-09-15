package com.simon.java.functional;

/**
 * @author Simon
 */
public class Either<L, R> {

    private L left = null;
    private R right = null;

    private Either(L left, R right) {
    }

    public L getLeft() {
        return left;
    }

    public R getRight() {
        return right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }

    public static <L, R> Either<L, R> left(L left) {
        return new Either<L, R>(left, null);
    }

    public static <L, R> Either<L, R> right(R right) {
        return new Either<L, R>(null, right);
    }
}
