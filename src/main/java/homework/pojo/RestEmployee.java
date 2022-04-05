package homework.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//import org.springframework.web.client.RestTemplate;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestEmployee {

//    private static final long serialVersionUID = 1 L;

    private int id;

    private String employee_name;

    private int employee_salary;

    private int employee_age;

    private String profile_image;

}


