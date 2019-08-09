package dev.vcs.jenkins.datepicker;

import hudson.Extension;
import org.kohsuke.stapler.DataBoundConstructor;

/**
 * @author Vimalraj Chandra Sekaran (rajvimalc)
 * Email: rajvimalc@gmail.com
 * Created on: 8/7/2019 3:25 PM
 */
public class TimePickerDefinition extends PickerDefinition {

    private static final String DEFAULT_DATE_FORMAT = "HH:mm";

    private static final String DISPLAY_NAME = "Time Picker Parameter";

    @DataBoundConstructor
    public TimePickerDefinition(String name, String defaultValue, String description) {
        super(name, defaultValue, description, DEFAULT_DATE_FORMAT);
    }

    @Extension
    public static final class DescriptorImpl extends PickerDescriptor {
        public DescriptorImpl() {
            super(DISPLAY_NAME, DEFAULT_DATE_FORMAT);
        }
    }

}
