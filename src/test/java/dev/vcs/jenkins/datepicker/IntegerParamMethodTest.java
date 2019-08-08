package dev.vcs.jenkins.datepicker;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.is;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class IntegerParamMethodTest {

    @Test
    public void test_method() {
        IntegerParamMethod method = new IntegerParamMethod("plusDays(1);");
        Assert.assertEquals(method.getName(), "plusDays");
        Assert.assertThat(method.getParameter(), is(1));
    }

}