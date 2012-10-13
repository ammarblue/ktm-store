package org.ktm.domain.quantity;

import org.ktm.utils.Localizer;

public class Bath extends SIBaseUnit {

    public Bath() {
        setMetricName(Localizer.getMessage("unit.bath", Bath.class));
        setSymbol(Localizer.getMessage("unit.bath", Bath.class));
        setDefinition(Localizer.getMessage("unit.bath.desc", Bath.class));
    }
}
