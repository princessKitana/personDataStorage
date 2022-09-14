package integrationTests;

import com.pds.core.api.common.v1.GetPersonDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

public class GetPersonTest extends TestHelper {

    private ParameterizedTypeReference<List<GetPersonDTO>> responseType = new ParameterizedTypeReference<List<GetPersonDTO>>() {
    };

    @Test
    public void shouldReturnAllPersons() {
        //add one person
        addPerson("050187-66399", "1987-01-05");

        //add another person
        addPerson("211120-66399", "2020-11-21");

        ResponseEntity<List<GetPersonDTO>> response = restTemplate.exchange(
                getAllPersonsEndPoint,
                HttpMethod.GET,
                null,
                responseType);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(2, response.getBody().size());
    }

    @Test
    public void shouldFindPersonByPersonalId() {
        //add one person
        addPerson("050187-66399", "1987-01-05");

        //add another person
        addPerson("211120-66399", "1987-01-05");

        GetPersonDTO getPersonByParamRequest = new GetPersonDTO();
        getPersonByParamRequest.setPersonalId("211120-66399");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(getPersonByParamRequest, headers);

        var response = restTemplate.exchange(
                findPersonsByParamEndPoint,
                HttpMethod.POST,
                request,
                responseType);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(1, response.getBody().size());
    }

    @Test
    public void shouldFindPersonByDateOfBirth() {
        //add one person
        addPerson("050187-66399", "1987-01-05");

        //add another person
        addPerson("211120-66399", "1987-01-05");

        GetPersonDTO getPersonByParamRequest = new GetPersonDTO();
        getPersonByParamRequest.setDateOfBirth("1987-01-05");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(getPersonByParamRequest, headers);

        var response = restTemplate.exchange(
                findPersonsByParamEndPoint,
                HttpMethod.POST,
                request,
                responseType);

        Assert.assertNotNull(response);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(2, response.getBody().size());
    }

}
