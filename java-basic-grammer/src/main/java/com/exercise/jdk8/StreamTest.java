package com.exercise.jdk8;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class StreamTest {

    public static void main(String[] args) {
        List<Employee> employeeList = Employee.getEmps();

        Random random = new Random();
        List<User> users = new ArrayList<>();
        for (int i = 1; i < 11; i++) {
            User user = User.builder().name("用户" + i).age(random.nextInt(80) + 20 ).build();
            users.add(user);
        }
        User user1 = User.builder().name("用户" + 10).age( -1 ).build();
        users.add(user1);
        User user2 = User.builder().name("用户" + 10).age( -2 ).build();
        users.add(user2);

        System.out.println("所有用户：" );
        users.forEach(User::print);

        System.out.println("stream获取按列表对象中的某个字段过滤后的集合" );
        List<User> oldPersons = users.stream().filter(item -> item.getAge() > 35).collect(Collectors.toList());
        oldPersons.forEach(User::toString);

        List<Integer> ageList = users.stream().map(u -> u.getAge()).collect(Collectors.toList());
        System.out.println("stream获取列表对象中的某个字段集合");
        ageList.forEach(System.out :: println);

        System.out.println("=====================sorted排序 start ================================================");
        System.out.println("stream按列表对象中的某个字段排序集合");
        List<User> sortedUserList = users.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
        sortedUserList.forEach(System.out :: println);
        System.out.println("自然排序");
        List<Integer> sortedNature = users.stream().map(User::getAge).sorted().collect(Collectors.toList());
        sortedNature.forEach(System.out :: println);
        System.out.println("逆序");
        List<User> sortedReversed = users.stream().sorted(Comparator.comparing(User::getAge).reversed()).collect(Collectors.toList());
        sortedReversed.forEach(System.out ::println);
        System.out.println("多字段排序");
        List<User> mulSorted = users.stream().sorted(Comparator.comparing(User::getAge, Comparator.reverseOrder()).thenComparing(User::getName)).collect(Collectors.toList());
        mulSorted.forEach(System.out ::println);
        System.out.println("=====================sorted排序 end  ================================================");

        System.out.println("stream按列表对象中的某个字段分组获取集合");
        //groupingBy(Function) 一个参数：一个分组器，使用提供的字段对集合元素进行分组，返回一个Map<字段，相同字段值的元素集>
        Map<String, List<User>> groupUserMap = users.stream().collect(Collectors.groupingBy(User::getName));
        groupUserMap.forEach((k,v)->{
            System.out.println(k + "->" + v);
        });
        //groupingBy(Function,Collector) 2个参数：一个是分组器，按提供的字段进行分组。一个收集器，下面举例了3种用途
        employeeList.forEach(System.out :: println);
        Map<String, IntSummaryStatistics> groupBySumMap = employeeList.stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.summarizingInt(Employee::getSales)));
        groupBySumMap.forEach((k, v)->{
            System.out.println("城市：" + k +"，销售总额："+ v);
        });
        System.out.println("stream按列表对象中的某个字段分组,再提取组内对象取某个字段");
        Map<String, Set<String>> groupByObjToSet = employeeList.stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.mapping(Employee::getName, Collectors.toSet())));
        groupByObjToSet.forEach((k, v) -> {
            System.out.println("城市："+k +",销售员：" + v);
        });
        System.out.println("stream按列表对象中的某个字段分组,再提取组内最大销售额");
        Map<String, Employee> groupByMax = employeeList.stream().collect(Collectors.groupingBy(Employee::getCity, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee::getSales)), Optional::get)));
        groupByMax.forEach((k, v) -> {
            System.out.println("城市：" + k + "， 最大销售额人员：" + v);
        });
        System.out.println("groupingBy参数：一个分组器，一个最终类型的生产者，一个收集器");
        TreeMap<String, Set<String>> groupBy3Parmas = employeeList.stream().collect(Collectors.groupingBy(Employee::getCity, TreeMap::new, Collectors.mapping(Employee::getName, Collectors.toSet())));
        groupBy3Parmas.forEach((k, v) -> {
            System.out.println("城市：" + k + "， 人员：" + v);
        });


        System.out.println("stream按列表对象中的某个字段获取map, key重复取第2个");
        Map<String, User> userNameMap = users.stream().collect(Collectors.toMap(i -> i.getName(), i -> i, (v1, v2)-> v2));
        userNameMap.forEach((k,v) -> {
            System.out.println(k + "->" + v.toString());
        });
        System.out.println("stream按列表对象中的某个字段获取map, key重复取第1个");
        Map<String, User> userNameMap2 = users.stream().collect(Collectors.toMap(i -> i.getName(), i -> i, (v1, v2)-> v1));
        userNameMap2.forEach((k,v) -> {
            System.out.println(k + "->" + v.toString());
        });
        System.out.println("stream按列表对象中的某个字段获取map, key重复转list");
        Map<String, List<User>> userNameMap3 = users.stream().collect(Collectors.toMap(User::getName, i -> Lists.newArrayList(i),
                (List<User> oldList, List<User> newList)->{
                    oldList.addAll(newList);
                return oldList;
        }));
        userNameMap3.forEach((k,v) -> {
            System.out.println(k + "->" + v.toString());
        });


        random.ints().limit(10).forEach(System.out :: println);

        random.ints().limit(10).sorted().forEach(System.out :: println);

        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        long count = strings.parallelStream().filter(s -> s.isEmpty()).count();
        System.out.println("空字符串数：" + count);

        IntSummaryStatistics intSummaryStatistics = users.stream().mapToInt(u -> u.getAge()).summaryStatistics();
        System.out.println("年龄中的最大值：" + intSummaryStatistics.getMax());
        System.out.println("年龄中的最小值：" + intSummaryStatistics.getMin());
        System.out.println("年龄中的平均值：" + intSummaryStatistics.getAverage());
        System.out.println("年龄中的和：" + intSummaryStatistics.getSum());

        Map<String, Long> nameCount = users.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()));
        nameCount.forEach((k,v)->{
            System.out.println(k + "->" + v);
        });
    }


}
