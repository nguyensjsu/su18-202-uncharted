package com.uncharted;

public interface EmployeeDAO
{
    boolean insertEmployee(EmployeeBO bo) throws Exception;
    EmployeeBO getEmployee(int id) throws Exception;
}
