package com.bivgroup.xbrl.entity;

/**
 * Interface values element
 * Created by bush on 10.03.2017.
 */
public interface Value<T> {
    /**
     * get value
     *
     * @return - value
     */
    T getValue();

    /**
     * set value
     *
     * @param value - value
     * @param <T>   - type value
     */
    <T> void setValue(T value);
}
