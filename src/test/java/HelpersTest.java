import org.junit.Assert;
import org.junit.Test;

public class HelpersTest {

    @Test
    public void changeBitFrom1to0Test(){
        Assert.assertEquals(1, Helpers.changeBit(0));
    }

    @Test
    public void changeBitFrom0to1Test(){
        Assert.assertEquals(0, Helpers.changeBit(1));
    }

    @Test
    public void convertToBinaryWithParityBitsTestSymbol(){
        StringBuilder sb = new StringBuilder("000011000001");
        Assert.assertEquals(0, sb.compareTo(Coder.convertToBinaryWithParityBits('a')));
    }

}