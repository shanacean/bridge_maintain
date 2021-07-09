package com.dancy.maintain.service.monitor;

import com.dancy.maintain.pojo.intermediate.TypeComponent;
import com.dancy.maintain.pojo.intermediate.TypePart;

import java.util.List;

public interface MonitorContentService {
    Boolean updatePartWeight(List<TypePart> parts);
    Boolean updateComponentWeight(List<TypeComponent> components);
}
