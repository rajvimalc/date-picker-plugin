package dev.vcs.jenkins.datepicker;

import hudson.Extension;
import hudson.model.ParameterDefinition;
import hudson.model.ParameterValue;
import hudson.util.FormValidation;
import net.sf.json.JSONObject;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isEmpty;

/**
 * @author Vimalraj Chandra Sekaran (vchandr4)
 * Email: vchandr4@mazdausa.com
 * Created on: 8/7/2019 3:25 PM
 */
public class DatePickerDefinition extends ParameterDefinition {

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm";

    private String defaultValue;

    private String value;

    @DataBoundConstructor
    public DatePickerDefinition(String name, String defaultValue, String description) {
        super(name, description);
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }


    /**
     * Called from jelly
     * @return String
     */
    public String getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Called from jelly
     * @return String
     */
    public String getValue() {
        return this.value;
    }

    @Override
    public DateParameterValue getDefaultParameterValue() {
        DateParameterValue value = new DateParameterValue(getName(), getValue(), getDescription());
        value.createValueFromDefault(DEFAULT_DATE_FORMAT);
        return value;
    }

    @Override
    public ParameterValue createValue(StaplerRequest req, JSONObject jo) {
        DateParameterValue value = req.bindJSON(DateParameterValue.class, jo);
        value.createValueFromJenkins(DEFAULT_DATE_FORMAT);
        return value;
    }

    @Override
    public ParameterValue createValue(StaplerRequest staplerRequest) {
        String requestedValue = staplerRequest.getParameter(getName());
        if (isEmpty(requestedValue)) {
            return getDefaultParameterValue();
        }

        DateParameterValue value = new DateParameterValue(getName(), requestedValue, DEFAULT_DATE_FORMAT, getDescription());
        value.createValueFromPostRequest(DEFAULT_DATE_FORMAT);
        return value;
    }

    @Extension
    public static final class DescriptorImpl extends ParameterDescriptor {

        private final static String DISPLAY_NAME = "Date Picker Parameter";

        @Override
        public String getDisplayName() {
            return DISPLAY_NAME;
        }

        public FormValidation doCheckName(@QueryParameter String name) {
            if (isEmpty(name)) {
                return FormValidation.error("Please enter a name");
            }
            return FormValidation.ok();
        }

        public FormValidation doCheckDefaultValue(@QueryParameter String defaultValue) {
            if (validateJavaDate(defaultValue)) {
                return FormValidation.ok();
            }
            return FormValidation.error("Invalid default value");
        }

        public boolean validateJavaDate(String strDate) {
            if (isBlank(strDate)) {
                return true;
            }
            final SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
            sdf.setLenient(false);
            try {
                sdf.parse(strDate);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }

    }

}
