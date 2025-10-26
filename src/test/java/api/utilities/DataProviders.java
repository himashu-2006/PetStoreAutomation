package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name="Data")
    
    public String[][] getAllData() throws IOException {
        // Construct the full path to the Excel file
        String path = System.getProperty("user.dir") + "//testData//Userdata.xlsx";
        
        // Initialize the Excel utility with the file path
        XLUtility xl = new XLUtility(path);

        // Get total rows and columns (assuming rownum is count of data rows, and colcount is data columns)
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", 1); // Getting column count from the second row (index 1)
        
        // Initialize 2D array for all API data
        String apidata[][] = new String[rownum][colcount];

        // Loop to read data into the 2D array
        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                // Note: i starts at 1 (to skip header) and j starts at 0.
                // apidata[i-1] adjusts for the array being 0-indexed.
                apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
            }
        }
        
        return apidata;
    }

    @DataProvider(name = "UserNames")
    public Object[][] getUserNames() throws IOException {
        // Construct the full path to the Excel file
        String path = System.getProperty("user.dir") + "//Userdata.xlsx";
        
        // Initialize Excel utility
        XLUtility xl = new XLUtility(path);

        // Get total number of data rows (excluding header)
        int rownum = xl.getRowCount("Sheet1");

        // Create a 2D array with one column (for TestNG DataProvider)
        Object[][] apidata = new Object[rownum][1];

        // Loop through rows — assuming usernames are in column 1
        for (int i = 1; i <= rownum; i++) {
            apidata[i - 1][0] = xl.getCellData("Sheet1", 1, i);
        }

        return apidata;
    }

    
//    @DataProvider(name = "UserNames")
//    public Object[][] getUserNames() throws IOException {
//        String path = System.getProperty("user.dir") + "/testData/Userdata.xlsx";
//        XLUtility xl = new XLUtility(path);
//        
//        int rownum = xl.getRowCount("DeleteUser");
//        Object[][] userNames = new Object[rownum][1];
//
//        for (int i = 1; i <= rownum; i++) {
//            userNames[i-1][0] = xl.getCellData("DeleteUser", 0, i);
//        }
//        return userNames;
//    }

}