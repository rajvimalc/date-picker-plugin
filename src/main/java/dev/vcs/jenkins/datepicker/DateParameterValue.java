package dev.vcs.jenkins.datepicker;

import hudson.AbortException;
import hudson.EnvVars;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Run;
import hudson.model.StringParameterValue;
import hudson.tasks.BuildWrapper;
import hudson.util.VariableResolver;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class DateParameterValue extends StringParameterValue {

    @Getter
    private String dateFormat;

    @DataBoundConstructor
    public DateParameterValue(String name, String value, String description) {
        super(name, value, description);
    }

    public DateParameterValue(String name, String value, String dateFormat, String description) {
        this(name, value, description);
        this.dateFormat = dateFormat;
    }

    public void createValueFromDefault(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void createValueFromJenkins(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public void createValueFromPostRequest(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public VariableResolver<String> createVariableResolver(AbstractBuild<?, ?> build) {
        return new VariableResolver<String>() {
            @Override
            public String resolve(String s) {
                return getValue();
            }
        };
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        super.buildEnvironment(build, env);
    }

    @Override
    public BuildWrapper createBuildWrapper(AbstractBuild<?, ?> build) {
        if (StringUtils.isEmpty(getValue())) {
            return null;
        }

        StringLocalDateValue value = new StringLocalDateValue(getValue(), getDateFormat());
        if (!value.isCompletionFormat()) {
            return new BuildWrapper() {
                @Override
                public Environment setUp(AbstractBuild build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
                    throw new AbortException("Can't parse date format '" + getValue() + "' with '" + getDateFormat() + "'");
                }
            };
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DateParameterValue that = (DateParameterValue) o;
        return Objects.equals(dateFormat, that.dateFormat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dateFormat);
    }
}
