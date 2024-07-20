import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class CoderTest {
    @Test
    public void coderOneChar(){
        String expected = "110111010001";
        assertEquals(expected, Coder.start("a"));
    }

    @Test
    public void coderThreeChars(){
        String expected = "110111010001000011010010000111000011";
        assertEquals(expected, Coder.start("abc"));
    }

    @Test
    public void coderWithSpace(){
        String expected = "100101110001010101000000010001110010010101000000010101100011"; // "1 2 3"
        assertEquals(expected, Coder.start("1 2 3"));
    }

    @Test
    public void coderFromFile1_2_3() throws IOException {
        File expectedFile = new File("src/test/resources/1_2_3_expected.txt");
        Helpers.writeToFile(Coder.start("1 2 3"), "src/test/resources/1_2_3_test.txt");
        File resultFile = new File("src/test/resources/1_2_3_test.txt");

        String expectedContent = FileUtils.readFileToString(expectedFile, "UTF-8");
        String resultContent = FileUtils.readFileToString(resultFile, "UTF-8");

        assertEquals(expectedContent, resultContent);
    }
}