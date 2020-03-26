/*
package org.ticparabien.hotelcovid19.integration;

import org.ticparabien.hotelcovid19.controller.api.SampleController;
import org.ticparabien.hotelcovid19.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static java.util.Arrays.asList;
import static org.ticparabien.hotelcovid19.helpers.InvoicesHelper.*;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class, secure = false)
public class ApiSampleControllerShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReportService reportServiceStub;

    @Test
    public void retrieve_Rooms_parsed_to_json() throws Exception {
        List<TotalsByRoom> totalsByRooms =
                asList(aTotalByRoom("fooRoom", 1, "29.50", "28.50"),
                        aTotalByRoom("barRoom", 2, "32.50", "30.50"));
        TotalsReport totalsReport = new TotalsReport(totalsByRooms);
        given(reportServiceStub.generateReport(any())).willReturn(totalsReport);

        String expectedReport =
        "{" +
            "\"totalGrossAmount\":\"" + totalsReport.totalGrossAmount() + "\"," +
            "\"totalNetAmount\":\""   + totalsReport.totalNetAmount() + "\"," +
            "\"totalNumberOfInvoices\":" + totalsReport.totalNumberOfInvoices() + "," +
            "\"totalAverageGrossTotals\":\"" + totalsReport.totalAverageGrossAmount() + "\"," +
            "\"filters\":\"?Rooms[]=1&fromDay=2017-05-08&toDay=2017-05-08\"," +
            "\"Rooms\":" +
               "[{\"name\":\"fooRoom\",\"grossTotals\":29.50, \"netTotals\":28.50, \"numberOfInvoices\":1, \"averageGrossTotals\":29.50}," +
               " {\"name\":\"barRoom\",\"grossTotals\":32.50, \"netTotals\":30.50, \"numberOfInvoices\":2, \"averageGrossTotals\":16.25}]" +
        "}";

        this.mockMvc.perform(get("/api/hotelcovid19/json/totals")
                .param("fromDay", "2017-05-08")
                .param("toDay", "2017-05-08")
                .param("Rooms[]", "1"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedReport));
    }

    @Test
    public void retrieve_history_report_json() throws Exception {
        PeriodsReportFilters filters = new PeriodsReportFilters(
                april1_2016, october3, asList("1"), GroupByPeriod.Month);
        ReportByPeriod report = new ReportByPeriod(invoicesByRoom()
                .with(1,
                        aStoredInvoice(1, april1_2016, "5"),
                        aStoredInvoice(1, may30,  "3"))
                .areGiven(), filters);
        given(reportServiceStub.generateReportByPeriods(any())).willReturn(report);

        String expectedJson = "{" +
                                "\"periodNames\":[\"ABRIL\",\"MAYO\",\"JUNIO\",\"JULIO\",\"AGOSTO\",\"SEPTIEMBRE\",\"OCTUBRE\"]," +
                                "\"Rooms\":[{" +
                                                        "\"name\":\"Room_1\"," +
                                                        "\"grossTotals\":[\"5\",\"3\",\"0\",\"0\",\"0\",\"0\",\"0\"]" +
                                                    "}]," +
                                "\"filters\":\"?Rooms[]=1&fromDay="+ april1_2016 + "&toDay=" + october3 + "&groupByPeriod=Month\"" +
                              "}";

        this.mockMvc.perform(get("/api/hotelcovid19/json/history")
                .param("fromDay", april1_2016)
                .param("toDay", october3)
                .param("Rooms[]", "1")
                .param("groupByPeriod", "Month"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }


    private TotalsByRoom aTotalByRoom(String name, Integer numberOfInvoices, String grossTotals, String netTotals) {
        return new TotalsByRoom(name, numberOfInvoices, new BigDecimal(grossTotals), new BigDecimal(netTotals));
    }

}
*/
