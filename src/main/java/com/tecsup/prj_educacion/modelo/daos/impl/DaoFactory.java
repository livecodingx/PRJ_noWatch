package com.tecsup.prj_educacion.modelo.daos.impl;

import com.tecsup.prj_educacion.modelo.daos.UsuarioDao;
import com.tecsup.prj_educacion.modelo.daos.CursoDao;
import com.tecsup.prj_educacion.util.Tipo;

public class DaoFactory {

    public static UsuarioDao getUsuarioDao(Tipo tipo){
        switch (tipo){
            case MEM:
                //
            case PST:
                return new UsuarioDaoPreparedStatement();
            case CST:
                //
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