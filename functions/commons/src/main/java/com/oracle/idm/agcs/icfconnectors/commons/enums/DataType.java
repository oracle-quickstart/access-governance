package com.oracle.idm.agcs.icfconnectors.commons.enums;

public enum DataType {
  TEXT(String.class),
  NUMBER(Long.class),
  DECIMAL_NUMBER(Double.class),
  DATE(Long.class),
  FLAG(Boolean.class),
  UNKNOWN_ENUM_VALUE(null);

  private static final java.util.Map<String, DataType> map;

  static {
    map = new java.util.HashMap<>();
    for (DataType v : DataType.values()) {
      map.put(v.name(), v);
    }
  }

  private final Class<?> type;
  DataType(Class<?> type) {
    this.type = type;
  }

  public static DataType getByName(String name) {
    if (map.containsKey(name)) {
      return map.get(name);
    }
    return UNKNOWN_ENUM_VALUE;
  }

  public Class<?> type() {
    return type;
  }
}

