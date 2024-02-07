package com.oracle.idm.agcs.icfconnectors.commons.util;

import java.util.HashMap;
import java.util.Map;

public class ConnectorDataHolder {
    private static ThreadLocal<Map<ConnectorDataType, Object>> dataHolder = new ThreadLocal<>();

    public static Object getData(ConnectorDataType connectorDataType) {
        if (dataHolder.get() == null) {
            dataHolder.set(new HashMap<>());
        }
        return dataHolder.get().get(connectorDataType);
    }

    public static void setData(ConnectorDataType connectorDataType, Object data) {
        if (dataHolder.get() == null) {
            dataHolder.set(new HashMap<>());
        }
        dataHolder.get().put(connectorDataType, data);
    }
}
