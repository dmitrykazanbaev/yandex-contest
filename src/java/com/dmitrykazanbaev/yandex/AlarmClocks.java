package com.dmitrykazanbaev.yandex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class AlarmClocks {
    public static void main(String[] args) throws Exception {
        String file = "input.txt";
        BufferedReader r = new BufferedReader(new FileReader(file));

        String first = r.readLine();
        String[] values = first.split(" ");
        int count = Integer.parseInt(values[0]);
        int interval = Integer.parseInt(values[1]);
        int alarmCount = Integer.parseInt(values[2]);

        String second = r.readLine();
        String[] s = second.split(" ");

        List<Long> collect = Arrays.stream(s)
                .mapToLong(Long::parseLong)
                .limit(count)
                .boxed()
                .distinct()
                .collect(groupingBy(i -> i % interval))
                .values().stream()
                .map(item -> item.stream().mapToLong(i -> i).min().orElse(-1))
                .collect(toList());

        binarySearch(collect.stream().mapToLong(Long::longValue).min().getAsLong(), Long.MAX_VALUE, collect, alarmCount, interval);
    }

    private static void binarySearch(Long i, Long maxValue, List<Long> collect, int alarmCount, int interval) {
        Long diff = maxValue - i;
        if (diff == 1L) {
            diff = 0L;
        }
        Long neededDiff = diff / 2;
        Long time = i + neededDiff;
        Long sum = collect.stream()
                .mapToLong(item -> time >= item ? (time - item) / interval + 1 : 0)
                .sum();
        boolean b = collect.stream()
                .anyMatch(item -> ((time - item) % interval == 0));
        if (sum > alarmCount) {
            binarySearch(i, time, collect, alarmCount, interval);
        } else if (sum < alarmCount) {
            binarySearch(time, maxValue, collect, alarmCount, interval);
        } else if (!b) {
            binarySearch(i, time, collect, alarmCount, interval);
        } else {
            System.out.println(time);
        }
    }
}