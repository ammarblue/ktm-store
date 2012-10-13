package org.ktm.domain.quantity;

import org.ktm.utils.Localizer;

public class Kilogram extends SIBaseUnit {

    public Kilogram() {
        setMetricName(Localizer.getMessage("unit.kilogram", Box.class));
        setSymbol(Localizer.getMessage("unit.kg", Box.class));
        setDefinition(Localizer.getMessage("unit.kilogram.desc", Box.class));
    }
}
