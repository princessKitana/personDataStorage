package integrationTests;

import com.pds.core.api.common.v1.AddPersonDTO;
import com.pds.core.api.error.v1.ApplicationExceptionDTO;
import com.pds.core.service.error.ApplicationError;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static com.pds.core.enums.BusinessRules.BR_3000;
import static org.junit.Assert.assertEquals;

public class AddPersonTest extends TestHelper {

    @Test
    public void shouldAddPersonSuccessfully() {
        ResponseEntity<AddPersonDTO> response = addPerson("210990-66399", "2000-11-21");
        Assert.assertEquals("210990-66399", response.getBody().getPersonalId());
        Assert.assertEquals("2000-11-21", response.getBody().getDateOfBirth());
        Assert.assertNotNull(response.getBody().getGender());
        Assert.assertNotNull(response.getBody().getFirstName());
        Assert.assertNotNull(response.getBody().getLastName());
    }

    @Test
    public void shouldFailWhenMandatoryFieldPersonalIdIsMissing() {
        ResponseEntity<String> response = restTemplate.postForEntity(
                addPersonEndPoint,
                createAddPersonDTO("", "2000-11-21"),
                String.class);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        ApplicationExceptionDTO appEx = convertToAppException(response.getBody());
        List<ApplicationError> errors = appEx.getErrors();
        assertEquals(1, errors.size());
        assertEquals("personalId", errors.get(0).getField());
        assertEquals(BR_3000.name(), errors.get(0).getStatusCode());
        assertEquals("personalId: is mandatory", errors.get(0).getDescription());
    }

}
