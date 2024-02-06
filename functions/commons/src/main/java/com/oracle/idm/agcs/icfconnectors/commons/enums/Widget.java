package com.oracle.idm.agcs.icfconnectors.commons.enums;

public enum Widget {
    Text("input-text"),
    Password("input-password"),
    Number("input-number"),
    Date("date"),
    SelectOne("select-one"),
    RepeatableFieldSet("repeatableFieldSet"),
    CheckboxSet("checkboxset");

    private final String widgetId;

    Widget(String widgetId) {
        this.widgetId = widgetId;
    }

    public String getWidgetId() {
        return widgetId;
    }
}
