package unitTests.person.add;

import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.error.ApplicationException;
import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.person.add.AddPersonService;
import com.pds.core.service.person.add.AddPersonServiceImpl;
import com.pds.core.service.person.add.AddPersonValidator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class AddPersonServiceTest {

    @Mock
    private AddPersonValidator validator;

    @InjectMocks
    private AddPersonService service = new AddPersonServiceImpl();

    @Test
    public void shouldReturnFailedResponseWhenValidationErrorsExist() {
        PersonRequest request = new PersonRequest();
        request.setPersonalId("210990-66399");

        ApplicationError error = new ApplicationError();
        error.setField("personalId");
        error.setDescription("Cannot be empty");
        List<ApplicationError> errors = new ArrayList<>();
        errors.add(error);

        Mockito.when(validator.validate(request)).thenReturn(errors);

        try {
            service.addPerson(request);
        } catch (ApplicationException e) {
            Assert.assertEquals("personalId", e.getErrors().get(0).getField());
            Assert.assertEquals("Cannot be empty", e.getErrors().get(0).getDescription());
        }
    }

}
