package dev.vcs.jenkins.datepicker;

import hudson.tasks.BuildWrapper;
import hudson.util.VariableResolver;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class DateParameterValueTest {

    @Test
    public void date_parameter_value_basic_test() {
        DateParameterValue value = new DateParameterValue("MY_DATE", "2017-05-01", "description");
        VariableResolver<String> resolver = value.createVariableResolver(null);
        String variable = resolver.resolve(null);
        Assert.assertEquals("2017-05-01", variable);
    }

    @Test
    public void date_parameter_value_build_wrapper_when_value_is_empty_test() {
        DateParameterValue value = new DateParameterValue("MY_DATE", "", "description");
        BuildWrapper buildWrapper = value.createBuildWrapper(null);
        Assert.assertNull(buildWrapper);
    }

    @Test
    public void date_parameter_value_build_wrapper_test() {
        DateParameterValue value = new DateParameterValue("MY_DATE", "2017-05-01", "description");
        value.createValueFromJenkins("yyyy-MM-dd");
        BuildWrapper buildWrapper = value.createBuildWrapper(null);
        Assert.assertNull(buildWrapper);
    }

    @Test
    public void date_parameter_value_build_wrapper_when_wrong_date_format_test() {
        DateParameterValue value = new DateParameterValue("MY_DATE", "20170501", "description");
        value.createValueFromJenkins("yyyy-MM-dd");
        BuildWrapper buildWrapper = value.createBuildWrapper(null);
        Assert.assertNotNull(buildWrapper);
    }

}
