package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Employee;
import entity.Project;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import repository.AddressRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoMain {
    public static void main(String[] args) throws SQLException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        AddressRepository addressRepository = context.getBean(AddressRepository.class);
        EmployeeRepository employeeRepository= context.getBean(EmployeeRepository.class);
        ProjectRepository projectRepository = context.getBean(ProjectRepository.class);

        Address address = new Address();
         address.setId(1L);
        address.setCountry("DC");
        address.setCity("Gotman City");
        address.setStreet("Arkham street 1");
        address.setPostCode("12345");

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Gotham PD");

        Employee employee = new Employee();

        employee.setId(1L);
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        Calendar calendar = Calendar.getInstance();
        calendar.set(1939,Calendar.MAY,1);
        employee.setBirthDay( new java.sql.Date(calendar.getTime().getTime()));
        employee.setAddress(address);

        Set<Employee> employees = new HashSet<Employee>();
        employees.add(employee);
        project.setEmployees(employees);


        Set<Project> projects = new HashSet<Project>();
        projects.add(project);
        employee.setProjects(projects);

        addressRepository.save(address);
        employeeRepository.save(employee);
        projectRepository.save(project);
        System.out.println(employeeRepository.findByLastName("Gordon"));

//      addressService.add(address);
//      employeeService.add(employee);
//      projectService.add(project);
      List<Address> addresses = addressRepository.findAll();
       for(Address a: addresses) System.out.println(a);
      HibernateUtil.shutdown();

    }

}