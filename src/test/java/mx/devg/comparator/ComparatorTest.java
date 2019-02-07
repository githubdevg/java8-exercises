package mx.devg.comparator;

import mx.devg.comparator.model.Employee;

import java.util.Arrays;
import java.util.stream.Stream;

public class ComparatorTest {

    static Employee[]  employees = {new Employee("John", 25, 3000d, 9922001l),
    new Employee("Ace", 22, 2000d, 5924001l),
    new Employee("Keith", 35, 4000d, 3924401l)};


    public static void main(String[] args) {

        Stream<Employee> employeeStream = Arrays.stream(employees);

        employeeStream.forEach(e -> System.out.println(e.getName()));


    }

}
