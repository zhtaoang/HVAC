import org.junit.Test;

import static org.assertj.core.api.Assertions.*;


/**
 * Created by tao.zhang on 6/15/16.
 */
public class ConstantsTest {

    @Test
    public void typeShouldBeString(){
        assertThat(Constants.INVALID_TEMP_RANGE_MESSAGE.contains(Constants.getInvalidTempRangeMessage()));
    }



}