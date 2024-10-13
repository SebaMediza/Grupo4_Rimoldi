package com.rimoldi.services.DAOs;

import com.rimoldi.practico2.model.Inquilino;
import java.util.List;

public interface InterfaceInquilinoDAO {
    public List<Inquilino> getInquilino();
    public Inquilino getInquilinoByDni(int dni);
    public boolean insertInquilino(Inquilino inquilino);
    public boolean updateInquilino(int dni, Inquilino inquilino);
    public boolean deleteInquilino(int dni);
}
