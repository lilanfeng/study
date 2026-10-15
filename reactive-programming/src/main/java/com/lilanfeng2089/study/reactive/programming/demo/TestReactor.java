package com.lilanfeng2089.study.reactive.programming.demo;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * @author lilanfeng2089，微信：lilanfeng2089
 * @description
 * @date 2025/5/2 06:31
 * @github https://github.com/lilanfeng
 * @Copyright 公众号：lilanfeng2089 | 博客：https://lilanfeng2089.cn - 沉淀、分享、成长，让自己和他人都能有所收获！
 */
public class TestReactor {

    public static void main(String[] args) {
        Flux<Integer> integerFlux = Flux.just(1,2,3,4,5);

        integerFlux.subscribe(System.out::println);

        Flux<String> stringFlux = Flux.just("hello", "world");
        stringFlux.subscribe(System.out::println);

        Integer[] array = {1,2,3,4,5};
        Flux.fromArray(array).subscribe(System.out::println);

        List<Integer> integers = Arrays.asList(1,2,3,4,5);
        Flux.fromIterable(integers).subscribe(System.out::println);

        Stream<Integer> stream = integers.stream();
        Flux.fromStream(stream).subscribe(System.out::println);

        Flux.just("Ben","Michel","Mark").subscribe(new Subscriber<String>() {
            @Override
            public void onSubscribe(Subscription s) {
                s.request(3);
            }

            @Override
            public void onNext(String s) {
                System.out.println("Hello " + s +"!");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {
                System.out.println("Completed");
            }
        });

        Flux.just("Ben","Michel","Mark").doOnNext(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("Hello " + s +"!");
            }
        }).doOnComplete(new Runnable() {
            @Override
            public void run() {
                System.out.println("Completed");
            }
        }).subscribe();

        Flux.just("Ben","Michel","Mark")
                .doOnNext(s -> System.out.println("Hello " + s +"!"))
                .doOnComplete(() -> System.out.println("Completed"))
                .take(2)
                .subscribe();


    }
}
