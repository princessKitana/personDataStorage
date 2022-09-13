package integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.pds.CoreApplication;
import com.pds.core.api.common.v1.AddPersonDTO;
import com.pds.core.api.common.v1.PersonDTO;
import com.pds.core.api.error.v1.ApplicationExceptionDTO;
import com.pds.core.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {CoreApplication.class})
class TestHelper {

    protected String addPersonEndPoint = "/person/addPerson";
    protected String getAllPersonsEndPoint = "/person/getAllPersons";
    protected String findPersonsByParamEndPoint = "/person/findPersonsByParams";

    @LocalServerPort
    public String port;

    @Autowired
    public TestRestTemplate restTemplate;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    public static RestTemplate getRestTemplate(String port) {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .rootUri("http://localhost:" + port)
                .build();
        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    public static HttpEntity<String> getEntity(String messageString) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(messageString, headers);
    }

    protected ApplicationExceptionDTO convertToAppException(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(response, ApplicationExceptionDTO.class);
        } catch (IOException e) {
            throw new RuntimeException("JSON parsing exception", e);
        }
    }

    public PersonDTO createAddPersonDTO(String personalId, String dateOfBirth) {
        var personDTO = new AddPersonDTO();
        personDTO.setPersonalId(personalId);
        personDTO.setDateOfBirth(dateOfBirth);
        return personDTO;
    }

    protected ResponseEntity<AddPersonDTO> addPerson(String personalId, String dateOfBirth) {
        ResponseEntity<AddPersonDTO> response = restTemplate.postForEntity(
                addPersonEndPoint,
                createAddPersonDTO(personalId, dateOfBirth),
                AddPersonDTO.class);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        return response;
    }

}
