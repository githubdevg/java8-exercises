package mx.devg.comparator;

import mx.devg.comparator.model.Employee;
import org.junit.Before;

import java.util.Arrays;
import java.util.stream.Stream;

public class ComparatorTest {

    private Employee[] sortedEmployeesByName = null;
    private Employee[] sortedEmployeesByNameDesc = null;
    private Employee[] sortedEmployeesByAge = null;


    @Before
    public void init() {
        sortedEmployeesByName = new Employee[]{new Employee("Ace", 22, 2000d, 5924001l),
                new Employee("John", 25, 3000d, 9922001l),
                new Employee("Keith", 35, 4000d, 3924401l)};

        sortedEmployeesByNameDesc = new Employee[]{new Employee("Keith", 35, 4000d, 3924401l),
                new Employee("John", 25, 3000d, 9922001l),
                new Employee("Ace", 22, 2000d, 5924001l)};

        sortedEmployeesByAge = new Employee[]{new Employee("Ace", 22, 2000d, 5924001l),
                new Employee("John", 25, 3000d, 9922001l),
                new Employee("Keith", 35, 4000d, 3924401l)
        };


    }


//    public static void main(String[] args) {
//
//        Stream<Employee> employeeStream = Arrays.stream(employees);
//
//        employeeStream.forEach(e -> System.out.println(e.getName()));
//
//
//    }

}
