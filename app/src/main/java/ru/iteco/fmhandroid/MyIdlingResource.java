package ru.iteco.fmhandroid;

import androidx.test.espresso.IdlingResource;

import java.util.concurrent.atomic.AtomicInteger;

public class MyIdlingResource implements IdlingResource {
    private final String resourceName;
    private final AtomicInteger counter = new AtomicInteger(0);
    private volatile ResourceCallback callback;

    public MyIdlingResource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String getName() {
        return resourceName;
    }

    @Override
    public boolean isIdleNow() {
        return counter.get() == 0; // true, если нет активных операций
    }

    @Override
    public void registerIdleTransitionCallback(ResourceCallback callback) {
        this.callback = callback;
    }

    // Увеличиваем счётчик при запуске операции
    public void increment() {
        counter.getAndIncrement();
    }

    // Уменьшаем счётчик при завершении
    public void decrement() {
        int newVal = counter.decrementAndGet();
        if (newVal == 0 && callback != null) {
            callback.onTransitionToIdle(); // уведомляем Espresso
        }
    }
}
