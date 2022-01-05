package converter;

import java.util.function.Supplier;

public class ConversionUtilities {
    @FunctionalInterface
    public interface Converter {
        Value convert(Value value, int base);
    }

    public static abstract class Value {
        public abstract <T> T get();

        public static <T> Value of(Supplier<T> supplier) {
            return of(supplier.get());
        }

        public static <T> Value of(T value) { return new GenericValue<>(value); }
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
