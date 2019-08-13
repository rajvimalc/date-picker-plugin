package dev.vcs.jenkins.datepicker;

import org.apache.commons.lang.StringUtils;

/**
 * @author Vimalraj Chandra Sekaran (vchandr4)
 * Email: vchandr4@mazdausa.com
 * Created on: 8/9/2019 3:15 PM
 */
public enum PickerType {

    DATE_TIME("yyyy-MM-dd'T'HH:mm"),

    DATE("yyyy-MM-dd"),

    TIME("HH:mm");

    private final String format;

    private final String displayDescription;

    PickerType(String format) {
        this.format = format;
        this.displayDescription = String.format("%s (%s)", this.name(), format);
    }

    public String getType() {
        return this.name();
    }

    public String getFormat() {
        return format;
    }

    public String getDisplayDescription() {
        return displayDescription;
    }

    public static PickerType resolve(String typeStr) {
        if (StringUtils.isNotBlank(typeStr)) {
            for (PickerType type : PickerType.values()) {
                if (type.getType().equals(typeStr)) {
                    return type;
                }
            }
        }
        throw new PickerException("Invalid Picker Type");
    }
}
