package org.ktm.domain.quantity;

import org.ktm.utils.Localizer;

public class Box extends SIBaseUnit {

    public Box() {
        setMetricName(Localizer.getMessage("unit.box", Box.class));
        setSymbol(Localizer.getMessage("unit.box", Box.class));
        setDefinition(Localizer.getMessage("unit.box.desc", Box.class));
    }
}
