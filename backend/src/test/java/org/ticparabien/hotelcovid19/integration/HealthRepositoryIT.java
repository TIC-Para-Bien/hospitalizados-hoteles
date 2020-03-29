package org.ticparabien.hotelcovid19.integration;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.ticparabien.hotelcovid19.domain.HealthRecord;
import org.ticparabien.hotelcovid19.domain.repositories.HealthRecordRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
class HealthRepositoryIT {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String NAME = "name";

    private static final String PERSONAL_ID = "personalId";

    private static final String PHONE = "phone";

    @Autowired
    private HealthRecordRepository repository;

    @DatabaseSetup("/dataset/multiple-patient-records.xml")
    @Test
    void given_AnExistingPersonalId_When_RetrievingPatientByPersonalId_Then_GetEntity() throws ParseException {
        Date date = simpleDateFormat.parse("2020-01-01 20:30:00");
        List<HealthRecord> list = repository.findAllLastRecordsWithTemperatureHigherThanAfterDate(37.5f, date);

        assertThat(list).hasSize(2);
        assertThat(list).extracting("id").contains(4, 6);
    }
}
