package data.way2banking.customer;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;

public class CustomerDataProvider {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return new Object[][] {
                {
                    new CustomerData(
                            "DataProvider Name",
                            "DataProvider Lastname",
                            "123456",
                            new ArrayList<>(),
                            new ArrayList<>()
                            )
                }
        };
    }
}
