package com.cdm.gastos.services;

import com.cdm.gastos.core.utils.GenericServiceAPI;
import com.cdm.gastos.models.Informe;

import java.util.List;

public interface InformeServiceAPI  extends GenericServiceAPI <Informe, Long> {
    List<Informe> findAllInformeGeneric();
}
