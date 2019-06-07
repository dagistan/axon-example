package com.si.scheme.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEvent<T> {

    public final T id;

    public BaseEvent(T id) {
        this.id = id;
    }
}
