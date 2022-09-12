package unitTests.person.add;

import com.pds.core.domain.Person;
import com.pds.core.enums.BusinessRules;
import com.pds.core.repository.PersonRepository;
import com.pds.core.service.error.ApplicationError;
import com.pds.core.service.person.PersonRequest;
import com.pds.core.service.person.add.AddPersonValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class AddPersonValidatorTest {

    @Mock
    private PersonRepository repository;

    @InjectMocks
    private AddPersonValidator validator = new AddPersonValidator();

    @Test
    public void shouldNotReturnErrorWhenRequestCorrect() {
        Mockito.when(repository.findByPersonalId(any())).thenReturn(Optional.empty());

        PersonRequest request = new PersonRequest();
        request.setPersonalId("210990-66399");
        request.setDateOfBirth("2000-09-08");

        List<ApplicationError> applicationErrors = validator.validate(request);
        assertEquals(0, applicationErrors.size());
    }

    @Test
    public void shouldReturnErrorsWhenRequestIsEmpty() {
        List<ApplicationError> applicationErrors = validator.validate(new PersonRequest());
        assertEquals(2, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3000.name())));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidPersonalId() {
        PersonRequest request = new PersonRequest();
        request.setPersonalId("21054990-6aajhhgj6399");
        request.setDateOfBirth("2000-09-08");

        List<ApplicationError> applicationErrors = validator.validate(request);
        assertEquals(1, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3001.name())));
    }

    @Test
    public void shouldReturnErrorsWhenInvalidDateOfBirth() {
        PersonRequest request = new PersonRequest();
        request.setPersonalId("210990-66399");
        request.setDateOfBirth("2000-60-50");

        List<ApplicationError> applicationErrors = validator.validate(request);
        assertEquals(1, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3003.name())));

        request.setDateOfBirth("200a0-222-50f");
        applicationErrors = validator.validate(request);
        assertEquals(1, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3003.name())));
    }

    @Test
    public void shouldReturnErrorsWhenDateOfBirthMoreThenCurrentDate() {
        PersonRequest request = new PersonRequest();
        request.setPersonalId("210990-66399");
        request.setDateOfBirth(String.valueOf(LocalDate.now().plus(1, ChronoUnit.DAYS)));

        List<ApplicationError> applicationErrors = validator.validate(request);
        assertEquals(1, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3002.name())));
    }

    @Test
    public void shouldReturnErrorWhenPersonalIdAlreadyExistsInDB() {
        var person = new Person();
        person.setPersonalId("210990-66399");
        Mockito.when(repository.findByPersonalId(any())).thenReturn(Optional.of(person));

        PersonRequest request = new PersonRequest();
        request.setPersonalId("210990-66399");
        request.setDateOfBirth("2000-01-01");

        List<ApplicationError> applicationErrors = validator.validate(request);
        assertEquals(1, applicationErrors.size());
        assertTrue(applicationErrors.stream().anyMatch(e -> e.getStatusCode().equals(BusinessRules.BR_3004.name())));
    }

}
