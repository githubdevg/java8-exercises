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
    private Employee[] sortedEmployeesByPhone = null;
    private Employee[] sortedEmployeesBySalary = null;
    private Employee[] employeesWithNulls = null;
    private Employee[] employeesWithNullsFirst = null;
    private Employee[] employeesWithNullsLast = null;
    private Employee[] sortedEmployeesByAgeName = null;
    private Employee[] sortedEmployeesByNameAge = null;
    private Employee[] otherEmployees = null;


    @Before
    public void init() {
        Employee employee1 = new Employee("Ace", 22, 2000d, 5924001l);
        Employee employee2 = new Employee("John", 25, 3000d, 9922001l);
        Employee employee3 = new Employee("Keith", 35, 4000d, 3924401l);
        Employee employee4 = new Employee("Jake", 35, 3000d, 9922001l);


        employees = new Employee[] {employee2, employee1, employee3};
        otherEmployees = new Employee[] {employee3, employee1, employee2, employee4};

        employeesWithNulls = new Employee[] {employee2, null, employee1, null,  employee3};
        employeesWithNullsFirst = new Employee[] {null, null, employee1, employee2, employee3};
        employeesWithNullsLast = new Employee[] {employee1, employee2, employee3, null, null};


        sortedEmployeesByName = new Employee[]{employee1, employee2, employee3};

        sortedEmployeesByNameDesc = new Employee[]{employee3, employee2, employee1};

        sortedEmployeesByAge = new Employee[]{employee1, employee2, employee3};
        sortedEmployeesByPhone = new Employee[]{employee3, employee1, employee2};
        sortedEmployeesBySalary = new Employee[]{employee1, employee2, employee3};

        sortedEmployeesByAgeName = new Employee[] {employee1, employee2, employee4, employee3};
        sortedEmployeesByNameAge = new Employee[] {employee1, employee4, employee2, employee3};



    }

    @Test
    public void whenComparing_thenSortedByName() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Arrays.sort(employees, employeeNameComparator);
        assertArrayEquals(employees, sortedEmployeesByName);

    }

    @Test
    public void whenComparingWithComparator_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparatorDesc = Comparator.comparing(Employee::getName, (s1, s2) -> s2.compareTo(s1));
        Arrays.sort(employees, employeeNameComparatorDesc);
        assertArrayEquals(employees, sortedEmployeesByNameDesc);
    }

    @Test
    public void whenReversed_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        Arrays.sort(employees, employeeNameComparator.reversed());
        assertArrayEquals(employees, sortedEmployeesByNameDesc);
    }

    @Test
    public void whenComparingInt_thenSortedByAge() {
        Comparator<Employee> employeeComparatorByAge = Comparator.comparingInt(Employee::getAge);
        Arrays.sort(employees, employeeComparatorByAge);
        assertArrayEquals(employees, sortedEmployeesByAge);
    }

    @Test
    public void whenComparingLong_thenSortedByMobile() {
        Comparator<Employee> employeeComparatorByPhone = Comparator.comparingLong(Employee::getMobile);
        Arrays.sort(employees, employeeComparatorByPhone);
        assertArrayEquals(employees, sortedEmployeesByPhone);
    }


    @Test
    public void whenComparingDouble_thenSortedBySalary() {
        Comparator<Employee> employeeComparatorBySalary = Comparator.comparingDouble(Employee::getSalary);
        Arrays.sort(employees, employeeComparatorBySalary);
        assertArrayEquals(employees, sortedEmployeesBySalary);
    }


    @Test
    public void whenNaturalOrder_thenSortedByName() {
        Comparator<Employee> employeeNameComparator = Comparator.<Employee>naturalOrder();
        Arrays.sort(employees, employeeNameComparator);
        assertArrayEquals(employees, sortedEmployeesByName);
    }

    @Test
    public void whenReverseOrder_thenSortedByNameDesc() {
        Comparator<Employee> employeeNameComparator = Comparator.reverseOrder();
        Arrays.sort(employees, employeeNameComparator);
        assertArrayEquals(employees, sortedEmployeesByNameDesc);
    }

    @Test
    public void whenNullsFirst_thenSortedByNameWithNullsFirst() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullFirst = Comparator.nullsFirst(employeeNameComparator);
        Arrays.sort(employeesWithNulls, employeeNameComparator_nullFirst);

        assertArrayEquals(employeesWithNulls, employeesWithNullsFirst);
    }


    @Test
    public void whenNullsLast_thenSortedByNameWithNullsLast() {
        Comparator<Employee> employeeNameComparator
                = Comparator.comparing(Employee::getName);
        Comparator<Employee> employeeNameComparator_nullLast = Comparator.nullsLast(employeeNameComparator);
        Arrays.sort(employeesWithNulls, employeeNameComparator_nullLast);

        assertArrayEquals(employeesWithNulls, employeesWithNullsLast);

    }

    @Test
    public void whenThenComparing_thenSortedByAgeName(){
        Comparator<Employee> employeeByAgeNameComparator = Comparator.comparing(Employee::getAge).thenComparing(Employee::getName);
        Arrays.sort(otherEmployees, employeeByAgeNameComparator);

        assertArrayEquals(otherEmployees, sortedEmployeesByAgeName);
    }

    @Test
    public void whenThenComparing_thenSortedByNameAge() {
        Comparator<Employee> employeeByNameAgeComparator = Comparator.comparing(Employee::getName).thenComparingInt(Employee::getAge);
        Arrays.sort(otherEmployees, employeeByNameAgeComparator);

        assertArrayEquals(otherEmployees, sortedEmployeesByNameAge);
    }



    @Test
    public void whenComparing_thenSortedByName_Comparator() {
        Comparator<Employee> employeeNameComparator = Comparator.comparing(Employee::getName);
        printArray(employees);
        Arrays.sort(employees, new EmployeeByNameComparator());
        printArray(employees);
        assertArrayEquals(employees, sortedEmployeesByName);
    }


    private void printArray(Employee[] employeeArray) {
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
