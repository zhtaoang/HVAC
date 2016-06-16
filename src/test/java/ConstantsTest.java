import org.junit.Test;



/**
 * Created by tao.zhang on 6/15/16.
 */
public class ConstantsTest {

    @Test
    public void typeShouldBeString(){
        assert(Constants.INVALID_TEMP_RANGE_MESSAGE.contains(Constants.getInvalidTempRangeMessage()));
    }



}