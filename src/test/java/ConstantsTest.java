import org.junit.Test;



/**
 * Created by tao.zhang on 6/15/16.
 */
public class ConstantsTest {

    @Test
    public void tempRange(){
        assert(Constants.INVALID_TEMP_RANGE_MESSAGE.contains(Constants.getInvalidTempRangeMessage()));
    }

    @Test
    public void port(){
        assert(Constants.PORT==Constants.getPORT());
    }

}