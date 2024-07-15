import org.junit.Assert;
import org.junit.Test;

public class CoderTest {
    @Test
    public void coderOneChar(){
        String expected = "110111010001";
        Assert.assertEquals(expected, Coder.start("a"));
    }
    @Test
    public void coderThreeChars(){
        String expected = "110111010001000011010010000111000011";
        Assert.assertEquals(expected, Coder.start("abc"));
    }
    public void coderWithSpace(){
        String expected = "100101110001010101000000010001110010010101000000010101100011"; // "1 2 3"
        Assert.assertEquals(expected, Coder.start("1 2 3"));
    }
}