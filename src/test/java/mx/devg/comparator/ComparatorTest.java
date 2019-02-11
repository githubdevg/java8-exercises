package mx.devg.comparator;

import mx.devg.comparator.model.Employee;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;


import static org.junit.Assert.assertArrayEquals;

public class ComparatorTest {

    private Employee[] employees = null;
    private Employee[] sortedEmployeesByName = null;
    private Employee[] sortedEmployeesByNameDesc = null;
    private Employee[] sortedEmployeesByAge = null;


    @Before
    public void init() {
        Employee employee1 = new Employee("Aceeee", 22, 2000d, 5924001l);
        Employee employee2 = new Employee("John", 25, 3000d, 9922001l);
        Employee employee3 = new Employee("Keith", 35, 4000d, 3924401l);

        employees = new Employee[] {employee2, employee1, employee3};

        sortedEmployeesByName = new Employee[]{employee1, employee2, employee3};

        sortedEmployeesByNameDesc = new Employee[]{employee3, employee2, employee1};

        sortedEmployeesByAge = new Employee[]{employee1, employee2, employee3};

    }

    @Test
    public void whenComparing_thenSortedByName() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(e -> e.getName());

        printArray(employees);


        Arrays.sort(employees, new EmployeeByNameComparator());

        printArray(employees);

        assertArrayEquals(employees, sortedEmployeesByName);

    }


    public void printArray(Employee[] employeeArray) {
        Arrays.stream(employeeArray).forEach(e -> System.out.println("Name: " + e.getName()));
    }

    class EmployeeByNameComparator implements Comparator<Employee>{

        @Override
        public int compare(Employee e1, Employee e2) {
            return e1.getName().compareToIgnoreCase(e2.getName());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }

}
