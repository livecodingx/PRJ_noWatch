package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.AdministradorDao;
import com.tecsup.prj_educacion.modelo.daos.CursoDao;
import com.tecsup.prj_educacion.util.Tipo;

public class DaoFactory {

    public static AdministradorDao getAdministradorDao(Tipo tipo){
        switch (tipo){
            case MEM:
                //return new AdministradorDaoMemory();
            case PST:
                //return new AdministradorDaoPreparedStatement();
            case CST:
                return new AdministradorDaoCallableStatement();
            default:
                return null;
        }
    }

    public static CursoDao getCursoDao(Tipo tipo){
        switch (tipo){
            case MEM:
                //return new CursoDaoMemory();
            case PST:
                //return new CursoDaoPreparedStatement();
            case CST:
                return new CursoDaoCallableStatement();
            default:
                return null;
        }
    }
}