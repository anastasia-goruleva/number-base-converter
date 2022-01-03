package converter;

import java.util.function.Supplier;

public class ConversionInterface {
    public static abstract class Value {
        public abstract <T> T get();

        public static <T> GenericValue<T> of(Supplier<T> supplier) {
            return new GenericValue<>(supplier.get());
        }

        public static <T> GenericValue<T> of(T value) { return new GenericValue<>(value); }
    }

    @FunctionalInterface
    public interface ConversionFunction {
        Value convert(Value value, int base);
    }

    private static class GenericValue<T> extends Value {
        private T value;

        public GenericValue(T value) {
            this.value = value;
        }

        @Override
        public T get() {
            return value;
        }
    }
}
