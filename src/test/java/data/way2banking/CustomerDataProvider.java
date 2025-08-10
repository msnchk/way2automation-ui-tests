package data.way2banking;

import org.testng.annotations.DataProvider;

public class CustomerDataProvider {

    @DataProvider(name = "customerData")
    public Object[][] customerData() {
        return new Object[][] {
                {
                    new CustomerData("DataProvider Name", "DataProvider Lastname", "123456")
                }
        };
    }
}
