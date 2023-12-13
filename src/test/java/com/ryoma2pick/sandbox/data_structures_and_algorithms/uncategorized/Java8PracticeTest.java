package com.ryoma2pick.sandbox.data_structures_and_algorithms.uncategorized;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

class Java8PracticeTest {

    @Test
    void q1() {
        // Write a Java program to calculate the average of a list of integers using streams.
        OptionalDouble average = new Random().ints(10, 0, 10).average();
        System.out.println(average);
    }

    @Test
    void q2() {
        // Write a Java program to convert a list of strings to uppercase or lowercase using streams.
        List<String> strings = new ArrayList<>();
        strings.add("Foo");
        strings.add("Bar");
        strings.add("hOGe");
        strings.add("fUGA");

        List<String> converted = strings.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        System.out.println(converted.toString());
    }

    @Test
    void q3() {
        // Write a Java program to calculate the sum of all even, odd numbers in a list using streams.
        int[] nums = new Random().ints(10, 0, 10).toArray();
        Arrays.stream(nums).filter(i -> i % 2 == 0).sum();
        Arrays.stream(nums).filter(i -> i % 2 != 0).sum();
    }

    @Test
    void q4() {
        // Write a Java program to remove all duplicate elements from a list using streams.
        List<String> strings = new ArrayList<>();
        strings.add("unique");
        strings.add("duplicate");
        strings.add("duplicate");

        System.out.println("*** before distinct");
        strings.stream().forEach(System.out::println);

        System.out.println("*** after distinct");
        strings.stream().distinct().forEach(System.out::println);
    }

    @Test
    void q5() {
        // Write a Java program to count the number of strings in a list that start with a specific letter using streams
        List<String> strings = new ArrayList<>();
        strings.add("foo");
        strings.add("fu");
        strings.add("bar");
        strings.add("f***");
        strings.stream().forEach(System.out::println);

        long count = strings.stream().filter(str -> str.startsWith("f")).count();
        System.out.println(count);
    }

    @Test
    void q6() {
        // Write a Java program to sort a list of strings in alphabetical order, ascending and descending using streams.
        List<String> strings = new ArrayList<>();
        strings.add("zoo");
        strings.add("hOGE");
        strings.add("f***");
        strings.stream().forEach(System.out::println);

        strings.stream().sorted().forEach(System.out::println);
    }

    @Test
    void Stream_skip() {
        int[] nums = new Random().ints(10, 0, 100).toArray();
        System.out.println("*** before ***");
        Arrays.stream(nums).forEach(System.out::println);
        System.out.println("*** after ***");
        Arrays.stream(nums).sorted().skip(2).forEach(System.out::println);
    }

    @Test
    void Stream_limit() {
        List<String> strings = new ArrayList<>();
        strings.add("Bob");
        strings.add("Daisuke");
        strings.add("Alice");
        strings.add("Chris");
        strings.stream().forEach(System.out::println);

        System.out.println("***");
        strings.stream().sorted(Comparator.reverseOrder()).limit(2).forEach(System.out::println);
    }

}