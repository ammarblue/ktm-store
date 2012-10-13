package org.ktm.domain.quantity;

import org.ktm.utils.Localizer;

public class Meter extends SIBaseUnit {

    public Meter() {
        setMetricName(Localizer.getMessage("meter", Meter.class));
        setSymbol(Localizer.getMessage("m", Meter.class));
        setDefinition("");
    }
}
