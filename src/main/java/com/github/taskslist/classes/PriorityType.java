package com.github.taskslist.classes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Developer on 21.10.2016.
 */
public enum PriorityType {
    HIGH(1), MEDIUM(2), LOW(3);
    private Integer value;
    PriorityType(Integer value)
    {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    static final Map<Integer, PriorityType> dict;
    static {
        PriorityType[] array = PriorityType.values();
        dict = new HashMap<>(array.length);
        for (PriorityType e : array)
        {
         dict.put(e.getValue(), e);
        }
    }

    public static PriorityType getPriorityTypeByValue(Integer s)
    {
        return dict.get(s);
    }



}
