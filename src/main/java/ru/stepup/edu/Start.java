package ru.stepup.edu;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Start {

    public static void main(String[] args) {

        // ТЗ: "Реализуйте удаление из листа всех дубликатов"
        IntStream.of(5, 2, 10, 9, 4, 3, 10, 1, 13 ).distinct().forEach(System.out::println);
        System.out.println("---------------------------");

        // ТЗ: "Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)"
        System.out.println(IntStream.of(5, 2, 10, 9, 4, 3, 10, 1, 13 )
                .boxed()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .skip(2).findFirst().get());

        System.out.println("---------------------------");

        // ТЗ: "Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
        // в отличие от прошлой задачи здесь разные 10 считает за одно число)"
        System.out.println(IntStream.of(5, 2, 10, 9, 4, 3, 10, 1, 13 )
                .boxed()
                .distinct()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .skip(2).findFirst().get());

        System.out.println("---------------------------");

        // ТЗ: "Имеется список объектов типа Сотрудник (имя, возраст, должность), необходимо получить список имен 3 самых старших
        // сотрудников с должностью «Инженер», в порядке убывания возраста"
        List<Employee> employeeList = Arrays.asList(new Employee("Иванов", 30, "Инженер"),
                new Employee("Петров", 40, "Стажер"),
                new Employee("Сидоров", 35, "Начальник"),
                new Employee("Саврасов", 36, "Инженер"),
                new Employee("Семенов", 25, "Стажер"),
                new Employee("Репин", 42, "Инженер"),
                new Employee("Злобин", 50, "Инженер"),
                new Employee("Галкин", 31, "Инженер")
        );

        employeeList.stream().filter(x -> x.getPosition() == "Инженер")
                .sorted((x1, x2) -> (x2.getAge()).compareTo(x1.getAge()))
                .limit(3).map(x->x.getName()).forEach(System.out::println);
        System.out.println("---------------------------");

        // ТЗ: "Имеется список объектов типа Сотрудник (имя, возраст, должность), посчитайте средний возраст сотрудников с должностью «Инженер»"
        System.out.println(employeeList.stream().filter(x -> x.getPosition() == "Инженер")
                .mapToInt(x -> x.getAge()).average().orElse(0));

        System.out.println("---------------------------");

        // ТЗ: "Найдите в списке слов самое длинное"
        // My comment: для примера будем искать в предыдущем списке сотрудников
        System.out.println(employeeList.stream().max(Comparator.comparingInt(x -> x.getName().length()))
                .map(x-> "самое длинное слово '" + x.getName() + "'").orElse("самое длинное слово не было найдено"));

        System.out.println("---------------------------");

        // ТЗ: "Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
        // Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке"
        String text = "In this article, we will delve deeper into the purpose of printer test pages, how to print them, and what to look for when evaluating the results".toLowerCase();
        Map<String, Long> wordMap = new ArrayList<String>(
                Arrays.asList(text.replace("  ", " ")
                        .replace(",", "")
                        .split(" ")))
                .stream()
                .collect(
                        Collectors.groupingBy(x -> x, Collectors.counting()));

        for(Map.Entry<String, Long> item : wordMap.entrySet()){
            System.out.println("слово '" + item.getKey() + "' - " + item.getValue());
        }
        System.out.println("---------------------------");

        // ТЗ: Отпечатайте в консоль строки из списка в порядке увеличения длины слова, если слова имеют одинаковую длины,
        // то должен быть сохранен алфавитный порядок
        // My comment: 1-ый способ
        new ArrayList<String>(
                Arrays.asList(text.replace("  ", " ")
                        .replace(",", "")
                        .split(" ")))
                .stream()
                .sorted(Comparator.comparingInt(String::length)
                        .thenComparing(Comparator.naturalOrder())).forEach(System.out::println);

        System.out.println("--------------------------- 2-ой способ");

        // My comment: 2-ой способ
        new ArrayList<String>(
                Arrays.asList(text.replace("  ", " ")
                        .replace(",", "")
                        .split(" ")))
                .stream()
                .sorted((w1, w2) -> {
                            if (w1.length() == w2.length()) {
                                return w1.compareTo(w2);
                            }
                            else {
                                return w1.length() - w2.length();
                            }
                        }
                ).forEach(System.out::println);

        /////////////////////////////////////
        System.out.println("------------------------");

        // ТЗ: "Имеется массив строк, в каждой из которых лежит набор из 5 строк (My comment: слов?), разделенных пробелом,
        // найдите среди всех слов самое длинное, если таких слов несколько, получите любое из них"

        // My comment: массив строк из пяти слов
        String arr[] = {"In this article we will"
                , "delve deeper into the purpose"
                , "of printer test pages how to"
                , "print them and what to"
                , "look for when evaluating the"
        };

        // My comment: делаем List<List<String>> и затем применяем к нему flatMap
        System.out.println(Arrays.stream(arr).map(x-> Arrays.stream(x.split(" ")).toList()).toList()
                .stream().flatMap(list -> list.stream())
                .reduce((w1, w2) -> w1.length() > w2.length() ? w1 : w2)
                .get());
    }
}
