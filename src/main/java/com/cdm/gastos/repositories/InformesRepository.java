package com.cdm.gastos.repositories;

import com.cdm.gastos.models.Informe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InformesRepository extends JpaRepository <Informe,Long> {
    @Query(value = "SELECT\n" +
            "i.id_informe, i.anulado,\n" +
            "( SELECT SUM(s.cantidad) FROM registro_salida s  WHERE s.id_informe = i.id_informe ) AS credito,\n" +
            "( SELECT SUM(e.cantidad) FROM registro_entrada e WHERE e.id_informe = i.id_informe ) as debito,\n" +
            "i.fecha,\n" +
            "SUM(\n" +
            "\tDISTINCT \n" +
            "\t( SELECT SUM(e.cantidad) FROM registro_entrada e WHERE e.id_informe = i.id_informe ) \n" +
            "\t\t- \n" +
            "\t( SELECT SUM(s.cantidad) FROM registro_salida s  WHERE s.id_informe = i.id_informe )\n" +
            "\t) AS total,\n" +
            "p.id_proyecto,\n" +
            "u.id\n" +
            "FROM informe i, registro_salida s, registro_entrada e, proyecto p, users u\n" +
            "WHERE e.id_informe = i.id_informe\n" +
            "AND s.id_informe = i.id_informe\n" +
            "AND i.id = u.id\n" +
            "AND i.id_proyecto = p.id_proyecto\n" +
            "GROUP BY i.id_informe, p.id_proyecto, u.id", nativeQuery = true)
    List<Informe> findAllInformeGeneric();

}
