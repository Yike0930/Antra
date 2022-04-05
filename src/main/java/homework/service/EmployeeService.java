package homework.service;

import homework.pojo.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import homework.pojo.RestEmployee;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final String url = "http://dummy.restapiexample.com/api/v1/employees";


    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<RestEmployee> getAllEmployees() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        return restTemplate.exchange("http://dummy.restapiexample.com/api/v1/employees",
                        HttpMethod.GET, entity, RestResponse.class).getBody().getData();
    }

    public List<List<RestEmployee>> getAllEmployeesGroupByAge() {
        Map<Integer, List<RestEmployee>> employeesAgeMap = getAllEmployees().stream().collect(Collectors.groupingBy(RestEmployee::getEmployee_age));
//                .collect(() -> {return new HashMap<Integer, List<RestEmployee>>();}, (ageMap, restEmployee) -> {
//                List<RestEmployee> employeesAge = ageMap.getOrDefault(restEmployee.getEmployee_age(), new ArrayList<RestEmployee>());
//                employeesAge.add(restEmployee);
//                ageMap.put(restEmployee.getEmployee_age(), employeesAge);
//                }, null );
        List<List<RestEmployee>> result = new ArrayList<>();
        for(int key : employeesAgeMap.keySet()) {
            result.add(employeesAgeMap.get(key));
        }
        return result;

    }

    public List<RestEmployee> getEmployeesByAge(int age) {
//        RestResponse rR = getAllEmployees();
        return getAllEmployees().stream().filter(employee -> employee.getEmployee_age() == age).collect(Collectors.toList());
    }
}
